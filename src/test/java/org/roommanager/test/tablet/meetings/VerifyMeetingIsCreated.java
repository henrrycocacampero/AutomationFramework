package org.roommanager.test.tablet.meetings;

import org.roommanager.framework.pages.tablet.home.HomePage;
import org.roommanager.framework.pages.tablet.scheduler.CredentialsPage;
import org.roommanager.framework.pages.tablet.scheduler.SchedulerPage;
import org.roommanager.framework.pages.tablet.settings.SettingsPage;
import org.roommanager.framework.utilities.api.admin.EmailServerApi;
import org.roommanager.framework.utilities.api.tablet.MeetingApi;
import org.roommanager.framework.utilities.common.TestBase;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.roommanager.framework.utilities.common.PropertiesReader;

public class VerifyMeetingIsCreated extends TestBase{
	private String username = PropertiesReader.getUsername();
	private String password = PropertiesReader.getPassword();
	private String roomName = "Room07";
	private String meetingSubject = "Subject Meeting Pablo";
	private String errorMessage = "The Test failed because the created couldn't be found in the Scheduler Page";
	
	@Test 
	public void VerifyAMeetingIsCreated(){
		SettingsPage settings = new SettingsPage(driver);
		
		HomePage home = settings
			.waitForSettingPageToLoad()
			.clickRoomItem(roomName)
			.clickAcceptButton();
		
		SchedulerPage scheduler = home
			.clickOnSchedulerPageLink();
			
		CredentialsPage credential = scheduler
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
	
	@AfterTest
	public void testTearDown(){
		MeetingApi.deleteMeetingBySubjectName(roomName, meetingSubject);
	}
	
	@BeforeTest
	public void beforeTest(){
		if(EmailServerApi.getEmailServiceId() == null)
			EmailServerApi.createEmailServer(PropertiesReader.getUsername(), PropertiesReader.getPassword(), PropertiesReader.getExchangeHostName());
	}
}
