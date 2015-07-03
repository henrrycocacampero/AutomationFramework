package org.roommanager.test.admin.resource;

import org.roommanager.framework.pages.admin.home.HomePage;
import org.roommanager.framework.pages.admin.login.LoginPage;
import org.roommanager.framework.pages.admin.resource.CreateResourcePage;
import org.roommanager.framework.pages.admin.resource.ResourcePage;
import org.roommanager.framework.utilities.api.admin.ResourceApi;
import org.roommanager.framework.utilities.common.PropertiesReader;
import org.roommanager.framework.utilities.common.TestBase;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;
import static org.junit.Assert.assertEquals;

public class VerifyResourceNameUpdate extends TestBase{
	
	 private String resourceName = "Resource01";
     private String resourceDisplayName = "Resource01";
     private String resourceDescription = "Description Resource01";
     private String resourceIcon = "";
	 private String resourceNameUpdate = "NewTestResource";
     
@BeforeTest
 public void BeforeTest(){
    ResourceApi.createResource(resourceName, resourceDisplayName, resourceIcon, resourceDescription);
}

@AfterTest
public void AfterTest(){
	ResourceApi.deleteResourceByName(resourceNameUpdate);
}
	
	
  @Test  (groups = {"ACCEPTANCE"})
	public void verifyResourceNameUpdate() throws Exception {
		String errorMessage = "The resource name is not changed";
		driver.get(PropertiesReader.getLoginUrlAdminModule());
		driver.manage().window().maximize();

		LoginPage login = new LoginPage(driver);
		HomePage homePage = login
				.clickSignInButton();
		ResourcePage resource =  homePage
				.selectResourcesLink();
		CreateResourcePage resourcePage = resource.doubleClickOnResourceFromTable(resourceName);
		resource = resourcePage.enterResourceName(resourceNameUpdate).clickSaveResourceButton();
		String actualResourceName = resource.getResourceNameInTable(resourceNameUpdate);
		assertEquals(errorMessage, actualResourceName, resourceNameUpdate);
  }
  
}