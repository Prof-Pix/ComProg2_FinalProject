package Database;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import ChatBot.Faqs;
import Loan.Loan;
import Loan.LoanRequest;
import Loan.LoanSchedule;

import java.awt.Graphics2D;
import java.awt.Toolkit;

import Products.Product;
import Products.ProductLoanTerm;
import Products.ProductRegistrationData;
import User.Admin;
import User.AdminRegistrationData;
import User.Loaner;
import User.LoanerRegistrationData;
import User.Merchant;
import User.MerchantRegistrationData;
import UserEnums.UserRoles;
import Utilities.HelperUtility;

public class DatabaseManager {

	private Connection connection;
	private static final String USER = "root";
	private static final String PASSWORD = "homecredit123";

	//For establishing a connection to the database
	public void connect() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			try {
				connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/homecredit", USER, PASSWORD);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public boolean registerAdminInDatabase(AdminRegistrationData data) {

		int retrievedId = 0 ;
		String userType = UserRoles.ADMIN.toString().toLowerCase();
		Date birthdate = Date.valueOf(data.getBirthdate());

		String query = "INSERT INTO users (user_type, "
				+ "username, "
				+ "password, "
				+ "first_name, "
				+ "middle_name, "
				+ "last_name, "
				+ "full_name,"
				+ "gender,"
				+ "birthday, "
				+ "email, "
				+ "phone_number) "
				+ "VALUES(?,?,?,?,?,?,?,?,?,?,?)";

		try (PreparedStatement prepSt = connection.prepareStatement(query)){

			prepSt.setString(1, userType);
			prepSt.setString(2, data.getUsername());
			prepSt.setString(3, data.getPassword());
			prepSt.setString(4, data.getFirstName());
			prepSt.setString(5, data.getMiddleName());
			prepSt.setString(6, data.getLastName());
			prepSt.setString(7, data.getFullName());
			prepSt.setString(8, data.getGender());
			prepSt.setDate(9, birthdate);
			prepSt.setString(10, data.getEmail());
			prepSt.setString(11, data.getPhoneNumber());

			prepSt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}

		//Retrieve the user id
		String retrievedIdQuery = "SELECT user_id FROM users WHERE username = ? AND user_type = ?";

		try (PreparedStatement prepSt = connection.prepareStatement(retrievedIdQuery)) {
			prepSt.setString(1, data.getUsername());
			prepSt.setString(2, userType);

			ResultSet rs = prepSt.executeQuery();

			if (rs.next()) {
				retrievedId = rs.getInt(1);
				System.out.println("Retrieved ID: " + retrievedId);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}

		//Send the remaining merchant-specific information
		String adminRegQuery = "INSERT INTO admin_table (user_id) "
				+ "VALUES(?)";

		try (PreparedStatement prepSt = connection.prepareStatement(adminRegQuery)) {
			prepSt.setInt(1, retrievedId);

			prepSt.executeUpdate();

			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	public boolean registerMerchantInDatabase(MerchantRegistrationData data) {
		String userType = UserRoles.MERCHANT.toString().toLowerCase();

		int retrievedId = 0;

		Date birthdate = Date.valueOf(data.getBirthdate());

		String usersQuery = "INSERT INTO users (user_type, "
				+ "username, "
				+ "password, "
				+ "first_name, "
				+ "middle_name, "
				+ "last_name, "
				+ "full_name,"
				+ "gender,"
				+ "birthday, "
				+ "email, "
				+ "phone_number) "
				+ "VALUES(?,?,?,?,?,?,?,?,?,?,?)";

		//Send the first the data that are common to all user roles
		try (PreparedStatement prepSt = connection.prepareStatement(usersQuery)){

			prepSt.setString(1, userType);
			prepSt.setString(2, data.getUsername());
			prepSt.setString(3, data.getPassword());
			prepSt.setString(4, data.getFirstName());
			prepSt.setString(5, data.getMiddleName());
			prepSt.setString(6, data.getLastName());
			prepSt.setString(7, data.getFullName());
			prepSt.setString(8, data.getGender());
			prepSt.setDate(9, birthdate);
			prepSt.setString(10, data.getEmail());
			prepSt.setString(11, data.getPhoneNumber());

			prepSt.executeUpdate();


		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}

		//Retrieve the user id
		String retrievedIdQuery = "SELECT user_id FROM users WHERE username = ? AND user_type = ?";

		try (PreparedStatement prepSt = connection.prepareStatement(retrievedIdQuery)) {
			prepSt.setString(1, data.getUsername());
			prepSt.setString(2, userType);

			ResultSet rs = prepSt.executeQuery();

			if (rs.next()) {
				retrievedId = rs.getInt(1);
				System.out.println("Retrieved ID: " + retrievedId);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}

		//Send the remaining merchant-specific information
		String merchantRegQuery = "INSERT INTO merchant_table (user_id, "
				+ "merchant_name, "
				+ "merchant_category, "
				+ "merchant_region_location, "
				+ "merchant_province_location,"
				+ "merchant_city_location,"
				+ "merchant_barangay_location,"
				+ "merchant_street_location,"
				+ "merchant_address) "
				+ "VALUES(?,?,?,?,?,?,?,?,?)";

		try (PreparedStatement prepSt = connection.prepareStatement(merchantRegQuery)) {
			prepSt.setInt(1, retrievedId);
			prepSt.setString(2, data.getMerchantName());
			prepSt.setString(3, data.getMerchantCategory());
			prepSt.setString(4, data.getMerchantRegionLocation());
			prepSt.setString(5, data.getMerchantProvinceLocation());
			prepSt.setString(6, data.getMerchantCityLocation());
			prepSt.setString(7, data.getMerchantBarangayLocation());
			prepSt.setString(8, data.getMerchantStreetLocation());
			prepSt.setString(9, data.getMerchantAddress());


			prepSt.executeUpdate();

			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	public boolean registerLoanerInDatabase(LoanerRegistrationData data) {

		String userType = UserRoles.LOANER.toString().toLowerCase();

		int retrievedId = 0;

		Date birthdate = Date.valueOf(data.getBirthdate());

		String usersQuery = "INSERT INTO users (user_type, "
				+ "username, "
				+ "password, "
				+ "first_name, "
				+ "middle_name, "
				+ "last_name, "
				+ "full_name,"
				+ "gender,"
				+ "birthday, "
				+ "email, "
				+ "phone_number) "
				+ "VALUES(?,?,?,?,?,?,?,?,?,?,?)";

		//Send the first the data that are common to all user roles
		try (PreparedStatement prepSt = connection.prepareStatement(usersQuery)){

			prepSt.setString(1, userType);
			prepSt.setString(2, data.getUsername());
			prepSt.setString(3, data.getPassword());
			prepSt.setString(4, data.getFirstName());
			prepSt.setString(5, data.getMiddleName());
			prepSt.setString(6, data.getLastName());
			prepSt.setString(7, data.getFullName());
			prepSt.setString(8, data.getGender());
			prepSt.setDate(9, birthdate);
			prepSt.setString(10, data.getEmail());
			prepSt.setString(11, data.getPhoneNumber());

			prepSt.executeUpdate();


		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}

		//Retrieve the user id
		String retrievedIdQuery = "SELECT user_id FROM users WHERE username = ? AND user_type = ?";

		try (PreparedStatement prepSt = connection.prepareStatement(retrievedIdQuery)) {
			prepSt.setString(1, data.getUsername());
			prepSt.setString(2, userType);

			ResultSet rs = prepSt.executeQuery();

			if (rs.next()) {
				retrievedId = rs.getInt(1);
				System.out.println("Retrieved ID: " + retrievedId);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}

		//Send the remaining merchant-specific information
		String merchantRegQuery = "INSERT INTO loaner_table (source_of_income, "
				+ "occupation, "
				+ "monthly_income, "
				+ "user_id) "
				+ "VALUES(?,?,?,?)";

		try (PreparedStatement prepSt = connection.prepareStatement(merchantRegQuery)) {
			prepSt.setString(1, data.getSourceOfIncome());
			prepSt.setString(2, data.getOccupation());
			prepSt.setString(3, data.getMonthlyIncome());
			prepSt.setInt(4, retrievedId);

			prepSt.executeUpdate();

			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	public boolean addProductForMerchant(int merchantUserId, ProductRegistrationData data) {

		int retrievedProductId = 0;
		String addProductQuery = "INSERT INTO products (merchant_id, "
				+ "product_picture, "
				+ "product_name, "
				+ "product_brand, "
				+ "product_description, "
				+ "product_specifications, "
				+ "product_price, "
				+ "product_stocks_available,"
				+ "product_category) "
				+ "VALUES (?,?,?,?,?,?,?,?,?)";


		File imageFile = new File(data.getImageFilePath());

		try(PreparedStatement prepSt = connection.prepareStatement(addProductQuery)) {

			//For image
			FileInputStream fileInputImage = null;
			try {
				fileInputImage = new FileInputStream(imageFile);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}


			prepSt.setInt(1, data.getMerchantOwnerId());
			prepSt.setBlob(2, fileInputImage);
			prepSt.setString(3, data.getName());
			prepSt.setString(4, data.getBrand());
			prepSt.setString(5, data.getDescription());
			prepSt.setString(6, data.getSpecifications());
			prepSt.setFloat(7, data.getPrice());
			prepSt.setInt(8, data.getStocksAvailable());
			prepSt.setString(9, data.getCategory());

			prepSt.executeUpdate();	

		}catch(SQLException e) {
			e.printStackTrace();
			return false;
		}

		//Retrieve the product id
		String retrieveProductIdQuery = "SELECT * FROM products where product_name = ?";

		try(PreparedStatement prepSt = connection.prepareStatement(retrieveProductIdQuery)) {

			prepSt.setString(1, data.getName());

			ResultSet rs = prepSt.executeQuery();

			if(rs.next()) {
				retrievedProductId = rs.getInt(1);
			}

		}catch(SQLException e) {
			e.printStackTrace();
			return false;
		} 

		//Send the product loans
		String addProductLoans = "INSERT INTO product_loan_table (product_id, months_to_pay, interest_rate) VALUES (?, ? , ?) ";

		try (PreparedStatement prepSt = connection.prepareStatement(addProductLoans)){

			prepSt.setInt(1, retrievedProductId);
			for(ProductLoanTerm prodLoanTerms : data.getProductLoans()) {
				prepSt.setInt(2, prodLoanTerms.getMonthsToPay());
				prepSt.setFloat(3, prodLoanTerms.getInterestRate());

				prepSt.executeUpdate();
			}

			return true;
		}catch(SQLException e) {
			e.printStackTrace();
			return false;
		}

	}

	public String checkDuplicateCommonFieldsInput(String username, String email, String fullName, String phoneNumber, String userType) {

		if (HelperUtility.doesCommonValueExist(connection, "username", username, userType)) { 		//check for username duplication
			return "Username already taken. Please try again.";
		}
		else if (HelperUtility.doesCommonValueExist(connection, "email", email, userType)) { 		//check for email duplication
			return "Email already registered. Please try again."; 
		}
		else if (HelperUtility.doesCommonValueExist(connection, "full_name", fullName, userType)) { //check for full name duplication
			return "Name already registered. Please try again.";
		}
		else if (HelperUtility.doesCommonValueExist(connection, "phone_number", phoneNumber, userType)) {
			return "Phone already registered. Please try again.";
		}
		return null;
	}

	public String checkDuplicateAdminInput(String username, String email, String fullName, String phoneNumber, String userType) {

		String statusText = checkDuplicateCommonFieldsInput(username, email, fullName, phoneNumber, userType);

		if (statusText == null) {
			return "ok";
		} 
		else {
			return statusText;
		}

	}

	public String checkDuplicateMerchantInput(String username, String email, String fullName, String phoneNumber, String merchantName, String userType) {

		String statusText = checkDuplicateCommonFieldsInput(username, email, fullName, phoneNumber, userType);
		if (statusText != null) {
			return statusText;
		} else if (HelperUtility.doesSpecificValueExist(connection, "merchant_table", "merchant_name", merchantName)){
			return "Merchant name already taken. Please try again.";
		} 
		return "ok";

	}

	public String checkDuplicateLoanerInput(String username, String email, String fullName, String phoneNumber, String userType) {
		String statusText = checkDuplicateCommonFieldsInput(username, email, fullName, phoneNumber, userType);

		if (statusText == null) {
			return "ok";
		} 
		else {
			return statusText;
		}
	}

	public String checkDuplicateProductOfMerchant(int merchantUserId, String productName) {

		String query = "SELECT product_name FROM products WHERE merchant_id = ? AND product_name = ?";

		try(PreparedStatement prepSt = connection.prepareStatement(query)) {
			prepSt.setInt(1, merchantUserId);
			prepSt.setString(2, productName);

			ResultSet rs = prepSt.executeQuery();

			if(rs.next()) {
				return "Product name '" + productName + "' is already in use. Please choose a different name.";
			}

		}catch (SQLException e) {
			e.printStackTrace();
			return "System error. Please try again later.";
		}
		return "ok";
	}

	public int verifyLoginInput(String userType, String usernameEmail, String password) {

		String checkCredentialsQuery = "SELECT password, user_id FROM users WHERE (username = ? OR email = ?) AND user_type = ?";
		int retrievedUserId = 0; 

		try (PreparedStatement prepSt = connection.prepareStatement(checkCredentialsQuery)){

			prepSt.setString(1, usernameEmail);
			prepSt.setString(2, usernameEmail);
			prepSt.setString(3, userType);

			ResultSet rs = prepSt.executeQuery();

			if(rs.next()) {

				String retrievedPassword = rs.getString(1);

				if (!password.equals(retrievedPassword)) {
					return 0;
				} else {
					retrievedUserId = rs.getInt(2);
				}

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}

		if(userType.equals("admin")) {
			//If the password is correct, get the merchant id using the user_id
			String retrieveAdminIdQuery = "SELECT admin_id FROM admin_table WHERE user_id = ?";

			try (PreparedStatement prepSt = connection.prepareStatement(retrieveAdminIdQuery)){

				prepSt.setInt(1, retrievedUserId);

				ResultSet rs = prepSt.executeQuery();

				if(rs.next()) {
					return rs.getInt(1);
				}
				return 0;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return 0;
			}
		} else if(userType.equals("loaner")) {
			//If the password is correct, get the merchant id using the user_id
			String retrieveLoanerIdQuery = "SELECT loaner_id FROM loaner_table WHERE user_id = ?";

			try (PreparedStatement prepSt = connection.prepareStatement(retrieveLoanerIdQuery)){

				prepSt.setInt(1, retrievedUserId);

				ResultSet rs = prepSt.executeQuery();

				if(rs.next()) {
					return rs.getInt(1);
				}
				return 0;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return 0;
			}	
		} else if (userType.equals("merchant")) {
			//If the password is correct, get the merchant id using the user_id
			String retrieveMerchantIdQuery = "SELECT merchant_id FROM merchant_table WHERE user_id = ?";

			try (PreparedStatement prepSt = connection.prepareStatement(retrieveMerchantIdQuery)){

				prepSt.setInt(1, retrievedUserId);

				ResultSet rs = prepSt.executeQuery();

				if(rs.next()) {
					return rs.getInt(1);
				}
				return 0;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return 0;
			}
		}

		return 0;


	}

	public void getUserdata(int userId) {
		HashMap <String, String> userData = new HashMap<>();

		String query = "SELECT * FROM users WHERE user_id = ?";

		try(PreparedStatement prepSt = connection.prepareStatement(query)) {

			prepSt.setInt(1, userId);

			ResultSet rs = prepSt.executeQuery();

			if (rs.next()) {
				String username = rs.getString("username");
				String password = rs.getString("password");
				String firstName = rs.getString("first_name");
				String middleName = rs.getString("middle_name");
				String lastName = rs.getString("last_name");
				String fullName = rs.getString("full_name");
				Date birthdate = rs.getDate("birthday");
				String email = rs.getString("email");
				String phoneNumber = rs.getString("phone_number");

				userData.put("username", username); 
				userData.put("password", password);
				userData.put("firstName", firstName);
				userData.put("middleName", middleName);
				userData.put("lastName", lastName);
				userData.put("fullName", fullName);

				// For the birthdate, convert it to a string if needed:
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); 
				userData.put("birthdate", dateFormat.format(birthdate));

				userData.put("email", email);
				userData.put("phoneNumber", phoneNumber); 

				System.out.println(userData);


			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}

	public ArrayList<Product> getMerchantProductData(int merchantId) {

		ArrayList<Product> merchantProducts = new ArrayList<>();

		String productQuery = "SELECT * FROM products WHERE merchant_id = ?";

		try(PreparedStatement productSt = connection.prepareStatement(productQuery)) {

			productSt.setInt(1, merchantId);

			ResultSet productResultSet = productSt.executeQuery();

			while(productResultSet.next()) {

				int productId = productResultSet.getInt("product_id");

				Product merchantProd = getProductData(productId);

				merchantProducts.add(merchantProd);

			}
		} catch(SQLException e) {
			e.printStackTrace();
		}


		return merchantProducts;

	}

	//Decide what to do (to delete the product completely or avoid deleting once there is an ongoing loan)
	public boolean deleteMerchantProduct(int merchantId, int productId) {

		System.out.println(productId);
		//First delete all the product loan terms
		String deleteProductLoanTermsQuery = "DELETE FROM product_loan_table WHERE product_id = ?";
		try(PreparedStatement prepSt = connection.prepareStatement(deleteProductLoanTermsQuery)) {
			prepSt.setInt(1, productId);
			prepSt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		String deleteProductQuery = "DELETE FROM products WHERE merchant_id = ? AND product_id = ?";

		try(PreparedStatement prepSt = connection.prepareStatement(deleteProductQuery)) {
			prepSt.setInt(1, merchantId);
			prepSt.setInt(2, productId);

			prepSt.executeUpdate();

			return true;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}

	}

	public boolean editMerchantProduct(int merchantId, ProductRegistrationData data, int productId) {

		String updateProductQuery = "UPDATE products "
				+ "SET product_picture = ?,"
				+ "product_name = ?, "
				+ "product_brand = ?,"
				+ "product_description = ?,"
				+ "product_specifications = ?,"
				+ "product_price = ?,"
				+ "product_stocks_available = ?,"
				+ "product_category = ?"
				+ "WHERE merchant_id = ? AND product_id = ?";

		Image image = data.getProductImage().getImage();

		BufferedImage bufferedImage = new BufferedImage(image.getWidth(null), image.getHeight(null), BufferedImage.TYPE_INT_ARGB);

		// Draw the original image onto the BufferedImage
		Graphics2D g2d = bufferedImage.createGraphics();
		g2d.drawImage(image, 0, 0, null);
		g2d.dispose(); 

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try {
			if (!ImageIO.write(bufferedImage, "jpg", baos)) { // Try JPG first
				if (!ImageIO.write(bufferedImage, "png", baos)) { // Then try PNG
					// ... Try other formats or handle if none work
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		byte[] imageBytes = baos.toByteArray();
		ByteArrayInputStream inStream = new ByteArrayInputStream(imageBytes);


		try(PreparedStatement prepSt = connection.prepareStatement(updateProductQuery)) {

			prepSt.setBinaryStream(1,inStream,inStream.available());
			prepSt.setString(2, data.getName());
			prepSt.setString(3, data.getBrand());
			prepSt.setString(4, data.getDescription());
			prepSt.setString(5, data.getSpecifications());
			prepSt.setFloat(6, data.getPrice());
			prepSt.setInt(7, data.getStocksAvailable());
			prepSt.setString(8, data.getCategory());
			prepSt.setInt(9, merchantId);
			prepSt.setInt(10, productId);

			prepSt.executeUpdate();	

		}catch(SQLException e) {
			e.printStackTrace();
			return false;
		}


		String deleteProductLoanTermsQuery = "DELETE FROM product_loan_table WHERE product_id = ?";
		try(PreparedStatement prepSt = connection.prepareStatement(deleteProductLoanTermsQuery)) {
			prepSt.setInt(1, productId);
			prepSt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		//Send the product loans
		String addProductLoans = "INSERT INTO product_loan_table (product_id, months_to_pay, interest_rate) VALUES (?, ? , ?) ";

		try (PreparedStatement prepSt = connection.prepareStatement(addProductLoans)){

			prepSt.setInt(1, productId);
			for(ProductLoanTerm prodLoanTerms : data.getProductLoans()) {
				prepSt.setInt(2, prodLoanTerms.getMonthsToPay());
				prepSt.setFloat(3, prodLoanTerms.getInterestRate());

				prepSt.executeUpdate();


			}

			return true;
		}catch(SQLException e) {
			e.printStackTrace();
			return false;
		}

	}

	public ArrayList<Product> getAllMerchantProducts() {
		String query = "SELECT * FROM products";

		ArrayList<Product> merchantProducts = new ArrayList<>();

		try {
			Statement retrieveSt = connection.createStatement();


			ResultSet productResultSet = retrieveSt.executeQuery(query);


			while(productResultSet.next()) {
				int productId = productResultSet.getInt("product_id");
				Product prod = getProductData(productId);
				merchantProducts.add(prod);
			}



		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return merchantProducts;
	}

	public String getMerchantName(int merchantId) {
		String query = "SELECT merchant_name FROM merchant_table WHERE merchant_id = ?";

		try(PreparedStatement prepSt = connection.prepareStatement(query)) {
			prepSt.setInt(1, merchantId);

			ResultSet merchantNameSet = prepSt.executeQuery();

			if(merchantNameSet.next()) {
				return merchantNameSet.getString("merchant_name");
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		return "Store Name";
	}

	public boolean sendLoanRequest(Loaner loanerData, Product productData, ProductLoanTerm selectedProductLoanTerm) {

		String sendLoanRequestQuery = "INSERT INTO loan_request_table(merchant_id,"
				+ "loaner_id,"
				+ "product_id, "
				+ "months_to_pay, "
				+ "interest_rate, "
				+ "loan_request_date,"
				+ "is_pending) "
				+ "VALUES (?,?,?,?,?,?,?)";

		try(PreparedStatement prepSt = connection.prepareStatement(sendLoanRequestQuery)) {
			prepSt.setInt(1, productData.getMerchantOwnerId());
			prepSt.setInt(2, loanerData.getLoanerId());
			prepSt.setInt(3, productData.getProductId());
			prepSt.setInt(4, selectedProductLoanTerm.getMonthsToPay());
			prepSt.setFloat(5, selectedProductLoanTerm.getInterestRate());
			prepSt.setDate(6, java.sql.Date.valueOf(LocalDate.now()) );
			prepSt.setBoolean(7, true);

			prepSt.executeUpdate();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}

	}

	//FOR MERCHANT
	public ArrayList<LoanRequest> getPendingLoanRequest(int merchantId) {
		String retrieveLoanRequest = "SELECT * FROM loan_request_table WHERE merchant_id = ?";

		ArrayList<LoanRequest> loanRequests = new ArrayList<>();

		try(PreparedStatement prepSt = connection.prepareStatement(retrieveLoanRequest)) {

			prepSt.setInt(1, merchantId);

			ResultSet loanRequestSet = prepSt.executeQuery();

			while(loanRequestSet.next()) {

				int productId = loanRequestSet.getInt("product_id");
				int loanerId = loanRequestSet.getInt("loaner_id");

				Product productToLoan = getProductData(productId);
				Loaner loanerLoan = getLoanerData(loanerId);
				Merchant merchantLoan = getMerchantData(merchantId);


				LoanRequest loanReq = new LoanRequest(loanRequestSet.getInt("merchant_id"), 
						loanRequestSet.getInt("loaner_id"),
						loanRequestSet.getInt("product_id"),
						merchantLoan,
						productToLoan,
						loanerLoan,
						loanRequestSet.getInt("months_to_pay"),
						loanRequestSet.getFloat("interest_rate"),
						loanRequestSet.getDate("loan_request_date").toLocalDate(),
						loanRequestSet.getBoolean("is_pending"));

				loanRequests.add(loanReq);
			}
			return loanRequests;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	public ArrayList<LoanRequest> getApprovedRejectedLoanRequest(int merchantId) {
		String retrieveLoanRequest = "SELECT * FROM loan_request_table WHERE merchant_id = ?";

		ArrayList<LoanRequest> loanRequests = new ArrayList<>();

		try(PreparedStatement prepSt = connection.prepareStatement(retrieveLoanRequest)) {

			prepSt.setInt(1, merchantId);

			ResultSet loanRequestSet = prepSt.executeQuery();


			while(loanRequestSet.next()) {

				boolean isPending = loanRequestSet.getBoolean("is_pending");


				//				public LoanRequest(int merchantId, int loanerId, int productId, Merchant merchantLoanData, Product productToLoanData, Loaner loanerLoanData , LocalDate loanRequestDate,
				//						LocalDate loanApproveDate, LocalDate loanRejectDate, boolean isPending, boolean isRejected,
				//						boolean isApproved) 

				if(!isPending) {

					int productId = loanRequestSet.getInt("product_id");
					int loanerId = loanRequestSet.getInt("loaner_id");

					int loanRequestId = loanRequestSet.getInt("loan_request_id");
					Product productToLoan = getProductData(productId);
					Loaner loanerLoan = getLoanerData(loanerId);
					Merchant merchantLoan = getMerchantData(merchantId);

					LoanRequest loanReq = new LoanRequest(loanRequestId, loanRequestSet.getInt("merchant_id"), 
							loanRequestSet.getInt("loaner_id"),
							loanRequestSet.getInt("product_id"),
							merchantLoan,
							productToLoan,
							loanerLoan,
							loanRequestSet.getInt("months_to_pay"),
							loanRequestSet.getFloat("interest_rate"),
							loanRequestSet.getDate("loan_request_date").toLocalDate(),
							loanRequestSet.getDate("loan_approve_date").toLocalDate(),
							loanRequestSet.getDate("loan_reject_date").toLocalDate(),
							loanRequestSet.getBoolean("is_pending"),
							loanRequestSet.getBoolean("is_rejected"),
							loanRequestSet.getBoolean("is_approved"),
							loanRequestSet.getBoolean("is_downpayment_pending"),
							loanRequestSet.getBoolean("is_downpayment_paid"),
							loanRequestSet.getBoolean("is_cancelled"));

					loanRequests.add(loanReq);
				}


			}
			return loanRequests;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	public boolean approvedRequest(int merchantId, int loanerId, int productId, String decision) {

		//Retrieve the merchant_loan_id
		int retrievedLoanId = 0;
		String retrieveLoanRequestIdQuery = "SELECT loan_request_id FROM loan_request_table WHERE merchant_id = ? AND loaner_id = ? AND product_id = ? AND is_pending = ?";

		try(PreparedStatement prepSt = connection.prepareStatement(retrieveLoanRequestIdQuery)) {
			prepSt.setInt(1, merchantId);
			prepSt.setInt(2, loanerId);
			prepSt.setInt(3, productId);
			prepSt.setBoolean(4, true);

			ResultSet loanIdSet = prepSt.executeQuery();

			if(loanIdSet.next()) {
				retrievedLoanId = loanIdSet.getInt("loan_request_id");

				String approveQuery = "UPDATE loan_request_table SET is_pending = ? , is_approved = ?, is_rejected = ? , loan_approve_date = ?, loan_reject_date = ?, is_downpayment_pending = ? , is_downpayment_paid = ? , is_cancelled = ?  WHERE loan_request_id = ? AND is_pending = ?";

				try(PreparedStatement prepStt = connection.prepareStatement(approveQuery)) {
					prepStt.setBoolean(1, false);
					prepStt.setBoolean(2, true);
					prepStt.setBoolean(3, false);
					prepStt.setDate(4, java.sql.Date.valueOf(LocalDate.now()));
					prepStt.setDate(5, java.sql.Date.valueOf(LocalDate.now()));
					prepStt.setBoolean(6, true);
					prepStt.setBoolean(7, false);
					prepStt.setBoolean(8, false);
					prepStt.setInt(9, retrievedLoanId);
					prepStt.setBoolean(10, true);

					prepStt.executeUpdate();
					return true;
				}

			}

			return false;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}

	}

	public boolean rejectRequest(int merchantId, int loanerId, int productId, String decision) {

		//Retrieve the merchant_loan_id
		int retrievedLoanId = 0;
		String retrieveLoanIdQuery = "SELECT loan_request_id FROM loan_request_table WHERE merchant_id = ? AND loaner_id = ? AND product_id = ? AND is_pending = ?";

		try(PreparedStatement prepSt = connection.prepareStatement(retrieveLoanIdQuery)) {
			prepSt.setInt(1, merchantId);
			prepSt.setInt(2, loanerId);
			prepSt.setInt(3, productId);
			prepSt.setBoolean(4, true);

			ResultSet loanIdSet = prepSt.executeQuery();

			if(loanIdSet.next()) {
				retrievedLoanId = loanIdSet.getInt("loan_request_id");

				String rejectQuery = "UPDATE loan_request_table SET is_pending = ? , is_approved = ?, is_rejected = ? , loan_approve_date = ?, loan_reject_date = ?, is_downpayment_pending = ? , is_downpayment_paid = ? , is_cancelled = ?  WHERE loan_request_id = ? AND is_pending = ?";

				try(PreparedStatement prepStt = connection.prepareStatement(rejectQuery)) {
					prepStt.setBoolean(1, false);
					prepStt.setBoolean(2, false);
					prepStt.setBoolean(3, true);
					prepStt.setDate(4, java.sql.Date.valueOf(LocalDate.now()));
					prepStt.setDate(5, java.sql.Date.valueOf(LocalDate.now()));
					prepStt.setBoolean(6, false);
					prepStt.setBoolean(7, false);
					prepStt.setBoolean(8, false);
					prepStt.setInt(9, retrievedLoanId);
					prepStt.setBoolean(10, true);

					prepStt.executeUpdate();
					return true;
				}

			}

			return false;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}

	}

	//FOR LOANER
	public ArrayList<LoanRequest> getPendingLoans(int loanerId) {

		ArrayList<LoanRequest> pendingLoans = new ArrayList<>();
		String query = "SELECT * FROM loan_request_table WHERE loaner_id = ?";

		try(PreparedStatement prepSt = connection.prepareStatement(query)) {

			prepSt.setInt(1, loanerId);

			ResultSet pendingLoansSet = prepSt.executeQuery();

			while(pendingLoansSet.next()) {
				int productId =  pendingLoansSet.getInt("product_id");
				int merchantId =  pendingLoansSet.getInt("merchant_id");

				Product productToLoan = getProductData(productId);
				Loaner loanerLoan = getLoanerData(loanerId);
				Merchant merchantLoan = getMerchantData(merchantId);

				LoanRequest loanReq = new LoanRequest(pendingLoansSet.getInt("merchant_id"), 
						pendingLoansSet.getInt("loaner_id"),
						pendingLoansSet.getInt("product_id"),
						merchantLoan,
						productToLoan,
						loanerLoan,
						pendingLoansSet.getInt("months_to_pay"),
						pendingLoansSet.getFloat("interest_rate"),
						pendingLoansSet.getDate("loan_request_date").toLocalDate(),
						pendingLoansSet.getBoolean("is_pending"));

				pendingLoans.add(loanReq);

			}

			return pendingLoans;


		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

	}

	public ArrayList<LoanRequest> getApprovedRejectedLoans(int loanerId) {

		ArrayList<LoanRequest> pendingLoans = new ArrayList<>();
		String query = "SELECT * FROM loan_request_table WHERE loaner_id = ?";

		try(PreparedStatement prepSt = connection.prepareStatement(query)) {

			prepSt.setInt(1, loanerId);

			ResultSet pendingLoansSet = prepSt.executeQuery();

			while(pendingLoansSet.next()) {

				boolean isPending = pendingLoansSet.getBoolean("is_pending");

				if(!isPending) {

					int loanRequestId = pendingLoansSet.getInt("loan_request_id");
					int productId =  pendingLoansSet.getInt("product_id");
					int merchantId =  pendingLoansSet.getInt("merchant_id");

					Product productToLoan = getProductData(productId);
					Loaner loanerLoan = getLoanerData(loanerId);
					Merchant merchantLoan = getMerchantData(merchantId);

					LoanRequest loanReq = new LoanRequest(loanRequestId, pendingLoansSet.getInt("merchant_id"), 
							pendingLoansSet.getInt("loaner_id"),
							pendingLoansSet.getInt("product_id"),
							merchantLoan,
							productToLoan,
							loanerLoan,
							pendingLoansSet.getInt("months_to_pay"),
							pendingLoansSet.getFloat("interest_rate"),
							pendingLoansSet.getDate("loan_request_date").toLocalDate(),
							pendingLoansSet.getDate("loan_approve_date").toLocalDate(),
							pendingLoansSet.getDate("loan_reject_date").toLocalDate(),
							pendingLoansSet.getBoolean("is_pending"),
							pendingLoansSet.getBoolean("is_rejected"),
							pendingLoansSet.getBoolean("is_approved"),
							pendingLoansSet.getBoolean("is_downpayment_pending"),
							pendingLoansSet.getBoolean("is_downpayment_paid"),
							pendingLoansSet.getBoolean("is_cancelled"));

					pendingLoans.add(loanReq);
				}



			}

			return pendingLoans;


		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

	}


	//For each classes
	public Admin getAdminData(int admindId) {
		String retrieveAdminDataQuery = "SELECT * FROM users INNER JOIN admin_table ON users.user_id = admin_table.user_id WHERE admin_id = ?";
		
		try(PreparedStatement adminDataRetrieveSt = connection.prepareStatement(retrieveAdminDataQuery)) {
			
			adminDataRetrieveSt.setInt(1, admindId);
			
			ResultSet adminDataSet = adminDataRetrieveSt.executeQuery();
			
			if(adminDataSet.next()) {
				LocalDate birthdate = adminDataSet.getDate("birthday").toLocalDate();
				
				Admin admin = new Admin(adminDataSet.getInt("admin_id"),
						adminDataSet.getString("username"),
						adminDataSet.getString("password"),
						adminDataSet.getString("first_name"),
						adminDataSet.getString("middle_name"),
						adminDataSet.getString("last_name"),
						adminDataSet.getString("gender"),
						birthdate,
						adminDataSet.getInt("age"),
						adminDataSet.getString("email"),
						adminDataSet.getString("phone_number"));
				
				return admin;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		return null;
	}
	
	public Merchant getMerchantData(int merchantId) {

			//Get the user data
			String retrieveMerchantDataQuery = "SELECT * FROM users INNER JOIN merchant_table ON users.user_id = merchant_table.user_id WHERE merchant_id = ?";

			try(PreparedStatement merchantDataRetrieveSt = connection.prepareStatement(retrieveMerchantDataQuery)) {
				merchantDataRetrieveSt.setInt(1, merchantId);

				ResultSet merchantDataSet = merchantDataRetrieveSt.executeQuery();

				if(merchantDataSet.next()) {
					// For the birthdate
					LocalDate birthdate = merchantDataSet.getDate("birthday").toLocalDate();

					Merchant merchant = new Merchant(merchantDataSet.getInt("merchant_id"),
							merchantDataSet.getString("username"),
							merchantDataSet.getString("password"),
							merchantDataSet.getString("first_name"),
							merchantDataSet.getString("middle_name"),
							merchantDataSet.getString("last_name"),
							merchantDataSet.getString("gender"),
							birthdate,
							merchantDataSet.getInt("age"),
							merchantDataSet.getString("email"),
							merchantDataSet.getString("phone_number"),
							merchantDataSet.getString("merchant_name"),
							merchantDataSet.getString("merchant_category"),
							merchantDataSet.getString("merchant_region_location"),
							merchantDataSet.getString("merchant_province_location"),
							merchantDataSet.getString("merchant_city_location"),
							merchantDataSet.getString("merchant_barangay_location"),
							merchantDataSet.getString("merchant_street_location")
							);

					return merchant;
				}

				return null;


			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}


	}

	public Loaner getLoanerData(int loanerId) {

		
			String retrieveLoanerDataQuery = "SELECT * FROM users INNER JOIN loaner_table ON users.user_id = loaner_table.user_id WHERE loaner_id = ?";

			try(PreparedStatement loanerDataRetrieveSt = connection.prepareStatement(retrieveLoanerDataQuery)) {
				loanerDataRetrieveSt.setInt(1, loanerId);
				
				ResultSet loanerDataSet = loanerDataRetrieveSt.executeQuery();

				if(loanerDataSet.next()) {
					// For the birthdate
					LocalDate birthdate = loanerDataSet.getDate("birthday").toLocalDate();

					Loaner loaner = new Loaner(loanerDataSet.getInt("loaner_id"),
							loanerDataSet.getString("username"),
							loanerDataSet.getString("password"),
							loanerDataSet.getString("first_name"),
							loanerDataSet.getString("middle_name"),
							loanerDataSet.getString("last_name"),
							loanerDataSet.getString("gender"),
							birthdate,
							loanerDataSet.getInt("age"),
							loanerDataSet.getString("email"),
							loanerDataSet.getString("phone_number"),
							loanerDataSet.getString("source_of_income"),
							loanerDataSet.getString("occupation"),
							loanerDataSet.getString("monthly_income")
							);

					return loaner;
				}

				return null;


			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}


	}

	//For getting the product data 
	public Product getProductData(int productId) {

		String retrieveProductQuery = "SELECT * FROM products WHERE product_id = ?";

		try(PreparedStatement prepSt = connection.prepareStatement(retrieveProductQuery)) {

			prepSt.setInt(1, productId);

			ResultSet productSet = prepSt.executeQuery();

			if(productSet.next()) {

				//Get the product loan terms of each product
				ArrayList<ProductLoanTerm> productLoanTerms = new ArrayList<>();
				String productLoanTermQuery = "SELECT * FROM product_loan_table WHERE product_id = ?";

				try(PreparedStatement productLoanTermSt = connection.prepareStatement(productLoanTermQuery)) {

					productLoanTermSt.setInt(1, productId);

					ResultSet productLoanTermsResultSet = productLoanTermSt.executeQuery();

					while(productLoanTermsResultSet.next()) {		

						int monthsToPay = productLoanTermsResultSet.getInt("months_to_pay");
						float interestRate = productLoanTermsResultSet.getFloat("interest_rate");

						ProductLoanTerm prodLoanTerm = new ProductLoanTerm(monthsToPay, interestRate);
						productLoanTerms.add(prodLoanTerm);


					}

					//Conversion of BLOB to ImageIcon
					byte[] imageBytes = productSet.getBytes("product_picture");
					ImageIcon imageIcon = new ImageIcon(imageBytes);

					//For merchant name
					String merchantName = getMerchantName(productSet.getInt("merchant_id"));

					Product productRetrieved = new Product(productId, 
							productSet.getInt("merchant_id"), 
							merchantName,
							imageIcon, 
							productSet.getString("product_name"), 
							productSet.getString("product_brand"),
							productSet.getString("product_description"),
							productSet.getString("product_specifications"),
							productSet.getFloat("product_price"),
							productSet.getInt("product_stocks_available"),
							productSet.getString("product_category"),
							productLoanTerms);

					return productRetrieved;

				} catch(SQLException e) {
					e.printStackTrace();
					return null;
				} 

			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return null;
	}

	public String getProductName(int productId) {
		String query = "SELECT product_name FROM products WHERE product_id = ?";

		try(PreparedStatement prepSt = connection.prepareStatement(query)) {
			prepSt.setInt(1, productId);

			ResultSet productNameSet = prepSt.executeQuery();

			if(productNameSet.next()) {
				return productNameSet.getString("product_name");
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		return "Store Name";
	}

	public LoanRequest getLoanRequestData(int loanerId , int loanRequestId) {

		String query = "SELECT * FROM loan_request_table WHERE loaner_id = ? AND loan_request_id = ?";

		try(PreparedStatement prepSt = connection.prepareStatement(query)) {

			prepSt.setInt(1, loanerId);
			prepSt.setInt(2, loanRequestId);

			ResultSet loanRequestSet = prepSt.executeQuery();

			if(loanRequestSet.next()) {

				int productId =  loanRequestSet.getInt("product_id");
				int merchantId =  loanRequestSet.getInt("merchant_id");

				Product productToLoan = getProductData(productId);
				Loaner loanerLoan = getLoanerData(loanerId);
				Merchant merchantLoan = getMerchantData(merchantId);

				LoanRequest loanReq = new LoanRequest(loanRequestId, loanRequestSet.getInt("merchant_id"), 
						loanRequestSet.getInt("loaner_id"),
						loanRequestSet.getInt("product_id"),
						merchantLoan,
						productToLoan,
						loanerLoan,
						loanRequestSet.getInt("months_to_pay"),
						loanRequestSet.getFloat("interest_rate"),
						loanRequestSet.getDate("loan_request_date").toLocalDate(),
						loanRequestSet.getDate("loan_approve_date").toLocalDate(),
						loanRequestSet.getDate("loan_reject_date").toLocalDate(),
						loanRequestSet.getBoolean("is_pending"),
						loanRequestSet.getBoolean("is_rejected"),
						loanRequestSet.getBoolean("is_approved"),
						loanRequestSet.getBoolean("is_downpayment_pending"),
						loanRequestSet.getBoolean("is_downpayment_paid"),
						loanRequestSet.getBoolean("is_cancelled"));

				return loanReq;


			}
			return null;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	public boolean setLoanRequestToOngoing(LoanRequest loanReq, int monthsPaidInAdvance) {

		//Set the status of loans
		String updateLoanRequestStatusQuery = "UPDATE loan_request_table "
				+ "SET is_downpayment_pending = ?,"
				+ "is_downpayment_paid = ?,"
				+ "is_cancelled = ? "
				+ "WHERE loan_request_id = ?";

		System.out.println(loanReq.getLoanRequestId());

		try(PreparedStatement prepSt = connection.prepareStatement(updateLoanRequestStatusQuery)) {
			prepSt.setBoolean(1, false);
			prepSt.setBoolean(2, true);
			prepSt.setBoolean(3, false);
			prepSt.setInt(4, loanReq.getLoanRequestId());

			prepSt.executeUpdate();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return false;
		}

		//For loan id
		String loanId = String.valueOf(HelperUtility.generateRandomId());

		//For the loan start date
		LocalDate localStartDate = LocalDate.now();
		Date startDate = Date.valueOf(localStartDate);

		float totalInterestedPrice = loanReq.getDownPayment() + ((loanReq.getMonthlyPayment()*loanReq.getLoanedProductMonthsToPay()));

		int remainingMonthsToPay = loanReq.getLoanedProductMonthsToPay() - monthsPaidInAdvance;
		float paidBalance = loanReq.getDownPaymentAmount() + (loanReq.getMonthlyPayment() * monthsPaidInAdvance);
		float remainingBalance = totalInterestedPrice - paidBalance;



		String addLoanQuery = "INSERT INTO loan_table (loan_id,"
				+ "loan_request_id, "
				+ "merchant_id,"
				+ "product_id,"
				+ "loaner_id,"
				+ "loan_start_date,"
				+ "monthly_payment, "
				+ "remaining_months_to_pay,"
				+ "total_price,"
				+ "remaining_balance,"
				+ "paid_months,"
				+ "paid_balance,"
				+ "loan_status) VALUES(?,?,?,?,?,?,?,?,?,?,?,?, ?)";

		try(PreparedStatement prepSt = connection.prepareStatement(addLoanQuery)) {
			prepSt.setString(1, loanId);
			prepSt.setInt(2, loanReq.getLoanRequestId());
			prepSt.setInt(3, loanReq.getMerchantId());
			prepSt.setInt(4, loanReq.getProductId());
			prepSt.setInt(5, loanReq.getLoanerId());
			prepSt.setDate(6, startDate);
			prepSt.setFloat(7, loanReq.getMonthlyPayment());
			prepSt.setInt(8, remainingMonthsToPay);
			prepSt.setFloat(9, totalInterestedPrice);
			prepSt.setFloat(10, remainingBalance);
			prepSt.setInt(11, monthsPaidInAdvance);
			prepSt.setFloat(12, paidBalance);
			prepSt.setString(13, "active");

			prepSt.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}

		String addScheduleLoanQuery = "INSERT INTO loan_schedule_table (loan_id,"
				+ "loan_month,"
				+ "loan_schedule_date,"
				+ "loan_schedule_amount,"
				+ "status, "
				+ "is_due) VALUES(?,?,?,?,?,?)";

		String addScheduleLoanPaidQuery = "INSERT INTO loan_schedule_table (loan_id,"
				+ "loan_month,"
				+ "loan_schedule_date,"
				+ "loan_schedule_amount,"
				+ "loan_paid_date, "
				+ "status, "
				+ "is_due) VALUES(?,?,?,?,?,?, ?)";

		//
		for(int i = 1; i <= loanReq.getLoanedProductMonthsToPay(); i++) {

			int loanMonth = i;


			if(monthsPaidInAdvance >= i) {

				//The schedule date is also the paid date when it is already paid
				LocalDate loanPaidDate = localStartDate;
				Date paidDate =  Date.valueOf(loanPaidDate);
				String status = "paid";

				try(PreparedStatement prepSt = connection.prepareStatement(addScheduleLoanPaidQuery)) {
					prepSt.setString(1, loanId);
					prepSt.setInt(2, loanMonth);
					prepSt.setDate(3, paidDate);
					prepSt.setFloat(4, loanReq.getMonthlyPayment());
					prepSt.setDate(5, paidDate);
					prepSt.setString(6, status);
					prepSt.setBoolean(7, false);

					prepSt.executeUpdate();

				} catch (SQLException e) {

					// TODO Auto-generated catch block
					e.printStackTrace();
					return false;
				}


			} else {
				LocalDate localScheduleDate = localStartDate.plus(i - monthsPaidInAdvance, ChronoUnit.MONTHS);
				Date scheduleDate =  Date.valueOf(localScheduleDate);

				String status = "notpaid";

				try(PreparedStatement prepSt = connection.prepareStatement(addScheduleLoanQuery)) {
					prepSt.setString(1, loanId);
					prepSt.setInt(2, loanMonth);
					prepSt.setDate(3, scheduleDate);
					prepSt.setFloat(4, loanReq.getMonthlyPayment());
					prepSt.setString(5, status);
					prepSt.setBoolean(6, false);

					prepSt.executeUpdate();
				} catch (SQLException e) {

					// TODO Auto-generated catch block
					e.printStackTrace();
					return false;
				}
			}


		}



		return true;


	}

	public boolean payOnGoingLoan(Loan loan, int monthsPaidOnPayment) {


		int remainingMonthsToPay = loan.getRemainingMonthsToPay() - monthsPaidOnPayment;
		float remainingBalance = loan.getRemainingBalance() - (loan.getMonthlyPayment() * monthsPaidOnPayment);
		float paidBalance = loan.getPaidBalance() + (loan.getMonthlyPayment() * monthsPaidOnPayment);
		int monthsPaid = loan.getPaidMonths() + monthsPaidOnPayment;

		if(remainingMonthsToPay == 0) {
			String updateLoanCompleteQuery = "UPDATE loan_table "
					+ "SET remaining_months_to_pay = ?, "
					+ "remaining_balance = ? ,"
					+ "paid_months = ?,"
					+ "paid_balance = ?,"
					+ "loan_status = ? "
					+ "WHERE loan_id = ?";

			try(PreparedStatement prepSt = connection.prepareStatement(updateLoanCompleteQuery)) {

				prepSt.setInt(1, remainingMonthsToPay);
				prepSt.setFloat(2, remainingBalance);
				prepSt.setInt(3, monthsPaid);
				prepSt.setFloat(4, paidBalance);
				prepSt.setString(5, "complete");
				prepSt.setString(6, loan.getLoanId());

				prepSt.executeUpdate();


			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}
		} else {
			String updateLoanQuery = "UPDATE loan_table "
					+ "SET remaining_months_to_pay = ?, "
					+ "remaining_balance = ? ,"
					+ "paid_months = ?,"
					+ "paid_balance = ?"
					+ "WHERE loan_id = ?";

			try(PreparedStatement prepSt = connection.prepareStatement(updateLoanQuery)) {

				prepSt.setInt(1, remainingMonthsToPay);
				prepSt.setFloat(2, remainingBalance);
				prepSt.setInt(3, monthsPaid);
				prepSt.setFloat(4, paidBalance);
				prepSt.setString(5, loan.getLoanId());

				prepSt.executeUpdate();


			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}
		}

		//Update Loan Schedule

		for(LoanSchedule loanSched : loan.getLoanSchedule()) {



			if(loanSched.getStatus().equals("notpaid") && loanSched.getLoanMonth() <= monthsPaid) {



				String updateLoanScheduleQuery = "UPDATE loan_schedule_table "
						+ "SET loan_paid_date = ? ,"
						+ "status = ? "
						+ "WHERE loan_id = ? AND loan_schedule_id = ?";

				//For the loan start date
				LocalDate localCurrentDate = LocalDate.now();
				Date currentDate = Date.valueOf(localCurrentDate);

				try(PreparedStatement prepSt = connection.prepareStatement(updateLoanScheduleQuery)) {
					prepSt.setDate(1, currentDate);
					prepSt.setString(2, "paid");
					prepSt.setString(3, loanSched.getLoanId());
					prepSt.setInt(4, loanSched.getLoanScheduleId());

					prepSt.executeUpdate();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return false;
							
				}


			}

		}
		return true;

	}

	public ArrayList<Loan> getOngoingLoans(int loanerId) {

		ArrayList<Loan> loans = new ArrayList<>();

		String getOngoingLoans = "SELECT * FROM loan_table WHERE loaner_id = ?";

		try(PreparedStatement prepSt = connection.prepareStatement(getOngoingLoans)) {
			prepSt.setInt(1, loanerId);

			ResultSet ongoingLoanSet = prepSt.executeQuery();

			while(ongoingLoanSet.next()) {

				ArrayList<LoanSchedule> loanSchedules = new ArrayList<>();

				String loanId = ongoingLoanSet.getString("loan_id");

				//Get each loan schedule
				String getLoanScheduleQuery = "SELECT * FROM loan_schedule_table WHERE loan_id = ?";

				try(PreparedStatement prepStt = connection.prepareStatement(getLoanScheduleQuery)) {
					prepStt.setString(1, loanId);

					ResultSet loanScheduleSet = prepStt.executeQuery();


					while(loanScheduleSet.next()) {

						if(loanScheduleSet.getDate("loan_paid_date") == null) {
							LoanSchedule loanSched = new LoanSchedule(loanScheduleSet.getInt("loan_schedule_id"),
									loanScheduleSet.getString("loan_id"),
									loanScheduleSet.getInt("loan_month"),
									loanScheduleSet.getDate("loan_schedule_date").toLocalDate(),
									loanScheduleSet.getFloat("loan_schedule_amount"),
									null,
									0,
									loanScheduleSet.getString("status"),
									loanScheduleSet.getBoolean("is_due")
									);
							loanSchedules.add(loanSched);
						} else {
							LoanSchedule loanSched = new LoanSchedule(loanScheduleSet.getInt("loan_schedule_id"),
									loanScheduleSet.getString("loan_id"),
									loanScheduleSet.getInt("loan_month"),
									loanScheduleSet.getDate("loan_schedule_date").toLocalDate(),
									loanScheduleSet.getFloat("loan_schedule_amount"),
									loanScheduleSet.getDate("loan_paid_date").toLocalDate(),
									loanScheduleSet.getFloat("loan_penalty"),
									loanScheduleSet.getString("status"),
									loanScheduleSet.getBoolean("is_due")
									);
							loanSchedules.add(loanSched);
						}


					}
				}


				Loan ln = new Loan(ongoingLoanSet.getString("loan_id"),
						ongoingLoanSet.getInt("loan_request_id"),
						ongoingLoanSet.getInt("merchant_id"),
						ongoingLoanSet.getInt("product_id"),
						ongoingLoanSet.getInt("loaner_id"),
						ongoingLoanSet.getDate("loan_start_date").toLocalDate(),
						ongoingLoanSet.getFloat("monthly_payment"),
						ongoingLoanSet.getInt("remaining_months_to_pay"),
						ongoingLoanSet.getFloat("remaining_balance"),
						ongoingLoanSet.getInt("paid_months"),
						ongoingLoanSet.getFloat("paid_balance"),
						ongoingLoanSet.getString("loan_status"),
						loanSchedules
						);

				loans.add(ln);
			}

			return loans;


		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}


	public ArrayList<Merchant> getAllMerchants() {
		ArrayList<Merchant> allMerchants = new ArrayList<>();
		
		String getAllMerchantQuery = "SELECT * FROM merchant_table";
		
		try(PreparedStatement prepSt = connection.prepareStatement(getAllMerchantQuery)) {
			
			ResultSet allMerchantsSet = prepSt.executeQuery();
			
			while(allMerchantsSet.next()) {
				int merchantId = allMerchantsSet.getInt("merchant_id");
				Merchant merch = getMerchantData(merchantId);
				System.out.println(merch.getMerchantId());
				
				allMerchants.add(merch);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		return allMerchants;
		
	}
	
	public ArrayList<Faqs> getAllFaqs() {
		ArrayList<Faqs> faqs = new ArrayList<>();
		
		String retrieveFaqsQuery = "SELECT * FROM faqs_table";
		
		try(PreparedStatement prepSt = connection.prepareStatement(retrieveFaqsQuery)) {
			ResultSet faqsSet = prepSt.executeQuery();
			
			
			while(faqsSet.next()) {
				Faqs faq = new Faqs(faqsSet.getInt("faq_id"),
						faqsSet.getString("faq_title"),
						faqsSet.getString("faq_query"),
						faqsSet.getString("faq_answer"),
						faqsSet.getString("faq_user_type"));
				
				faqs.add(faq);
			}
			
			return faqs;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
	}
}








