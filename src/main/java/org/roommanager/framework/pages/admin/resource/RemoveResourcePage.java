package org.roommanager.framework.pages.admin.resource;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class RemoveResourcePage {
	private WebDriver driver;
	public RemoveResourcePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
}
