package org.roommanager.framework.pages.admin.login;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.roommanager.framework.models.admin.login.LoginConstant;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.roommanager.framework.pages.admin.home.HomePage;

public class LoginPage {

	WebDriver  driver;
	public static final String loginButton = LoginConstant.LOGIN_BUTTON;
	
	@FindBy (xpath = loginButton) WebElement login_Button;
	
	public LoginPage(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public HomePage clickSignInButton(){
		(new WebDriverWait(driver,30)).until(ExpectedConditions.visibilityOf(login_Button));
		login_Button.click();
		return new HomePage(driver);
	}
}
