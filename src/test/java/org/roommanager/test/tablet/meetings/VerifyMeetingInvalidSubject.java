package org.roommanager.test.tablet.meetings;

import org.roommanager.framework.pages.tablet.home.HomePage;
import org.roommanager.framework.pages.tablet.scheduler.SchedulerPage;
import org.roommanager.framework.pages.tablet.setting.SettingsPage;
import org.roommanager.framework.utilities.common.PropertiesReader;
import org.roommanager.framework.utilities.common.TestBase;
import org.testng.Assert;
import org.testng.annotations.Test;

public class VerifyMeetingInvalidSubject extends TestBase{
	private String username = PropertiesReader.getUsername();
	private String roomName = "SM-Room9";
	private String invalidMeetingSubject = "";
	private String errorMessage = "The Test failed because the Meeting accepts invalid data";
	
	@Test 
	public void VerifyMeetingInvalidSubjectErrorMessage(){
		SettingsPage settings = new SettingsPage(driver);
		
		HomePage home = settings
			.waitForSettingPageToLoad()
			.clickRoomItem(roomName)
			.clickAcceptButton();
		
		SchedulerPage scheduler = home
			.clickSchedulerLink();
			
		scheduler
			.setOrganizerTextField(username)
			.setSubjectTextField(invalidMeetingSubject)
			.clickCreateButton();
		
		boolean isErrorMessagePresent = scheduler.isSubjectFieldErrorMessagePresent();
		
		Assert.assertTrue(isErrorMessagePresent, errorMessage);
		
	}
}
