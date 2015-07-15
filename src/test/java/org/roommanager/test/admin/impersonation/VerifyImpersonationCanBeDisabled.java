package org.roommanager.test.admin.impersonation;

import org.roommanager.framework.pages.admin.impersonation.ImpersonationPage;
import org.roommanager.framework.pages.admin.login.LoginPage;
import org.roommanager.framework.utilities.api.admin.EmailServerApi;
import org.roommanager.framework.utilities.api.admin.ImpersonationApi;
import org.roommanager.framework.utilities.common.PropertiesReader;
import org.roommanager.framework.utilities.common.TestBase;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class VerifyImpersonationCanBeDisabled extends TestBase{

	/** 
	* expected: It contains the result expected.
	*/
	private String expected = "Impersonation is now disabled.";
	
	/** 
	* errorMessage: It contains the error message that is displayed 
	* if test case fails
	*/
	private String errorMessage = "Impersonate was not disabled";
	
	private String impersonation = "true";
	
	private String name = "Microsoft Exchange Server 2010 SP3";
	
	@Test
	public void verifyImpersonationCanBeDisabled (){
		
		LoginPage login = new LoginPage(driver);
		
		ImpersonationPage impersonation = login.clickSignInButton()
										.selectImpersonationLink();
		
		impersonation.clickImpersonationCheckBox().clickSaveButton();
		
		Assert.assertEquals(impersonation.getConfirmationMessage(), 
				expected,errorMessage);
	}
	
	@BeforeTest
	public void beforeTest(){
		if(EmailServerApi.getEmailServiceId() == null){
			EmailServerApi.createEmailServer(PropertiesReader.getExchangeUserName(),
											 PropertiesReader.getExchangePassWord(),
											 PropertiesReader.getExchangeHostName());
		}
		ImpersonationApi.setImpersonation(impersonation, name);
	}
}
