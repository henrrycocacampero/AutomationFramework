package org.roommanager.framework.pages.admin.conferenceroom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.roommanager.framework.models.admin.conferencerooms.RoomInfoPageConstant;

public class RoomInfoPage extends ConferenceRoomTopMenu{

	@FindBy(xpath = RoomInfoPageConstant.SAVE_BUTTON_ROOM)
	private WebElement saveButton_Room;
	@FindBy(css = RoomInfoPageConstant.SAVE_CANCEL_ROOM)
	private WebElement saveCancel_Room;
	@FindBy(xpath = RoomInfoPageConstant.POWER_BUTTON_ROOM)
	private WebElement powerButton_Room;
	@FindBy(xpath = RoomInfoPageConstant.DISPLAYNAME_ROOM_TEXT_FIELD)
	private WebElement displaynameRoomText_Field;
	@FindBy(xpath = RoomInfoPageConstant.CODE_ROOM_TEXT_FIELD)
	private WebElement codeRoomText_Field;
	@FindBy(xpath = RoomInfoPageConstant.CAPACITY_ROOM_TEXT_FIELD)
	private WebElement capacityRoomText_Field;
	@FindBy(css = RoomInfoPageConstant.LOCATION_BUTTON)
	private WebElement location_Button;
	@FindBy(xpath = RoomInfoPageConstant.LOCATION_LIST)
	private WebElement Location_List;
	private WebDriver driver;
	
	public RoomInfoPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	


}


