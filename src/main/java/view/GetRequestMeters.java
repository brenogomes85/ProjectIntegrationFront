package main.java.view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

public class GetRequestMeters {
	
	private static HttpURLConnection connection;
	
	private static final String GET_URL = "http://localhost:8090/portfolio";
	
	@SuppressWarnings("unused")
	public static List<MetersView> sendGET() throws IOException {	
		URL myurl = new URL(GET_URL);
		connection = (HttpURLConnection) myurl.openConnection();
			
		connection.setRequestMethod("GET");
		connection.setRequestProperty("Accept", "application/json");
			
		int responseCode = connection.getResponseCode();
		System.out.println("GET Response Code :: " +responseCode);
			
		StringBuilder content = new StringBuilder();
		
		StringBuffer response = new StringBuffer();
			
		if(responseCode == HttpURLConnection.HTTP_OK){
			BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String inputLine;
				
			while((inputLine = in.readLine()) != null) {
				content.append(inputLine);
			}
			in.close();
		
		} else {
			System.out.println("GET Request não funcionou");
		}
		return fromJsonAsList(content.toString(), MetersView[].class);
		
		
	}
	
	public static <T> List<T> fromJsonAsList(String json, Class<T[]> clazz){
		return Arrays.asList(new Gson().fromJson(json, clazz));
	}
	
}

