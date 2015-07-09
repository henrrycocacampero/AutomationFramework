package org.roommanager.framework.pages.admin.conferenceroom;

import java.util.List;

import org.openqa.selenium.By;
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
	@FindBy(xpath = RoomInfoPageConstant.LOCATION_BUTTON)
	private WebElement locationButton;
	@FindBy(xpath = RoomInfoPageConstant.LOCATION_LIST)
	private WebElement locationList;
	@FindBy(xpath = RoomInfoPageConstant.ROOM_NAME_TEXT_FIELD)
	private WebElement roomNameTextField;
	@FindBy (xpath = RoomInfoPageConstant.LOCATION_TYPE_BUTTTON)
	private WebElement locationTypeButton;
	@FindBy (xpath = RoomInfoPageConstant.LOCATION_TEXT_FIELD)
	private WebElement locationTextField;
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

<<<<<<< HEAD
	public ConferenceRoomPage locationName(){
		(new WebDriverWait(driver,60)).until(ExpectedConditions.visibilityOf(saveCancelRoom));
=======
	/**
	 * clickButtonCancelInfoRoom: It clicks on the Cancel button on the Page Info.
	 * @return ConferenceRoomPage
	 */
	public ConferenceRoomPage clickButtonCancelInfoRoom(){
		(new WebDriverWait(driver,60)).until(ExpectedConditions
				.visibilityOf(saveCancelRoom));
>>>>>>> origin/dev
		saveCancelRoom.click();
		LogManager.info("RoomInfoPage - click on the Cancel Button");
		return new ConferenceRoomPage(driver); 
	}
<<<<<<< HEAD
	public RoomInfoPage clickLocationButton (){
		(new WebDriverWait(driver,60)).until(ExpectedConditions.visibilityOf(locationButton));
		locationButton.click();
		LogManager.info("RoomInfoPage - click on the Location Button");
		return this;
	}
	public RoomInfoPage clickLocationTypeButton (){
		(new WebDriverWait(driver,60)).until(ExpectedConditions.visibilityOf(locationTypeButton));
		locationTypeButton.click();
		LogManager.info("RoomInfoPage - click on the Location Type Button");
		return this;
	}
=======

	/**
	 * getDisplayNameRoom: It get the Display Name of the Room in the
	 * Room Info Page.
	 * @return getNameRoom: It contains the current rooms display name.
	 */
>>>>>>> origin/dev
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

	public WebElement getLocationByName(String location) {
		(new WebDriverWait(driver, 60))
			.until(ExpectedConditions.visibilityOf(locationList));
		List<WebElement> locationTable = locationList
				.findElements(By.xpath(RoomInfoPageConstant.DIV_ELEMENT));
		for (WebElement locationElement : locationTable){
			String locationName = locationElement.findElement(
					By.xpath(RoomInfoPageConstant.NAME_LOCATION)).getText();
			if (locationName.equals(location)) {
				LogManager.info("Location: <" + locationName+ "> was retrieved from Location Table");
				return locationElement;	
			}
		}
		LogManager.info("Location: <" + location + "> wasn't found");
		return null;
	}
	public RoomInfoPage clickOnLocation(String location) {
		WebElement locationName = getLocationByName(location);
		locationName.click();
		LogManager.info("Location: <" + location + "> was selected");
		return this;
	}
	
	public boolean IsLocationTextFieldFilled(String location){
		WebElement organization = new WebDriverWait(driver,60).
				until(ExpectedConditions.visibilityOf(locationTextField));			
		String getLocationRoom = organization.getText();
		LogManager.info("Location: <" + location + "> is set in text field");
		return getLocationRoom.contains(location);
	}
}


