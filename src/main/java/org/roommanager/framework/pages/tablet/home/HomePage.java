package org.roommanager.framework.pages.tablet.home;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.roommanager.framework.models.admin.resource.CreateResourceConstant;
import org.roommanager.framework.models.tablet.home.HomeConstant;
import org.roommanager.framework.pages.tablet.scheduler.SchedulerPage;
import org.roommanager.framework.pages.tablet.search.SearchPage;
import org.roommanager.framework.pages.tablet.setting.SettingsPage;

public class HomePage extends PageFactory {
	
	private WebDriver driver;
	
	@FindBy (xpath = HomeConstant.GOTOSETTINGS_BUTTON)
	private WebElement button_gotosettings;
	@FindBy(xpath = HomeConstant.AVALIABLE_BUTTON)
	private WebElement button_available;
	@FindBy(xpath = HomeConstant.ENDOFDAY_BUTTON)
	private WebElement button_endofday;
	@FindBy(xpath = HomeConstant.SCHEDULER_BUTTON)
	private WebElement button_scheduler;
	@FindBy(xpath = HomeConstant.SEARCH_BUTTON)
	private WebElement button_search;


	public HomePage(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
		}
	
	 public SchedulerPage clickSchedulerLink(){
		    (new WebDriverWait(driver,60)).until(ExpectedConditions.visibilityOf(button_scheduler));
		    button_scheduler.click();
		    return new SchedulerPage(driver);
	    }
	 
	 public  SettingsPage clickSettingsLink(){
		    (new WebDriverWait(driver,60)).until(ExpectedConditions.visibilityOf(button_gotosettings));
		    button_gotosettings.click();
		    return new SettingsPage(driver);
	    }

	 public  SchedulerPage clickAvaliableLink(){
		    (new WebDriverWait(driver,60)).until(ExpectedConditions.visibilityOf(button_available));
		    button_available.click();
		    return new SchedulerPage(driver);
	    }
	 
	 public  SchedulerPage clickEndOfDayLink(){
		    (new WebDriverWait(driver,60)).until(ExpectedConditions.visibilityOf(button_endofday));
		    button_endofday.click();
		    return new SchedulerPage(driver);
	    }
	 
	 public  SearchPage clickSearchLink(){
		    (new WebDriverWait(driver,60)).until(ExpectedConditions.visibilityOf(button_search));
		    button_search.click();
		    return new SearchPage(driver);
	    }

}
