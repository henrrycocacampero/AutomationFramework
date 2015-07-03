package org.roommanager.framework.pages.admin.resource;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.roommanager.framework.models.admin.resource.CreateResourceConstant;

public class ResourceInfoPage {
	private WebDriver driver;
	public final static String resourceNameTextField = CreateResourceConstant.RESOURCE_NAME_FIELD;
	public final static String resourceDisplayNameTextField = CreateResourceConstant.RESOURCE_DISPLAY_NAME_FIELD;
	public final static String resourceDescriptionAreaText = CreateResourceConstant.RESOURCE_DESCRIPTION_AREA;
	public final static String saveResourceButton = CreateResourceConstant.SAVE_BUTTON;
	@FindBy(xpath = resourceNameTextField)
	WebElement resourceName_TextField;
	@FindBy(xpath = resourceDisplayNameTextField)
	WebElement resourceDisplayName_TextField;
	@FindBy(xpath = resourceDescriptionAreaText)
	WebElement resourceDescription_AreaText;
	@FindBy(css = saveResourceButton)
	WebElement saveResource_Button;

	public ResourceInfoPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public ResourceInfoPage enterResourceName(String resourceName) {
		(new WebDriverWait(driver, 60))
				.until(ExpectedConditions
						.visibilityOf(resourceName_TextField));
		resourceName_TextField.clear();
		resourceName_TextField.sendKeys(resourceName);
		//Logger.info("Resource Name: <" + resourceName + "> was entered");
		return this;
	}
	public ResourceInfoPage clickSaveResourceButton() {
		(new WebDriverWait(driver, 60))
				.until(ExpectedConditions
						.visibilityOf(saveResource_Button));
		saveResource_Button.click();
		(new WebDriverWait(driver, 60)).until(ExpectedConditions
				.invisibilityOfElementLocated(By.cssSelector(saveResourceButton)));
		//Logger.info("Save Resource button was clicked");
		return new ResourceInfoPage(driver);
	}

}
