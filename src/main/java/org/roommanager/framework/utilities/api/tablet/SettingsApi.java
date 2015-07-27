package org.roommanager.framework.utilities.api.tablet;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.simple.parser.JSONParser;
import org.roommanager.framework.utilities.api.ApiManager;
import org.roommanager.framework.utilities.common.LogManager;

public class SettingsApi {
	
	public static String json;
	
	public static Object jsonRequest(String json){
		Object resultObject = null; 
		try {
             JSONParser parser = new JSONParser();
             resultObject = parser.parse(json);
		 }
		 catch(Exception ex){
			LogManager.info("Error Message");
		 }
		 return resultObject;

	}
	
	public static String getHttpMethod(String url){
		String response = ApiManager.getHttpMethod(url);
		return response;
	}

}
