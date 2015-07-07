package org.roommanager.framework.pages.admin.resource;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.roommanager.framework.models.admin.resource.ResourceConstant;
import org.roommanager.framework.pages.admin.common.LeftMenu;
import org.roommanager.framework.utilities.common.LogManager;

public class ResourcePage extends LeftMenu {
	private WebDriver driver;

	@FindBy(xpath = ResourceConstant.ADD_RESOURCE_BUTTON)
	private WebElement addResource_Button;
	@FindBy(id = ResourceConstant.REMOVE_RESOURCE_BUTTON)
	private WebElement removeResource_Button;
	@FindBy(xpath = ResourceConstant.NEXT_PAGE_BUTTON)
	private WebElement nextPage_Button;
	@FindBy(xpath = ResourceConstant.NEXT_PAGE_INPUT)
	private WebElement nextPage_Input;
	@FindBy(xpath = ResourceConstant.RESOURCES_TABLE_NUMBER_OF_PAGES)
	private WebElement resource_TableNumberOfPages;
	@FindBy(xpath = ResourceConstant.RESOURCES_LIST)
	private WebElement resource_List;
	@FindBy(xpath = ResourceConstant.RESOURCE_TABLE_ITEM)
	private WebElement resource_ListItem;
	@FindBy(xpath = ResourceConstant.DIV_ELEMENT)
	private WebElement div_Element;

	public ResourcePage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public CreateResourcePage clickAddResourceButton() {
		(new WebDriverWait(driver, 60)).until(ExpectedConditions.visibilityOf(addResource_Button));
		addResource_Button.click();
		LogManager.info("Add Resource button was clicked");
		return new CreateResourcePage(driver);
	}
	
	public CreateResourcePage doubleClickOnResourceFromTable(String resourceName) {
		WebElement resource = getResourceFromAllPagesByName(resourceName,
				getResourcesTableNumberOfPages());
		String resourceItemName = resource.findElement(By.xpath(ResourceConstant.RESOURCE_TABLE_ITEM)).getText();
		Actions action = new Actions(driver);
		action.doubleClick(resource);
		action.perform();
		LogManager.info("Double Click on Resource: <" + resourceItemName+ "> from Resources Table");
		return new CreateResourcePage(driver);
	}

	public ResourcePage clickOnResourceFromTable(String resourceName) {
		WebElement resource = getResourceFromAllPagesByName(resourceName,
				getResourcesTableNumberOfPages());
		String resourceItemName = resource.findElement(By.xpath(ResourceConstant.RESOURCE_TABLE_ITEM))
				.getText();
		resource.click();
		LogManager.info("Click on Resource: <" + resourceItemName+ "> from Resources Table");
		return this;
	}

	public String getResourceNameInTable(String resourceName) {
		WebElement resource = getResourceFromAllPagesByName(resourceName,
				getResourcesTableNumberOfPages());
		String resourceItemName = resource.findElement(By.xpath(ResourceConstant.RESOURCE_TABLE_ITEM))
				.getText();
		LogManager.info("Resource Name: <" + resourceItemName+ "> was retrieved");
		System.out.println(resourceItemName);
		return resourceItemName;
	}

	public boolean verifyElementDoesNotExist(String resourceName) {
		WebElement resource = getResourceFromAllPagesByName(resourceName,
				getResourcesTableNumberOfPages());
		return resource == null ? true : false;
	}

	private WebElement getResourceFromAllPagesByName(String resourceName,
			int numberOfPages) {
		WebElement resource = null;
		for (int index = 1; index <= numberOfPages; index++) {
			resource = getResourceByName(resourceName);
			if (resource != null) {
				LogManager.info("Resource: <" + resourceName+ "> was found in page:" + index);
				return resource;
			}
			clickNextPageButton(index + 1, numberOfPages);
			LogManager.info("Searching for resource in page: " + index);
		}
		return resource;
	}

	private void clickNextPageButton(int actualPage, int numberOfPages) {
		(new WebDriverWait(driver, 60)).until(ExpectedConditions
				.visibilityOf(nextPage_Button));
		nextPage_Button.click();
		String nextPageinput = nextPage_Input
				.getAttribute("value");
		while (Integer.parseInt(nextPageinput) != actualPage
				&& actualPage <= numberOfPages) {
			nextPageinput = nextPage_Input
					.getAttribute("value");
		}
		LogManager.info("The Next Page button was clicked");
	}

	private int getResourcesTableNumberOfPages() {
		(new WebDriverWait(driver, 60)).until(ExpectedConditions.visibilityOf(resource_TableNumberOfPages));
		String pages = resource_TableNumberOfPages.getText().replace("/ ", "");
		LogManager.info("The number of Pages of the Resources Table is: "+ Integer.parseInt(pages));
		return Integer.parseInt(pages);
	}

	private WebElement getResourceByName(String resourceName) {
		(new WebDriverWait(driver, 60)).until(ExpectedConditions.visibilityOf(resource_List));
		List<WebElement> resourcesTable = resource_List
				.findElements(By.xpath(ResourceConstant.DIV_ELEMENT));
		for (WebElement resource : resourcesTable) {
			String resourceItemName = resource.findElement(
					By.xpath(ResourceConstant.RESOURCE_TABLE_ITEM)).getText();
			if (resourceItemName.equals(resourceName)) {
				LogManager.info("Resource: <" + resourceItemName+ "> was retrieved from Resources Table");
				return resource;
			}
		}
		LogManager.info("Resource: <" + resourceName + "> wasn't found");
		return null;
	}

}
