package org.roommanager.framework.utilities.api.admin;

import org.roommanager.framework.utilities.api.ApiManager;
import org.roommanager.framework.utilities.common.PropertiesReader;

public class RoomApi {

	public static String getRoomEmailByName(String roomName){
		String url = PropertiesReader.getRoomManagerApi() + "rooms";
		String propertyName = "displayName";
		
		String roomId = ApiManager.getObejctPropertyByGivenPropertyValue("emailAddress", propertyName, roomName, url);
		return roomId;
	}
	
	public static String getRoomIdByName(String roomName){
		String url = PropertiesReader.getRoomManagerApi() + "rooms";
		String propertyName = "displayName";
		
		String roomId = ApiManager.getObejctPropertyByGivenPropertyValue("_id", propertyName, roomName, url);
		return roomId;
	}
}
