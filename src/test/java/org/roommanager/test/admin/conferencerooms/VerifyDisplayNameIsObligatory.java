package org.roommanager.test.admin.conferencerooms;

import org.roommanager.framework.pages.admin.conferenceroom.ConferenceRoomPage;
import org.roommanager.framework.pages.admin.conferenceroom.RoomInfoPage;
import org.roommanager.framework.pages.admin.home.HomePage;
import org.roommanager.framework.pages.admin.login.LoginPage;
import org.roommanager.framework.utilities.common.TestBase;
import org.testng.Assert;
import org.testng.annotations.Test;

public class VerifyDisplayNameIsObligatory extends TestBase{
	
	/** roomName: Name of room to be changed*/
	private String roomName = "SM-Room1";
	
	/** displayName: Display name of room to be changed*/
	private String displayName = "";
	
	/** expectedResult: Message of error display name*/
	private String expectedResult = "Display Name must not be empty";
	
	  /**
	  * This method performs the test case:Verify that the rename of 
	  * a room is disabled
	  */
	@Test
	public void VerifyRoomIsCanceled() {
				
		LoginPage loginPage = new LoginPage(driver);
		
		HomePage homePage = loginPage
				.clickSignInButton();
		
		ConferenceRoomPage conferenceRoomPage= homePage
				.selectConferenceRoomsLink();
		
		RoomInfoPage roomInfoPage = conferenceRoomPage
				.doubleClickOnRoom(roomName);

		roomInfoPage.setDisplayNameRoom(displayName)
				.clickButtonSaveInfoRoom();
		
		String errorMessageDisplayName = roomInfoPage
				.verificationRoomWithoutDisplayName(expectedResult);
		Assert.assertEquals(errorMessageDisplayName, expectedResult);
	}
}
