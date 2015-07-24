package org.roommanager.test.tablet.search;

import org.roommanager.framework.pages.tablet.search.SearchPage;
import org.roommanager.framework.pages.tablet.settings.ConnectionPage;
import org.roommanager.framework.pages.tablet.settings.NavigationPage;
import org.roommanager.framework.utilities.common.TestBase;
import org.testng.annotations.Test;
import org.testng.Assert;

public class VerifySearchByRoomName extends TestBase{
	/** roomName: Name of room to be used*/
	private String roomName = "Room02";
	
	/** 
	 * errorMessage: It contains the error message that is displayed 
	 * if test case fails
	 */
	private String errorMessage = "The test failed because the "
			+ "Room wasn't available";

	/**
	 * This method performs the test case: Verify that an specific room 
	 * is displayed on [Rooms List] schedule table using the [Room Name] 
	 * filter.
	 */
	@Test
	public void verifySearchByName(){
		ConnectionPage connection = new ConnectionPage(driver);
		connection.enterServiceUrl("http://172.20.208.84:4040/")
		.clickSaveButton();
		
		NavigationPage navigation = connection.clickNavigationLink()
									.clickDefaultRoomComboBox()
									.selectConferenceRoomByName(roomName)
									.clickSaveButton();
		
		SearchPage search = navigation.clickOnSearchPageLink();
		
		search.clickAdvancedButton().enterRoomName(roomName);
		
		Assert.assertTrue(search.isRoomPresent(roomName), errorMessage);
	}
}
