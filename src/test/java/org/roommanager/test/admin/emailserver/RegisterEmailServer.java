package org.roommanager.test.admin.emailserver;

import org.roommanager.framework.pages.admin.emailserver.CreateEmailServerPage;
import org.roommanager.framework.pages.admin.emailserver.EmailServerPage;
import org.roommanager.framework.pages.admin.home.HomePage;
import org.roommanager.framework.pages.admin.login.LoginPage;
import org.roommanager.framework.utilities.common.TestBase;
import org.testng.Assert;
import org.testng.annotations.Test;

public class RegisterEmailServer extends TestBase{

	  @Test
	  public void registerEmailServer() {
		  String expected = "qa202-5.qa202.local" + "\nMicrosoft Exchange Server 2010 SP3";
		  String errorMessage = "The email server was not registered";  
		  driver.get("http://172.20.208.174:4044/admin/#/login");
		  
		  LoginPage login = new LoginPage(driver);
		  HomePage home = login.clickSignInButton();	
		  EmailServerPage emailServer = home.selectEmailServerLink();	
		  CreateEmailServerPage addServer = emailServer.clickAddButton();
		  addServer.setHostname("qa202-5.qa202.local");
		  addServer.setUsername("Administrator");
		  addServer.setPassword("Monitor2020");
		  emailServer = addServer.clickSaveButton();
		  Assert.assertEquals(emailServer.getEmailServer(), expected, errorMessage);
	  }
}
