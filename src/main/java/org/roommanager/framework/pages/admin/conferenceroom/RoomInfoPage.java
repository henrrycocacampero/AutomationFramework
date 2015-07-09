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
	 * RoomNameIsDisable: It returns true if the the room text field is disabled.
	 * @return isRoomNameTextFieldDisabled
	 */	
	public boolean RoomNameIsDisable(){
		new WebDriverWait(driver,60)
			.until(ExpectedConditions.visibilityOf(roomNameTextField));
		boolean isRoomNameTextFieldDisabled = 
				roomNameTextField.isEnabled() == true? false:true;
		LogManager.info("RoomInfoPage - Room Name is Disable: "
				+isRoomNameTextFieldDisabled);
		return isRoomNameTextFieldDisabled;
	}
	
	/**
	 * clickButtonSaveInfoRoom: It clicks on the Save button on the Page Info.
	 * @return ConferenceRoomPage
	 */
	public ConferenceRoomPage clickButtonSaveInfoRoom(){
		(new WebDriverWait(driver,60)).until(ExpectedConditions
				.visibilityOf(saveButtonRoom));
		saveButtonRoom.click();
		LogManager.info("RoomInfoPage - click on the Save Button");
		return new ConferenceRoomPage(driver); 
	}

	/**
	 * clickButtonCancelInfoRoom: It clicks on the Cancel button on the Page Info.
	 * @return ConferenceRoomPage
	 */
	public ConferenceRoomPage clickButtonCancelInfoRoom(){
		(new WebDriverWait(driver,60)).until(ExpectedConditions
				.visibilityOf(saveCancelRoom));
		saveCancelRoom.click();
		LogManager.info("RoomInfoPage - click on the Cancel Button");
		return new ConferenceRoomPage(driver); 
	}

	/**
	 * getDisplayNameRoom: It get the Display Name of the Room in the
	 * Room Info Page.
	 * @return getNameRoom: It contains the current rooms display name.
	 */
	public String getDisplayNameRoom(){
		WebElement textFieldDisplayNameRoom = new WebDriverWait(driver,60).
				until(ExpectedConditions.visibilityOf(displaynameRoomTextField));
		String getNameRoom = textFieldDisplayNameRoom.getAttribute("value");
		LogManager.info("RoomInfoPage - get the room display name: "+getNameRoom);
		return getNameRoom ;
	}
	
	/**
	 * setDisplayNameRoom: It sets the Display Name of the Room in the
	 * Room Info Page.
	 * @param DisplayNameRoom: It represents the value for the rooms display name.
	 */
	public void setDisplayNameRoom(String DisplayNameRoom){
		new WebDriverWait(driver,60).until(ExpectedConditions
				.visibilityOf(displaynameRoomTextField));	
		displaynameRoomTextField.clear();
		displaynameRoomTextField.sendKeys(DisplayNameRoom);
		LogManager.info("RoomInfoPage - set the room display name");
	}
	
	/**
	 * getCodeRoom: It get the code of the Room in the Room Info Page.
	 * @return getCodeRoom: It contains the current rooms code.
	 */
	public String getCodeRoom(){
		WebElement textFieldCodeRoom = new WebDriverWait(driver,60).
				until(ExpectedConditions.visibilityOf(codeRoomTextField));
		String getCodeRoom = textFieldCodeRoom.getAttribute("value");
		LogManager.info("RoomInfoPage - get the room code: "+getCodeRoom);
		return getCodeRoom ;
	}
	
	/**
	 * setCodeRoom: It sets the code of the Room in the Room Info Page.
	 * @param CodeRoom: It represents the value for the rooms code.
	 */
	public void setCodeRoom(String CodeRoom){
		new WebDriverWait(driver,60).until(ExpectedConditions
				.visibilityOf(codeRoomTextField));	
		codeRoomTextField.clear();
		codeRoomTextField.sendKeys(CodeRoom);
		LogManager.info("RoomInfoPage - set the room code");
	}
	
	/**
	 * getCapacityRoom: It get the capacity of the Room in the Room Info Page.
	 * @return getCapacityRoom: It contains the current rooms capacity.
	 */
	public String getCapacityRoom(){
		WebElement textFieldCapacityRoom = new WebDriverWait(driver,60).
				until(ExpectedConditions.visibilityOf(capacityRoomTextField));			
		String getCapacityRoom = textFieldCapacityRoom.getAttribute("value");
		LogManager.info("RoomInfoPage - get the room capacity: "+getCapacityRoom);
		return getCapacityRoom ;
	}
	
	/**
	 * setCapacityRoom: It sets the capacity of the Room in the Room Info Page.
	 * @param CapacityRoom: It represents the value for the rooms capacity.
	 */
	public void setCapacityRoom(String CapacityRoom){
		new WebDriverWait(driver,60).until(ExpectedConditions.visibilityOf(capacityRoomTextField));
		capacityRoomTextField.clear();
		capacityRoomTextField.sendKeys(CapacityRoom);
		LogManager.info("RoomInfoPage - set the room capacity");
	}	
}


