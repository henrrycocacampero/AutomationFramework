package org.roommanager.framework.utilities.api.admin;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.roommanager.framework.utilities.common.LogManager;
import org.roommanager.framework.utilities.common.PropertiesReader;

public class ImpersonationApi {
	
    private static String json;
    private static final String IMPERSONATION_BODY = "{\"impersonate\": \"[impersonation]\"}";
	
	public static Object jsonRequest(String json){
		Object resultObject = null; 
		try {
             JSONParser parser = new JSONParser();
             resultObject = parser.parse(json);
		 }
		 catch(Exception ex){
			 LogManager.error("Error Message"+ex);
		 }
		 return resultObject;
	}
	
	public static String getRequest(String urlRequest) {	 
		try (CloseableHttpClient httpClient = HttpClientBuilder.create().build()) {
            HttpGet request = new HttpGet(urlRequest);
            request.addHeader("content-type", "application/json");
            HttpResponse result = httpClient.execute(request);
            json = EntityUtils.toString(result.getEntity(), "UTF-8");
        } 
		catch (IOException ex) {
			LogManager.error("Error Message"+ex);
        }
		return json;
    }
	
	public static String getImpersonation(String name) {
		String id = null;
		String url = PropertiesReader.getRoomManagerApi() + "services";
		String propertyName = "name";
		String propertyId = "_id";		
		String json = getRequest(url);
		Object resourcesAsJson = jsonRequest(json);
		if (resourcesAsJson instanceof JSONArray) {
            JSONArray array=(JSONArray)resourcesAsJson;
            for (Object object : array) {
                JSONObject obj =(JSONObject)object;
                if(obj.get(propertyName).toString().equals(name)){
                	return obj.get(propertyId).toString();
                }
            }
        }else if (resourcesAsJson instanceof JSONObject) {
            JSONObject obj =(JSONObject)resourcesAsJson;
            if(obj.get(propertyName).toString().equals(name)){
            	return obj.get(propertyId).toString();
            }
        }
		return id;
    }
	
	public static void setImpersonation(String impersonation, String name){
		String id = getImpersonation(name);
		String impersonationBody = IMPERSONATION_BODY;
		impersonationBody = impersonationBody.replace("[impersonation]", 
													  impersonation);
		String url = PropertiesReader.getRoomManagerApi() + "services/" + id;
		try (CloseableHttpClient httpClient = HttpClientBuilder.create().build()) {
            HttpPut request = new HttpPut(url);
            StringEntity params = new StringEntity(impersonationBody);
            request.addHeader("content-type", "application/json");
            request.setEntity(params);
            httpClient.execute(request);   
        } 
		catch (Exception ex) {
			LogManager.error("Error Message"+ex);
        }	
	}
}
