package User;

import java.time.LocalDate;

import UserEnums.MonthlyIncome;
import UserEnums.Occupation;
import UserEnums.SourceOfIncome;

public class Loaner extends User {
	
	//User Attributes
	private String username;
	private String password;
	private String firstName;
	private String middleName;
	private String lastName;
	private LocalDate birthdate;
	private int age;
	private String email;
	private long phoneNumber;
	
	//Loaner Specific Attributes
	private SourceOfIncome sourceOfIncome;
	private Occupation occupation;
	private MonthlyIncome monthlyIncome;
	
	public Loaner(String username, String password, String firstName, String middleName, String lastName,
			LocalDate birthdate, int age, String email, long phoneNumber, SourceOfIncome sourceOfIncome, Occupation occupation, MonthlyIncome monthlyIncome) {
		super(username, password, firstName, middleName, lastName, birthdate, age, email, phoneNumber);
		
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
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
