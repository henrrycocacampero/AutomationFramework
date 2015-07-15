package org.roommanager.framework.pages.admin.locations;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.roommanager.framework.models.admin.locations.LocationsConstant;
import org.roommanager.framework.utilities.common.LogManager;

public class LocationsPage extends PageFactory{
	
	WebDriver  driver;
	@FindBy (xpath = LocationsConstant.ADD_BUTTON) 
	private WebElement addButton;
	@FindBy (xpath = LocationsConstant.REMOVE_BUTTON) 
	private WebElement removeButton;
	@FindBy (xpath = LocationsConstant.LOCATIONS_TABLE) 
	private WebElement locationsTable;
	
	public LocationsPage(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public LocationsInfoPage clickAddButton(){
		(new WebDriverWait(driver,30))
			.until(ExpectedConditions.visibilityOf(addButton));
		addButton.click();
		return new LocationsInfoPage(driver);
	}
	
	public RemoveLocationsPage clickRemoveButton(){
		(new WebDriverWait(driver,30))
			.until(ExpectedConditions.elementToBeClickable(removeButton));
		removeButton.click();
		return new RemoveLocationsPage(driver);
	}
	
	public LocationsPage checkLocation(String name){
		WebElement locationCheckBox = getLocationByName(name)
				.findElement(By.xpath(LocationsConstant.LOCATION_CHECK_BOX));
		locationCheckBox.click();
		return this;
	}
	
	private WebElement getLocationByName(String name){
		(new WebDriverWait(driver,30))
			.until(ExpectedConditions.visibilityOf(locationsTable));
		List<WebElement> locations = locationsTable.findElements(By.xpath("div"));
		for (WebElement location : locations) {
			String locationName = location.findElement(By
					.xpath(LocationsConstant.LOCATION_NAME)).getText();
			if(locationName.equals(name)){
				return location;
			}
		}
		return null;
	}
	
	public LocationsInfoPage doubleClickOnLocation(String name){
		WebElement location = getLocationByName(name).findElement(By
				.xpath(LocationsConstant.LOCATION_DISPLAY_NAME));
		(new Actions(driver)).doubleClick(location).perform();
		LogManager.info("Double Click on Location: <" + 
				name + "> from Locations Table");
		return new LocationsInfoPage(driver);
	} 
}
