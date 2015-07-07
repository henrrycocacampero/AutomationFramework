package org.roommanager.framework.pages.admin.conferencerooms;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.roommanager.framework.models.admin.conferencerooms.RoomInfoPageConstant;
import org.roommanager.framework.pages.admin.common.LeftMenu;
import org.roommanager.framework.pages.admin.resource.CreateResourcePage;
import org.roommanager.framework.utilities.common.LogManager;

public class RoomInfoPage{
	private WebDriver driver;
	public final static String saveButtonRoom= RoomInfoPageConstant.SAVE_BUTTON_ROOM;
	public final static String saveCancelRoom = RoomInfoPageConstant.SAVE_CANCEL_ROOM;
	public final static String powerButtonRoom = RoomInfoPageConstant.POWER_BUTTON_ROOM;
	public final static String displaynameRoomTextField = RoomInfoPageConstant.DISPLAYNAME_ROOM_TEXT_FIELD;
	public final static String codeRoomTextField= RoomInfoPageConstant.CODE_ROOM_TEXT_FIELD;
    public final static String capacityRoomTextField= RoomInfoPageConstant.CAPACITY_ROOM_TEXT_FIELD;
	public final static String locationButton= RoomInfoPageConstant.LOCATION_BUTTON;
	public final static String LocationList = RoomInfoPageConstant.LOCATION_LIST;
	
	@FindBy(xpath = saveButtonRoom)
	WebElement saveButton_Room;
	@FindBy(css = saveCancelRoom)
	WebElement saveCancel_Room;
	@FindBy(xpath = powerButtonRoom)
	WebElement powerButton_Room;
	@FindBy(xpath = displaynameRoomTextField)
	WebElement displaynameRoomText_Field;
	@FindBy(xpath = codeRoomTextField)
	WebElement codeRoomText_Field;
	@FindBy(xpath = capacityRoomTextField)
	WebElement capacityRoomText_Field;
	@FindBy(css = locationButton)
	WebElement location_Button;
	@FindBy(xpath = LocationList)
	WebElement Location_List;
	
	
	public RoomInfoPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public RoomInfoPage enterRoomDisplayName(String roomDisplayName){
		(new webDriverWait(driver, 60))
			.until(ExpectedConditions
					.visibilityOf(displaynameRoomText_Field));
	
		
	}
	
 
											
	
/*
 * 		public CreateResourcePage enterResourceName(String resourceName) {
			(new WebDriverWait(driver, 60))
					.until(ExpectedConditions
							.visibilityOf(resourceName_TextField));
			resourceName_TextField.clear();
			resourceName_TextField.sendKeys(resourceName);
			LogManager.info("Resource Name: <" + resourceName + "> was entered");
			return this;
		}	
 */

}


