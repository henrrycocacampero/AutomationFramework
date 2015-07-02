package org.roommanager.test.admin.emailserver;

import org.roommanager.framework.pages.admin.emailserver.EmailServerPage;
import org.roommanager.framework.pages.admin.emailserver.RemoveEmailServerPage;
import org.roommanager.framework.pages.admin.home.HomePage;
import org.roommanager.framework.pages.admin.login.LoginPage;
import org.roommanager.framework.utilities.common.PropertiesReader;
import org.roommanager.framework.utilities.common.TestBase;
import org.testng.Assert;
import org.testng.annotations.Test;

public class RemoveEmailServer extends TestBase {
	
	 @Test
	  public void removeEmailServer(){
		  String errorMessage = "The email server was not removed";
		  driver.get(PropertiesReader.getLoginUrlAdminModule());
		  
		  LoginPage login = new LoginPage(driver);
		  HomePage home = login.clickSignInButton();
		  EmailServerPage emailServer = home.selectEmailServerLink();
		  RemoveEmailServerPage removeEmailServer = emailServer.clickRemoveButton();
		  emailServer = removeEmailServer.clickOnYesButton();
		  Assert.assertTrue(emailServer.existsEmailServerRegistered(), errorMessage);
	 }
	
}
