package org.roommanager.framework.pages.admin;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import org.roommanager.framework.pages.admin.HomeAdminPageExample;

import org.roommanager.framework.pages.admin.ResourcePageExample;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.roommanager.framework.models.admin.HomeConstantExample;

public class HomeAdminPageExample {

	public final static String linkResourceConstant = HomeConstantExample.LINKRESOURCES;
    
    
	@FindBy (linkText = linkResourceConstant)
	WebElement linkResource;
	private WebDriver driver;
	
	public HomeAdminPageExample(WebDriver driver){
			this.driver = driver;
			PageFactory.initElements(driver, this);
		}
   
    /**
	 * This method select the link of the resource
	 * @return ResourcePage
	 */
    public  ResourcePageExample lnk_Resources(){
	    (new WebDriverWait(driver,20)).until(ExpectedConditions.visibilityOf(linkResource));
	    linkResource.click();
	    return new ResourcePageExample(driver);
    }
}