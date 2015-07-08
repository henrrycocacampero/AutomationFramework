package org.roommanager.test.admin.conferencerooms;

import org.roommanager.framework.pages.admin.conferenceroom.ConferenceRoomPage;
import org.roommanager.framework.pages.admin.conferenceroom.ResourceAssociationsPage;
import org.roommanager.framework.pages.admin.home.HomePage;
import org.roommanager.framework.pages.admin.login.LoginPage;
import org.roommanager.framework.utilities.api.admin.ResourceApi;
import org.roommanager.framework.utilities.common.TestBase;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class VerifyAssociateResourceToRoom extends TestBase {
	 private String resourceName = "Resource01";
     private String resourceDisplayName = "Resource01";
     private String resourceDescription = "Description Resource01";
     private String resourceIcon = "";
     private String roomName = "SM-Room1";
	 
	 @BeforeTest
	  public void beforeTest(){
		ResourceApi.createResource(resourceName, resourceDisplayName, resourceIcon, resourceDescription);
	  }
	  @Test
	  public void associateResourceToRoom() {
		  String errorMessage = "The Resource"+resourceName+"is not associated";  
		  LoginPage login = new LoginPage(driver);
		  HomePage home = login.clickSignInButton(); 
		  ConferenceRoomPage conferenceRoom = home.selectConferenceRoomsLink();	
		  ResourceAssociationsPage association = conferenceRoom.doubleClickOnRoom(roomName).
				  clickOnResourceAssociations(roomName);
		  association.clickOnAddResourceButton(resourceDisplayName).clickSaveButton();
		  conferenceRoom.doubleClickOnRoom(roomName).
		  clickOnResourceAssociations(roomName);
		  String associatedResourceName = association
					.getResourceAssociatedByNameInTable(resourceDisplayName);
		  Assert.assertEquals( associatedResourceName, resourceDisplayName,errorMessage);
		  association.clickCancelButton();
	  }
	  @AfterTest
	  public void afterTest(){
		 ResourceApi.deleteResourceByName(resourceName);
	  }
}
