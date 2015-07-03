package org.roommanager.test.tablet.meetings;

import org.roommanager.framework.pages.tablet.home.HomePage;
import org.roommanager.framework.pages.tablet.scheduler.CredentialsPage;
import org.roommanager.framework.pages.tablet.scheduler.SchedulerPage;
import org.roommanager.framework.pages.tablet.setting.SettingsPage;
import org.roommanager.framework.utilities.api.tablet.MeetingApi;
import org.roommanager.framework.utilities.common.Generator;
import org.roommanager.framework.utilities.common.PropertiesReader;
import org.roommanager.framework.utilities.common.TestBase;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;

public class VerifyMeetingIsRemoved extends TestBase{

	private String username = PropertiesReader.getUsername();
	private String password = PropertiesReader.getPassword();
	private String organizer = username;
	private String attendee = "\"" + username + "@" + PropertiesReader.getExchangeDomain() + "\"";
	private String conferenceRoom = "SM-Room10";
	private String subject = "Subject Test";
	private String startTime = Generator.getStartTime();
	private String endTime = Generator.getEndTime();
	private String errorMessage = "The Test failed because the deleted meeting could be found in the Scheduler Page";
	
    @Test
    public void VerifyIfMeetingIsRemoved() {
    	SettingsPage settings = new SettingsPage(driver);
		
		HomePage home = settings
			.waitForSettingPageToLoad()
			.clickRoomItem(conferenceRoom)
			.clickAcceptButton();
		
		SchedulerPage scheduler = home
			.clickSchedulerLink();
			
		CredentialsPage credential = scheduler
			.clickOnMeetingBox(subject)
			.clickRemoveButton();
		
		scheduler = credential
			.enterUsername(username)
			.enterPassword(password)
			.clickOkButton();
		
		Assert.assertFalse(scheduler.existSubjectOnTimeline(subject), errorMessage);
    }
    @BeforeTest
    public void beforeTest() {
    	MeetingApi.createMeeting(organizer, subject, startTime, endTime, conferenceRoom, attendee);
    }

}
