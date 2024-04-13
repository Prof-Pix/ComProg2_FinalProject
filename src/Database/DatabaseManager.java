package Database;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import User.AdminRegistrationData;
import User.LoanerRegistrationData;
import User.MerchantRegistrationData;
import UserEnums.UserRoles;
import Utilities.HelperUtility;

public class DatabaseManager {

	private Connection connection;
	private static final String USER = "root";
	private static final String PASSWORD = "homecredit123";

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
				+ "birthday, "
				+ "email, "
				+ "phone_number) "
				+ "VALUES(?,?,?,?,?,?,?,?,?,?)";

		try (PreparedStatement prepSt = connection.prepareStatement(query)){

			prepSt.setString(1, userType);
			prepSt.setString(2, data.getUsername());
			prepSt.setString(3, data.getPassword());
			prepSt.setString(4, data.getFirstName());
			prepSt.setString(5, data.getMiddleName());
			prepSt.setString(6, data.getLastName());
			prepSt.setString(7, data.getFullName());
			prepSt.setDate(8, birthdate);
			prepSt.setString(9, data.getEmail());
			prepSt.setString(10, data.getPhoneNumber());

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
				+ "birthday, "
				+ "email, "
				+ "phone_number) "
				+ "VALUES(?,?,?,?,?,?,?,?,?,?)";

		//Send the first the data that are common to all user roles
		try (PreparedStatement prepSt = connection.prepareStatement(usersQuery)){

			prepSt.setString(1, userType);
			prepSt.setString(2, data.getUsername());
			prepSt.setString(3, data.getPassword());
			prepSt.setString(4, data.getFirstName());
			prepSt.setString(5, data.getMiddleName());
			prepSt.setString(6, data.getLastName());
			prepSt.setString(7, data.getFullName());
			prepSt.setDate(8, birthdate);
			prepSt.setString(9, data.getEmail());
			prepSt.setString(10, data.getPhoneNumber());

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
		String merchantRegQuery = "INSERT INTO merchant_table (merchant_name, "
				+ "merchant_category, "
				+ "merchant_address, "
				+ "user_id) "
				+ "VALUES(?,?,?,?)";

		try (PreparedStatement prepSt = connection.prepareStatement(merchantRegQuery)) {
			prepSt.setString(1, data.getMerchantName());
			prepSt.setString(2, data.getMerchantCategory());
			prepSt.setString(3, data.getMerchantAddress());
			prepSt.setInt(4, retrievedId);

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
				+ "birthday, "
				+ "email, "
				+ "phone_number) "
				+ "VALUES(?,?,?,?,?,?,?,?,?,?)";

		//Send the first the data that are common to all user roles
		try (PreparedStatement prepSt = connection.prepareStatement(usersQuery)){

			prepSt.setString(1, userType);
			prepSt.setString(2, data.getUsername());
			prepSt.setString(3, data.getPassword());
			prepSt.setString(4, data.getFirstName());
			prepSt.setString(5, data.getMiddleName());
			prepSt.setString(6, data.getLastName());
			prepSt.setString(7, data.getFullName());
			prepSt.setDate(8, birthdate);
			prepSt.setString(9, data.getEmail());
			prepSt.setString(10, data.getPhoneNumber());

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

	public String checkDuplicateCommonFieldsInput(String username, String email, String fullName, String phoneNumber, String userType) {

		String tableName = "users";
		if (HelperUtility.doesValueExist(connection, tableName, "username", username, userType)) { 		//check for username duplication
			return "Username already taken. Please try again.";
		}
		else if (HelperUtility.doesValueExist(connection, tableName, "email", email, userType)) { 		//check for email duplication
			return "Email already registered. Please try again."; 
		}
		else if (HelperUtility.doesValueExist(connection, tableName, "full_name", fullName, userType)) { //check for full name duplication
			return "Name already registered. Please try again.";
		}
		else if (HelperUtility.doesValueExist(connection, tableName, "phone_number", phoneNumber, userType)) {
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
		} else if (HelperUtility.doesValueExist(connection, "merchant_table", "merchant_name", merchantName, userType)){
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

	public boolean verifyLoginInput(String userType, String username, String password) {
		
		String query = "SELECT username, password FROM users WHERE username = ? AND user_type = ?";
		
		try (PreparedStatement prepSt = connection.prepareStatement(query)){

			prepSt.setString(1, username);
			prepSt.setString(2, userType);

			ResultSet rs = prepSt.executeQuery();
			
			if(rs.next()) {
				String retrievedUsername = rs.getString(1);
				String retrievedPassword = rs.getString(2);
				
				if (password.equals(retrievedPassword)) {
					return true;
				}
				return false;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return false;
		
	}
}
