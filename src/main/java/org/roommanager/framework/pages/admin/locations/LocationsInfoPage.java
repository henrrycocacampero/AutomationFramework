package org.roommanager.framework.pages.admin.locations;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class LocationsInfoPage extends PageFactory{
	WebDriver driver;
	
	public LocationsInfoPage(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
}
