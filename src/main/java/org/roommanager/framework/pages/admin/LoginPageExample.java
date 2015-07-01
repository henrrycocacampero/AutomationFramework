package org.roommanager.framework.pages.admin;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.roommanager.framework.models.admin.LoginConstantExample;




/**
 * This page is for login to Room Manager
 * @author Milenca Ventura
 */
public class LoginPageExample {

	private WebDriver driver;
	public final static String btnEnter = LoginConstantExample.BTNLOGIN;
	
	@FindBy (xpath = btnEnter)
    WebElement btnEnterPage;
	public LoginPageExample(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	/**
	 * This method do click on login button
	 * @return HomeAdminPage
	 */
    public HomeAdminPageExample  btn_signIn(){
    	(new WebDriverWait(driver,30)).until(ExpectedConditions.visibilityOf(btnEnterPage));
    	btnEnterPage.click();
	    return new HomeAdminPageExample(driver);
    }
}
