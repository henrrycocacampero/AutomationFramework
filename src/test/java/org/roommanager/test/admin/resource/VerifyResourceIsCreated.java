package org.roommanager.test.admin.resource;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.roommanager.framework.pages.admin.home.HomePage;
import org.roommanager.framework.pages.admin.login.LoginPage;
import org.roommanager.framework.pages.admin.resource.CreateResourcePage;
import org.roommanager.framework.pages.admin.resource.ResourcePage;
import org.roommanager.framework.utilities.api.admin.ResourceApi;
import org.roommanager.framework.utilities.common.PropertiesReader;
import org.roommanager.framework.utilities.common.TestBase;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class VerifyResourceIsCreated extends TestBase {
	private String resourceName = "TestResource";
	private String resourceDisplayName = "TestResource";
	private String resourceDescription = "Description TestResource";
	private String errorMessage = "The test failed because the created Resource was not found";

	@Test
	public void verifyAResourceIsCreated() throws Exception {
		driver.get(PropertiesReader.getLoginUrlAdminModule());
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
		Assert.assertEquals( actualResourceName, resourceName,errorMessage);
	}

	@AfterTest
	public void testTearDown() {
		ResourceApi.deleteResourceByName(resourceName);
	}
}
