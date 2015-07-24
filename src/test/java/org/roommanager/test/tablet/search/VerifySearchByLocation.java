package org.roommanager.test.tablet.search;

import org.roommanager.framework.pages.tablet.search.SearchPage;
import org.roommanager.framework.pages.tablet.settings.ConnectionPage;
import org.roommanager.framework.pages.tablet.settings.NavigationPage;
import org.roommanager.framework.utilities.api.admin.LocationApi;
import org.roommanager.framework.utilities.common.TestBase;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class VerifySearchByLocation extends TestBase{
	/** roomName: Name of room to be used*/
	private String roomName = "Room02";
	
	/** locationName: represents the location's name to be created*/
	private String locationName = "Location Name";
	
	/** locationDisplayName: represents the location's display name to be created*/
	private String locationDisplayName = "Location Display Name";
	
	/** locationDescription: represents the location's description to be created*/
	private String locationDescription = "Location Description";
	
	/** 
	 * errorMessage: It contains the error message that would appear 
	 * if test case fails
	 */
	private String errorMessage = "The Test failed because the Location "
								  + "couldn't be dissociated from the specified Room";
	
	/**
     * This method creates a Location and associates it to a Room
     */
    @BeforeTest
    public void beforeTest(){
    	LocationApi.createLocation(locationName, locationDisplayName, 
    							   locationDescription);
    	LocationApi.associateLocation(locationName, roomName);
    }
    
    /**
	 * Test method: Verify that an specific room is displayed on 
	 * [Rooms List] schedule table using the [Location] filter.
	 */
    @Test
	public void verifySearchByLocation() throws InterruptedException{
    	ConnectionPage connection = new ConnectionPage(driver);
		connection.enterServiceUrl("http://172.20.208.84:4040/")
		.clickSaveButton();
		
		NavigationPage navigation = connection.clickNavigationLink()
									.clickDefaultRoomComboBox()
									.selectConferenceRoomByName(roomName)
									.clickSaveButton();
		
		SearchPage search = navigation.clickOnSearchPageLink();
		
		search.clickAdvancedButton().selectLocation()
		.clickOnSelectLocation(locationDisplayName);
		
		Assert.assertTrue(search.isRoomPresent(roomName), errorMessage);
	}
    
    /**
     * This method removes the Location that was created by the test
     */
    @AfterTest
    public void afterTest(){
    	LocationApi.deleteLocationByName(locationName);
    }
}
