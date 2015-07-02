package org.roommanager.test.admin.emailserver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.roommanager.framework.pages.admin.emailserver.CreateEmailServerPage;
import org.roommanager.framework.pages.admin.emailserver.EmailServerPage;
import org.roommanager.framework.pages.admin.home.HomePage;
import org.roommanager.framework.pages.admin.login.LoginPage;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class RegisterEmailServer {
	private static WebDriver driver = null;
	
	 @BeforeSuite (groups = {"ACCEPTANCE"})
		public void setUp() throws Exception {
	      driver= new FirefoxDriver();
		}
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
	  @AfterSuite (groups = {"ACCEPTANCE"})
		public void tearDown() throws Exception {
		   driver.quit();
		}
}
