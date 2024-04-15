package User;

import java.time.LocalDate;

public class MerchantRegistrationData extends Merchant {

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
	
	public MerchantRegistrationData(String username, 
			String password, 
			String firstName, 
			String middleName, 
			String lastName,
			LocalDate birthdate, 
			int age, 
			String email, 
			String phoneNumber, 
			String merchantName,
			String merchantCategory, 
			String merchantRegionLocation,
			String merchantProvinceLocation,
			String merchantCityLocation,
			String merchantBarangayLocation,
			String merchantStreetLocation
			) {
		super(username, password, firstName, 
				middleName, lastName, birthdate, 
				age, email, phoneNumber, merchantName, 
				merchantCategory, merchantRegionLocation, merchantProvinceLocation, merchantCityLocation, merchantBarangayLocation, merchantStreetLocation);
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
		this.merchantAddress = this.getMerchantAddress();	}
}
