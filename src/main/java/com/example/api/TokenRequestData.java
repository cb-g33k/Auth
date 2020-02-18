package com.example.api;

public class TokenRequestData {
	String name;
	String password;
	String scopes;
	
	public TokenRequestData(String name, String password, String scopes) {
		super();
		this.name = name;
		this.password = password;
		this.scopes = scopes;
	}

	public String getUsername() {
		return name;
	}
	
	public void setUsername(String username) {
		this.name = username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getScopes() {
		return scopes;
	}
	
	public void setScopes(String scopes) {
		this.scopes = scopes;
	}	
	
}
