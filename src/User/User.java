package User;

import java.time.LocalDate; 

public class User {

	private String username;
	private String password;
	private String firstName;
	private String middleName;
	private String lastName;
	private String fullName;
	private String gender;
	private LocalDate birthdate;
	private int age;
	private String email;
	private String phoneNumber;
	
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
	
	public User(String username, 
			String password, 
			String firstName, 
			String middleName, 
			String lastName, 
			String gender,
			LocalDate birthdate,
			int age, 
			String email, 
			String phoneNumber) {
		
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		this.gender = gender;
		this.birthdate = birthdate;
		this.age = age;
		this.email = email;
		this.phoneNumber = phoneNumber;
		fullName = firstName + " " + middleName + " " + lastName;
	}

	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getMiddleName() {
		return middleName;
	}
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public LocalDate getBirthdate() {
		return birthdate;
	}
	public void setBirthdate(LocalDate birthdate) {
		this.birthdate = birthdate;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	
	public boolean isUserInputValid() {
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
		
		public String checkErrorInputRegistration() {
			if (username.length() < 8 ) {
				return "Username must be atleast 8 characters.";
			}
			else if (!username.matches(USERNAME_REGEX)) {
				return "Username must start with an uppercase letter and must be followed by lowercase letters. \n"
						+ "Username cannot have any symbol.";
			}
			else if (password.length() < 8) {
				return "Password must be at least 8 characters.";
			}
			else if (!firstName.matches(NAME_REGEX) || !middleName.matches(NAME_REGEX) || !lastName.matches(NAME_REGEX)) {
				return "Name must start with an uppercase letter and must be followed by lowercase letters.\n"
						+ "Name cannot have any number or symbol.";
			}
			else if((birthdate.isBefore(sixtyYearsAgo) ||  birthdate.isAfter(eighteenYearsAgo)) || (age < 18 || age > 60)) {
				return "Age must be at least 18 years old but not older than 60 years old.";
			}
			else if(!email.matches(EMAIL_REGEX)) {
				return "Email is not valid";
			}
			else if(!phoneNumber.matches(PHONE_REGEX)) {
				return "Phone number is not valid";
			}
			return null;

		}
	
	
	
}
