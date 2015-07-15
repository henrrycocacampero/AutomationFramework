package org.roommanager.test.admin.locations;


import org.roommanager.framework.pages.admin.home.HomePage;
import org.roommanager.framework.pages.admin.locations.LocationsPage;
import org.roommanager.framework.pages.admin.login.LoginPage;
import org.roommanager.framework.utilities.common.TestBase;
import org.testng.annotations.Test;

public class VerifyCreateLocation extends TestBase{
	
  @Test
  public void verifyCreateLocation() {
	  LoginPage login = new LoginPage(driver);
	  
	  HomePage home = login.clickSignInButton();
	  
	  LocationsPage locations = home.selectLocationsLink();
	  
	  locations
	  	.doubleClickOnLocation("test")
	  	.setNameTextField("hola")
	  	.setDisplayNameTextField("mundo");
	  
	  //LocationsInfoPage locationInfo = locations.clickAddButton();
  }
}
