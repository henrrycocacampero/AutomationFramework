package org.roommanager.test.tablet.settings;

import org.roommanager.framework.pages.tablet.settings.ConnectionPage;
import org.roommanager.framework.utilities.api.admin.EmailServerApi;
import org.roommanager.framework.utilities.common.PropertiesReader;
import org.roommanager.framework.utilities.common.TestBase;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

/**
 * This class contains a test for verify the room name displayed
 * 
 * @author Samuel Vargas
 *
 */
public class VerifyUrlSettingsIsCreated extends TestBase {
	
	private String roomName = "Room01";
	
	/**
	 * This method registers an email server if it is not registered
	 */
	@BeforeTest
	public void BeforeTest(){
		driver.manage().deleteAllCookies();
		if (EmailServerApi.getEmailServiceId() == null) {
			EmailServerApi.createEmailServer(
						PropertiesReader.getExchangeUserName(),
						PropertiesReader.getExchangePassWord(),
						PropertiesReader.getExchangeHostName());
		}
	}
	
	/**
	 * Verify that after entering a valid service Url a connection is stablished
	 */
	@Test 
	public void VerifyAMeetingIsCreated(){
		
		ConnectionPage connection = new ConnectionPage(driver);
		
		connection.enterServiceUrl(PropertiesReader.getRoomManagerApi())
			.clickSaveButton()
			.clickNavigationLink()
			.clickDefaultRoomComboBox()
			.selectConferenceRoomByName(roomName)
			.clickSaveButton();
		
		String roomByName = connection.getRoomName();
		
		Assert.assertEquals(roomByName, roomName);
	}

}
