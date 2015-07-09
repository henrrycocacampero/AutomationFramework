package org.roommanager.test.admin.roominfo.outoforder;

import org.roommanager.framework.pages.admin.conferenceroom.ConferenceRoomPage;
import org.roommanager.framework.pages.admin.conferenceroom.OutOfOrderPage;
import org.roommanager.framework.pages.admin.home.HomePage;
import org.roommanager.framework.pages.admin.login.LoginPage;
import org.roommanager.framework.utilities.api.admin.RoomApi;
import org.roommanager.framework.utilities.common.LogManager;
import org.roommanager.framework.utilities.common.TestBase;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

/**
 * The VerifyOutOfOrderIsCreated class contains the test case: 
 * Create a Out Of Order in the Room<Value> when the values of 
 * "From"(date and hour) field is less than "To"(date and hour),
 * to be saved  
 * @author Daneiva Gamboa
 *
 */
public class VerifyOutOfOrderIsCreated extends TestBase{
	
	/** isPresentOutOfOrder: Boolean value, that indicates whether or not there is an 
	* Out-Of-Order is created 
	**/
	boolean isPresentOutOfOrder= false;
	  
	/** 
    * errorMessage: It contains the error message that would appear 
	* if test case fails
	*/	  
	String msgError= "The Out Of Order was not created!";
    	  
	/** roomSelected: Name of room to be selected for create a Out-Of-Order*/	  
    String roomSelected = "SM-Room6";
        
    /**
	* This method performs the test case: Verify if is possible create 
	* a Out-Of-Order with correct values("From" field is less than "To").
	*/
    @Test
    public void verifyOutOfOrderIsCreated() {	    
	LoginPage login = new LoginPage(driver);
	HomePage adminHome = login.clickSignInButton();
		
    ConferenceRoomPage conferenceRoom = adminHome.selectConferenceRoomsLink();
	  
	OutOfOrderPage outOfOrderPage = conferenceRoom.doubleClickOnRoom(roomSelected)
			.clickOnOutOfOrderPlanning();
	outOfOrderPage.setRandomTitle();
	outOfOrderPage.setDescription();
	outOfOrderPage.clickSaveButtonOutOfOrder();
	isPresentOutOfOrder = outOfOrderPage.existOutOfOrder(roomSelected);
	/*Asserts*/	  
	Assert.assertTrue( isPresentOutOfOrder,msgError );
	LogManager.info("Assert Test Create Out Of Order Planing was created:"+ isPresentOutOfOrder);
	LogManager.info(" Test Create Out Of Order");	  
  }
    /**
     * beforeTest: This method creates a Out-Of-Order that will be used 
     * in the test case.
     */
    @BeforeTest
    public void beforeTest(){
    	 
    }
    
    /**
     * afterTest: This method deletes the created Out-Of-Order in the 
     * beforeTest method.
     */
    @AfterTest
	public void afterTest(){
    	RoomApi.deleteAllOutOfOrders(roomSelected);
	}

}
