package User;

import java.time.LocalDate;
import java.util.Date;

import UserEnums.MerchantCategory;

public class Merchant extends User {
	
	private String merchantName;
	private MerchantCategory merchantCategory;
	private String merchantAddress;
	
	public Merchant(String username, String password, String firstName, String middleName, String lastName,
			LocalDate birthdate, int age, String email, int phoneNumber, String merchantName,
			MerchantCategory merchantCategory, String merchantAddress) {
		super(username, password, firstName, middleName, lastName, birthdate, age, email, phoneNumber);
		this.merchantName = merchantName;
		this.merchantCategory = merchantCategory;
		this.merchantAddress = merchantAddress;
	}
	
	
}
