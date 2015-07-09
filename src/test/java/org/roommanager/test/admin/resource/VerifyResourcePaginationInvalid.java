package org.roommanager.test.admin.resource;

import org.roommanager.framework.pages.admin.home.HomePage;
import org.roommanager.framework.pages.admin.login.LoginPage;
import org.roommanager.framework.pages.admin.resource.ResourcePage;
import org.roommanager.framework.utilities.common.TestBase;
import org.testng.annotations.Test;

public class VerifyResourcePaginationInvalid extends TestBase{
	
	private String page = "abc";
	
    @Test
    public void verifyResourcePaginationInvalid() {

		LoginPage login = new LoginPage(driver);
		
		HomePage homePage = login.clickSignInButton();
		
		ResourcePage resource =  homePage.selectResourcesLink();
		
		resource.setPaginationTextField(page);
		
		String value = resource.getPaginationTextField();
		//Assert.assertNotEquals(actual1, actual2);
		
		
		System.out.println("valor " + value);
    }
}
