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
import java.util.ArrayList;
import java.util.HashMap;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import LoanRequest.LoanRequest;

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

	public ArrayList<Product> getMerchantData(int merchantId) {

		ArrayList<Product> merchantProducts = new ArrayList<>();

		String productQuery = "SELECT * FROM products WHERE merchant_id = ?";

		try(PreparedStatement productSt = connection.prepareStatement(productQuery)) {

			productSt.setInt(1, merchantId);

			ResultSet productResultSet = productSt.executeQuery();

			//Get the product loan terms of each product
			String productLoanTermQuery = "SELECT * FROM product_loan_table WHERE product_id = ?";
			try(PreparedStatement productLoanTermSt = connection.prepareStatement(productLoanTermQuery)) {

				while(productResultSet.next()) {				
					int productId = productResultSet.getInt("product_id");

					productLoanTermSt.setInt(1, productId);

					ResultSet productLoanTermsResultSet = productLoanTermSt.executeQuery();

					ArrayList<ProductLoanTerm> productLoanTerms = new ArrayList<>();
					while(productLoanTermsResultSet.next()) {		

						int monthsToPay = productLoanTermsResultSet.getInt("months_to_pay");
						float interestRate = productLoanTermsResultSet.getFloat("interest_rate");

						ProductLoanTerm prodLoanTerm = new ProductLoanTerm(monthsToPay, interestRate);
						productLoanTerms.add(prodLoanTerm);


					}

					//Conversion of BLOB to ImageIcon
					byte[] imageBytes = productResultSet.getBytes("product_picture");
					ImageIcon imageIcon = new ImageIcon(imageBytes);		

					Product prod = new Product(merchantId, 
							imageIcon, 
							productResultSet.getString("product_name"), 
							productResultSet.getString("product_brand"),
							productResultSet.getString("product_description"),
							productResultSet.getString("product_specifications"),
							productResultSet.getFloat("product_price"),
							productResultSet.getInt("product_stocks_available"),
							productResultSet.getString("product_category"),
							productLoanTerms);

					merchantProducts.add(prod);

				}

			} catch (SQLException e) {
				e.printStackTrace();
				return null;
			}

		} catch(SQLException e) {
			e.printStackTrace();
		}


		return merchantProducts;

	}

	public boolean deleteMerchantProduct(int merchantId, String productName) {

		//THIS FOLLOWS A CHILD-PARENT DELETION
		int retrievedProductId = 0;

		//Retrieve the product id
		String retrieveProductIdQuery = "SELECT * FROM products where product_name = ?";

		try(PreparedStatement prepSt = connection.prepareStatement(retrieveProductIdQuery)) {

			prepSt.setString(1, productName);

			ResultSet rs = prepSt.executeQuery();

			if(rs.next()) {
				retrievedProductId = rs.getInt(1);
			}

		}catch(SQLException e) {
			e.printStackTrace();
			return false;
		} 

		//First delete all the product loan terms
		String deleteProductLoanTermsQuery = "DELETE FROM product_loan_table WHERE product_id = ?";
		try(PreparedStatement prepSt = connection.prepareStatement(deleteProductLoanTermsQuery)) {
			prepSt.setInt(1, retrievedProductId);
			prepSt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		String deleteProductQuery = "DELETE FROM products WHERE merchant_id = ? AND product_name = ?";

		try(PreparedStatement prepSt = connection.prepareStatement(deleteProductQuery)) {
			prepSt.setInt(1, merchantId);
			prepSt.setString(2, productName);

			prepSt.executeUpdate();

			return true;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}

	}

	public boolean editMerchantProduct(int merchantId, ProductRegistrationData data, String originalProductName) {

		int retrievedProductId = 0;
		String updateProductQuery = "UPDATE products "
				+ "SET product_picture = ?,"
				+ "product_name = ?, "
				+ "product_brand = ?,"
				+ "product_description = ?,"
				+ "product_specifications = ?,"
				+ "product_price = ?,"
				+ "product_stocks_available = ?,"
				+ "product_category = ?"
				+ "WHERE merchant_id = ? AND product_name = ?";

		Image image = data.getProductImage().getImage();
		BufferedImage bufferedImage = (BufferedImage) image;
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


		try(PreparedStatement prepSt = connection.prepareStatement(updateProductQuery)) {

			ByteArrayInputStream inputStream = new ByteArrayInputStream(imageBytes);

			prepSt.setBinaryStream(1, inputStream, imageBytes.length);
			prepSt.setString(2, data.getName());
			prepSt.setString(3, data.getBrand());
			prepSt.setString(4, data.getDescription());
			prepSt.setString(5, data.getSpecifications());
			prepSt.setFloat(6, data.getPrice());
			prepSt.setInt(7, data.getStocksAvailable());
			prepSt.setString(8, data.getCategory());
			prepSt.setInt(9, merchantId);
			prepSt.setString(10, originalProductName);

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

		String deleteProductLoanTermsQuery = "DELETE FROM product_loan_table WHERE product_id = ?";
		try(PreparedStatement prepSt = connection.prepareStatement(deleteProductLoanTermsQuery)) {
			prepSt.setInt(1, retrievedProductId);
			prepSt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
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

	public ArrayList<Product> getAllMerchantProducts() {
		String query = "SELECT * FROM products";

		ArrayList<Product> merchantProducts = new ArrayList<>();

		try {
			Statement retrieveSt = connection.createStatement();


			ResultSet productResultSet = retrieveSt.executeQuery(query);

			//Get the product loan terms of each product
			String productLoanTermQuery = "SELECT * FROM product_loan_table WHERE product_id = ?";
			try(PreparedStatement productLoanTermSt = connection.prepareStatement(productLoanTermQuery)) {

				while(productResultSet.next()) {				
					int productId = productResultSet.getInt("product_id");

					productLoanTermSt.setInt(1, productId);

					//Retrieval of Loan Terms
					ResultSet productLoanTermsResultSet = productLoanTermSt.executeQuery();

					ArrayList<ProductLoanTerm> productLoanTerms = new ArrayList<>();
					while(productLoanTermsResultSet.next()) {		

						int monthsToPay = productLoanTermsResultSet.getInt("months_to_pay");
						float interestRate = productLoanTermsResultSet.getFloat("interest_rate");

						ProductLoanTerm prodLoanTerm = new ProductLoanTerm(monthsToPay, interestRate);
						productLoanTerms.add(prodLoanTerm);	
					}

					//Retrieve the merchant name
					String merchantName = getMerchantName(productResultSet.getInt("merchant_id"));

					//Conversion of BLOB to ImageIcon
					byte[] imageBytes = productResultSet.getBytes("product_picture");
					ImageIcon imageIcon = new ImageIcon(imageBytes);		

					Product prod = new Product(productResultSet.getInt("merchant_id"), 
							merchantName,
							imageIcon, 
							productResultSet.getString("product_name"), 
							productResultSet.getString("product_brand"),
							productResultSet.getString("product_description"),
							productResultSet.getString("product_specifications"),
							productResultSet.getFloat("product_price"),
							productResultSet.getInt("product_stocks_available"),
							productResultSet.getString("product_category"),
							productLoanTerms);

					merchantProducts.add(prod);

				}

			} catch (SQLException e) {
				e.printStackTrace();
				return null;
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

	public Loaner getLoanerData(int loanerId) {

		//Retrieve first the user id
		int retrieveUserId = 0;
		String retrieveUserIdQuery = "SELECT * FROM loaner_table WHERE loaner_id = ?";

		try(PreparedStatement userIdRetrieveSt = connection.prepareStatement(retrieveUserIdQuery)) {
			userIdRetrieveSt.setInt(1, loanerId);

			ResultSet loanerDataSet = userIdRetrieveSt.executeQuery();

			if(loanerDataSet.next()) {
				retrieveUserId = loanerDataSet.getInt("user_id");
			}

			//Get the user data
			String retrieveUserDataQuery = "SELECT * FROM users WHERE user_id = ?";

			try(PreparedStatement userDataRetrieveSt = connection.prepareStatement(retrieveUserDataQuery)) {

				userDataRetrieveSt.setInt(1, retrieveUserId);

				ResultSet userDataSet = userDataRetrieveSt.executeQuery();

				if(userDataSet.next()) {
					// For the birthdate
					LocalDate birthdate = userDataSet.getDate("birthday").toLocalDate();

					Loaner loaner = new Loaner(userDataSet.getString("username"),
							userDataSet.getString("password"),
							userDataSet.getString("first_name"),
							userDataSet.getString("middle_name"),
							userDataSet.getString("last_name"),
							userDataSet.getString("gender"),
							birthdate,
							userDataSet.getInt("age"),
							userDataSet.getString("email"),
							userDataSet.getString("phone_number"),
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

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}


	}


	public boolean sendLoanRequest(int loanerId, Loaner loanerData, Product productData, ProductLoanTerm productLoanTerm) {

		//THIS FOLLOWS A CHILD-PARENT DELETION
		int retrievedProductId = 0;

		//Retrieve the product id
		String retrieveProductIdQuery = "SELECT * FROM products WHERE merchant_id = ? AND product_name = ?";

		try(PreparedStatement prepSt = connection.prepareStatement(retrieveProductIdQuery)) {

			prepSt.setInt(1, productData.getMerchantOwnerId());
			prepSt.setString(2, productData.getName());

			ResultSet rs = prepSt.executeQuery();

			if(rs.next()) {
				retrievedProductId = rs.getInt(1);
			}

		}catch(SQLException e) {
			e.printStackTrace();
			return false;
		} 

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

		String sendLoanRequestQuery = "INSERT INTO merchant_loans_list_table(merchant_id,"
				+ "loaner_id,"
				+ "product_id,"
				+ "loaned_product_name,"
				+ "loaned_product_price,"
				+ "loaned_product_months_to_pay,"
				+ "loaned_product_interest_rate,"
				+ "loaner_name,"
				+ "loan_request_date,"
				+ "is_pending) "
				+ "VALUES (?,?,?,?,?,?,?,?,?,?)";

		try(PreparedStatement prepSt = connection.prepareStatement(sendLoanRequestQuery)) {
			prepSt.setInt(1, productData.getMerchantOwnerId());
			prepSt.setInt(2, loanerId);
			prepSt.setInt(3, retrievedProductId);
			prepSt.setString(4, productData.getName());
			prepSt.setFloat(5, productData.getPrice());
			prepSt.setInt(6, productLoanTerm.getMonthsToPay());
			prepSt.setFloat(7, productLoanTerm.getInterestRate());
			prepSt.setString(8, loanerData.getFullName());
			prepSt.setDate(9, java.sql.Date.valueOf(LocalDate.now()) );
			prepSt.setBoolean(10, true);

			prepSt.executeUpdate();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}

	}


	public ArrayList<LoanRequest> getPendingLoanRequest(int merchantId) {
		String retrieveLoanRequest = "SELECT * FROM merchant_loans_list_table WHERE merchant_id = ?";

		ArrayList<LoanRequest> loanRequests = new ArrayList<>();

		try(PreparedStatement prepSt = connection.prepareStatement(retrieveLoanRequest)) {

			prepSt.setInt(1, merchantId);

			ResultSet loanRequestSet = prepSt.executeQuery();

			while(loanRequestSet.next()) {
				LoanRequest loanReq = new LoanRequest(loanRequestSet.getInt("merchant_id"), loanRequestSet.getInt("loaner_id"),
						loanRequestSet.getInt("product_id"),
						loanRequestSet.getString("loaned_product_name"),
						loanRequestSet.getFloat("loaned_product_price"),
						loanRequestSet.getInt("loaned_product_months_to_pay"),
						loanRequestSet.getFloat("loaned_product_interest_rate"),
						loanRequestSet.getString("loaner_name"),
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
		String retrieveLoanRequest = "SELECT * FROM merchant_loans_list_table WHERE merchant_id = ?";

		ArrayList<LoanRequest> loanRequests = new ArrayList<>();

		try(PreparedStatement prepSt = connection.prepareStatement(retrieveLoanRequest)) {

			prepSt.setInt(1, merchantId);

			ResultSet loanRequestSet = prepSt.executeQuery();
			
			
			while(loanRequestSet.next()) {
				
				boolean isPending = loanRequestSet.getBoolean("is_pending");
				
				if(!isPending) {
					LoanRequest loanReq = new LoanRequest(loanRequestSet.getInt("merchant_id"), loanRequestSet.getInt("loaner_id"),
							loanRequestSet.getInt("product_id"),
							loanRequestSet.getString("loaned_product_name"),
							loanRequestSet.getFloat("loaned_product_price"),
							loanRequestSet.getInt("loaned_product_months_to_pay"),
							loanRequestSet.getFloat("loaned_product_interest_rate"),
							loanRequestSet.getString("loaner_name"),
							loanRequestSet.getDate("loan_request_date").toLocalDate(),
							loanRequestSet.getDate("loan_approve_date").toLocalDate(),
							loanRequestSet.getDate("loan_reject_date").toLocalDate(),
							loanRequestSet.getBoolean("is_pending"),
							loanRequestSet.getBoolean("is_rejected"),
							loanRequestSet.getBoolean("is_approved"));

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
		String retrieveLoanIdQuery = "SELECT merchant_loan_id FROM merchant_loans_list_table WHERE merchant_id = ? AND loaner_id = ? AND product_id = ? AND is_pending = ?";

		try(PreparedStatement prepSt = connection.prepareStatement(retrieveLoanIdQuery)) {
			prepSt.setInt(1, merchantId);
			prepSt.setInt(2, loanerId);
			prepSt.setInt(3, productId);
			prepSt.setBoolean(4, true);

			ResultSet loanIdSet = prepSt.executeQuery();

			if(loanIdSet.next()) {
				retrievedLoanId = loanIdSet.getInt("merchant_loan_id");
				
				String approveQuery = "UPDATE merchant_loans_list_table SET is_pending = ? , is_approved = ?, is_rejected = ? , loan_approve_date = ?, loan_reject_date = ? WHERE merchant_loan_id = ? AND is_pending = ?";
				
				try(PreparedStatement prepStt = connection.prepareStatement(approveQuery)) {
					prepStt.setBoolean(1, false);
					prepStt.setBoolean(2, true);
					prepStt.setBoolean(3, false);
					prepStt.setDate(4, java.sql.Date.valueOf(LocalDate.now()));
					prepStt.setDate(5, java.sql.Date.valueOf(LocalDate.now()));
					prepStt.setInt(6, retrievedLoanId);
					prepStt.setBoolean(7, true);
					
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
		String retrieveLoanIdQuery = "SELECT merchant_loan_id FROM merchant_loans_list_table WHERE merchant_id = ? AND loaner_id = ? AND product_id = ? AND is_pending = ?";

		try(PreparedStatement prepSt = connection.prepareStatement(retrieveLoanIdQuery)) {
			prepSt.setInt(1, merchantId);
			prepSt.setInt(2, loanerId);
			prepSt.setInt(3, productId);
			prepSt.setBoolean(4, true);

			ResultSet loanIdSet = prepSt.executeQuery();

			if(loanIdSet.next()) {
				retrievedLoanId = loanIdSet.getInt("merchant_loan_id");
				
				String approveQuery = "UPDATE merchant_loans_list_table SET is_pending = ? , is_approved = ?, is_rejected = ? , loan_approve_date = ?, loan_reject_date = ? WHERE merchant_loan_id = ? AND is_pending = ?";
				
				try(PreparedStatement prepStt = connection.prepareStatement(approveQuery)) {
					prepStt.setBoolean(1, false);
					prepStt.setBoolean(2, false);
					prepStt.setBoolean(3, true);
					prepStt.setDate(4, java.sql.Date.valueOf(LocalDate.now()));
					prepStt.setDate(5, java.sql.Date.valueOf(LocalDate.now()));
					prepStt.setInt(6, retrievedLoanId);
					prepStt.setBoolean(7, true);
					
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
		String query = "SELECT * FROM merchant_loans_list_table WHERE loaner_id = ?";
		
		try(PreparedStatement prepSt = connection.prepareStatement(query)) {
			
			prepSt.setInt(1, loanerId);
			
			ResultSet pendingLoansSet = prepSt.executeQuery();
			
			while(pendingLoansSet.next()) {
				LoanRequest loanReq = new LoanRequest(pendingLoansSet.getInt("merchant_id"), pendingLoansSet.getInt("loaner_id"),
						pendingLoansSet.getInt("product_id"),
						pendingLoansSet.getString("loaned_product_name"),
						pendingLoansSet.getFloat("loaned_product_price"),
						pendingLoansSet.getInt("loaned_product_months_to_pay"),
						pendingLoansSet.getFloat("loaned_product_interest_rate"),
						pendingLoansSet.getString("loaner_name"),
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
		String query = "SELECT * FROM merchant_loans_list_table WHERE loaner_id = ?";
		
		try(PreparedStatement prepSt = connection.prepareStatement(query)) {
			
			prepSt.setInt(1, loanerId);
			
			ResultSet pendingLoansSet = prepSt.executeQuery();
			
			while(pendingLoansSet.next()) {
				
				boolean isPending = pendingLoansSet.getBoolean("is_pending");
				System.out.println(isPending);
				
				if(!isPending) {
					LoanRequest loanReq = new LoanRequest(pendingLoansSet.getInt("merchant_id"), pendingLoansSet.getInt("loaner_id"),
							pendingLoansSet.getInt("product_id"),
							pendingLoansSet.getString("loaned_product_name"),
							pendingLoansSet.getFloat("loaned_product_price"),
							pendingLoansSet.getInt("loaned_product_months_to_pay"),
							pendingLoansSet.getFloat("loaned_product_interest_rate"),
							pendingLoansSet.getString("loaner_name"),
							pendingLoansSet.getDate("loan_request_date").toLocalDate(),
							pendingLoansSet.getDate("loan_approve_date").toLocalDate(),
							pendingLoansSet.getDate("loan_reject_date").toLocalDate(),
							pendingLoansSet.getBoolean("is_pending"),
							pendingLoansSet.getBoolean("is_rejected"),
							pendingLoansSet.getBoolean("is_approved"));
					
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
	
	public LoanRequest getLoanRequestInformation(LoanRequest loanReq) {
		
		String retrieveProductImageQuery = "SELECT product_picture , product_brand FROM products WHERE merchant_id = ? AND product_id = ? ";
		
		ImageIcon productImage = null;
		String productBrand = null;
		String merchantName = null;
		
		try(PreparedStatement prepSt = connection.prepareStatement(retrieveProductImageQuery)) {
			try {
				prepSt.setInt(1, loanReq.getMerchantId());
				prepSt.setInt(2, loanReq.getProductId());
				
				ResultSet productSet = prepSt.executeQuery();
				
				while(productSet.next()) {
					
					//Conversion of BLOB to ImageIcon
					byte[] imageBytes = productSet.getBytes("product_picture");
					productImage = new ImageIcon(imageBytes);
					
					productBrand = productSet.getString("product_brand");
					
					
				}
 				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		String retrieveMerchantName = "SELECT merchant_name FROM merchant_table WHERE merchant_id = ?";
		
		try(PreparedStatement prepSt = connection.prepareStatement(retrieveMerchantName)) {
			prepSt.setInt(1, loanReq.getMerchantId());
			
			ResultSet merchantSet = prepSt.executeQuery();
			
			while(merchantSet.next()) {
				merchantName = merchantSet.getString("merchant_name");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
//		public LoanRequest(int merchantId, 
//				String merchantName, 
//				int loanerId, 
//				int productId, 
//				String loanedProductName,
//				String loanedProductBrand, 
//				ImageIcon loanedProductPicture, 
//				float loanedProductPrice,
//				int loanedProductMonthsToPay, 
//				float loanedProductInterestRate, 
//				String loanerName, 
//				LocalDate loanRequestDate,
//				LocalDate loanApproveDate, 
//				LocalDate loanRejectDate, 
//				boolean isPending, boolean isRejected,
//				boolean isApproved)
		
		
		LoanRequest newLoanReq = new LoanRequest(loanReq.getMerchantId() , 
				merchantName, 
				loanReq.getLoanerId(), 
				loanReq.getProductId(), 
				loanReq.getLoanedProductName(), 
				productBrand, 
				productImage, 
				loanReq.getLoanedProductPrice(), 
				loanReq.getLoanedProductMonthsToPay(), 
				loanReq.getLoanedProductInterestRate(), 
				loanReq.getLoanerName(), 
				loanReq.getLoanRequestDate(), 
				loanReq.getLoanApproveDate(), 
				loanReq.getLoanRejectDate(), 
				loanReq.isPending(), 
				loanReq.isRejected(), 
				loanReq.isApproved());
		
		return newLoanReq;
		
	}
    
}








