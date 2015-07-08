package org.roommanager.test.admin.conferencerooms;

import org.roommanager.framework.pages.admin.conferenceroom.ConferenceRoomPage;
import org.roommanager.framework.pages.admin.conferenceroom.ResourceAssociationsPage;
import org.roommanager.framework.pages.admin.home.HomePage;
import org.roommanager.framework.pages.admin.login.LoginPage;
import org.roommanager.framework.utilities.api.admin.ResourceApi;
import org.roommanager.framework.utilities.common.PropertiesReader;
import org.roommanager.framework.utilities.common.TestBase;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class VerifyAssociateRoom extends TestBase {
	 private String resourceName = "Resource01";
     private String resourceDisplayName = "Resource01";
     private String resourceDescription = "Description Resource01";
     private String resourceIcon = "";
	 
	 @BeforeTest
	  public void beforeTest(){
		ResourceApi.createResource(resourceName, resourceDisplayName, resourceIcon, resourceDescription);
	  }
	  @Test
	  public void registerEmailServer() {
		  String expected = PropertiesReader.getExchangeHostName()+"\n" + PropertiesReader.getExchangeServerDescription();
		  String errorMessage = "The email server was not registered";  
		  LoginPage login = new LoginPage(driver);
		  HomePage home = login.clickSignInButton();
		 
		  ConferenceRoomPage conferenceRoom = home.selectConferenceRoomsLink();	

		  ResourceAssociationsPage association = conferenceRoom.doubleClickOnRoom("SM-Room1").clickOnResourceAssociations("SM-Room1");
		  association.clickOnAddResourceButton(resourceDisplayName).clickOnDesassociatedResourceButton(resourceDisplayName)
		  .clickSaveButton();
		  
		  //Assert.assertEquals(emailServer.getEmailServer(), expected, errorMessage);
	  }
	  @AfterTest
	  public void afterTest(){
		 //ResourceApi.deleteResourceByName(resourceName);
	  }
}
