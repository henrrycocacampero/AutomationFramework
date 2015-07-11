package org.roommanager.test.admin.conferencerooms;

import org.roommanager.framework.pages.admin.conferenceroom.ConferenceRoomPage;
import org.roommanager.framework.pages.admin.conferenceroom.RoomInfoPage;
import org.roommanager.framework.pages.admin.login.LoginPage;
import org.roommanager.framework.utilities.common.TestBase;
import org.testng.Assert;
import org.testng.annotations.Test;

public class VerifyRoomInfoLinkIsDisplayed extends TestBase{
	private String roomName = "SM-Room9";
	/** 
	 * errorMessage: It contains the error message that is displayed 
	 * if test case fails
	 */
	private String errorMessage = "The test failed because Room Info Link wasn't displayed";
	
	/**
	 * verifyRoomInfoPageLinkIsDisplayed: Check that after clicking on 
	 * Room Info tab in the Room Form, page is displayed.
	 * 
	 */
	@Test
	public void verifyRoomInfoPageLinkIsDisplayed() {
		LoginPage login = new LoginPage(driver);
		
		ConferenceRoomPage conferenceRoom = login
			.clickSignInButton()
			.selectConferenceRoomsLink();
			
		RoomInfoPage roomInfo = conferenceRoom
			.doubleClickOnRoom(roomName)
			.clickOnRoomInfoLink();
		
		boolean isRoomInfoLinkPresent = roomInfo
										.isRoomInfoPageTitlePresent(roomName)
									 	&& roomInfo.isRoomInfoPageLinkPresent();
		
		Assert.assertTrue(isRoomInfoLinkPresent, errorMessage);
	} 
}
