package org.roommanager.test.admin;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;


import org.roommanager.framework.pages.admin.HomeAdminPageExample;
import org.roommanager.framework.pages.admin.LoginPageExample;
import org.roommanager.framework.pages.admin.ResourceCreatePageExample;
import org.roommanager.framework.pages.admin.ResourcePageExample;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;


public class CreateResourceTestExample {
	/*Test Case:
	   * Verify that is possible create a resource*/
	private static WebDriver driver = null;
  Logger logger=Logger.getLogger("test02CreateResource");
  @BeforeSuite (groups = {"ACCEPTANCE"})
	public void setUp() throws Exception {
      driver= new FirefoxDriver();
	}
  @Test  (groups = {"ACCEPTANCE"})
	public void CreateResource() throws Exception {
		String nameResource = "Resource1";
		String message = "The resource cannot be created";
		driver.get("http://172.20.208.174:4044/admin/#/login");
		HomeAdminPageExample home = new HomeAdminPageExample(driver);
		LoginPageExample login = new LoginPageExample(driver);
		login.btn_signIn();
		home.lnk_Resources();
		ResourcePageExample resourcePage = new ResourcePageExample(driver);
		resourcePage.btn_Add();
		ResourceCreatePageExample resourceAddPage = new ResourceCreatePageExample(driver);
		resourceAddPage.txt_name().txt_displayName().btn_Save();
		String resources = resourcePage.getResourceName(nameResource);
		Assert.assertEquals(resources, nameResource, message);
		
  }
	
  @AfterSuite (groups = {"ACCEPTANCE"})
	public void tearDown() throws Exception {
	   driver.quit();
	}
}