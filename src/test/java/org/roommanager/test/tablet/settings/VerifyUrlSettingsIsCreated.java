package org.roommanager.test.tablet.settings;

import org.roommanager.framework.pages.tablet.settings.ConnectionPage;
import org.roommanager.framework.utilities.common.TestBase;
import org.testng.annotations.Test;

public class VerifyUrlSettingsIsCreated extends TestBase {
	
	@Test 
	public void VerifyAMeetingIsCreated(){
		ConnectionPage connection = new ConnectionPage(driver);
		connection.enterServiceUrl("http://172.20.208.84:4040/")
		.clickSaveButton()
		.clickNavigationLink()
		.clickDefaultRoomComboBox()
		.selectConferenceRoomByName("Room01")
		.clickSaveButton()
		.clickOnSchedulerPageLink();
	}

}
