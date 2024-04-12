package Database;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import User.AdminRegistrationData;
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
		
		String userType = UserRoles.ADMIN.toString();
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
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	
	public String checkDuplicateAdminInput(String username, String email, String fullName, String phoneNumber) {
	
		if (HelperUtility.doesValueExist(connection, "username", username)) { 		//check for username duplication
			return "Username already taken. Please try again.";
		}
		else if (HelperUtility.doesValueExist(connection, "email", email)) { 		//check for email duplication
			return "Email already registered. Please try again."; 
		}
		else if (HelperUtility.doesValueExist(connection, "full_name", fullName)) { //check for full name duplication
			return "Name already registered. Please try again.";
		}
		else if (HelperUtility.doesValueExist(connection, "phone_number", phoneNumber)) {
			return "Phone already registered. Please try again.";
		}
		return "ok";
	}
	
	
}
