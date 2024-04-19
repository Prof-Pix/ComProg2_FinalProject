package AuthFrames;

import Utilities.HelperUtility;

public interface RegistrationPage {	

	default boolean isCommonFieldsEmpty(String username, String password, String firstName, String middleName, String lastName, String month, String day, String year, String gender, String age, String email, String phoneNumber) {
		return HelperUtility.isInputFieldEmpty(username) ||
				HelperUtility.isInputFieldEmpty(password) ||
				HelperUtility.isInputFieldEmpty(firstName) ||
				HelperUtility.isInputFieldEmpty(middleName) ||
				HelperUtility.isInputFieldEmpty(lastName) ||
				HelperUtility.isInputFieldEmpty(gender) ||
				HelperUtility.isInputFieldEmpty(month) ||
				HelperUtility.isInputFieldEmpty(day) ||
				HelperUtility.isInputFieldEmpty(year) ||
				HelperUtility.isInputFieldEmpty(age) ||
				HelperUtility.isInputFieldEmpty(email) ||
				HelperUtility.isInputFieldEmpty(phoneNumber);
	}
	
	 boolean isSpecificFieldsEmpty();

}
