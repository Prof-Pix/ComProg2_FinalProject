package Utilities;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnectionUtil {


	private static Connection con = null; 
	private static final String USER = "root";
	private static final String PASSWORD = "homecredit123";

	public static Connection getConnection() { 
		if (con != null) {
			return con;
		}

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", USER, PASSWORD);
			return con;
		} catch (Exception e) {
			System.err.println("Error establishing database connection: " + e.getMessage());
			return null; 
		}
	}

}
