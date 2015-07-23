package org.roommanager.test.tablet.search;

import org.testng.Assert;

import org.roommanager.framework.pages.tablet.search.SearchPage;
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
 * This class contains a test case of Search feature
 * 
 * @author Alejandra Arteaga
 *
 */
public class VerifyMeetingIsDisplayedOnScheduleTable extends TestBase {
	
	/** username: It represents the name of the Current User*/
	private String username = PropertiesReader.getUsername();
	
	/** organizer: It represents the name of the Meeting's Organizer*/
	private String organizer = username;
	
	/** attendee: It represents the Email of an attendee*/
	private String attendee = "\"" + username + "@" + PropertiesReader.getExchangeDomain() + "\"";
	
	/** conferenceRoom: It represents the name of the Room*/
	private String conferenceRoom = "Room02";
	
	/** subject: It represents the Meeting's Subject*/
	private String subject = "Meeting Test";
	
	/** startTime: It represents the Meeting's Start Time*/
	private String startTime = Generator.getStartTime();
	
	/** startTime: It represents the eeting's End Time*/
	private String endTime = Generator.getEndTime();
	
	/**
	 * errorMessage: It contains the error message that would appear if test
	 * case fails
	 */
	private String errorMessage = "The test failed because the Room "
			+ "was not searched by its capacity";

	/**
	 * This method associates a capacity to a Room
	 */
	@BeforeTest
    public void beforeTest() {
    	MeetingApi.createMeeting(organizer, subject, startTime, 
    							 endTime, conferenceRoom, attendee);
    }

	/**
	 * Test method: Verify that a room can be searched by its capacity
	 */
	@Test
	public void verifyMeetingIsDisplayedOnScheduleTable() {
		ConnectionPage connection = new ConnectionPage(driver);
		connection.enterServiceUrl("http://172.20.208.84:4040/")
		.clickSaveButton();
		
		NavigationPage navigation = connection.clickNavigationLink()
									.clickDefaultRoomComboBox()
									.selectConferenceRoomByName("Room07")
									.clickSaveButton();

		SearchPage search = navigation.clickOnSearchPageLink();
		search.clickSearchIcon();
		Assert.assertEquals(search.getMeetingByRoom(conferenceRoom), subject,errorMessage);
	}
	@AfterTest
	public void testTearDown(){
		MeetingApi.deleteMeetingBySubjectName(conferenceRoom, subject);
	}

}
