package Utilities;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

import javax.swing.AbstractButton;
import javax.swing.JDialog;
import javax.swing.JFrame;

public class HelperUtility {

	//FOR CAPITALIZING THE FIRST WORD
	public static String capitalizeWords(String str) {
		String[] words = str.split("\\s"); // Split into words
		StringBuilder capitalized = new StringBuilder();

		for (String word : words) {
			String firstLetter = word.substring(0, 1).toUpperCase();
			String remainingLetters = word.substring(1);
			capitalized.append(firstLetter).append(remainingLetters).append(" ");
		}

		return capitalized.toString().trim(); // Remove trailing space
	}

	//FOR CLOSING A FRAME
	public static void closePage(JFrame frame) {
		frame.dispose();
	}

	public static void closeDialog(JDialog dialog) {
		dialog.dispose();
	}

	//FOR DUPLICATE CHECKING OF USERS FIELDS
	//FOR USERS DUPLICATE CHECKING
	public static boolean doesCommonValueExist(Connection con, String columnName, String value, String conditionType) {
		String query = "SELECT * FROM users WHERE user_type = ? AND " + columnName + " = ?"; 
		try (PreparedStatement prepSt = con.prepareStatement(query)) {
			prepSt.setString(1, conditionType); //user type
			prepSt.setString(2, value);
			ResultSet rs = prepSt.executeQuery();

			if (rs.next()) {
				return true; 
			}
			return false;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false; 
	}

	//FOR ROLE-SPECIFIC FIELDS DUPLICATE CHECKING
	public static boolean doesSpecificValueExist(Connection con, String tableName, String columnName, String value) {
		String query = "SELECT * FROM " + tableName + " WHERE " + columnName + " = ?"; 
		try (PreparedStatement prepSt = con.prepareStatement(query)) {
			prepSt.setString(1, value);
			ResultSet rs = prepSt.executeQuery();

			if (rs.next()) {
				return true; 
			}
			return false;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false; 
	}

	//FOR CHECKING IF AN INPUT FIELD IS EMPTY
	public static boolean isInputFieldEmpty(String input) {
		return input == null || input.trim().isEmpty() ;
	}


	public static long generateRandomId() {
		UUID uuid = UUID.randomUUID();
		long id = Math.abs(uuid.getMostSignificantBits()); // Get the first 64 bits
		return id; 
	}


}

