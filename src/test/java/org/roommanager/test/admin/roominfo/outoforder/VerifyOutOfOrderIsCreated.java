package org.roommanager.test.admin.roominfo.outoforder;

import org.roommanager.framework.pages.admin.conferenceroom.ConferenceRoomPage;
import org.roommanager.framework.pages.admin.conferencerooms.OutOfOrderPage;
import org.roommanager.framework.pages.admin.home.HomePage;
import org.roommanager.framework.pages.admin.login.LoginPage;
import org.roommanager.framework.utilities.common.LogManager;
import org.roommanager.framework.utilities.common.TestBase;
import org.testng.Assert;
import org.testng.annotations.Test;

public class VerifyOutOfOrderIsCreated extends TestBase{
	
  @Test
  public void verifyOutOfOrderIsCreated() {
	  
	  boolean isPresentOutOfOrder= false;
	  String msgError= "The Out Of Order was not created!";
      String roomSelected = "SM-Room15";
	    
	  LoginPage login = new LoginPage(driver);
	  HomePage adminHome = login.clickSignInButton();
		
      ConferenceRoomPage conferenceRoom = adminHome.selectConferenceRoomsLink();
	  
	  OutOfOrderPage outOfOrderPage = conferenceRoom.doubleClickOnRoom(roomSelected ).clickOnOutOfOrderPlanning(roomSelected);
	  outOfOrderPage.setRandomTitle();
	  outOfOrderPage.setDescription();
	  outOfOrderPage.clickSaveButtonOutOfOrder();
	  isPresentOutOfOrder = outOfOrderPage.existOutOfOrder(roomSelected);
	  /*Asserts*/	  
	  Assert.assertTrue( isPresentOutOfOrder,msgError );
	  LogManager.info("Assert Test Create Out Of Order Planing was created:"+ isPresentOutOfOrder);
	 LogManager.info(" Test Create Out Of Order");	  
  }
}
