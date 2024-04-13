package User;

import java.time.LocalDate;
import java.util.Date;

import Database.DatabaseManager;

public class AdminRegistrationData extends Admin {
	
	private String username;
	private String password;
	private String firstName;
	private String middleName;
	private String lastName;
	private LocalDate birthdate;
	private int age;
	private String email;
	private String phoneNumber;
	
	public AdminRegistrationData(String username, String password, String firstName, String middleName, String lastName, LocalDate birthdate,
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
	}	
	
}
