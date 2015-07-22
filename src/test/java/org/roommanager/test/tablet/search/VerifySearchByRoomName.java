package org.roommanager.test.tablet.search;

import junit.framework.Assert;

import org.roommanager.framework.pages.tablet.search.SearchPage;
import org.roommanager.framework.utilities.common.TestBase;
import org.testng.annotations.Test;

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
	 * This method performs the test case: Verify that a resource created
	 * is shown in the list of Available Resources of a Room.
	 * @throws InterruptedException 
	 */
	@Test
	public void verifySearchByName() throws InterruptedException{
		SearchPage search = new SearchPage(driver);
		
		Thread.sleep(20000);
		search.clickSearchIcon().clickAdvancedButton().enterRoomName(roomName);
		
		Assert.assertTrue(search.isRoomPresent(roomName));
	}
}
