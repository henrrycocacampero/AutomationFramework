package org.roommanager.framework.pages.tablet.search;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.roommanager.framework.models.tablet.search.SearchConstant;
import org.roommanager.framework.utilities.common.LogManager;
import org.roommanager.framework.utilities.common.PropertiesReader;

public class SearchPage {
	@FindBy(xpath = SearchConstant.SEARCH_ICON)
	private WebElement searchIcon;
	@FindBy(xpath = SearchConstant.ADVANCED_BUTTON)
	private WebElement advancedButton;
	@FindBy(xpath = SearchConstant.ROOM_NAME_TEXT_FIELD)
	private WebElement roomNameTextField;
	@FindBy(xpath = SearchConstant.MINIMUN_CAPACITY_TEXT_FIELD)
	private WebElement minimunCapacityTextField;
	@FindBy(xpath = SearchConstant.ROOM_LIST)
	private WebElement roomList;
	@FindBy(xpath = SearchConstant.DIV_ELEMENT)
	private WebElement divElement;
	@FindBy(xpath = SearchConstant.ROOM_NAME)
	private WebDriver driver;

	public SearchPage(WebDriver driver) {
		driver.get(PropertiesReader.getLoginUrlTabletModule());
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public SearchPage clickSearchIcon() {
		(new WebDriverWait(driver, 60)).until(ExpectedConditions
				.visibilityOf(searchIcon));
		searchIcon.click();
		LogManager.info("Click on Search Icon");
		return this;
	}

	public SearchPage clickAdvancedButton() {
		(new WebDriverWait(driver, 60)).until(ExpectedConditions
				.visibilityOf(advancedButton));
		advancedButton.click();
		LogManager.info("Click on advanced Button");
		return this;
	}

	public SearchPage enterRoomName(String roomName) {
		(new WebDriverWait(driver, 60)).until(ExpectedConditions
				.visibilityOf(roomNameTextField));
		roomNameTextField.clear();
		roomNameTextField.sendKeys(roomName);
		LogManager.info("RoomName: <" + roomName + "> was entered");
		return this;
	}

	public SearchPage enterCapacity(String capacity) {
		(new WebDriverWait(driver, 60)).until(ExpectedConditions
				.visibilityOf(minimunCapacityTextField));
		minimunCapacityTextField.clear();
		minimunCapacityTextField.sendKeys(capacity);
		LogManager.info("Minimun Capacity: <" + capacity + "> was entered");
		return this;
	}

	public WebElement getRoomByName(String roomName) {
		(new WebDriverWait(driver, 60)).until(ExpectedConditions
				.visibilityOf(roomList));
		List<WebElement> rooms = roomList.findElements(By
				.xpath(SearchConstant.DIV_ELEMENT));
		for (WebElement room : rooms) {
			(new WebDriverWait(driver, 60)).until(ExpectedConditions
					.visibilityOf(room));
			String roomItemName = room.findElement(
					By.xpath(SearchConstant.ROOM_NAME)).getText();
			if (roomItemName.equals(roomName)) {
				LogManager.info("Room: <" + roomItemName
						+ "> was found on Room list");
				return room;
			}
		}
		LogManager.info("Room: <" + roomName + "> wasn't found on Room list");
		return null;
	}

	private Integer getRoomPosition(String roomName) {
		(new WebDriverWait(driver, 60)).until(ExpectedConditions
				.visibilityOf(roomList));
		List<WebElement> rooms = roomList.findElements(By
				.xpath(SearchConstant.DIV_ELEMENT));
		for (WebElement room : rooms) {

			(new WebDriverWait(driver, 60)).until(ExpectedConditions
					.visibilityOf(room));
			String roomItemName = room.findElement(
					By.xpath(SearchConstant.ROOM_NAME)).getText();
			if (roomItemName.equals(roomName)) {
				Integer index = rooms.indexOf(room) + 1;
				LogManager.info("The index of the room: <" + roomItemName
						+ "> is: " + index);

				return index;
			}
		}
		LogManager.info("The index of the room: <" + roomName
				+ "> wasn't found on Room list");
		return null;
	}

	public String getMeetingByRoom(String roomName) {
		Integer index = getRoomPosition(roomName);
		String path = "//div[2]/div/div/div[3]/div[2]/div/div[2]/div/rm-timeline/div/div[4]/div[1]/div/div[2]/div["
				+ index + "]/div/div/div/div[1]";
		WebElement roomMeeting = driver.findElement(By.xpath(path));
		String subject = roomMeeting.getText();
		LogManager.info("Room: <" + roomName + "> has a meeting with subject: <" + subject+">");
		return subject;
	}

	public boolean isRoomPresent(String roomName) {
		WebElement room = getRoomByName(roomName);
		return room == null ? true : false;
	}
}
