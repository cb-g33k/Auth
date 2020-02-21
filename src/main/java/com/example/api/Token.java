package com.example.api;

public class Token {
	private static String token;

	public Token() {}
	
	public Token(String token) {
		super();
		this.token = token;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	@Override
	public String toString() {
		return getToken();//"Token [getToken()=" + getToken() + "]";
	}
}
