package User;

import java.util.Date;

import UserEnums.MerchantCategory;

public class Merchant extends User {
	
	private String merchantName;
	private MerchantCategory merchantCategory;
	private String merchantAddress;
	
	public Merchant(String username, String password, String firstName, String middleName, String lastName,
			Date birthdate, int age, String email, int phoneNumber, String merchantName,
			MerchantCategory merchantCategory, String merchantAddress) {
		super(username, password, firstName, middleName, lastName, birthdate, age, email, phoneNumber);
		this.merchantName = merchantName;
		this.merchantCategory = merchantCategory;
		this.merchantAddress = merchantAddress;
	}

	public String getMerchantName() {
		return merchantName;
	}

	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
	}

	public MerchantCategory getMerchantCategory() {
		return merchantCategory;
	}

	public void setMerchantCategory(MerchantCategory merchantCategory) {
		this.merchantCategory = merchantCategory;
	}

	public String getMerchantAddress() {
		return merchantAddress;
	}

	public void setMerchantAddress(String merchantAddress) {
		this.merchantAddress = merchantAddress;
	}
	
	
	
}
