package org.roommanager.test.admin.resource;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.roommanager.framework.pages.admin.home.HomePage;
import org.roommanager.framework.pages.admin.login.LoginPage;
import org.roommanager.framework.pages.admin.resource.CreateResourcePage;
import org.roommanager.framework.pages.admin.resource.ResourcePage;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class CreateResourceTest {
	private WebDriver driver;
	private String resourceName = "ResourcePablo";
	private String resourceDisplayName = "ResourcePablo";
	private String resourceDescription = "Description ResourcePablo";
	private String errorMessage = "The test failed because the created Resource was not found";

	@BeforeSuite
	public void setUp() throws Exception {
		driver= new FirefoxDriver();
	}

	@Test
	public void verifyAResourceIsCreated() throws Exception {
		driver.get("http://172.20.208.174:4044/admin/#/login");
		driver.manage().window().maximize();
		LoginPage login = new LoginPage(driver);
		HomePage adminHome = login.clickSignInButton();
		ResourcePage resources = adminHome.selectResourcesLink();
		CreateResourcePage createResource = resources.clickAddResourceButton()
				.enterResourceName(resourceName)
				.enterResourceDisplayName(resourceDisplayName)
				.enterResourceDescription(resourceDescription);
		resources = createResource.clickSaveResourceButton();
		String actualResourceName = resources
				.getResourceNameInTable(resourceName);
		Assert.assertEquals(errorMessage, actualResourceName, resourceName);
	}

	@AfterTest
	public void testTearDown() {
		//HttpRequest.deleteResourceByName(resourceName);
	}

	@AfterSuite
	public void suiteTearDown() {
		driver.quit();
	}
}
