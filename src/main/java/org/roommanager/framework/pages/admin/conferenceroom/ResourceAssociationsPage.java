package org.roommanager.framework.pages.admin.conferenceroom;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.roommanager.framework.models.admin.conferencerooms.ResourceAssociationsConstant;
import org.roommanager.framework.utilities.common.LogManager;

public class ResourceAssociationsPage {
	WebDriver driver;
	WebElement associate;
	@FindBy (css = ResourceAssociationsConstant.CANCEL_BUTTON) 
	WebElement cancelButton;
	@FindBy (css = ResourceAssociationsConstant.SAVE_BUTTON) 
	WebElement saveButton;
	@FindBy (xpath = ResourceAssociationsConstant.CLOSE_BUTTON) 
	WebElement closeButton;
	@FindBy (xpath = ResourceAssociationsConstant.LIST_RESOURCES_AVAILABLE) 
	WebElement resourceAvailableList;
	@FindBy (xpath = ResourceAssociationsConstant.LIST_RESOURCE_ASSOCIATED) 
	WebElement resourceAssociatedList;
	@FindBy (xpath = ResourceAssociationsConstant.DIV_ELEMENT ) 
	WebElement divElement;
	@FindBy (xpath = ResourceAssociationsConstant.NAME_RESOURCE ) 
	WebElement nameResource;
	@FindBy (xpath = ResourceAssociationsConstant.ASSOCIATE_BUTTON)
	WebElement associateButton;
	@FindBy (css = ResourceAssociationsConstant.DESASSOCIATE_RESOURCE ) 
	WebElement desassociateButton;
	@FindBy (xpath = ResourceAssociationsConstant.QUANTITY_ASSOCIATE_TEXT_FIELD ) 
	WebElement quantityAssociateTextField;
	
	public ResourceAssociationsPage(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public ConferenceRoomPage clickCancelButton() {
		(new WebDriverWait(driver, 60)).until(ExpectedConditions.visibilityOf(cancelButton));
		cancelButton.click();
		LogManager.info("Cancel button was clicked");
		return new ConferenceRoomPage(driver);
	}
	public ConferenceRoomPage clickCloseButton() {
		(new WebDriverWait(driver, 60)).until(ExpectedConditions.visibilityOf(closeButton));
		closeButton.click();
		LogManager.info("Close button was clicked");
		return new ConferenceRoomPage(driver);
	}
	
	public ConferenceRoomPage clickSaveButton() {
		(new WebDriverWait(driver, 60)).until(ExpectedConditions.visibilityOf(saveButton));
		saveButton.click();
		LogManager.info("Save button was clicked");
		return new ConferenceRoomPage(driver);
	}
	
	public ResourceAssociationsPage clickOnAddResourceButton(String resourceName) {
		WebElement resourceAssociationButton = getResourceByName(resourceName);
		resourceAssociationButton.click();
		LogManager.info("The Resource: <" + resourceName+ "> was associated to the room");
		return this;
	}
	
	private WebElement getResourceByName(String resourceName) {
		(new WebDriverWait(driver, 60))
			.until(ExpectedConditions.visibilityOf(resourceAvailableList));
		List<WebElement> resourcesTable = resourceAvailableList
				.findElements(By.xpath(ResourceAssociationsConstant.DIV_ELEMENT));
		for (WebElement resource : resourcesTable){
			String resourceItemName = resource.findElement(
					By.xpath(ResourceAssociationsConstant.NAME_RESOURCE)).getText();
			if (resourceItemName.equals(resourceName)) {
				LogManager.info("Resource: <" + resourceItemName+ "> was retrieved from Resources Table");
				int position = resourcesTable.indexOf(resource)+ 1;

				String associationButtonLocator = ResourceAssociationsConstant.LIST_RESOURCES_AVAILABLE+"/"+
						ResourceAssociationsConstant.DIV_ELEMENT + "[" + position +"]/" +
						ResourceAssociationsConstant.ASSOCIATE_BUTTON;
				LogManager.info("Resource: <" + resourceName + "> in the Available table was found");
				return driver.findElement(By.xpath(associationButtonLocator));	
			}
		}
		LogManager.info("Resource: <" + resourceName + "> in the Available table wasn't found");
		return null;
	}
	public ResourceAssociationsPage clickOnDesassociatedResourceButton(String resourceName) {
		WebElement resourceDesassociationButton = getResourceAssociatedByName(resourceName);
		resourceDesassociationButton.click();
		LogManager.info("Resource: <" + resourceName + "> was dissasociate from the room");
		return this;
	}
	
	private WebElement getResourceAssociatedByName(String resourceName) {
		(new WebDriverWait(driver, 60))
			.until(ExpectedConditions.visibilityOf(resourceAssociatedList));
		List<WebElement> resourcesTable = resourceAssociatedList
				.findElements(By.xpath(ResourceAssociationsConstant.DIV_ELEMENT));
		for (WebElement resource : resourcesTable){
			String resourceItemName = resource.findElement(
					By.xpath(ResourceAssociationsConstant.NAME_RESOURCE)).getText();
			if (resourceItemName.equals(resourceName)) {
				LogManager.info("Resource: <" + resourceItemName+ "> was retrieved from Resources Table");
				int position = resourcesTable.indexOf(resource)+ 1;

				String desassociationButtonLocator = ResourceAssociationsConstant.LIST_RESOURCE_ASSOCIATED+"/"+
						ResourceAssociationsConstant.DIV_ELEMENT + "[" + position +"]/" +
						ResourceAssociationsConstant.DESASSOCIATE_RESOURCE;
				LogManager.info("Resource: <" + resourceName + "> in the Associated table was found");
				return driver.findElement(By.xpath(desassociationButtonLocator));	
			}
		}
		LogManager.info("Resource: <" + resourceName + "> in the Associated table wasn't found");
		return null;
	}
	public String getResourceAssociatedByNameInTable(String resourceName) {
		(new WebDriverWait(driver, 60))
			.until(ExpectedConditions.visibilityOf(resourceAssociatedList));
		List<WebElement> resourcesTable = resourceAssociatedList
				.findElements(By.xpath(ResourceAssociationsConstant.DIV_ELEMENT));
		for (WebElement resource : resourcesTable){
			String resourceItemName = resource.findElement(
					By.xpath(ResourceAssociationsConstant.NAME_RESOURCE)).getText();
			if (resourceItemName.equals(resourceName)) {
				LogManager.info("Resource: <" + resourceItemName+ "> was retrieved from Resources Table");
				return resourceName;	
			}
		}
		LogManager.info("Resource: <" + resourceName + "> wasn't found");
		return null;
	}
	public boolean verifyElementDoesNotExistOnAssociated(String resourceName) {
		WebElement resource = getResourceAssociatedByName(resourceName);	
		LogManager.info("Resource: <" + resourceName + "> was disassociated succefully");
		return resource == null ? true : false;

	}
	
	/**
	 * isResourceAvailableOnTheRoom: It verifies if the Resource is Available
	 * in the Room.
	 * @return boolean
	 */
	public boolean isResourceAvailableOnTheRoom(String resourceName){
		return getResourceByName(resourceName) == null? false : true;
	}
}

