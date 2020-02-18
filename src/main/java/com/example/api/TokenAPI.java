package com.example.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/account/token")
public class TokenAPI {
    JWTUtil jwtUtil = new JWTHelper();

	@SuppressWarnings("unused")
	@PostMapping(consumes = "application/json")
	public ResponseEntity<?> getToken(@RequestBody TokenRequestData tokenRequestData) throws IOException {
		String pwd = "";
		String request = "http://localhost:8080/api/customers/byname/" + tokenRequestData.username;
		
		URL url = new URL(request);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setDoOutput(true);
		conn.setInstanceFollowRedirects(false);
		conn.setRequestMethod("POST");
		conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
		conn.setUseCaches(false);
		int x = conn.getResponseCode();

		BufferedReader resp = new BufferedReader(new InputStreamReader(conn.getInputStream()));

		String inputLine;
		StringBuffer bufferResponse = new StringBuffer();

		while ((inputLine = resp.readLine()) != null) {
			bufferResponse.append(inputLine);
		}
		resp.close();
		String stringResponse = bufferResponse.toString();
		
		if (stringResponse != null && stringResponse.length() > 0) {
			pwd = stringResponse.substring(stringResponse.indexOf("\"password\":\"") + 12,
					stringResponse.indexOf("\",\"id"));
		}
		if (pwd.equals(tokenRequestData.password)) {
			String token = jwtUtil.createToken(tokenRequestData.scopes);
			ResponseEntity<?> response = ResponseEntity.ok(token);
			sendToken(token);
			return response;
		}
		return (ResponseEntity<?>) ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
	}
	
	public void sendToken(String token) throws IOException {
		String request = "http://localhost:8080/api/proxy/token/" + token;
		
		URL url = new URL(request);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setDoOutput(true);
		conn.setInstanceFollowRedirects(false);
		conn.setRequestMethod("POST");
		conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
		conn.setUseCaches(false);
		int x = conn.getResponseCode();

		BufferedReader resp = new BufferedReader(new InputStreamReader(conn.getInputStream()));

		String inputLine;
		StringBuffer bufferResponse = new StringBuffer();

		while ((inputLine = resp.readLine()) != null) {
			bufferResponse.append(inputLine);
		}
		resp.close();
	}
}