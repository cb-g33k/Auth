package com.example.api;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
/*
 * @deprecated: provided hard coded user login checks
 */
public class Authenticator {
	
	public static boolean checkUser(String username) {
		
		if((username != null && username.length() > 0) &&
				(username.equalsIgnoreCase("john")
				|| username.equalsIgnoreCase("susan"))) {
			return true;
		}else {
			return false;
		}
	}
	
	public static boolean checkPassword(String username, String password) {
		if(checkUser(username)) {
			if(username.equalsIgnoreCase("john") && password.equals("pass")) {
				return true;
			}
			if(username.equalsIgnoreCase("susan") && password.equals("pass")) {
				return true;
			}			
		}else {
			return false;
		}
		return false;
	}
	
}
