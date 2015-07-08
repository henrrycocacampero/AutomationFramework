package org.roommanager.framework.pages.admin.conferenceroom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.roommanager.framework.models.admin.conferencerooms.RoomInfoPageConstant;

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
	public boolean RoomNameIsDisable(){
		new WebDriverWait(driver,60)
			.until(ExpectedConditions.visibilityOf(roomNameTextField));
		boolean isRoomNameTextFieldDisabled = 
				roomNameTextField.isEnabled() == true? false:true;
		return isRoomNameTextFieldDisabled;
	}
	public ConferenceRoomPage clickButtonSaveUpdateInfoRoom(){
		(new WebDriverWait(driver,60)).until(ExpectedConditions.visibilityOf(saveButtonRoom));
		saveButtonRoom.click();
		return new ConferenceRoomPage(driver); 
	}

	public ConferenceRoomPage clickButtonCancelUpdateInfoRoom(){
		(new WebDriverWait(driver,60)).until(ExpectedConditions.visibilityOf(saveCancelRoom));
		saveCancelRoom.click();
		return new ConferenceRoomPage(driver); 
	}

	public String getDisplayNameRoom(){
		WebElement textFieldDisplayNameRoom = new WebDriverWait(driver,60).
				until(ExpectedConditions.visibilityOf(displaynameRoomTextField));
		String getNameRoom = textFieldDisplayNameRoom.getAttribute("Value");
		return getNameRoom ;
	}
	
	public void setDisplayNameRoom(String DisplayNameRoom){
		new WebDriverWait(driver,60).until(ExpectedConditions.visibilityOf(displaynameRoomTextField));	
		displaynameRoomTextField.clear();
		displaynameRoomTextField.sendKeys(DisplayNameRoom);
	}
	
	public String getCodeRoom(){
		WebElement textFieldCodeRoom = new WebDriverWait(driver,60).
				until(ExpectedConditions.visibilityOf(codeRoomTextField));
		String getCodeRoom = textFieldCodeRoom.getAttribute("Value");
		return getCodeRoom ;
	}
	
	public void setCodeRoom(String CodeRoom){
		new WebDriverWait(driver,60).until(ExpectedConditions.visibilityOf(codeRoomTextField));	
		codeRoomTextField.clear();
		codeRoomTextField.sendKeys(CodeRoom);
	}
	
	public String getCapacityRoom(){
		WebElement textFieldCapacityRoom = new WebDriverWait(driver,60).
				until(ExpectedConditions.visibilityOf(capacityRoomTextField));			
		String getCapacityRoom = textFieldCapacityRoom.getAttribute("Value");
		return getCapacityRoom ;
	}
	
	public void setCapacityRoom(String CapacityRoom){
		new WebDriverWait(driver,60).until(ExpectedConditions.visibilityOf(capacityRoomTextField));
		capacityRoomTextField.clear();
		capacityRoomTextField.sendKeys(CapacityRoom);
	}	
}


