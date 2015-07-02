package org.roommanager.framework.utilities.api.admin;

import org.roommanager.framework.utilities.api.ApiManager;
import org.roommanager.framework.utilities.common.PropertiesReader;

public class ResourceApi {

	private static final String RESOURCE_BODY = "{ \"name\": \"[name]\", \"customName\": \"[displayName]\",\"fontIcon\": \"[fontIcon]\", \"from\": \"\", \"description\": \"[description]\"}";
	
	public static void deleteResourceByName(String name){
		String id = getResourceByName(name);
		String url = PropertiesReader.getRoomManagerApi() + "resources/" + id;
		ApiManager.deleteHttpMethod(url);
	}

	public static void createResource(String name, String displayName, String icon, String description){
		String url = PropertiesReader.getRoomManagerApi() + "resources";
		String resourceBody = RESOURCE_BODY;
		
		resourceBody = resourceBody.replace("[name]", name)
			.replace("[displayName]", displayName)
			.replace("[fontIcon]", icon)
			.replace("[description]", description);
		ApiManager.postHttpMethod(url, resourceBody);
	}	
	
	private static String getResourceByName(String resourceName) {
		String url = PropertiesReader.getRoomManagerApi() + "resources";
		String propertyName = "name";
		
		String resourceId = ApiManager.getObejctPropertyByGivenPropertyValue("_id", propertyName, resourceName, url);
		return resourceId;
    }
}
