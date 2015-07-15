package org.roommanager.framework.pages.admin.locations;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.roommanager.framework.models.admin.locations.LocationAssociationsConstant;
import org.roommanager.framework.utilities.common.LogManager;

public class LocationAssociationsPage extends LocationsTopMenu {
	private WebDriver  driver;
	@FindBy (xpath = LocationAssociationsConstant.AVAILABLE_ROOMS_LIST) 
	private WebElement availableRoomsList;
	@FindBy (xpath = LocationAssociationsConstant.ASSOCIATED_ROOMS_LIST) 
	private WebElement associatedRoomsList;
	@FindBy (xpath = LocationAssociationsConstant.ASSOCIATED_ROOMS_TABLE) 
	private WebElement associatedRoomsTable;
	@FindBy (xpath = LocationAssociationsConstant.SAVE_BUTTON) 
	private WebElement saveButton;
	
	public LocationAssociationsPage(WebDriver driver){
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public LocationAssociationsPage clickOnAssociateRoomButton(String roomName){
		WebElement room = getRoomByNameFromAvailableRooms(roomName);
		WebElement addRoomButton = room
							   	   .findElement(LocationAssociationsConstant
									   			.ASSOCIATE_ROOM_BUTTON);
		addRoomButton.click();
		LogManager.info("Click on Room: <"+ roomName +"> Associate Button");
		return this;
	}
	
	public LocationAssociationsPage clickOnDissociateRoomButton(String roomName){
		WebElement room = getRoomByNameFromAssociatedRooms(roomName);
		WebElement removeRoomButton = room
							   	   	  .findElement(LocationAssociationsConstant
							   	   			  	   .ASSOCIATE_ROOM_BUTTON);
		removeRoomButton.click();
		LogManager.info("Click on Room: <"+ roomName +"> Dissociate Button");
		return this;
	}
	
	public String getAssociatedRoomName(String roomName){
		WebElement room = getRoomByNameFromAssociatedRooms(roomName);
		String actualRoomName = room.findElement(LocationAssociationsConstant
												 .ROOM_NAME).getText();
		LogManager.info("Room Name: <"+ actualRoomName +"> was retrieved");
		return actualRoomName; 
	}
	
	public boolean isRoomInAssociatedRooms(String roomName){
		WebElement room = getRoomByNameFromAssociatedRooms(roomName);
		boolean isRoomAssociated = room != null;
		if(isRoomAssociated){
			LogManager.info("Room: <"+ roomName +"> was found "
							+ "in Associated Rooms List");
		} else{
			LogManager.info("Room: <"+ roomName +"> wasn't found "
							+ "in Associated Rooms List");
		}
		
		return isRoomAssociated;
	}
	
	public LocationsPage clickOnSaveButton(){
		new WebDriverWait(driver, 30)
			.until(ExpectedConditions.visibilityOf(saveButton));
		saveButton.click();
		LogManager.info("Click on Save Button");
		
		new WebDriverWait(driver, 30)
			.until(ExpectedConditions
				   .invisibilityOfElementLocated(
				   By.xpath(LocationAssociationsConstant.SAVE_BUTTON)));
		return new LocationsPage(driver);
	}
	
	private WebElement getRoomByNameFromAvailableRooms(String roomName){
		new WebDriverWait(driver, 30)
			.until(ExpectedConditions.visibilityOf(availableRoomsList));
		List <WebElement> rooms = availableRoomsList
								  .findElements(LocationAssociationsConstant
										  		.LIST_ITEM_ELEMENT);
		for (WebElement room : rooms) {
			String actualRoomName = room
									.findElement(LocationAssociationsConstant
												 .ROOM_NAME).getText();
			if(actualRoomName.equals(roomName)){
				LogManager.info("Room: <"+ actualRoomName +"> was found "
								+ "in Available Rooms List");
				return room;
			}
		}
		LogManager.info("Room: <"+ roomName +"> wasn't found "
						+ "in Available Rooms List");
		return null;
	}
	
	private WebElement getRoomByNameFromAssociatedRooms(String roomName){
		new WebDriverWait(driver, 30)
			.until(ExpectedConditions.visibilityOf(associatedRoomsTable));
		List <WebElement> rooms = associatedRoomsList
								  .findElements(LocationAssociationsConstant
										  		.LIST_ITEM_ELEMENT);
		for (WebElement room : rooms) {
			String actualRoomName = room
									.findElement(LocationAssociationsConstant
												 .ROOM_NAME).getText();
			if(actualRoomName.equals(roomName)){
				LogManager.info("Room: <"+ actualRoomName +"> was found "
								+ "in Associated Rooms List");
				return room;
			}
		}
		LogManager.info("Room: <"+ roomName +"> wasn't found "
						+ "in Associated Rooms List");
		return null;
	}
}
