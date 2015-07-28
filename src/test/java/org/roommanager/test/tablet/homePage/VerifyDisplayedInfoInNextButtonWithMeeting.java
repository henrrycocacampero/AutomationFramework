package org.roommanager.test.tablet.homePage;

import org.testng.Assert;
import org.roommanager.framework.pages.tablet.home.HomePage;
import org.roommanager.framework.pages.tablet.settings.ConnectionPage;
import org.roommanager.framework.utilities.api.tablet.MeetingApi;
import org.roommanager.framework.utilities.common.Generator;
import org.roommanager.framework.utilities.common.PropertiesReader;
import org.roommanager.framework.utilities.common.TestBase;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

/**
 * Verify the meetings information is displayed on Next button when the meeting
 * is created after the current date.
 * 
 * @author Daneiva Gamboa
 *
 */
public class VerifyDisplayedInfoInNextButtonWithMeeting extends TestBase {
	/** urlTablet :It represents the URL of module Tablet */
	private String urlTablet = PropertiesReader.getRoomManagerApi();

	/** username: It represents the name of the Current User */
	private String username = PropertiesReader.getUsername();

	/** organizer: It represents the name of the Meeting's Organizer */
	private String organizer = username;

	/** attendee: It represents the Email of an attendee */
	private String attendee = "\"" + username + "@"
			+ PropertiesReader.getExchangeDomain() + "\"";

	/** conferenceRoom: It represents the name of the Room */
	private String conferenceRoom = "Room01";

	/**
	 * afterCurrentTime: It represents time after current time for start meeting
	 */
	int afterCurrentTime = 1;

	/** startTime: It represents the Meeting's Start Time */
	private String startTime = Generator
			.getStartTimeAfterCurrentTime(afterCurrentTime);

	/** startTime: It represents the eeting's End Time */
	private String endTime = Generator
			.getEndTimeLateAfterCurrentTime(afterCurrentTime);

	/**
	 * errorMessage: It represents the Error Message that will be displayed if
	 * the test fails
	 */
	private String msgError = "The Test failed because the meeting not found in the Next Button";

	/** subject: It represents the Meeting's Subject */
	private String subject = "Subject Test";

	/**
	 * isPresentMeeting: Boolean value, that indicates whether or not there is a
	 * meeting is displayed in the Now Button.
	 */
	boolean isPresentMeeting = false;

	/** connection: Name of a new ConnectionPage */
	ConnectionPage connection;

	/**
	 * verifyDisplayedInfoInNextButtonWithMeeting: The meetings information is
	 * displayed on Next button.
	 *
	 */
	@Test
	public void verifyDisplayedInfoInNextButtonWithMeeting() {

		HomePage homePage = connection.clickOnHomePageLink();

		isPresentMeeting = homePage
				.existMeetingInNextButton(subject, organizer);
		/* Assert */
		Assert.assertTrue(isPresentMeeting, msgError);
	}

	/**
	 * beforeTest: This method verify the Meeting was created.
	 */
	@BeforeTest
	public void beforeTest() {
		MeetingApi.deleteAllRoomMeetings(conferenceRoom);
		MeetingApi.createMeeting(organizer, subject, startTime, endTime,
				conferenceRoom, attendee);
		/**
		 * Create a new connection if there is not
		 */
		connection = new ConnectionPage(driver);
		if (connection.isConnectionNotEstablished(urlTablet)) {
			connection.enterServiceUrl(urlTablet).clickSaveButton()
					.clickNavigationLink().clickDefaultRoomComboBox()
					.selectConferenceRoomByName(conferenceRoom)
					.clickSaveButton();
		}
	}

	/**
	 * afterTest: It deletes the Meeting that was created by the test.
	 */
	@AfterTest
	public void afterTest() {
		MeetingApi.deleteMeetingBySubjectName(conferenceRoom, subject);
	}
}
