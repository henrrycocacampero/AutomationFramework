package org.roommanager.test.admin.conferencerooms;

import org.roommanager.framework.pages.admin.conferenceroom.ConferenceRoomPage;
import org.roommanager.framework.pages.admin.conferenceroom.RoomInfoPage;
import org.roommanager.framework.pages.admin.login.LoginPage;
import org.roommanager.framework.utilities.common.TestBase;
import org.testng.Assert;
import org.testng.annotations.Test;

public class VerifyRoomInfoPageIsDisplayed extends TestBase{
	/** roomName: Name of room to be used*/
	private String roomName = "SM-Room9";
	
	/** 
	 * errorMessage: It contains the error message that is displayed 
	 * if test case fails
	 */
	private String errorMessage = "The test failed because Room Info Page wasn't displayed";
	
	/**
	 * verifyRoomInfoPageIsDisplayed: Check that after 
	 * clicking on any Room of RoomTable, the Room Form page is displayed.
	 */
	@Test
	public void verifyRoomInfoPageIsDisplayed() {
		LoginPage login = new LoginPage(driver);
		
		ConferenceRoomPage conferenceRoom = login
			.clickSignInButton()
			.selectConferenceRoomsLink();
			
		RoomInfoPage roomInfo = conferenceRoom
			.doubleClickOnRoom(roomName);
		
		boolean isRoomTitlePresent = roomInfo.isRoomInfoPageTitlePresent(roomName);
		
		Assert.assertTrue(isRoomTitlePresent, errorMessage);
	} 
}
