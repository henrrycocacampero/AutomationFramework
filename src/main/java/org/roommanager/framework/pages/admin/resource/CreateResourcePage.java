package org.roommanager.framework.pages.admin.resource;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.roommanager.framework.pages.admin.common.LeftMenu;

public class CreateResourcePage  {
	private WebDriver driver;
	public CreateResourcePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
}
