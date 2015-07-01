package org.roommanager.framework.pages.admin;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.roommanager.framework.models.admin.ResourceAddConstantExample;
import org.roommanager.framework.models.admin.ResourceConstantExample;


/**
 * This page is needed to create a new resource. 
 * @author Milenca Ventura
 */

public class ResourcePageExample {
	private WebDriver driver;
	
	public final static String btnAdd = ResourceConstantExample.BTNADD;
	public final static String btnResource = ResourceConstantExample.BTNRESOURCE;
	public final static String txtSearch = ResourceConstantExample.TXTSEARCH;
	public final static String firstResource = ResourceConstantExample.FIRSTRESOURCE;
	public final static String checkResource = ResourceConstantExample.SELECTRESOURCE;
	public final static String btnRemove = ResourceConstantExample.BTNREMOVE;
	public final static String allResources = ResourceConstantExample.ALLRESOURCES;
	public final static String elementDiv = ResourceConstantExample.DIVELEMENT;
	public final static String textElement = ResourceConstantExample.RESOURCESTEXT;
	public final static String resourceElement = ResourceConstantExample.RESOURCECUSTOMNAME;
	public final static String errorMessage = ResourceAddConstantExample.ERRORMESSAGE;

	@FindBy (xpath = btnAdd) WebElement addBtn;
	@FindBy (id = btnRemove) WebElement removeBtn;
	@FindBy (css = btnResource) WebElement resourceBtn;
	@FindBy (xpath = txtSearch) WebElement setTxtSearch;
	@FindBy (css = firstResource) WebElement resourceFirst;
	@FindBy (css = checkResource) WebElement Resourcecheck;
	@FindBy (xpath = allResources) WebElement listResource;
	@FindBy (xpath = resourceElement) WebElement elementResource;
	@FindBy (xpath = textElement) WebElement elementText;
	@FindBy (xpath = elementDiv) WebElement divElement;
	
	
    Logger logger=Logger.getLogger("test Resource");
    
    public ResourcePageExample(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
    
    public ResourcePageExample btnRemoveResource(){  	
    	(new WebDriverWait(driver,30)).until(ExpectedConditions.visibilityOf(removeBtn));
    	removeBtn.click();
	    logger.info("Resource: Click on remove button");
    	  	return this;
        }
    /**
	 * This method do click on add button 
	 * @return ResourcePage
	 */
	public  ResourcePageExample btn_Add(){
		(new WebDriverWait(driver,30)).until(ExpectedConditions.visibilityOf(addBtn));
		addBtn.click();
	    logger.info("Resource: Click on save button");
	    return this;
	}

	/**
	 * This method is for list of resources, and search a specific resource 
	 * @param name
	 * @return WebElement
	 */
    private WebElement getResource(String name){
		WebElement resource = (new WebDriverWait(driver, 160))
			.until(ExpectedConditions.visibilityOf(listResource));
		List<WebElement> ResourcesElement = resource.findElements(By.xpath(elementDiv));
		 for (WebElement webElement : ResourcesElement) {
				String resourceName = webElement.findElement(By.xpath(textElement)).getText();
			if (name.equals(resourceName)) {
				return webElement;
			}
		}
		 return null;
	}
    /**
	 * This method is for list of resources, and search a specific resource by display name
	 * @param customName
	 * @return WebElement
	 */
    private WebElement getCustomNameResource(String customName){
		WebElement resource = (new WebDriverWait(driver, 160))
			.until(ExpectedConditions.visibilityOf(listResource));
		List<WebElement> ResourcesElement = resource.findElements(By.xpath(elementDiv));
		 for (WebElement webElement : ResourcesElement) {
				String resourceCustomName = webElement.findElement(By.xpath(textElement)).getText();
			if (customName.equals(resourceCustomName)) {
				return webElement;
			}
		}
		 return null;
	}
    /**
	 * This method is for select a resource
	 * @param name
	 * @return ResourcePage
	 */
    public ResourcePageExample   clickSelectResource(String name){
    	Actions act = new Actions(driver);
    	WebElement resource = getResource(name);
    	act.doubleClick(resource);
    	act.perform();
    	return this;
    }
    /**
	 * This method is for check a resource
	 * @param name
	 * @return ResourcePage
	 */
    public ResourcePageExample checkResource(String name){
    	WebElement resource = getResource(name);
    	resource.click();
		logger.info("Resource: check in resource");
		return this;
	}
    /**
	 * This method is boolean for verify if the resource exist.
	 * @param name
	 * @return resource
	 */
	public boolean getAllresource(String name){
		return getResource(name) == null;
	}
	/**
	 * This method is for get a resource by name
	 * @param name
	 * @return text of the element
	 */
	public String getResourceName(String name){
		WebElement resource = getResource(name);
		return resource.findElement(By.xpath(textElement)).getText();
	}
	/**
	 * This method is for get a resource by  custom name
	 * @param customName
	 * @return text of the element
	 */
	public String getResourceCustomName(String customName){
		WebElement resource = getCustomNameResource(customName);
		return resource.findElement(By.xpath(textElement)).getText();
	}
	/**
	 * This method is for verify the message.
	 * @param customName
	 * @return text of the element
	 */
	public 	String verificationResourceWithoutName(String expectedResult){
			String message = driver.findElement(By.xpath(textElement)).getText();
			logger.info("Resource: verification values");
			return message;
	}
}
