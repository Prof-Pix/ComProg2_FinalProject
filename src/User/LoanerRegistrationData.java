package User;

import java.time.LocalDate;

import Database.DatabaseManager;
import UserEnums.MonthlyIncome;
import UserEnums.Occupation;

public class LoanerRegistrationData extends Loaner {
	
	DatabaseManager dbManager = new DatabaseManager();

	//User Attributes
	private String username;
	private String password;
	private String firstName;
	private String middleName;
	private String lastName;
	private String gender;
	private String fullName;
	private LocalDate birthdate;
	private int age;
	private String email;
	private String phoneNumber;

	//Loaner Specific Attributes
	private String sourceOfIncome;
	private String occupation;
	private String monthlyIncome;

	public LoanerRegistrationData(String username, 
			String password, 
			String firstName, 
			String middleName, 
			String lastName,
			String gender,
			LocalDate birthdate, 
			int age, 
			String email, 
			String phoneNumber, 
			String sourceOfIncome, 
			String occupation, 
			String monthlyIncome) {
		
		super(username, 
				password, 
				firstName, 
				middleName, 
				lastName, 
				gender,
				birthdate, 
				age, 
				email, 
				phoneNumber, 
				sourceOfIncome,
				occupation,
				monthlyIncome);

		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
		fullName = this.getFullName();
		this.birthdate = birthdate;
		this.age = age;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.sourceOfIncome = sourceOfIncome;
		this.occupation = occupation;
		this.monthlyIncome = monthlyIncome;
		// TODO Auto-generated constructor stub
	}


}
