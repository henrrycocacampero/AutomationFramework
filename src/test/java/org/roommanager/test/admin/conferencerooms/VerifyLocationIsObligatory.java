package org.roommanager.test.admin.conferencerooms;

import org.roommanager.framework.pages.admin.conferenceroom.ConferenceRoomPage;
import org.roommanager.framework.pages.admin.conferenceroom.RoomInfoPage;
import org.roommanager.framework.pages.admin.home.HomePage;
import org.roommanager.framework.pages.admin.login.LoginPage;
import org.roommanager.framework.utilities.api.admin.LocationApi;
import org.roommanager.framework.utilities.common.TestBase;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class VerifyLocationIsObligatory extends TestBase{
	
	/** locationName: Name of location to be created*/
	private String locationName = " ";
	
	/** locationDisplayName: Display name of location to be created*/
	private String locationDisplayName = "locationDisplayName";
	
	/** locationDescription: Description of location to be created*/
	private String locationDescription = "Description location";
	
	/** roomName: Name of the room*/
    private String roomName = "SM-Room1";
	 
    /** 
	  * errorMessage: It contains the error message that would appear 
	  * if test case fails.
	  */
	private String errorMessage = "The test cases failed: the capacity"
			+ "of the room can be empty.";
	
	  /**
	  * This method performs the test case:Verify that the rename of 
	  * a room is disabled
	  */
	
	@Test
	public void VerifyRoomNameUpdateIsDisable() {
				
		LoginPage loginPage = new LoginPage(driver);
		
		HomePage homePage = loginPage
				.clickSignInButton();
		
		ConferenceRoomPage conferenceRoomPage = homePage
				.selectConferenceRoomsLink();
		
		RoomInfoPage roomInfoPage = conferenceRoomPage
				.doubleClickOnRoom(roomName);
		
		roomInfoPage.clickLocationButton()
				.clickLocationTypeButton()
				.clickOnLocation(locationName)
				.clickLocationButton();
		
		boolean IsFill = roomInfoPage.IsLocationTextFieldFilled(locationName);
		
		Assert.assertEquals( IsFill, true,errorMessage);
		
		//roomInfoPage.clickButtonCancelInfoRoom();
	}
	
	@BeforeTest
	public void beforeTesr(){
		LocationApi.createLocation(locationName, locationDisplayName, 
				 locationDescription);
	}
	@AfterTest
	public void afterTest(){
		//LocationApi.deleteLocationByName(locationName);
	}
}
