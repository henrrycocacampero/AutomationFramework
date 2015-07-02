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

public class ResourcePage extends LeftMenu {
	private WebDriver driver;
	public final static String addResourceButton = ResourceConstant.ADD_RESOURCE_BUTTON;
	public final static String removeResourceButton = ResourceConstant.REMOVE_RESOURCE_BUTTON;
	public final static String nextPageButton = ResourceConstant.NEXT_PAGE_BUTTON;
	public final static String nextPageInput = ResourceConstant.NEXT_PAGE_INPUT;
	public final static String resourceTableNumberOfPages = ResourceConstant.RESOURCES_TABLE_NUMBER_OF_PAGES;
	public final static String resourceList = ResourceConstant.RESOURCES_LIST;
	public final static String resourceListItem = ResourceConstant.RESOURCE_TABLE_ITEM;
	public final static String divElement = ResourceConstant.DIV_ELEMENT;

	@FindBy(xpath = addResourceButton)
	WebElement addResource_Button;
	@FindBy(id = removeResourceButton)
	WebElement removeResource_Button;
	@FindBy(xpath = nextPageButton)
	WebElement nextPage_Button;
	@FindBy(xpath = nextPageInput)
	WebElement nextPage_Input;
	@FindBy(xpath = resourceTableNumberOfPages)
	WebElement resource_TableNumberOfPages;
	@FindBy(xpath = resourceList)
	WebElement resource_List;
	@FindBy(xpath = resourceListItem)
	WebElement resource_ListItem;
	@FindBy(xpath = divElement)
	WebElement div_Element;

	public ResourcePage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public CreateResourcePage clickAddResourceButton() {
		(new WebDriverWait(driver, 60)).until(ExpectedConditions.visibilityOf(addResource_Button));
		addResource_Button.click();
		//Log.info("Add Resource button was clicked");
		return new CreateResourcePage(driver);
	}

	public RemoveResourcePage clickRemoveResourceButton() {
		(new WebDriverWait(driver, 60)).until(ExpectedConditions.visibilityOf(removeResource_Button));
		removeResource_Button.click();
		//Log.info("Remove Resource button was clicked");
		return new RemoveResourcePage(driver);
	}

	public ResourceInfoPage doubleClickOnResourceFromTable(String resourceName) {
		WebElement resource = getResourceFromAllPagesByName(resourceName,
				getResourcesTableNumberOfPages());
		String resourceItemName = resource.findElement(By.xpath(resourceListItem)).getText();
		Actions action = new Actions(driver);
		action.doubleClick(resource);
		action.perform();
		//Log.info("Double Click on Resource: <" + resourceItemName+ "> from Resources Table");
		return new ResourceInfoPage(driver);
	}

	public ResourcePage clickOnResourceFromTable(String resourceName) {
		WebElement resource = getResourceFromAllPagesByName(resourceName,
				getResourcesTableNumberOfPages());
		String resourceItemName = resource.findElement(By.xpath(resourceListItem))
				.getText();
		resource.click();
		//Log.info("Click on Resource: <" + resourceItemName+ "> from Resources Table");
		return this;
	}

	public String getResourceNameInTable(String resourceName) {
		WebElement resource = getResourceFromAllPagesByName(resourceName,
				getResourcesTableNumberOfPages());
		String resourceItemName = resource.findElement(By.xpath(resourceListItem))
				.getText();
		//Log.info("Resource Name: <" + resourceItemName+ "> was retrieved");
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
				//Logger.info("Resource: <" + resourceName+ "> was found in page:" + index);
				return resource;
			}
			clickNextPageButton(index + 1, numberOfPages);
			//Log.info("Searching for resource in page: " + index);
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
		//Log.info("The Next Page button was clicked");
	}

	private int getResourcesTableNumberOfPages() {
		(new WebDriverWait(driver, 60)).until(ExpectedConditions.visibilityOf(resource_TableNumberOfPages));
		String pages = resource_TableNumberOfPages.getText().replace("/ ", "");
		//Log.info("The number of Pages of the Resources Table is: "+ Integer.parseInt(pages));
		return Integer.parseInt(pages);
	}

	private WebElement getResourceByName(String resourceName) {
		(new WebDriverWait(driver, 60)).until(ExpectedConditions.visibilityOf(resource_List));
		List<WebElement> resourcesTable = resource_List
				.findElements(By.xpath(divElement));
		for (WebElement resource : resourcesTable) {
			String resourceItemName = resource.findElement(
					By.xpath(resourceListItem)).getText();
			if (resourceItemName.equals(resourceName)) {
				//Log.info("Resource: <" + resourceItemName+ "> was retrieved from Resources Table");
				return resource;
			}
		}
		//Log.info("Resource: <" + resourceName + "> wasn't found");
		return null;
	}

}