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
	public static final String usernameTextField = LoginConstant.USERNAME_TEXT_FIELD;
	public static final String passwordTextField = LoginConstant.PASSWORD_TEXT_FIELD;
	
	@FindBy (xpath = loginButton) WebElement login_Button;
	@FindBy (css = usernameTextField) WebElement username_TextField;
	@FindBy (css = passwordTextField) WebElement password_TextField;

	public LoginPage(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public HomePage clickSignInButton(){
		(new WebDriverWait(driver,30)).until(ExpectedConditions.visibilityOf(login_Button));
		login_Button.click();
		return new HomePage(driver);
	}
	public LoginPage setUserName(String username){
		(new WebDriverWait(driver, 60)).until(ExpectedConditions.visibilityOf(username_TextField));
		username_TextField.clear();
		username_TextField.sendKeys(username);
		return this;
	}
	
	public LoginPage setPassword(String password){
		(new WebDriverWait(driver, 60)).until(ExpectedConditions.visibilityOf(password_TextField));
		password_TextField.clear();
		password_TextField.sendKeys(password);
		return this;
	}
}
