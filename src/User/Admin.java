package User;

import java.time.LocalDate;
import java.util.Date;

import Database.DatabaseManager;
import UserEnums.UserRoles;

public class Admin extends User {
	
	//For Database Connection
	DatabaseManager dbManager = new DatabaseManager();
	
	private int adminId;
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
	
	private static final String USER_TYPE = UserRoles.ADMIN.toString().toLowerCase();

	public Admin(int adminId, String username, String password, String firstName, String middleName, String lastName, String gender, LocalDate birthdate,
			int age, String email, String phoneNumber) {
		super(username, password, firstName, middleName, lastName, gender, birthdate, age, email, phoneNumber);
		
		this.adminId = adminId;
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
	
	public Admin( String username, String password, String firstName, String middleName, String lastName, String gender, LocalDate birthdate,
			int age, String email, String phoneNumber) {
		super(username, password, firstName, middleName, lastName, gender, birthdate, age, email, phoneNumber);
		

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
	
	public int getAdminId() {
		return adminId;
	}

	public void setAdminId(int adminId) {
		this.adminId = adminId;
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
	public String checkDuplicateInput() {
		try {
			dbManager.connect();
			return dbManager.checkDuplicateAdminInput(username, email, fullName, phoneNumber, USER_TYPE);
		} catch (Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
}
