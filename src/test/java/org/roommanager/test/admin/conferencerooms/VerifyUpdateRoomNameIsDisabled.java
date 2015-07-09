package org.roommanager.test.admin.conferencerooms;

import org.roommanager.framework.pages.admin.conferenceroom.ConferenceRoomPage;
import org.roommanager.framework.pages.admin.conferenceroom.RoomInfoPage;
import org.roommanager.framework.pages.admin.home.HomePage;
import org.roommanager.framework.pages.admin.login.LoginPage;
import org.roommanager.framework.utilities.common.TestBase;
import org.testng.Assert;
import org.testng.annotations.Test;
/**
 * VerifyRoomNameUpdateIsDisable class contains the test case:  
 * Verify that is not possible to update the Name of a room.
 * 
 * @author Paulo Ormachea
 *
 */
public class VerifyUpdateRoomNameIsDisabled extends TestBase {
	
	/** roomSelected: Name of the room*/ 
	private String roomSelected =  "SM-Room1";
    
	/** 
	  * errorMessage: It contains the error message that would appear 
	  * if test case fails.
	  */
	private String msgError = "Conferece Room Page - The room Name is Enabled";
	
	  /**
	  * This method performs the test case:Verify that the rename of a room 
	  * is disabled
	  */
	@Test
	public void VerifyRoomNameUpdateIsDisable() {
				
		LoginPage loginPage = new LoginPage(driver);
		
		HomePage homePage = loginPage
				.clickSignInButton();
		
		ConferenceRoomPage conferenceRoomPage = homePage
				.selectConferenceRoomsLink();
		
		RoomInfoPage updateRoomName = conferenceRoomPage
				.doubleClickOnRoom(roomSelected);
		
		updateRoomName.setDisplayNameRoom(roomSelected);
		
		boolean IsDisabled = updateRoomName.RoomNameIsDisable();
		
		Assert.assertEquals( IsDisabled, true,msgError);
		
		updateRoomName.clickButtonCancelInfoRoom();
	}
}


