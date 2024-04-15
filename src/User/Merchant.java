package User;

import java.time.LocalDate;
import java.util.Date;

import Database.DatabaseManager;
import UserEnums.MerchantCategory;
import UserEnums.UserRoles;

public class Merchant extends User {
	
	
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
	private String merchantName;
	private String merchantCategory;
	private String merchantRegionLocation;
	private String merchantProvinceLocation;
	private String merchantCityLocation;
	private String merchantBarangayLocation;
	private String merchantStreetLocation;
	private String merchantAddress;
	
	private static final String USER_TYPE = UserRoles.MERCHANT.toString().toLowerCase();;

	
	public Merchant(String username, String password, String firstName, String middleName, String lastName,
			LocalDate birthdate, int age, String email, String phoneNumber, String merchantName,
			String merchantCategory, String merchantRegionLocation, String merchantProvinceLocation, String merchantCityLocation, String merchantBarangayLocation, String merchantStreetLocation) {
		
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
		this.merchantName = merchantName;
		this.merchantCategory = merchantCategory;
		this.merchantRegionLocation = merchantRegionLocation;
		this.merchantProvinceLocation = merchantProvinceLocation;
		this.merchantCityLocation = merchantCityLocation;
		this.merchantBarangayLocation = merchantBarangayLocation;
		this.merchantStreetLocation = merchantStreetLocation;
		this.merchantAddress = merchantStreetLocation + "," + merchantBarangayLocation + "," + merchantCityLocation + "," + merchantProvinceLocation + "," + merchantRegionLocation;
	}

	public String getMerchantName() {
		return merchantName;
	}

	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
	}

	public String getMerchantCategory() {
		return merchantCategory;
	}

	public void setMerchantCategory(String merchantCategory) {
		this.merchantCategory = merchantCategory;
	}

	public String getMerchantAddress() {
		return merchantAddress;
	}

	public void setMerchantAddress(String merchantAddress) {
		this.merchantAddress = merchantAddress;
	}
	
	public String getMerchantRegionLocation() {
		return merchantRegionLocation;
	}

	public void setMerchantRegionLocation(String merchantRegionLocation) {
		this.merchantRegionLocation = merchantRegionLocation;
	}

	public String getMerchantProvinceLocation() {
		return merchantProvinceLocation;
	}

	public void setMerchantProvinceLocation(String merchantProvinceLocation) {
		this.merchantProvinceLocation = merchantProvinceLocation;
	}

	public String getMerchantCityLocation() {
		return merchantCityLocation;
	}

	public void setMerchantCityLocation(String merchantCityLocation) {
		this.merchantCityLocation = merchantCityLocation;
	}

	public String getMerchantBarangayLocation() {
		return merchantBarangayLocation;
	}

	public void setMerchantBarangayLocation(String merchantBarangayLocation) {
		this.merchantBarangayLocation = merchantBarangayLocation;
	}

	public String getMerchantStreetLocation() {
		return merchantStreetLocation;
	}

	public void setMerchantStreetLocation(String merchantStreetLocation) {
		this.merchantStreetLocation = merchantStreetLocation;
	}

	//For database registration
	public boolean registerMerchant(MerchantRegistrationData data) {
		try {
			dbManager.connect();
			return dbManager.registerMerchantInDatabase(data);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
			
	}
	
	//For database checking of duplication
		public String checkDuplicateInput() {
			try {
				dbManager.connect();
				return dbManager.checkDuplicateMerchantInput(username, email, fullName, phoneNumber, merchantName, USER_TYPE);
			} catch (Exception e){
				e.printStackTrace();
				return null;
			}
		}
	
}
