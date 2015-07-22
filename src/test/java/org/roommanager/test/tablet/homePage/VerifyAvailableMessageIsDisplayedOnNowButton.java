package org.roommanager.test.tablet.homePage;

import junit.framework.Assert;

import org.roommanager.framework.pages.tablet.home.HomePage;
import org.roommanager.framework.pages.tablet.settings.ConnectionPage;
import org.roommanager.framework.pages.tablet.settings.NavigationPage;
import org.roommanager.framework.utilities.api.tablet.MeetingApi;
import org.roommanager.framework.utilities.common.TestBase;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

public class VerifyAvailableMessageIsDisplayedOnNowButton extends TestBase{
	
	/** conferenceRoom: It represents the name of the Room*/
	private String conferenceRoom = "Room04";
	
	/** errorMessage: It represents the Error Message 
	 * that will be displayed if the test fails*/
	private String msgError = "The Test failed because the Available message not displayed in Now Button";
	
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
		connection.enterServiceUrl("http://172.20.208.84:4040/")
		.clickSaveButton();
		
		NavigationPage navigation = connection.clickNavigationLink()
									.clickDefaultRoomComboBox()
									.selectConferenceRoomByName("Room04")
									.clickSaveButton();
		
		HomePage homePage = navigation.clickOnHomePageLink();
		
		Assert.assertEquals(homePage.getAvailableMessage(),expectedMessage,msgError);		
	}
	
	/**
     * afterTest: It deletes the Meeting that was created by the test. 
     */
    @AfterTest
	  public void afterTest(){
		  MeetingApi.deleteMeetingBySubjectName(conferenceRoom, subject);
	}
}
