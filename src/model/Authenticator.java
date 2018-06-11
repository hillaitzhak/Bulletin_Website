package model;

import Repository.UserRepository;
import utils.LockUser;

public class Authenticator {
	/*
	 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	 * Authentication function is responsible to perform the connection to the database.
	 * In addition, the function is responsible for the authentication process.
	 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	 */  
	
	public String authenticate(String username, String password) 
	{
		//Doing SHA function to the passwords
		String hashedPassword = this.toSha256(password);
		User user_to_authenticate = new User(username);
		UserRepository userrep = new UserRepository();
		String result = userrep.userAuthenticator(user_to_authenticate,hashedPassword);
		return result;
	}
	
	public String toSha256(String password) {
		//Using salt
		String hardPassword = password+"j7pHco4!t5L9S";
		String hashed = org.apache.commons.codec.digest.DigestUtils.sha256Hex(hardPassword);   
		return hashed;
	}
}