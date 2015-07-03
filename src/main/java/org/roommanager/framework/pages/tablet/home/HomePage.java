package org.roommanager.framework.pages.tablet.home;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.roommanager.framework.models.tablet.home.HomeConstant;
import org.roommanager.framework.pages.tablet.scheduler.SchedulerPage;
import org.roommanager.framework.pages.tablet.setting.SettingsPage;

public class HomePage extends PageFactory {
	
	@FindBy (xpath = HomeConstant.SCHEDULER_LINK)
	private WebElement scheduleLink;
	@FindBy (xpath = HomeConstant.SETTINGS_LINK)
	private WebElement settingsLink;
	
	private WebDriver driver;
	
	public HomePage(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	 public  SchedulerPage clickSchedulerLink(){
		    (new WebDriverWait(driver,20)).until(ExpectedConditions.visibilityOf(scheduleLink));
		    scheduleLink.click();
		    return new SchedulerPage(driver);
	    }
	 
	 public  SettingsPage clickSettingsLink(){
		    (new WebDriverWait(driver,20)).until(ExpectedConditions.visibilityOf(settingsLink));
		    settingsLink.click();
		    return new SettingsPage(driver);
	    }

}