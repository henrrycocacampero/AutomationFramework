package org.roommanager.test.tablet.search;

import org.testng.Assert;
import org.roommanager.framework.pages.tablet.search.SearchPage;
import org.roommanager.framework.pages.tablet.settings.ConnectionPage;
import org.roommanager.framework.pages.tablet.settings.NavigationPage;
import org.roommanager.framework.utilities.api.admin.EmailServerApi;
import org.roommanager.framework.utilities.api.tablet.MeetingApi;
import org.roommanager.framework.utilities.common.Generator;
import org.roommanager.framework.utilities.common.PropertiesReader;
import org.roommanager.framework.utilities.common.TestBase;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

/**
 * This class contains a test case of Search feature
 * 
 * @author Alejandra Arteaga
 *
 */
public class VerifyMeetingIsDisplayedOnScheduleTable extends TestBase {

	/** username: It represents the name of the Current User */
	private String username = PropertiesReader.getUsername();

	/** organizer: It represents the name of the Meeting's Organizer */
	private String organizer = username;

	/** attendee: It represents the Email of an attendee */
	private String attendee = "\"" + username + "@"
			+ PropertiesReader.getExchangeDomain() + "\"";

	/** conferenceRoom: It represents the name of the Room */
	private String conferenceRoom = "Room02";

	/** subject: It represents the Meeting's Subject */
	private String subject = "Meeting Test";

	/** startTime: It represents the Meeting's Start Time */
	private String startTime = Generator.getStartTime();

	/** endTime: It represents the Meeting's End Time */
	private String endTime = Generator.getEndTime();

	/**
	 * errorMessage: It contains the error message that would appear if test
	 * case fails
	 */
	private String errorMessage = "The test failed because the Meeting "
			+ "was not displayed on the Scredule Tabled";

	/**
	 * This method creates a meeting to a Room
	 */
	@BeforeTest
	public void beforeTest() {
		if (EmailServerApi.getEmailServiceId() == null) {
			EmailServerApi.createEmailServer(
					PropertiesReader.getExchangeUserName(),
					PropertiesReader.getExchangePassWord(),
					PropertiesReader.getExchangeHostName());
		}
		MeetingApi.createMeeting(organizer, subject, startTime, endTime,
				conferenceRoom, attendee);
	}

	/**
	 * Test method: Verify that a meeting of an specific room is displayed on
	 * [Rooms List] schedule table after clicking on the search page
	 */
	@Test
	public void verifyMeetingIsDisplayedOnScheduleTable() {
		ConnectionPage connection = new ConnectionPage(driver);
		connection.enterServiceUrl("http://172.20.208.84:4040/")
				.clickSaveButton();

		NavigationPage navigation = connection.clickNavigationLink()
				.clickDefaultRoomComboBox()
				.selectConferenceRoomByName("Room07").clickSaveButton();

		SearchPage search = navigation.clickOnSearchPageLink();
		Assert.assertEquals(search.getMeetingByRoom(conferenceRoom), subject,
				errorMessage);
	}

	/**
	 * This method deletes the meeting created
	 */
	@AfterTest
	public void Aftertest() {
		MeetingApi.deleteMeetingBySubjectName(conferenceRoom, subject);
	}

}
