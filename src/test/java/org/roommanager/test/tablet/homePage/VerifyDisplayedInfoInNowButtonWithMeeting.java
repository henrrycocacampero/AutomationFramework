package org.roommanager.test.tablet.homePage;

import org.testng.Assert;
import org.roommanager.framework.pages.tablet.home.HomePage;
import org.roommanager.framework.pages.tablet.settings.ConnectionPage;
import org.roommanager.framework.pages.tablet.settings.NavigationPage;
import org.roommanager.framework.utilities.api.tablet.MeetingApi;
import org.roommanager.framework.utilities.common.Generator;
import org.roommanager.framework.utilities.common.PropertiesReader;
import org.roommanager.framework.utilities.common.TestBase;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

/**
 * Verify the meetings information is displayed on Now button when the meeting
 * is being held at this time.
 * 
 * @author Daneiva Gamboa
 *
 */
public class VerifyDisplayedInfoInNowButtonWithMeeting extends TestBase {

	/** username: It represents the name of the Current User */
	private String username = PropertiesReader.getUsername();

	/** organizer: It represents the name of the Meeting's Organizer */
	private String organizer = username;

	/** attendee: It represents the Email of an attendee */
	private String attendee = "\"" + username + "@"
			+ PropertiesReader.getExchangeDomain() + "\"";

	/** conferenceRoom: It represents the name of the Room */
	private String conferenceRoom = "Room01";

	/** startTime: It represents the Meeting's Start Time */
	private String startTime = Generator.getStartTime();

	/** startTime: It represents the eeting's End Time */
	private String endTime = Generator.getEndTime();

	/**
	 * errorMessage: It represents the Error Message that will be displayed if
	 * the test fails
	 */
	private String msgError = "The Test failed because the meeting not found in the Now Button";

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
	 * verifyDisplayedInfoInNowButtonWithMeeting: The meetings information is
	 * displayed on Now button.
	 *
	 */
	@Test
	public void verifyDisplayedInfoInNowButtonWithMeeting() {
    	ConnectionPage connection = new ConnectionPage(driver);
		NavigationPage navigation = connection
									.enterServiceUrl("http://172.20.208.84:4040/")
									.clickSaveButton()
									.clickNavigationLink()
									.clickDefaultRoomComboBox()
									.selectConferenceRoomByName(conferenceRoom)
									.clickSaveButton();
		HomePage homePage = navigation.clickOnHomePageLink();

		isPresentMeeting = homePage.existMeetingInNowButton(subject, organizer);
		/* Assert */
		Assert.assertTrue(isPresentMeeting, msgError);
	}

	/**
	 * beforeTest: This method verify the Meeting was created.
	 */
	@BeforeTest
	public void beforeTest() {
		MeetingApi.deleteMeetingBySubjectName(conferenceRoom, subject);
		MeetingApi.createMeeting(organizer, subject, startTime, endTime,
				conferenceRoom, attendee);
	}

	/**
	 * afterTest: It deletes the Meeting that was created by the test.
	 */
	@AfterTest
	public void afterTest() {
		MeetingApi.deleteMeetingBySubjectName(conferenceRoom, subject);
	}
}
