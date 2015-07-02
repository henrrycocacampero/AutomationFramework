package org.roommanager.framework.pages.tablet.scheduler;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.roommanager.framework.models.tablet.scheduler.SchedulerConstant;
import org.roommanager.framework.utilities.common.LogManager;

public class SchedulerPage extends PageFactory{
	WebDriver driver = null;

	@FindBy (xpath = SchedulerConstant.ATTENDEE_TEXT) 
	WebElement attendeeText;
	@FindBy (xpath = SchedulerConstant.ATTENDEES_LIST) 
	WebElement attendeesList;
	@FindBy (xpath = SchedulerConstant.BODY_TEXT_AREA) 
	WebElement bodyTextArea;
	@FindBy (xpath = SchedulerConstant.UPDATE_BUTTON) 
	WebElement updateButton;
	@FindBy (xpath = SchedulerConstant.REMOVE_BUTTON) 
	WebElement removeButton;
	@FindBy (xpath = SchedulerConstant.CONFIRMATION_MESSAGE) 
	WebElement confirmationMessage;
	@FindBy (xpath = SchedulerConstant.CREATE_BUTTON) 
	WebElement createButton;
	@FindBy (xpath = SchedulerConstant.ATTENDEES_TEXT_FIELD) 
	WebElement attendeesTextField;
	@FindBy (xpath = SchedulerConstant.SUBJECT_TEXT_FIELD) 
	WebElement subjectTextField;
	@FindBy (xpath = SchedulerConstant.ORGANIZER_TEXT_FIELD) 
	WebElement organizerTextField;
	
	@FindBy (xpath = SchedulerConstant.ROOM_TIMELINE) 
	WebElement roomTimeline;
	@FindBy (xpath = SchedulerConstant.MEETING_BOX) 
	WebElement meetingBox;
	
	public SchedulerPage(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	public SchedulerPage setOrganizerTextField(String organizer){
		(new WebDriverWait(driver, 30))
		.until(ExpectedConditions.visibilityOf(organizerTextField));
		organizerTextField.clear();
		organizerTextField.sendKeys(organizer);
		return this;
	}
	public SchedulerPage setSubjectTextField(String subject){
		(new WebDriverWait(driver, 30))
		.until(ExpectedConditions.visibilityOf(subjectTextField));
		subjectTextField.clear();
		subjectTextField.sendKeys(subject);
		return this;
	}
	public SchedulerPage setAttendeesTextField(String attendee){
		(new WebDriverWait(driver, 30))
		.until(ExpectedConditions.visibilityOf(attendeesTextField));
		attendeesTextField.clear();
		attendeesTextField.sendKeys(attendee + ";");
		return this;
	}
	public SchedulerPage setBodyTextArea(String body){
		(new WebDriverWait(driver, 30))
		.until(ExpectedConditions.visibilityOf(bodyTextArea));
		bodyTextArea.clear();
		bodyTextArea.sendKeys(body);
		return this;
	}
	public CredentialsPage clickCreateButton(){
		(new WebDriverWait(driver, 20))
		.until(ExpectedConditions.visibilityOf(createButton));
		createButton.click();
		return new CredentialsPage(driver);
	}
	public String getSuccessfulMessage(){
		(new WebDriverWait(driver,60))
		.until(ExpectedConditions.visibilityOf(confirmationMessage));
		return confirmationMessage.getText();
	}
	public SchedulerPage clickOnMeetingBox(String subject){
		getMeetingBoxBySubject(subject).click();
		(new WebDriverWait(driver,20))
		.until(ExpectedConditions.visibilityOf(updateButton));
		return this;
	}
	public CredentialsPage clickRemoveButton(){
		(new WebDriverWait(driver,30))
		.until(ExpectedConditions.visibilityOf(removeButton));
		removeButton.click();
		return new CredentialsPage(driver);
	}
	public CredentialsPage clickUpdateButton(){
		(new WebDriverWait(driver,30))
		.until(ExpectedConditions.visibilityOf(updateButton));
		updateButton.click();
		return new CredentialsPage(driver);
	}
	private WebElement getAttendee(String attendee){
		(new WebDriverWait(driver,60))
		.until(ExpectedConditions.visibilityOf(attendeesList));
		WebElement list = driver.findElement(By.xpath(SchedulerConstant.ATTENDEES_LIST));
		List<WebElement> attendees = list.findElements(By.xpath(SchedulerConstant.ATTENDEE_TEXT));
		for(WebElement element : attendees){
			if(element.getText().equals(attendee)){
				LogManager.info("Attendee found: " + attendee);
				return element;
			}
		}
		LogManager.info("Attendee not found: " + attendee);
		return null;
	}
	private WebElement getMeetingBoxBySubject(String subject){
		(new WebDriverWait(driver,60)).until(ExpectedConditions.visibilityOf(roomTimeline));
		WebElement time = driver.findElement(By.xpath(SchedulerConstant.ROOM_TIMELINE));
		List<WebElement> boxes = time.findElements(By.xpath(SchedulerConstant.MEETING_BOX));
		for(WebElement element: boxes){
			if(element.getText().equals(subject)){
				LogManager.info("Subject found: " + subject);
				return element;
			}
		}
		LogManager.info("Subject not found: " + subject);
		return null;
	}
	public boolean existSubjectOnTimeline(String subject){
		return getMeetingBoxBySubject(subject) != null ? true : false;
	}
	public boolean existAttendee(String attendee){
		return getAttendee(attendee) != null ? true : false;
	}
}
