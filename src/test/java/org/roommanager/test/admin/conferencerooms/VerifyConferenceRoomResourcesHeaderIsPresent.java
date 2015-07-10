package org.roommanager.test.admin.conferencerooms;

import org.junit.Assert;
import org.roommanager.framework.pages.admin.conferenceroom.ConferenceRoomPage;
import org.roommanager.framework.pages.admin.login.LoginPage;
import org.roommanager.framework.utilities.common.TestBase;
import org.testng.annotations.Test;

public class VerifyConferenceRoomResourcesHeaderIsPresent extends TestBase {
	/** 
	 * errorMessage: It contains the error message that is displayed 
	 * if test case fails
	 */
	private String errorMessage = "The test failed because Conference Room Header is not Present";
	
	/**
	 * verifyConferenceRoomResourcesHeaderIsPresent: Check that after 
	 * clicking on any Room of RoomTable, the Room Form page is displayed.
	 */
	@Test
	public void verifyConferenceRoomResourcesHeaderIsPresent() {
		LoginPage login = new LoginPage(driver);
		
		ConferenceRoomPage conferenceRoom = login
			.clickSignInButton()
			.selectConferenceRoomsLink();
			
		boolean isConferenceRoomHeaderPresent = 
				conferenceRoom.isResourceHeaderPresent() && 
				conferenceRoom.isRoomsTableHeaderPresent();
		
		Assert.assertTrue(errorMessage ,isConferenceRoomHeaderPresent);
	} 
}
