package org.roommanager.framework.pages.admin.conferenceroom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.roommanager.framework.models.admin.conferencerooms.RoomInfoPageConstant;
import org.roommanager.framework.utilities.common.LogManager;

public class RoomInfoPage extends ConferenceRoomTopMenu{

	@FindBy(xpath = RoomInfoPageConstant.SAVE_BUTTON_ROOM)
	private WebElement saveButtonRoom;
	@FindBy(css = RoomInfoPageConstant.SAVE_CANCEL_ROOM)
	private WebElement saveCancelRoom;
	@FindBy(xpath = RoomInfoPageConstant.POWER_BUTTON_ROOM)
	private WebElement powerButton_Room;
	@FindBy(xpath = RoomInfoPageConstant.DISPLAYNAME_ROOM_TEXT_FIELD)
	private WebElement displaynameRoomTextField;
	@FindBy(xpath = RoomInfoPageConstant.CODE_ROOM_TEXT_FIELD)
	private WebElement codeRoomTextField;
	@FindBy(xpath = RoomInfoPageConstant.CAPACITY_ROOM_TEXT_FIELD)
	private WebElement capacityRoomTextField;
	@FindBy(css = RoomInfoPageConstant.LOCATION_BUTTON)
	private WebElement locationButton;
	@FindBy(xpath = RoomInfoPageConstant.LOCATION_LIST)
	private WebElement LocationList;
	@FindBy(xpath = RoomInfoPageConstant.ROOM_NAME_TEXT_FIELD)
	private WebElement roomNameTextField;
	private WebDriver driver;
	
	public RoomInfoPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	/**
	 * RoomNameIsDisable: It double clicks on the specified Room.
	 * @return isRoomNameTextFieldDisabled
	 */	
	public boolean RoomNameIsDisable(){
		new WebDriverWait(driver,60)
			.until(ExpectedConditions.visibilityOf(roomNameTextField));
		boolean isRoomNameTextFieldDisabled = 
				roomNameTextField.isEnabled() == true? false:true;
		LogManager.info("RoomInfoPage - Room Name is Disable: "+isRoomNameTextFieldDisabled);
		return isRoomNameTextFieldDisabled;
	}
	public ConferenceRoomPage clickButtonSaveInfoRoom(){
		(new WebDriverWait(driver,60)).until(ExpectedConditions.visibilityOf(saveButtonRoom));
		saveButtonRoom.click();
		LogManager.info("RoomInfoPage - click on the Save Button");
		return new ConferenceRoomPage(driver); 
	}

	public ConferenceRoomPage clickButtonCancelInfoRoom(){
		(new WebDriverWait(driver,60)).until(ExpectedConditions.visibilityOf(saveCancelRoom));
		saveCancelRoom.click();
		LogManager.info("RoomInfoPage - click on the Cancel Button");
		return new ConferenceRoomPage(driver); 
	}

	public String getDisplayNameRoom(){
		WebElement textFieldDisplayNameRoom = new WebDriverWait(driver,60).
				until(ExpectedConditions.visibilityOf(displaynameRoomTextField));
		String getNameRoom = textFieldDisplayNameRoom.getAttribute("value");
		LogManager.info("RoomInfoPage - get the room display name: "+getNameRoom);
		return getNameRoom ;
	}
	
	public void setDisplayNameRoom(String DisplayNameRoom){
		new WebDriverWait(driver,60).until(ExpectedConditions.visibilityOf(displaynameRoomTextField));	
		displaynameRoomTextField.clear();
		displaynameRoomTextField.sendKeys(DisplayNameRoom);
		LogManager.info("RoomInfoPage - set the room display name");
	}
	
	public String getCodeRoom(){
		WebElement textFieldCodeRoom = new WebDriverWait(driver,60).
				until(ExpectedConditions.visibilityOf(codeRoomTextField));
		String getCodeRoom = textFieldCodeRoom.getAttribute("Value");
		LogManager.info("RoomInfoPage - get the room code: "+getCodeRoom);
		return getCodeRoom ;
	}
	
	public void setCodeRoom(String CodeRoom){
		new WebDriverWait(driver,60).until(ExpectedConditions.visibilityOf(codeRoomTextField));	
		codeRoomTextField.clear();
		codeRoomTextField.sendKeys(CodeRoom);
		LogManager.info("RoomInfoPage - set the room code");
	}
	
	public String getCapacityRoom(){
		WebElement textFieldCapacityRoom = new WebDriverWait(driver,60).
				until(ExpectedConditions.visibilityOf(capacityRoomTextField));			
		String getCapacityRoom = textFieldCapacityRoom.getAttribute("Value");
		LogManager.info("RoomInfoPage - get the room capacity: "+getCapacityRoom);
		return getCapacityRoom ;
	}
	
	public void setCapacityRoom(String CapacityRoom){
		new WebDriverWait(driver,60).until(ExpectedConditions.visibilityOf(capacityRoomTextField));
		capacityRoomTextField.clear();
		capacityRoomTextField.sendKeys(CapacityRoom);
		LogManager.info("RoomInfoPage - set the room capacity");
	}	
}


