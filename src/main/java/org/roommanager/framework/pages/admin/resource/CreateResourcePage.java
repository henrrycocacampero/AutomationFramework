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
	private WebElement resourceName_TextField;
	@FindBy(xpath = CreateResourceConstant.RESOURCE_DISPLAY_NAME_FIELD)
	private WebElement resourceDisplayName_TextField;
	@FindBy(xpath = CreateResourceConstant.RESOURCE_DESCRIPTION_AREA)
	private WebElement resourceDescription_AreaText;
	@FindBy(css = CreateResourceConstant.SAVE_BUTTON)
	private WebElement saveResource_Button;
	
	
	public CreateResourcePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

		public CreateResourcePage enterResourceName(String resourceName) {
			(new WebDriverWait(driver, 60))
					.until(ExpectedConditions
							.visibilityOf(resourceName_TextField));
			resourceName_TextField.clear();
			resourceName_TextField.sendKeys(resourceName);
			LogManager.info("Resource Name: <" + resourceName + "> was entered");
			return this;
		}

	public CreateResourcePage enterResourceDisplayName(
			String resourceDisplayName) {
		(new WebDriverWait(driver, 60))
				.until(ExpectedConditions
						.visibilityOf(resourceDisplayName_TextField));
		resourceDisplayName_TextField.clear();
		resourceDisplayName_TextField.sendKeys(resourceDisplayName);
		LogManager.info("Resource Display Name: <" + resourceDisplayName+ "> was entered");
		return this;
	}

	public CreateResourcePage enterResourceDescription(
			String resourceDescription) {
		(new WebDriverWait(driver, 60))
				.until(ExpectedConditions
						.visibilityOf(resourceDescription_AreaText));
		resourceDescription_AreaText.clear();
		resourceDescription_AreaText.sendKeys(resourceDescription);
		LogManager.info("Resource Description: <" + resourceDescription+ "> was entered");
		return this;
	}

	public ResourcePage clickSaveResourceButton() {
		(new WebDriverWait(driver, 60))
				.until(ExpectedConditions
						.visibilityOf(saveResource_Button));
		saveResource_Button.click();
		(new WebDriverWait(driver, 60)).until(ExpectedConditions
				.invisibilityOfElementLocated(By.cssSelector(CreateResourceConstant.SAVE_BUTTON)));
		LogManager.info("Save Resource button was clicked");
		return new ResourcePage(driver);
	}
}
