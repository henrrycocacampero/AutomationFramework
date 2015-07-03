package org.roommanager.test.admin.resource;

import org.apache.log4j.Logger;
import org.roommanager.framework.pages.admin.home.HomePage;
import org.roommanager.framework.pages.admin.login.LoginPage;
import org.roommanager.framework.pages.admin.resource.CreateResourcePage;
import org.roommanager.framework.pages.admin.resource.ResourceInfoPage;
import org.roommanager.framework.pages.admin.resource.ResourcePage;
import org.roommanager.framework.utilities.api.admin.ResourceApi;
import org.roommanager.framework.utilities.common.PropertiesReader;
import org.roommanager.framework.utilities.common.TestBase;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;
import org.roommanager.framework.pages.admin.resource.*;

public class VerifyResourceNameUpdate extends TestBase{
	
	 private String resourceName = "Resource01";
     private String resourceDisplayName = "Resource01";
     private String resourceDescription = "Description Resource01";
     private String resourceIcon = "";
	 private String ResourceNameUpdate = "NewTestResource";
     
@BeforeTest
 public void BeforeTest(){
    ResourceApi.createResource(resourceName, resourceDisplayName, resourceIcon, resourceDescription);
}

@AfterTest
public void AfterTest(){
	ResourceApi.deleteResourceByName(ResourceNameUpdate);
}
	
	
  @Test  (groups = {"ACCEPTANCE"})
	public void verifyResourceNameUpdate() throws Exception {
		String errormessage = "The resource name is not changed";
		driver.get(PropertiesReader.getLoginUrlAdminModule());
		driver.manage().window().maximize();

		LoginPage login = new LoginPage(driver);
		HomePage homePage = login
				.clickSignInButton();
		ResourcePage resource =  homePage
				.selectResourcesLink()
				;
		resource.getResourceNameInTable(resourceName);
		resource.clickOnResourceFromTable(resourceName);
		//CreateResourcePage resourceNew = 
		//System.out.println(resourceNew);
		
		
				
				
		
		
		
		
		/*
		 * 		

		AddResourcesPage resourceNewRes = resourceNew.
				doubleClickTableElement();
		AddResourcesPage resourceNewResEd = resourceNewRes
				.enterResourceName(newResourceName)
				.enterResourceDisplayName(newResourceDisplayName)
				.enterResourceDescription(newResourceDescription);
		resources = resourceNewResEd
				.clickSaveResourceButton();
		 */
		
  }
  
  /*Ya no se debe incluir BeforeSuite ni AfterSuite*/

}