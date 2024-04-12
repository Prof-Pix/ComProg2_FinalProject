package User;

import java.time.LocalDate;
import java.util.Date;

import Database.DatabaseManager;

public class Admin extends User {
	
	//For Database Connection
	DatabaseManager dbManager = new DatabaseManager();
	
	private String username;
	private String password;
	private String firstName;
	private String middleName;
	private String lastName;
	private String fullName;
	private LocalDate birthdate;
	private int age;
	private String email;
	private String phoneNumber;

	
	private static final String USER_TYPE = "admin";
	private static final String PHONE_REGEX = "^\\d{11}$"; // 10-digit phone number
	private static final String USERNAME_REGEX = "^[A-Z][a-z0-9]+$"; //asterisk means following characters are optional
	private static final String NAME_REGEX = "^[A-Z][a-z]+(\s[A-Z][a-z]+)*$"; //+ means following characters are required
	private static final String EMAIL_REGEX = "^[a-z][a-z0-9]+@[a-z]+.[a-z]{2,}$";
	
	//Get the date now
	LocalDate today  = LocalDate.now();
	
	int minAge = 18;
	int maxAge = 60;
	
	// Get the date 18 years ago and 60 years ago 
	LocalDate eighteenYearsAgo = today.minusYears(minAge);
	LocalDate sixtyYearsAgo = today.minusYears(maxAge);
	
	
	public Admin(String username, String password, String firstName, String middleName, String lastName, LocalDate birthdate,
			int age, String email, String phoneNumber) {
		super(username, password, firstName, middleName, lastName, birthdate, age, email, phoneNumber);
		
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		this.birthdate = birthdate;
		this.age = age;
		this.email = email;
		this.phoneNumber = phoneNumber;
		fullName = this.getFullName();
	}	
	
	public boolean isAdminInputValid() {
		return 	isUsernameValid() && 
		isPasswordValid() && 
		isFirstNameValid() && 
		isMiddleNameValid() && 
		isLastNameValid() && 
		isAgeValid() && 
		isEmailValid() &&
		isPhoneNumberValid();
	}
	
	public String checkErrorAdminRegistration() {
		if (username.length() < 8 ) {
			return "Username must be atleast 8 characters.";
		}
		else if (!username.matches(USERNAME_REGEX)) {
			return "Username must start with an uppercase letter. \n"
					+ "Username cannot have any symbol.";
		}
		else if (password.length() < 8) {
			return "Password must be at least 8 characters.";
		}
		else if (!firstName.matches(NAME_REGEX) || !middleName.matches(NAME_REGEX) || !lastName.matches(NAME_REGEX)) {
			return "Name must start with an uppercase letter \n"
					+ "Name cannot have any number or symbol.";
		}
		else if((birthdate.isBefore(sixtyYearsAgo) &&  birthdate.isAfter(eighteenYearsAgo)) && (age < 18 && age > 60)) {
			return "Age must be at least 18 years old but not older than 60 years old.";
		}
		else if(!email.matches(EMAIL_REGEX)) {
			return "Email is not valid";
		}
		else if(!phoneNumber.matches(PHONE_REGEX)) {
			return "Phone number is not valid";
		}
		
		//For checking if some inputs are duplicated
		return checkDuplicate();

	}
	{
		
	}
	//For username
	//8 or more characters
	//No symbols and must start with a capital letter
	boolean isUsernameValid() {
		return username.length() >= 8 && username.matches(USERNAME_REGEX);
	}
	
	//password must have a length equal or greater than 8
	boolean isPasswordValid() {
		return password.length() >= 8;
	}
	
	boolean isFirstNameValid() {
		return firstName.matches(NAME_REGEX);
	}
	
	boolean isMiddleNameValid() {
		return middleName.matches(NAME_REGEX);
	}
	
	boolean isLastNameValid() {
		return lastName.matches(NAME_REGEX);
	}
	
	//	For age validation
	//Allows only 18 years old and above but not older than 60 years
	boolean isAgeValid() {
		return (birthdate.isAfter(sixtyYearsAgo) &&  birthdate.isBefore(eighteenYearsAgo)) && (age >= 18 && age <= 60);
	}
	
	boolean isEmailValid() {
		return email.matches(EMAIL_REGEX);
	}

	boolean isPhoneNumberValid() {
		return String.valueOf(phoneNumber).matches(PHONE_REGEX);
	}
	
	//For database registration
	public boolean registerAdmin(AdminRegistrationData data) {
		try {
			dbManager.connect();
			return dbManager.registerAdminInDatabase(data);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
			
	}
	
	//For database checking of duplication
	public String checkDuplicate() {
		try {
			dbManager.connect();
			return dbManager.checkDuplicateAdminInput(username, email, fullName, phoneNumber);
		} catch (Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
}
