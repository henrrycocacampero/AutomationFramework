package org.roommanager.framework.pages.admin;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.roommanager.framework.models.admin.ResourceAddConstantExample;

/**
 * This page is needed to create a new resource. 
 * @author Milenca Ventura
 */

public class ResourceCreatePageExample {
	
	private WebDriver driver;
	public final static String txtName = ResourceAddConstantExample.TXTNAME;
	public final static String txtDisplayName = ResourceAddConstantExample.TXTDISPLAYNAME;
	public final static String btnSave = ResourceAddConstantExample.BTNSAVE;
	public final static String btnClose = ResourceAddConstantExample.BTNCLOSE;

	 @FindBy (xpath = txtName)WebElement setName;
	 @FindBy (xpath = txtDisplayName)WebElement setDisplayName;
	 @FindBy (css = btnSave)WebElement saveBtn;
	 @FindBy (css = btnClose)WebElement closeBtn;
	 
    Logger logger=Logger.getLogger("test Resource");
    
	public ResourceCreatePageExample(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	/**
	 * This method does click on close button
	 * @return ResourcePage
	 */
    public  ResourcePageExample btn_Close(){
    	(new WebDriverWait(driver,30)).until(ExpectedConditions.visibilityOf(closeBtn));
    	closeBtn.click();
	    logger.info("Resource: Click on close button");
	    return new ResourcePageExample(driver);
    }
    /**
	 * This method set a name of the resource
	 * @return ResourceCreatePage
	 */
    public  ResourceCreatePageExample txt_name(){
    	(new WebDriverWait(driver,30)).until(ExpectedConditions.visibilityOf(setName));
    	//WebElement element =driver.findElement(txtName);
    	setName.clear();
    	setName.sendKeys("Resource1");
        logger.info("Resource: Set a name of resource");
	    return this;
    }
    /**
	 * This method set a display name of the resource
	 * @return ResourceCreatePage
	 */
    public ResourceCreatePageExample txt_displayName(){
    	(new WebDriverWait(driver,30)).until(ExpectedConditions.visibilityOf(setDisplayName));
    	setDisplayName.clear();
    	setDisplayName.sendKeys("Calidad");
	    logger.info("Resource: Set a display name of resource");
	    return this;
    }
    /**
	 * This method do click on save button.
	 * @return ResourcePage
	 */
  public ResourcePageExample clickSaveButton(){
    	(new WebDriverWait(driver,60)).until(ExpectedConditions.visibilityOf(saveBtn));
    	saveBtn.click();
	    logger.info("Resource: Click on save button");	    
	    return new ResourcePageExample(driver);
    }
    public ResourcePageExample btn_Save(){
    	(new WebDriverWait(driver,60)).until(ExpectedConditions.visibilityOf(saveBtn));
    	saveBtn.click();
	    logger.info("Resource: Click on save button");
	    (new WebDriverWait(driver,60)).until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(btnClose)));
	    return new ResourcePageExample(driver);
    }
}
