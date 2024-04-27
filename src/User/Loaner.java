package User;

import java.time.LocalDate;

import Database.DatabaseManager;
import UserEnums.MonthlyIncome;
import UserEnums.Occupation;
import UserEnums.UserRoles;

public class Loaner extends User {
	
	DatabaseManager dbManager = new DatabaseManager();

	//
	private int loanerId;
	
	//User Attributes
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

	//Loaner Specific Attributes
	private String sourceOfIncome;
	private String occupation;
	private String monthlyIncome;
	
	private static final String USER_TYPE = UserRoles.LOANER.toString().toLowerCase();

	//All attributes
	public Loaner(int loanerId, String username, 
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
		super(username, password, firstName, middleName, lastName, gender, birthdate, age, email, phoneNumber);
		this.loanerId = loanerId;
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		fullName = this.getFullName();
		this.birthdate = birthdate;
		this.age = age;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.sourceOfIncome = sourceOfIncome;
		this.occupation = occupation;
		this.monthlyIncome = monthlyIncome;
	}

	//Without Loaner ID
	public Loaner(String username, 
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
		
		super(username, password, firstName, middleName, lastName, gender, birthdate, age, email, phoneNumber);

		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
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

	public int getLoanerId() {
		return loanerId;
	}

	public void setLoanerId(int loanerId) {
		this.loanerId = loanerId;
	}

	public String getSourceOfIncome() {
		return sourceOfIncome;
	}

	public void setSourceOfIncome(String sourceOfIncome) {
		this.sourceOfIncome = sourceOfIncome;
	}

	public String getOccupation() {
		return occupation;
	}

	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}

	public String getMonthlyIncome() {
		return monthlyIncome;
	}

	public void setMonthlyIncome(String monthlyIncome) {
		this.monthlyIncome = monthlyIncome;
	}
	
	//For database registration
	public boolean registerLoaner(LoanerRegistrationData data) {
		try {
			dbManager.connect();
			return dbManager.registerLoanerInDatabase(data);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
			
	}
	
	//For database checking of duplication
	public String checkDuplicateInput() {
		try {
			dbManager.connect();
			return dbManager.checkDuplicateLoanerInput(username, email, fullName, phoneNumber, USER_TYPE);
		} catch (Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	

}
