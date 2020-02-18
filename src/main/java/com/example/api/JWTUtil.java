package com.example.api;

/*
 * Token method interface
 */
public interface JWTUtil {
	public boolean verifyToken(String jwt_token);
	public String getScopes(String jwt_token) ;
	public String createToken(String scopes) ;
}