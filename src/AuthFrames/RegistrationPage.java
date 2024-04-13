package AuthFrames;

public interface RegistrationPage {	
	
	default boolean isFieldEmpty(String field) {
		return field == null || field.trim().isEmpty();
	}

	default boolean isCommonFieldsEmpty(String username, String password, String firstName, String middleName, String lastName, String month, String day, String year, String age, String email, String phoneNumber) {
		return isFieldEmpty(username) ||
				isFieldEmpty(password) ||
				isFieldEmpty(firstName) ||
				isFieldEmpty(middleName) ||
				isFieldEmpty(lastName) ||
				isFieldEmpty(month) ||
				isFieldEmpty(day) ||
				isFieldEmpty(year) ||
				isFieldEmpty(age) ||
				isFieldEmpty(email) ||
				isFieldEmpty(phoneNumber);
	}
	
	 boolean isSpecificFieldsEmpty();

}
