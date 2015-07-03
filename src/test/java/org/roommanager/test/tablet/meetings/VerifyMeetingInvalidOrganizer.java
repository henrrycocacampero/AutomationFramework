package org.roommanager.test.tablet.meetings;

import org.roommanager.framework.pages.tablet.home.HomePage;
import org.roommanager.framework.pages.tablet.scheduler.SchedulerPage;
import org.roommanager.framework.pages.tablet.setting.SettingsPage;
import org.roommanager.framework.utilities.common.PropertiesReader;
import org.roommanager.framework.utilities.common.TestBase;
import org.testng.Assert;
import org.testng.annotations.Test;

public class VerifyMeetingInvalidOrganizer extends TestBase{
	private String roomName = "SM-Room10";
	private String invalidOrganizer = "";
	private String subject = "Subject Test";
	private String errorMessage = "The Test failed because the Meeting accepts invalid organizer";
	
	@Test 
	public void VerifyErrorMeetingInvalidOrganizer(){
		SettingsPage settings = new SettingsPage(driver);
		
		HomePage home = settings
			.waitForSettingPageToLoad()
			.clickRoomItem(roomName)
			.clickAcceptButton();
		
		SchedulerPage scheduler = home
			.clickSchedulerLink();
			
		scheduler
			.setOrganizerTextField(invalidOrganizer)
			.setSubjectTextField(subject)
			.clickCreateButton();
		
		Assert.assertTrue(scheduler.isOrganizerFieldErrorMessagePresent(), errorMessage);
	}
}
