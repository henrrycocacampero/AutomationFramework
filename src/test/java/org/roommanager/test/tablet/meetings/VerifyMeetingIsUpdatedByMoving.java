package org.roommanager.test.tablet.meetings;

import org.roommanager.framework.pages.tablet.scheduler.CredentialsPage;
import org.roommanager.framework.pages.tablet.scheduler.SchedulerPage;
import org.roommanager.framework.pages.tablet.settings.ConnectionPage;
import org.roommanager.framework.pages.tablet.settings.NavigationPage;
import org.roommanager.framework.utilities.api.tablet.MeetingApi;
import org.roommanager.framework.utilities.common.Generator;
import org.roommanager.framework.utilities.common.PropertiesReader;
import org.roommanager.framework.utilities.common.TestBase;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;

public class VerifyMeetingIsUpdatedByMoving extends TestBase{
	/** username: It represents the name of the Current User*/
	private String username = PropertiesReader.getUsername();
	
	/** password: It represents the password of the Current User*/
	private String password = PropertiesReader.getPassword();
	
	/** organizer: It represents the name of the Meeting's Organizer*/
	private String organizer = username;
	
	/** attendee: It represents the Email of an attendee*/
	private String attendee = "\"" + username + "@" 
			+ PropertiesReader.getExchangeDomain() + "\"";
	
	/** conferenceRoom: It represents the name of the Room*/
	private String roomName = "Room05";
	
	/** subject: It represents the Meeting's Subject*/
	private String subject = "Subject Test";
	
	/** startTime: It represents the Meeting's Start Time*/
	private String startTime = Generator.getStartTime();
	
	/** startTime: It represents the eeting's End Time*/
	private String endTime = Generator.getEndTime();
	
	/** 
	 * errorMessage: It represents the Error Message 
	 * that will be displayed if the test fails
	 */
	private String errorMessage = "The Test failed because the updated"
			+ " meeting could be found in the Scheduler Page";
	
	/** 
	 * subjetcUpdate: It contains the new value of the 
	 * meeting's subject
	 */
	private String subjetcUpdate = "Subject was Updated";
	
	@Test
	public void f() throws InterruptedException {
		ConnectionPage connection = new ConnectionPage(driver);
		
		connection
			.enterServiceUrl(PropertiesReader.getRoomManagerApi())
			.clickSaveButton();
		
		NavigationPage navigation = connection
			.clickNavigationLink()
			.clickDefaultRoomComboBox()
			.selectConferenceRoomByName(roomName)
			.clickSaveButton();
		
		SchedulerPage scheduler = navigation
			.clickOnSchedulerPageLink();

		CredentialsPage credentials = scheduler
			.dragTimeLineBoxRightEnd(subject,22)
			.clickUpdateButton();
		
		credentials.enterPassword(password)
		.clickOkButton();
	}
	/**
	 * This method deletes the meeting updated by the test case
	 */
	@AfterTest
	public void afterTest(){
		MeetingApi.deleteMeetingBySubjectName(roomName, subjetcUpdate);
	}	
	 
	/**
	 * This method creates a new meeting to be user by the test case
	 */
	@BeforeTest
	public void beforeTest() {
		MeetingApi.createMeeting(organizer, subject, startTime, endTime,
				roomName, attendee);
	}
}
