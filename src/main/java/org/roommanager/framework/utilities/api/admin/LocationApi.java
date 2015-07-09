package org.roommanager.framework.utilities.api.admin;

import org.json.simple.JSONObject;
import org.roommanager.framework.utilities.api.ApiManager;
import org.roommanager.framework.utilities.common.PropertiesReader;

public class LocationApi {
	
	/** LOCATION_BODY: Body of the location*/
	private static final String LOCATION_BODY = 
			"{ \"name\": \"[name]\", \"customName\": \"[displayName]\",\"description\": \"[description]\", \"parentId\":\"\", \"roomIds\": []}";
	
	/**
	 * deleteLocationByName: It delete a location by name
	 * @param name: It represents the Location's Name
	 */
	public static void deleteLocationByName(String name){
		String id = getLocationIdByName(name);
		String url = PropertiesReader.getRoomManagerApi() + "locations/" + id;
		ApiManager.deleteHttpMethod(url);
	}

	/**
	 * createLocation: It create a location with  the location_body
	 * @param name: It represents the Location's Name
	 * @param displayName: It represents the Location's displayName
	 * @param description: It represents the Location's description
	 */
	public static void createLocation(String name, String displayName, String description){
		String url = PropertiesReader.getRoomManagerApi() + "locations";
		String locationBody = LOCATION_BODY;
		locationBody = locationBody.replace("[name]", name)
			.replace("[displayName]", displayName)
			.replace("[description]", description);
		ApiManager.postHttpMethod(url, locationBody);
	}	
	
	/**
	 * getLocationIdByName: It get the location's Id by name
	 * @param locationName: It represents the Location's Name
	 * @return String
	 */
	private static String getLocationIdByName(String locationName) {
		String url = PropertiesReader.getRoomManagerApi() + "locations";
		String propertyName = "name";
		String locationId = ApiManager.getObejctPropertyByGivenPropertyValue("_id", propertyName, locationName, url);
		return locationId;
    }
	
	/**
	 * getLocationByName: It get the location's name
	 * @param locationName: It represents the Location's Name
	 * @return JsonObject
	 */
	public static JSONObject getLocationByName(String locationName) {
		String url = PropertiesReader.getRoomManagerApi() + "locations";
		String propertyName = "name";
		String loactionId = ApiManager.getObejctPropertyByGivenPropertyValue("_id", propertyName, locationName, url);
		url = url + "/" + loactionId;
		JSONObject location = (JSONObject)ApiManager.jsonRequest(ApiManager.getHttpMethod(url));
		return location;
    }

}
