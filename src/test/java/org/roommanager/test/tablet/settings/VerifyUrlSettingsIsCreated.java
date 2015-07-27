package org.roommanager.test.tablet.settings;

import org.roommanager.framework.pages.tablet.settings.ConnectionPage;
import org.roommanager.framework.utilities.common.TestBase;
import org.testng.Assert;
import org.testng.annotations.Test;

public class VerifyUrlSettingsIsCreated extends TestBase {
	
	/**
	 * This class contains a test for verify the room name displayed
	 * 
	 * @author Samuel Vargas
	 *
	 */
	
	/** expectedMessage: It represents if the conection was stablished */
	private String expectedMessage = "Connection stablished";
	
	
	/**
	 * Verify that after entering a valid service Url a connection is stablished
	 */
	@Test 
	public void VerifyAMeetingIsCreated(){
		ConnectionPage connection = new ConnectionPage(driver);
		connection.enterServiceUrl("http://172.20.208.84:4040/")
		.clickSaveButton();
		String successfulMessage = connection.connectionMessage();

		Assert.assertEquals(successfulMessage, expectedMessage);
						
	}

}
