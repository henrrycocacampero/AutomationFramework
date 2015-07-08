package org.roommanager.framework.pages.admin.resource;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.roommanager.framework.models.admin.resource.CreateResourceConstant;
import org.roommanager.framework.utilities.common.LogManager;

public class CreateResourcePage {
	private WebDriver driver;
	
	@FindBy(xpath = CreateResourceConstant.RESOURCE_NAME_FIELD)
	private WebElement nameTextField;
	@FindBy(xpath = CreateResourceConstant.RESOURCE_DISPLAY_NAME_FIELD)
	private WebElement displayNameTextField;
	@FindBy(xpath = CreateResourceConstant.RESOURCE_DESCRIPTION_AREA)
	private WebElement descriptionTextArea;
	@FindBy(css = CreateResourceConstant.SAVE_BUTTON)
	private WebElement saveButton;
	@FindBy(xpath = CreateResourceConstant.NAME_ERROR_MESSAGE)
	private WebElement nameTextFieldErrorMessage;
	
	public CreateResourcePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

		public CreateResourcePage enterResourceName(String resourceName) {
			(new WebDriverWait(driver, 60))
					.until(ExpectedConditions
							.visibilityOf(nameTextField));
			nameTextField.clear();
			nameTextField.sendKeys(resourceName);
			LogManager.info("Resource Name: <" + resourceName + "> was entered");
			return this;
		}

	public CreateResourcePage enterResourceDisplayName(
			String resourceDisplayName) {
		(new WebDriverWait(driver, 60))
				.until(ExpectedConditions
						.visibilityOf(displayNameTextField));
		displayNameTextField.clear();
		displayNameTextField.sendKeys(resourceDisplayName);
		LogManager.info("Resource Display Name: <" + resourceDisplayName+ "> was entered");
		return this;
	}

	public CreateResourcePage enterResourceDescription(
			String resourceDescription) {
		(new WebDriverWait(driver, 60))
				.until(ExpectedConditions
						.visibilityOf(descriptionTextArea));
		descriptionTextArea.clear();
		descriptionTextArea.sendKeys(resourceDescription);
		LogManager.info("Resource Description: <" + resourceDescription+ "> was entered");
		return this;
	}

	public ResourcePage clickSaveResourceButton() {
		(new WebDriverWait(driver, 60))
				.until(ExpectedConditions.visibilityOf(saveButton));
		saveButton.click();
		(new WebDriverWait(driver, 60)).until(ExpectedConditions
				.invisibilityOfElementLocated(By.cssSelector(CreateResourceConstant.SAVE_BUTTON)));
		LogManager.info("Save button was clicked");
		return new ResourcePage(driver);
	}
	/**
	 * clickSaveButtonInvalidData: It performs a click on save button
	 * and returns to the same page.
	 * @return CreateResourcePage
	 */
	public CreateResourcePage clickSaveButtonInvalidData() {
		(new WebDriverWait(driver, 60))
				.until(ExpectedConditions.visibilityOf(saveButton));
		saveButton.click();
		LogManager.info("Save button was clicked");
		return this;
	}
	
	/**
	 * isNameFieldErrorMessagePresent: It returns true if the error message
	 * is present above the name text field.
	 * @return boolean
	 */
	public boolean isNameFieldErrorMessagePresent(){
		String expectedErrorMessage = "A resource with the same name already exists, "
				+ "please choose another name";
		(new WebDriverWait(driver, 20))
			.until(ExpectedConditions.visibilityOf(nameTextFieldErrorMessage));
		String errorMessage = nameTextFieldErrorMessage.getText();
		return errorMessage.equals(expectedErrorMessage);
	}
}
