package org.roommanager.test.admin.login;

import org.junit.Assert;
import org.roommanager.framework.pages.admin.login.LoginPage;
import org.roommanager.framework.utilities.common.TestBase;
import org.testng.annotations.Test;

public class VerifyLoginUsernameErrorMessage extends TestBase {

	/*Test case: NeT_AdminLogin - An error message is displayed when the Username 
	 * field and the Password field is leave in blank.*/
	@Test
	public void VerifyErrorMessageLoginUsername(){
		  String expected = "qa202-5.qa202.local" + "\nMicrosoft Exchange Server 2010 SP3";
		  String errorMessage = "The ";  
		  driver.get("http://172.20.208.174:4044/admin/#/login");
		  LoginPage login = new LoginPage(driver);
		  login.setUserName("").setPassword("").clickSignInButton();
		  
		  Assert.assertEquals(expected, "",errorMessage);
	}
}
