package org.roommanager.test.admin.emailserver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.roommanager.framework.pages.admin.emailserver.EmailServerPage;
import org.roommanager.framework.pages.admin.home.HomePage;
import org.roommanager.framework.pages.admin.login.LoginPage;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class RemoveEmailServer {
	private static WebDriver driver = null;
	
	@BeforeSuite
	  public void beforeSuite() {
		driver= new FirefoxDriver();
	  }
	 @Test
	  public void removeEmailServer(){
		  String errorMessage = "The email server was not removed";
		  driver.get("http://172.20.208.174:4044/admin/#/login");
	
		 
	 }
	  
	  

	  @AfterSuite
	  public void afterSuite() {
		  driver.quit();
	  }
}
