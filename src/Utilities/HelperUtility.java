package Utilities;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;

public class HelperUtility {

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
     
     public static void closePage(JFrame frame) {
 		frame.dispose();
 	}
     
     public static boolean doesValueExist(Connection con, String columnName, String value) {
    	    String query = "SELECT * FROM users WHERE " + columnName + " = ?"; 
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

}
