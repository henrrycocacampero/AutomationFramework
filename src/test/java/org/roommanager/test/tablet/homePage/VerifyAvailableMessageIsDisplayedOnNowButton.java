package org.roommanager.test.tablet.homePage;

import org.testng.Assert;
import org.roommanager.framework.pages.tablet.home.HomePage;
import org.roommanager.framework.pages.tablet.settings.ConnectionPage;
import org.roommanager.framework.pages.tablet.settings.NavigationPage;
import org.roommanager.framework.utilities.api.tablet.MeetingApi;
import org.roommanager.framework.utilities.common.PropertiesReader;
import org.roommanager.framework.utilities.common.TestBase;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

public class VerifyAvailableMessageIsDisplayedOnNowButton extends TestBase{
	/** urlTablet :It represents the URL of module Tablet  */
	private String urlTablet= PropertiesReader.getRoomManagerApi();
	
	/** conferenceRoom: It represents the name of the Room*/
	private String conferenceRoom = "Room09";
	
	/** errorMessage: It represents the Error Message 
	 * that will be displayed if the test fails*/
	private String msgError = "The test failed because the Available message not displayed in Now Button";
	
	/** subject: It represents the Meeting's Subject*/
	private String subject = "Subject Test";
	
	/** subject: It represents Available Message*/
	private String expectedMessage ="Available";
	
	/**
	 * verifyAvailableMessageIsDisplayedOnNowButton: The Available Message of room 
	 * selected is displayed on Now button.
	 *
	 * @author Paulo Ormachea
	 */
	@Test
	public void verifyAvailableMessageIsDisplayedOnNowButton() {
		
		ConnectionPage connection = new ConnectionPage(driver);
		connection.enterServiceUrl(urlTablet)
		.clickSaveButton();
		
		NavigationPage navigation = connection.clickNavigationLink()
									.clickDefaultRoomComboBox()
									.selectConferenceRoomByName(conferenceRoom)
									.clickSaveButton();
		
		HomePage homePage = navigation.clickOnHomePageLink();
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
