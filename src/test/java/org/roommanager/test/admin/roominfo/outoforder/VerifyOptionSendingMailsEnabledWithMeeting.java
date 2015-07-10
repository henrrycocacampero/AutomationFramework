package org.roommanager.test.admin.roominfo.outoforder;

import org.roommanager.framework.pages.admin.conferenceroom.ConferenceRoomPage;
import org.roommanager.framework.pages.admin.conferenceroom.OutOfOrderPage;
import org.roommanager.framework.pages.admin.home.HomePage;
import org.roommanager.framework.pages.admin.login.LoginPage;
import org.roommanager.framework.utilities.api.admin.EmailServerApi;
import org.roommanager.framework.utilities.api.admin.RoomApi;
import org.roommanager.framework.utilities.api.tablet.MeetingApi;
import org.roommanager.framework.utilities.common.LogManager;
import org.roommanager.framework.utilities.common.PropertiesReader;
import org.roommanager.framework.utilities.common.TestBase;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class VerifyOptionSendingMailsEnabledWithMeeting extends TestBase{
	private String meetingSubject = "Subject Meeting";
	/** setDescription: It contains Description for a Out-Of-Order*/
	String setDescription = "Out-Of-Order in the room";
	/** roomSelected: It contains Title for a Out-Of-Order*/
	String nameTitle ="Temporarily Out of Order";
	/** isPresentOutOfOrder: Boolean value, that indicates whether or not there is an 
	* Out-Of-Order is created 
	**/
	boolean isPresentOutOfOrder= false;
	  
	/** 
    * errorMessage: It contains the error message that would appear 
	* if test case fails
	*/	  
	String msgError= "The Out Of Order was not created!";
    	  
	/** roomSelected: Name of room to be selected for create a Out-Of-Order*/	  
    String roomSelected = "SM-Room8";
    
    /** isPresentErrorMessage: Boolean value, that indicates whether or not 
     * that indicates whether or not there is Error Message, 
     * In case: "To" field must be greater than "From" field 
	**/ 
    boolean isPresentErrorMessage =false;
        
    /**
	* This method performs the test case: Verify if is possible create 
	* a Out-Of-Order with correct values("From" field is less than "To").
	*/
	@Test
	public void verifyOptionSendingMailsEnabledWithMeeting() {
		LoginPage login = new LoginPage(driver);
		HomePage adminHome = login.clickSignInButton();
			
	    ConferenceRoomPage conferenceRoom = adminHome.selectConferenceRoomsLink();
		  
		OutOfOrderPage outOfOrderPage = conferenceRoom.doubleClickOnRoom(roomSelected)
										.clickOnOutOfOrderPlanning()
										.setTitle(nameTitle)										
										.setDescription(setDescription)
										
										.clickSaveButtonOutOfOrder();
		isPresentErrorMessage = outOfOrderPage.errorMessageToGreaterFrom();
		/*Asserts*/
		Assert.assertTrue(isPresentErrorMessage,msgError);
		LogManager.info("Assert Test Verify Out Of OrderNot Saved With "
						+ "Invalid Times:"+ isPresentErrorMessage);			
	}
	@AfterTest
	public void testTearDown(){
		MeetingApi.deleteMeetingBySubjectName(roomSelected, meetingSubject);
	}
	
	@BeforeTest
	public void beforeTest(){
		if(EmailServerApi.getEmailServiceId() == null)
			EmailServerApi.createEmailServer(PropertiesReader.getUsername(), 
											PropertiesReader.getPassword(), 
											PropertiesReader.getExchangeHostName());
		RoomApi.deleteAllOutOfOrders(roomSelected);
	}
}
