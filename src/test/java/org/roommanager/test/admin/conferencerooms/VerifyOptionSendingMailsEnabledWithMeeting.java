package org.roommanager.test.admin.conferencerooms;

import org.roommanager.framework.pages.admin.conferenceroom.ConferenceRoomPage;
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

/**
 * The VerifyOptionSendingMailsEnabledWithMeeting class contains the test case: 
 * Create a Out Of Order in the Room<Value> when click on On/Off ScheduleButton and 
 * VErify if check on, in send mail checkbox 
 *
 * @author Daneiva Gamboa
 *
 */
public class VerifyOptionSendingMailsEnabledWithMeeting extends TestBase{
	/** meetingSubject: It contains Subject for a Meeting*/
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
    String roomSelected = "SM-Room10";
    
    /** isPresentErrorMessage: Boolean value, that indicates whether or not 
     * that indicates whether or not there is Error Message, 
     * In case: "To" field must be greater than "From" field 
	**/ 
    boolean isPresentErrorMessage =false;
        
    /**
	* This method performs the test case: Verify if is possible create 
	* a Out-Of-Order if check on, in send mail checkbox .
	*/
	@Test
	public void verifyOptionSendingMailsEnabledWithMeeting() {
		LoginPage login = new LoginPage(driver);
		HomePage adminHome = login.clickSignInButton();
			
    	ConferenceRoomPage conferenceRoom = adminHome.selectConferenceRoomsLink();
	  
    	conferenceRoom.doubleClickOnRoom(roomSelected)
			.clickOnOutOfOrderPlanning()
			.setTitle(nameTitle)										
			.setDescription(setDescription)
			.clickScheduleButton()
			.checkSendMailCheckbox()
			.clickSaveButtonOutOfOrder();
    	isPresentOutOfOrder = conferenceRoom.existOutOfOrder(roomSelected);
		/*Asserts*/
		Assert.assertTrue( isPresentOutOfOrder,msgError );		
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
