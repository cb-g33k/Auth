package com.example.api;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;


@RestController
@RequestMapping("")
public class RegisterAPI {
	@PostMapping("/account/register")
	public String putCustomer(@RequestBody Customer customer, UriComponentsBuilder uriComponent) throws IOException {
			String uri = "http://localhost:8080/api/customers/0";
			URL url = new URL(uri);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setInstanceFollowRedirects(false);
			conn.setRequestMethod("PUT");
			conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
			conn.setUseCaches(false);
			
			DataOutputStream os = new DataOutputStream(conn.getOutputStream());
			
			os.writeChars("{\"name\":"+ "\""+customer.getName()+"\""+
					", \"email\":" +"\""+customer.getEmail()+"\""+
					", \"password\":"+"\""+customer.getPassword()+"\""+"}");
			os.close();

			int x = conn.getResponseCode();
			String y = conn.getResponseMessage();
			String inputLine = "";
			StringBuffer bufferResponse = new StringBuffer();
			BufferedReader resp = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			while ((inputLine = resp.readLine()) != null) {
				bufferResponse.append(inputLine);
			}
			return bufferResponse.toString();
	}
}