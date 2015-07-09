package org.roommanager.test.admin.resource;

import org.roommanager.framework.pages.admin.home.HomePage;
import org.roommanager.framework.pages.admin.login.LoginPage;
import org.roommanager.framework.pages.admin.resource.CreateResourcePage;
import org.roommanager.framework.pages.admin.resource.ResourcePage;
import org.roommanager.framework.utilities.api.admin.ResourceApi;
import org.roommanager.framework.utilities.common.TestBase;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class VerifyEmptyNameIsNotAllowed extends TestBase {

	/** resourceName Name of the resource to be Created*/
	 private String resourceName = "Resource01";
	 
	 /** resourceName Name of the resource to be Created*/
	 private String resourceNameEmpty = "";
	 
	 /** resourceDisplayName Display Name of the resource to be Created*/
    private String resourceDisplayName = "Resource01";
    
    /** resourceDescription Description of the resource to be Created*/
    private String resourceDescription = "Description Resource01";
    
    /** resourceIcon Icon of the resource to be Created*/
    private String resourceIcon = "";
    
    /** 
	 * errorMessage It contains the error message that would appear 
	 * if test case fails
	 */
	private String errorMessage = "The test failed because the error "
			+ "message was not displayed";
	
	
	 /**
	 * Method BeforeTest Create a resource to be updated in the test.
	 */
	@BeforeTest
	 public void BeforeTest(){
	    ResourceApi.createResource(resourceName, resourceDisplayName, resourceIcon, resourceDescription);
	}
	
	/**
	 * Method that execute the test case to verify that Display Name of a 
	 * Resource is updated
	 */
	@Test
    public void verifyEmptyNameIsNotAllowed() {
    	
    	LoginPage login = new LoginPage(driver);
    	
		HomePage home = login.clickSignInButton();
		
		ResourcePage resources = home.selectResourcesLink();
		
		CreateResourcePage create = resources.doubleClickOnResourceFromTable(resourceName)
				.enterResourceName(resourceNameEmpty);
		
		create.clickSaveButtonInvalidData();
		
		Assert.assertTrue(create.isEmptyNameFieldErrorMessagePresent()
				,errorMessage);
    }
	
	@AfterTest
	public void AfterTest(){
		ResourceApi.deleteResourceByName(resourceName);
	}

}
