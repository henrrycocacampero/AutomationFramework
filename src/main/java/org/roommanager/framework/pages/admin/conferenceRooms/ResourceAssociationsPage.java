package org.roommanager.framework.pages.admin.conferenceRooms;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.roommanager.framework.models.admin.conferencerooms.ResourceAssociationsConstant;
import org.roommanager.framework.pages.admin.resource.CreateResourcePage;
import org.roommanager.framework.utilities.common.LogManager;

public class ResourceAssociationsPage {
	WebDriver driver;
	
	@FindBy (css = ResourceAssociationsConstant.CANCEL_BUTTON) 
	WebElement cancelButton;
	@FindBy (css = ResourceAssociationsConstant.SAVE_BUTTON) 
	WebElement saveButton;
	@FindBy (xpath = ResourceAssociationsConstant.LIST_RESOURCES_AVAILABLE) 
	WebElement resourceAvailableList;
	@FindBy (xpath = ResourceAssociationsConstant.LIST_RESOURCE_ASSOCIATED) 
	WebElement resourceAssociatedList;
	@FindBy (xpath = ResourceAssociationsConstant.DIV_ELEMENT ) 
	WebElement divElement;
	@FindBy (xpath = ResourceAssociationsConstant.NAME_RESOURCE ) 
	WebElement nameResource;
	@FindBy (css = ResourceAssociationsConstant.ASSOCIATE_BUTTON)
	WebElement associateButton;
	@FindBy (css = ResourceAssociationsConstant.DESASSOCIATE_RESOURCE ) 
	WebElement desassociateButton;
	@FindBy (xpath = ResourceAssociationsConstant.QUANTITY_ASSOCIATE_TEXT_FIELD ) 
	WebElement quantityAssociateTextField;
	
	public ResourceAssociationsPage(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	public ResourceAssociationsPage clickCancelButton() {
		(new WebDriverWait(driver, 60)).until(ExpectedConditions.visibilityOf(cancelButton));
		cancelButton.click();
		LogManager.info("Cancel button was clicked");
		return new conferenceRoomPage(driver);
	}
	public ResourceAssociationsPage clickSaveButton() {
		(new WebDriverWait(driver, 60)).until(ExpectedConditions.visibilityOf(saveButton));
		saveButton.click();
		LogManager.info("Save button was clicked");
		return new conferenceRoomPage(driver);
	}
}
