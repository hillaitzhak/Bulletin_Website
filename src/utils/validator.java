package utils;

import org.owasp.esapi.ESAPI;

public class validator {

	public static boolean validatePassword(String pass){
		return ESAPI.validator().isValidInput("password", pass, "Password", 16, false);
	}
	
	public static boolean validateUserId(String userid){
		return ESAPI.validator().isValidInput("userid", userid, "UserId", 9, false);
	}
	
	public static boolean validateFirstname(String firstname){
		return ESAPI.validator().isValidInput("firstname", firstname, "Names", 20, false);
	}
	
	public static boolean validateLastname(String lastname){
		return ESAPI.validator().isValidInput("lastname", lastname, "Names", 20, false);
	}
	
	public static boolean validateUsername(String username){
		return ESAPI.validator().isValidInput("username", username, "UserName", 12, false);
	}
	
	public static boolean validateEmail(String email){
		return ESAPI.validator().isValidInput("email", email, "Email", 255, false);
	}
	
	public static boolean validateBirthDate(String bdate){
		return ESAPI.validator().isValidInput("bdate", bdate, "Date", 10, false);
	}
	
	public static boolean validateTelephone(String telephone){
		return ESAPI.validator().isValidInput("telephone", telephone, "Telephone", 10, false);
	}
	
	public static boolean validateText(String text){
		return ESAPI.validator().isValidInput("text", text, "Text", 40, false);
	}
	
	public static boolean validateTime(String time){
		return ESAPI.validator().isValidInput("time", time, "Time", 5, false);
	}
	
	public static boolean validateURL(String URL){
		return ESAPI.validator().isValidInput("URL", URL, "URL",200, false);
	}
}
