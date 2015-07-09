package org.roommanager.test.admin.resource;

import org.roommanager.framework.pages.admin.home.HomePage;
import org.roommanager.framework.pages.admin.login.LoginPage;
import org.roommanager.framework.pages.admin.resource.CreateResourcePage;
import org.roommanager.framework.pages.admin.resource.ResourcePage;
import org.roommanager.framework.utilities.api.admin.ResourceApi;
import org.roommanager.framework.utilities.common.TestBase;
import org.testng.Assert;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class VerifyResourceDisplayNameIsNotEmpty extends TestBase{
	/**
	 * The VerifyResourceResourceDisplayName class contains the test case 
	 * (with pre ): Verify that edit a [Display Name] text_box field does 
	 * not allow empty value.
	 * 
	 * @author Samuel Vargas A.
	 *
	 */ 
	
	 /** resourceName: Name of resource to be created*/
	 private String resourceName = "Resource01";
	
	 /** resourceDisplayName: Display name of resource to be created*/
     private String resourceDisplayName = "Resource01";
     
     /** resourceDescription: Description of resource to be created*/
     private String resourceDescription = "Description Resource01";
     
     /** resourceIcon: Icon of resource to be created*/
     private String resourceIcon = "";      
     
     /** resourceNameUpdate: Name of resource with empty value */
	 private String resourceNameUpdate = " ";
	 
	 /** 
	 * errorMessage: It contains the error message that would appear 
	 * if test case fails
	 */
	 private String errorMessage = "The test failed because the error "
				+ "message was not desplayed";
	 
	 @BeforeTest
	 	public void BeforeTest(){
	    ResourceApi.createResource(resourceName, resourceDisplayName, resourceIcon, resourceDescription);
	 }
	 
	 @Test
		public void VerifyResDisplayNameIsNotEmpty() throws Exception {
			
			LoginPage login = new LoginPage(driver);
			HomePage homePage = login
					.clickSignInButton();
			ResourcePage resource =  homePage
					.selectResourcesLink();
			CreateResourcePage resourcePage = resource.doubleClickOnResourceFromTable(resourceDisplayName);			
			resourcePage.enterResourceDisplayName(resourceNameUpdate);
			resourcePage.clickSaveButtonInvalidData();
						
			Assert.assertTrue(resourcePage.isDisplayNameFieldErrorMessagePresent() 
					,errorMessage);												
	  	}
		
}