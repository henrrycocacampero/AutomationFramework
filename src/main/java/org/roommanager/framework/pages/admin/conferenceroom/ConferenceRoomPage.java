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
	private By disabledRoomNameLocator = ConferenceRoomConstant.DISABLED_ROOM_NAME;
	private WebDriver driver;
	@FindBy (css = ConferenceRoomConstant.TITLE_TABLE_ROOMS) 
	private WebElement titleTableRooms;
	
	public ConferenceRoomPage(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	/**
	 * doubleClickOnRoom: It double clicks on the specified Room.
	 * @param roomName: It represents the Room's Name
	 * @return RoomInfoPage
	 */
	public RoomInfoPage doubleClickOnRoom(String roomName){
		WebElement room = getRoomFromAllPagesByName(roomName, 
				          getRoomsTableNumberOfPages(),true);
		
		Actions action = new Actions(driver);
		action.doubleClick(room);
		action.perform();
		LogManager.info("Double Click on Resource: <"+roomName+"> from Resources Table");
		return new RoomInfoPage(driver);
	}
	public RoomInfoPage doubleClickOnDisabledRoom(String roomName){
		WebElement room = getRoomFromAllPagesByName(roomName, 
				          getRoomsTableNumberOfPages(),false);
		Actions action = new Actions(driver);
		action.doubleClick(room);
		action.perform();
		LogManager.info("Double Click on Resource: <"+roomName+"> from Resources Table");
		return new RoomInfoPage(driver);
	}
	public String getStateColorOnDisabledRoom(String roomName){
		String stateOnButton=getStateColorRoomByName(roomName,false);
		return stateOnButton;
	}
	public String getStateColorOnEnabledRoom(String roomName){
		String stateOffButton=getStateColorRoomByName(roomName,true);
		return stateOffButton;
	}
	private String getStateColorRoomByName(String roomName, Boolean enabledRoom){
		(new WebDriverWait(driver, 60))
			.until(ExpectedConditions.visibilityOf(roomsList));
		List <WebElement> rooms = roomsList.findElements(divElementLocator);
		String stateRoom="";
		Integer i=1;
		for (WebElement room : rooms) {
			String roomItemName =enabledRoom==true ? 
					room.findElement(roomNameLocator).getText():
					room.findElement(disabledRoomNameLocator).getText();
			if(roomItemName.equals(roomName)){
				String c=ConferenceRoomConstant.LIST_ROOM+"/div["+i+"]/"+ConferenceRoomConstant.ONOFF_BUTTON;	
				stateRoom=driver.findElement(By.xpath(c)).getAttribute("class");
			}
			i=i+1;
		}
		LogManager.info("The state color of Room is: "+ stateRoom.split(" ")[0]);
		return stateRoom;
	}
	
	/**
	 * getRoomByName: It retrieves the specified Room.
	 * @param roomName: It represents the Room's Name
	 * @return WebElement
	 */
	private WebElement getRoomByName(String roomName, boolean enabledRoom){
		(new WebDriverWait(driver, 60))
			.until(ExpectedConditions.visibilityOf(roomsList));
		List <WebElement> rooms = roomsList.findElements(divElementLocator);
		
		for (WebElement room : rooms) {
			String roomItemName =enabledRoom==true ? 
				room.findElement(roomNameLocator).getText():
				room.findElement(disabledRoomNameLocator).getText();
			if(roomItemName.equals(roomName)){
				LogManager.info("Room: <"+ roomItemName +
						        "> was found on the Available Rooms List");
				return room;
			}
		}
		LogManager.info("Room: <"+ roomName +
				        "> wasn't found on the Available Rooms List");
		return null;
	}
	
	/**
	 * getRoomByName: It retrieves the specified Room from all the available pages.
	 * @param roomName: It represents the Room's Name
	 * @param numberOfPages: It represents the available number of Pages
	 * @return WebElement
	 */
	private WebElement getRoomFromAllPagesByName(String resourceName, int numberOfPages,boolean enabledRoom){
		WebElement resource = null;
		for(int index = 1; index <= numberOfPages; index++){
			 resource = getRoomByName(resourceName,enabledRoom);
			 if(resource != null){
				 LogManager.info("Resource: <" +resourceName+ 
						         "> was found in page:" + index);
				 return resource;
			 }
			 clickNextPageButton(index + 1, numberOfPages);
			 LogManager.info("Searching for room in page: " + index);
		}
		return resource;
	}
	
	/**
	 * clickNextPageButton: It clicks on the Next Page Button.
	 * @param actualPage: It represents the current Page Number
	 * @param numberOfPages: It represents the available number of Pages
	 */
	private void clickNextPageButton(int actualPage, int numberOfPages){
		(new WebDriverWait(driver, 60))
			.until(ExpectedConditions.visibilityOf(nextPageButton));
		nextPageButton.click();
		(new WebDriverWait(driver, 60))
			.until(ExpectedConditions.visibilityOf(pageNumber));
		String nextPageinput = pageNumber.getAttribute("value");
		while(Integer.parseInt(nextPageinput)!= actualPage 
		      && actualPage <= numberOfPages){
			nextPageinput = pageNumber.getAttribute("value");
		}
		LogManager.info("The Next Page button was clicked");
	}	
	/**
	 * getRoomsTableNumberOfPages: It retrieves Rooms Table's Number of Pages.
	 * @return int
	 */
	private int getRoomsTableNumberOfPages(){
		(new WebDriverWait(driver, 60))
			.until(ExpectedConditions.visibilityOf(numberOfPages));
		String pages = numberOfPages.getText().replace("/ ", "");
		LogManager.info("The number of Pages of the Rooms Table is: " 
		                + Integer.parseInt(pages));
		return Integer.parseInt(pages);
	}
	/**
	 * existOutOfOrder: Making the verification of an Out of Order 
	 * was created on a room.
	 * @return boolean
	 */
	public boolean existOutOfOrder(String roomName){

		/*Looking the room*/		
		boolean found= false;		
		
		new WebDriverWait(driver,80).until(ExpectedConditions.visibilityOf(titleTableRooms));
		
		WebElement list = driver.findElement(By.xpath(ConferenceRoomConstant.LIST_ROOM));
		List<WebElement> subList = list.findElements((ConferenceRoomConstant.SUBLIST_ROOM)); 		
	    
		for(int i = 0 ; i < subList.size(); i+=1){
	    	String valueGet = (subList.get(i).getText()).trim();
	    	String valueRoom = roomName.trim();	    	
	    	if(valueGet.equals(valueRoom)){
	    		System.out.println("Room:" + subList.get(i).getText()+ " Found!");	  
	    		try{
	    			subList.get(i).
	    			findElement(By.xpath("div[2]/div[2]/out-of-order-icon/div/div/div/span"));
	    			found = true;
	    		} 
	    		catch(Exception e)
	    		{	    			
	    		}   		 
	    		break;
	    	}	    	    	
	    }	
		System.out.println(subList.size());
	    LogManager.info("Out Of Order Planning exist? "+ found);
	    return found;	
	}
}
