package org.roommanager.test.tablet.meetings;

import org.roommanager.framework.pages.tablet.scheduler.CredentialsPage;
import org.roommanager.framework.pages.tablet.scheduler.SchedulerPage;
import org.roommanager.framework.pages.tablet.settings.ConnectionPage;
import org.roommanager.framework.pages.tablet.settings.NavigationPage;
import org.roommanager.framework.utilities.api.tablet.MeetingApi;
import org.roommanager.framework.utilities.common.PropertiesReader;
import org.roommanager.framework.utilities.common.TestBase;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

public class VerifySetTimeByTimeline extends TestBase{
	/** username: It represents the meeting's organizer*/
	private String username = PropertiesReader.getUsername();
	
	/** password: It contains the user's password*/
	private String password = PropertiesReader.getPassword();
	
	/** 
	 * roomName: It represents the conference room in which the 
	 * meeting will be created
	 */
	private String roomName = "Room10";
	
	/** 
	 * meetingSubject: It represents the subject of the meeting to 
	 * be created
	 */
	private String meetingSubject = "Subject";
	
	/** 
	 * startTime: It represents the start time from the meeting to 
	 * be created.
	 */
	private int meetingStartTime = 23;
	
	/** 
	 * errorMessage: It represents the Error Message 
	 * that will be displayed if the test fails
	 */
	private String errorMessage = "The Test failed because the meeting"
			+ " created couldn't be found in the Scheduler Page";
	
	/**
	 * This method contains the test case's steps and assertions
	 */
	@Test 
	public void verifySetTimeByTimeline(){
		
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
					
		CredentialsPage credential = scheduler
			.setSpecificTimeinTimeline(meetingStartTime)
			.setOrganizerTextField(username)
			.setSubjectTextField(meetingSubject)
			.clickCreateButton();
		
		scheduler = credential
			.enterUsername(username)
			.enterPassword(password)
			.clickOkButton();
		
		boolean meetingExists = scheduler.existSubjectOnTimeline(meetingSubject);
		
		Assert.assertTrue(meetingExists, errorMessage);
	}
	
	/**
	 * This method deletes the meeting created by the test case
	 */
	@AfterTest
	public void testTearDown(){
		MeetingApi.deleteMeetingBySubjectName(roomName, meetingSubject);
	}
}