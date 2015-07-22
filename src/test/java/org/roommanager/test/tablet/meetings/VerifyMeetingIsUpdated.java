package org.roommanager.test.tablet.meetings;

import org.roommanager.framework.pages.tablet.home.HomePage;
import org.roommanager.framework.pages.tablet.scheduler.CredentialsPage;
import org.roommanager.framework.pages.tablet.scheduler.SchedulerPage;
import org.roommanager.framework.pages.tablet.settings.SettingsPage;
import org.roommanager.framework.utilities.api.admin.EmailServerApi;
import org.roommanager.framework.utilities.api.tablet.MeetingApi;
import org.roommanager.framework.utilities.common.Generator;
import org.roommanager.framework.utilities.common.PropertiesReader;
import org.roommanager.framework.utilities.common.TestBase;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class VerifyMeetingIsUpdated extends TestBase {
	
	private String username = PropertiesReader.getUsername();
	private String password = PropertiesReader.getPassword();
	private String organizer = username;
	private String attendee = "\"" + username + "@" + PropertiesReader.getExchangeDomain() + "\"";
	private String conferenceRoom = "SM-Room18";
	private String subject = "Subject Test";
	private String subjetcUpdate = "Subject was Updated";
	private String startTime = Generator.getStartTime();
	private String endTime = Generator.getEndTime();
	private String errorMessage = "The Test failed because the updated meeting could be found in the Scheduler Page";

	@Test
	public void VerifyMeetingISUpdated(){
	    	SettingsPage settings = new SettingsPage(driver);
			
			HomePage home = settings
				.waitForSettingPageToLoad()
				.clickRoomItem(conferenceRoom)
				.clickAcceptButton();

			SchedulerPage scheduler = home
				.clickSchedulerLink();

			CredentialsPage credential = scheduler
				.clickOnMeetingBox(subject)
				.setSubjectTextField(subjetcUpdate)				
				.clickUpdateButton();

			scheduler = credential	
				.enterPassword(password)
				.clickOkButton();

			Assert.assertTrue(scheduler.existSubjectOnTimeline(subjetcUpdate), errorMessage);
	  }
	  
	  @AfterTest
	  public void afterTest(){
		  MeetingApi.deleteMeetingBySubjectName(conferenceRoom, subjetcUpdate);
	  }	
	  
	  @BeforeTest
	  public void beforeTest() {
		   if(EmailServerApi.getEmailServiceId() == null)
				EmailServerApi.createEmailServer(PropertiesReader.getUsername(), PropertiesReader.getPassword(), PropertiesReader.getExchangeHostName());
		   MeetingApi.createMeeting(organizer, subject, startTime, endTime, conferenceRoom, attendee);
	  }
}
