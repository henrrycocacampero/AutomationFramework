package org.roommanager.framework.pages.admin.resource;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class ResourceInfoPage {
	private WebDriver driver;
	public ResourceInfoPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
}
