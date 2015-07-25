package org.roommanager.test.tablet.settings;

import org.roommanager.framework.pages.tablet.home.HomePage;
import org.roommanager.framework.pages.tablet.settings.ConnectionPage;
import org.roommanager.framework.utilities.common.TestBase;
import org.testng.Assert;
import org.testng.annotations.Test;

public class VerifyDefaultRoomIsDisplayed extends TestBase{
	/**
	 * This class contains a test for verify the room name displayed
	 * 
	 * @author Samuel Vargas
	 *
	 */
	
	/** roomName: It represents the name of the Current room */
	private String roomName = "Room01";
	
	/**
	 * Verify that the room selected in "Default Conference Room" combo box 
	 * is the room by default
	 */
	@Test 
	public void VerifyDeafultRoomIsDisplayed(){
		ConnectionPage connection = new ConnectionPage(driver);
		connection.enterServiceUrl("http://172.20.208.84:4040/")
		.clickSaveButton()
		.clickNavigationLink()
		.clickDefaultRoomComboBox()
		.selectConferenceRoomByName(roomName)
		.clickSaveButton();
		HomePage home = connection.clickOnHomePageLink();		
		String roomByDefault = home.getRoomNameLabel();
		Assert.assertEquals(roomByDefault, roomName);
						
	}

}
