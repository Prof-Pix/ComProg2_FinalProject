package User;

import java.time.LocalDate;
import java.util.Date;

public class Admin extends User {
	
	private String username;
	private String password;
	private String firstName;
	private String middleName;
	private String lastName;
	private LocalDate birthdate;
	private int age;
	private String email;
	private long phoneNumber;
	
	private static final String PHONE_REGEX = "^\\d{11}$"; // 10-digit phone number
	private static final String USERNAME_REGEX = "^[A-Z][a-z0-9]+$"; //asterisk means following characters are optional
	private static final String NAME_REGEX = "^[A-Z][a-z]+(\s[A-Z][a-z]+)*$"; //+ means following characters are required
	private static final String EMAIL_REGEX = "^[a-z][a-z0-9]+@[a-z]+.[a-z]{2,}$";
	
	
	public Admin(String username, String password, String firstName, String middleName, String lastName, LocalDate birthdate,
			int age, String email, long phoneNumber) {
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
		System.out.println(firstName.matches(NAME_REGEX));
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
		//Get the date now
		LocalDate today  = LocalDate.now();
		
		int minAge = 18;
		int maxAge = 60;
		
		// Get the date 18 years ago and 60 years ago 
		LocalDate eighteenYearsAgo = today.minusYears(minAge);
		LocalDate sixtyYearsAgo = today.minusYears(maxAge);
		System.out.println(eighteenYearsAgo);
		System.out.println(sixtyYearsAgo);
		System.out.println(birthdate);
		System.out.println(birthdate.isBefore(eighteenYearsAgo) || birthdate.isAfter(sixtyYearsAgo));
		return (birthdate.isAfter(sixtyYearsAgo) &&  birthdate.isBefore(eighteenYearsAgo)) && (age >= 18 && age <= 60);
	}
	
	boolean isEmailValid() {
		return email.matches(EMAIL_REGEX);
	}
	
	

	boolean isPhoneNumberValid() {
		return String.valueOf(phoneNumber).matches(PHONE_REGEX);
	}
	
}
