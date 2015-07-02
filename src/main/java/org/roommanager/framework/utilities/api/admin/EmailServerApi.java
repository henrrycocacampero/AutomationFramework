package org.roommanager.framework.utilities.api.admin;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.roommanager.framework.utilities.api.ApiManager;
import org.roommanager.framework.utilities.common.PropertiesReader;

public class EmailServerApi {
	
	private static final String  EMAILSERVER_BODY = "{\"username\": \"[username]\",\"password\": \"[password]\", \"hostname\": \"[hostname]\"}";
	private static String servicesFilter = "?type=exchange";

	public static String getEmailServiceId(){
		String url = PropertiesReader.getRoomManagerApi() + "services";
		String propertyId = "_id";
		
		String serviceResponse = ApiManager.getHttpMethod(url);
		JSONArray services = (JSONArray)ApiManager.jsonRequest(serviceResponse);
		JSONObject emailService =(JSONObject)services.get(0);
		return emailService.get(propertyId).toString();
	}
	
	public static void removeEmailServer(){
		String url = PropertiesReader.getRoomManagerApi() + "services/[serviceId]";
		String serviceId = getEmailServiceId();
		url = url.replace("[serviceId]", serviceId);
		ApiManager.deleteHttpMethod(url);
	}
	
	public static void createEmailServer(String username, String password, String hostname){
		
		String services= EMAILSERVER_BODY;
		String url = PropertiesReader.getRoomManagerApi() + "services" + servicesFilter;
		
		services= services.replace("[username]", username)
				.replace("[password]", password)
				.replace("[hostname]", hostname);
		
		ApiManager.postHttpMethod(url, services);
	}
	
	
}
