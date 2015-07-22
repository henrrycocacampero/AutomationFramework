package org.roommanager.framework.pages.tablet.scheduler;

import java.util.Calendar;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
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
	@FindBy (xpath = SchedulerConstant.MEETING_SUBJECT_ERROR_MESSAGE) 
	WebElement meetingSubjectErrorMessage;
	@FindBy (xpath = SchedulerConstant.MEETING_ORGANIZER_ERROR_MESSAGE) 
	WebElement organizerSubjectErrorMessage;
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
		LogManager.info("Organizer: <"+ organizer +"> was entered");
		return this;
	}
	public SchedulerPage setSubjectTextField(String subject){
		(new WebDriverWait(driver, 30))
		.until(ExpectedConditions.visibilityOf(subjectTextField));
		subjectTextField.clear();
		subjectTextField.sendKeys(subject);
		LogManager.info("Subject: <"+ subject +"> was entered");
		return this;
	}
	public SchedulerPage setAttendeesTextField(String attendee){
		(new WebDriverWait(driver, 30))
		.until(ExpectedConditions.visibilityOf(attendeesTextField));
		attendeesTextField.clear();
		attendeesTextField.sendKeys(attendee + ";");
		LogManager.info("Attendee: <"+ attendee +"> was entered");
		return this;
	}
	public SchedulerPage setBodyTextArea(String body){
		(new WebDriverWait(driver, 30))
		.until(ExpectedConditions.visibilityOf(bodyTextArea));
		bodyTextArea.clear();
		bodyTextArea.sendKeys(body);
		LogManager.info("Body: <"+ body +"> was entered");
		return this;
	}
	public CredentialsPage clickCreateButton(){
		(new WebDriverWait(driver, 20))
		.until(ExpectedConditions.visibilityOf(createButton));
		createButton.click();
		LogManager.info("Create Button was clicked");
		return new CredentialsPage(driver);
	}
	public boolean isSubjectFieldErrorMessagePresent(){
		String expectedErrorMessage = "Subject is required";
		(new WebDriverWait(driver,60))
		.until(ExpectedConditions.visibilityOf(meetingSubjectErrorMessage));
		String errorMessage = meetingSubjectErrorMessage.getText();
		LogManager.info("Error Message: <"+ errorMessage +"> was displayed");
		return errorMessage.equals(expectedErrorMessage);
	}
	
	public boolean isOrganizerFieldErrorMessagePresent(){
		String expectedErrorMessage = "Organizer is required";
		(new WebDriverWait(driver,60))
		.until(ExpectedConditions.visibilityOf(organizerSubjectErrorMessage));
		String errorMessage = organizerSubjectErrorMessage.getText();
		LogManager.info("Error Message: <"+ errorMessage +"> was displayed");
		return errorMessage.equals(expectedErrorMessage);
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
		LogManager.info("Meeting <" + subject + "> was clicked");
		return this;
	}
	public CredentialsPage clickRemoveButton(){
		(new WebDriverWait(driver,30))
		.until(ExpectedConditions.visibilityOf(removeButton));
		removeButton.click();
		LogManager.info("Remove Button was clicked");
		return new CredentialsPage(driver);
	}
	public CredentialsPage clickUpdateButton(){
		(new WebDriverWait(driver,30))
		.until(ExpectedConditions.visibilityOf(updateButton));
		updateButton.click();
		LogManager.info("Update Button was clicked");
		return new CredentialsPage(driver);
	}
	private WebElement getAttendee(String attendee){
		(new WebDriverWait(driver,60))
		.until(ExpectedConditions.visibilityOf(attendeesList));
		WebElement list = driver.findElement(By.xpath(SchedulerConstant.ATTENDEES_LIST));
		List<WebElement> attendees = list.findElements(By.xpath(SchedulerConstant.ATTENDEE_TEXT));
		for(WebElement element : attendees){
			if(element.getText().equals(attendee)){
				LogManager.info("Attendee <" + attendee + "> was not found");
				return element;
			}
		}
		LogManager.info("Attendee <" + attendee + "> was not found");
		return null;
	}
	private WebElement getMeetingBoxBySubject(String subject){
		(new WebDriverWait(driver,60)).until(ExpectedConditions.visibilityOf(roomTimeline));
		moveTimeline();
		WebElement time = driver.findElement(By.xpath(SchedulerConstant.ROOM_TIMELINE));
		List<WebElement> boxes = time.findElements(By.xpath(SchedulerConstant.MEETING_BOX));
		for(WebElement element: boxes){
			if(element.getText().equals(subject)){
				LogManager.info("Subject <" + subject + "> was found");
				return element;
			}
		}
		LogManager.info("Subject <" + subject + "> was not found");
		return null;
	}
	public boolean existSubjectOnTimeline(String subject){
		return getMeetingBoxBySubject(subject) != null ? true : false;
	}
	public boolean existAttendee(String attendee){
		return getAttendee(attendee) != null ? true : false;
	}
	
	@SuppressWarnings("unused")
	private void moveTimeline(){
		int xDirection = 0;
		if(Calendar.HOUR_OF_DAY > 19)
			xDirection = -500;
		else if(Calendar.HOUR_OF_DAY < 7)
			xDirection = 800;
		(new Actions(driver)).dragAndDropBy(roomTimeline, xDirection, 0).perform();
	}
}
