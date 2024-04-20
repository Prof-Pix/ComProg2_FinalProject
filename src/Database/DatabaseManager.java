package Database;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.HashMap;

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

		FileInputStream productImageFile = null;
		try {
			productImageFile = new FileInputStream(new File(data.getProductImagePath()));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}

		try(PreparedStatement prepSt = connection.prepareStatement(addProductQuery)) {

			prepSt.setInt(1, data.getMerchantOwnerId());
			prepSt.setBinaryStream(2, productImageFile);
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


}
