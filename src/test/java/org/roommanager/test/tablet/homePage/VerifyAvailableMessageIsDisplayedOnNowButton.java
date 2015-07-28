package org.roommanager.test.tablet.homePage;

import org.testng.Assert;
import org.roommanager.framework.pages.tablet.home.HomePage;
import org.roommanager.framework.pages.tablet.settings.ConnectionPage;
import org.roommanager.framework.utilities.api.tablet.MeetingApi;
import org.roommanager.framework.utilities.common.PropertiesReader;
import org.roommanager.framework.utilities.common.TestBase;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class VerifyAvailableMessageIsDisplayedOnNowButton extends TestBase{
	/** urlTablet :It represents the URL of module Tablet  */
	private String urlTablet= PropertiesReader.getRoomManagerApi();
	
	/** conferenceRoom: It represents the name of the Room*/
	private String conferenceRoom = "Room01";
	
	/** errorMessage: It represents the Error Message 
	 * that will be displayed if the test fails*/
	private String msgError = "The test failed because the Available message not displayed in Now Button";
	
	/** subject: It represents the Meeting's Subject*/
	private String subject = "Subject Test";
	
	/** subject: It represents Available Message*/
	private String expectedMessage ="Available";
	
	/** connection: Name of a new ConnectionPage */
	ConnectionPage connection;

	/**
	 * This method creates a new connection if there is not
	 */
	@BeforeTest
	public void beforeTest() {
		connection = new ConnectionPage(driver);
		if (connection.isConnectionNotEstablished(urlTablet)) {
			connection.enterServiceUrl(urlTablet).clickSaveButton()
					.clickNavigationLink().clickDefaultRoomComboBox()
					.selectConferenceRoomByName(conferenceRoom).clickSaveButton();
		}
	}

	/**
	 * verifyAvailableMessageIsDisplayedOnNowButton: The Available Message of room 
	 * selected is displayed on Now button.
	 *
	 * @author Paulo Ormachea
	 */
	@Test
	public void verifyAvailableMessageIsDisplayedOnNowButton() {
		
		HomePage homePage = connection.clickOnHomePageLink();
		String actualMessage = homePage.getAvailableMessage();
		
		Assert.assertEquals(actualMessage, expectedMessage, msgError);
	}
	
	/**
     * afterTest: It deletes the Meeting that was created by the test. 
     */
    @AfterTest
	  public void afterTest(){
		  MeetingApi.deleteMeetingBySubjectName(conferenceRoom, subject);
	}
}
