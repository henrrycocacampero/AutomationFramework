package org.roommanager.framework.pages.admin.conferenceroom;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.roommanager.framework.models.admin.conferencerooms.ConferenceRoomConstant;
import org.roommanager.framework.utilities.common.LogManager;

public class ConferenceRoomPage {
	@FindBy (xpath = ConferenceRoomConstant.LIST_ROOM) 
	private WebElement roomsList;
	@FindBy (xpath = ConferenceRoomConstant.LIST_RESOURCE) 
	private WebElement resourcesList;
	@FindBy (xpath = ConferenceRoomConstant.NEXT_PAGE_BUTTON) 
	private WebElement nextPageButton;
	@FindBy (xpath  = ConferenceRoomConstant.PAGE_INDEX) 
	private WebElement pageNumber;
	@FindBy (xpath  = ConferenceRoomConstant.NUMBER_OF_PAGE) 
	private WebElement numberOfPages;

	private By divElementLocator = ConferenceRoomConstant.DIV_ELEMENT;
	private By roomNameLocator = ConferenceRoomConstant.ROOM_NAME;
	
	private WebDriver driver;
	
	public ConferenceRoomPage(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public RoomInfoPage doubleClickOnRoom(String roomName){
		WebElement room = getRoomFromAllPagesByName(roomName, getRoomsTableNumberOfPages());
		Actions action = new Actions(driver);
		action.doubleClick(room);
		action.perform();
		LogManager.info("Double Click on Resource: <"+roomName+"> from Resources Table");
		return new RoomInfoPage(driver);
	}
	
	private WebElement getRoomByName(String roomName){
		(new WebDriverWait(driver, 60))
			.until(ExpectedConditions.visibilityOf(roomsList));
		List <WebElement> rooms = roomsList.findElements(divElementLocator);
		
		for (WebElement room : rooms) {
			String roomItemName = room.findElement(roomNameLocator).getText();
			if(roomItemName.equals(roomName)){
				LogManager.info("Room: <"+ roomItemName +"> was found on the Available Rooms List");
				return room;
			}
		}
		LogManager.info("Room: <"+ roomName +"> wasn't found on the Available Rooms List");
		return null;
	}
	
	private WebElement getRoomFromAllPagesByName(String resourceName, int numberOfPages){
		WebElement resource = null;
		for(int index = 1; index <= numberOfPages; index++){
			 resource = getRoomByName(resourceName);
			 if(resource != null){
				 LogManager.info("Resource: <" +resourceName+ "> was found in page:" + index);
				 return resource;
			 }
			 clickNextPageButton(index + 1, numberOfPages);
			 LogManager.info("Searching for room in page: " + index);
		}
		return resource;
	}
	
	private void clickNextPageButton(int actualPage, int numberOfPages){
		(new WebDriverWait(driver, 60))
			.until(ExpectedConditions.visibilityOf(nextPageButton));
		nextPageButton.click();
		(new WebDriverWait(driver, 60))
			.until(ExpectedConditions.visibilityOf(pageNumber));
		String nextPageinput = pageNumber.getAttribute("value");
		while(Integer.parseInt(nextPageinput)!= actualPage && actualPage <= numberOfPages){
			nextPageinput = pageNumber.getAttribute("value");
		}
		LogManager.info("The Next Page button was clicked");
	}
	
	private int getRoomsTableNumberOfPages(){
		(new WebDriverWait(driver, 60))
			.until(ExpectedConditions.visibilityOf(numberOfPages));
		String pages = numberOfPages.getText().replace("/ ", "");
		LogManager.info("The number of Pages of the Rooms Table is: " + Integer.parseInt(pages));
		return Integer.parseInt(pages);
	}
}
