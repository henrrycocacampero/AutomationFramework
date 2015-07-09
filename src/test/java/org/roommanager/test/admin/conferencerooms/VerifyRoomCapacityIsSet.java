package org.roommanager.test.admin.conferencerooms;

import org.roommanager.framework.pages.admin.conferenceroom.ConferenceRoomPage;
import org.roommanager.framework.pages.admin.conferenceroom.RoomInfoPage;
import org.roommanager.framework.pages.admin.home.HomePage;
import org.roommanager.framework.pages.admin.login.LoginPage;
import org.roommanager.framework.utilities.common.TestBase;
import org.testng.Assert;
import org.testng.annotations.Test;
/**
 * VerifyRoomCapacityIsSet class contains the test case:  
 * Verify that the capacity of a room is set 
 * 
 * @author Paulo Ormachea
 *
 */
public class VerifyRoomCapacityIsSet extends TestBase {
	
	/** roomSelected: Name of the room*/ 
	private String roomSelected =  "SM-Room1";
	
	/**capacityRoomUpdated: Capacity of the room*/
    private String capacityRoomUpdated = "100";
        
	/** 
	  * errorMessage: It contains the error message that would appear 
	  * if test case fails.
	  */
	private String msgError = "Conferece Room Page - The room capacity is not set";
	
	  /**
	  * This method performs the test case:
	  * Verify that the capacity of a room is set
	  */
	@Test
	public void verifyRoomDisplayNameIsSet() {
				
		LoginPage loginPage = new LoginPage(driver);
		
		HomePage homePage = loginPage
				.clickSignInButton();
		
		ConferenceRoomPage conferenceRoomPage = homePage
				.selectConferenceRoomsLink();
		
		RoomInfoPage updateRoomName = conferenceRoomPage
				.doubleClickOnRoom(roomSelected);
		
		updateRoomName.setCapacityRoom(capacityRoomUpdated);
		
		String capacity = updateRoomName.getCapacityRoom();
		
		updateRoomName.clickButtonCancelInfoRoom();
		
		Assert.assertEquals( capacityRoomUpdated, 
				capacity,msgError);
				
	}
}

