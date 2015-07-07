package org.roommanager.framework.models.admin.conferencerooms;

import org.openqa.selenium.By;


public class ConferenceRoomConstant {
	/**
	 * Locator Type = Xpath*/
	public static final By ROOM_NAME = By.xpath("div[3]/div[2]/div/span[2]");
	
	/**
	 * Locator Type = Css*/
	public static final String TITLE_TABLE_ROOMS = "div.ngGroupPanelDescription.ng-binding";
	
	/**
	 * Locator Type = Xpath*/
	public static final String LIST_ROOM = "//div[@id='roomsGrid']/div[2]/div";
	
	/**
	 * Locator Type = Xpath*/
	public static final By DIV_ELEMENT = By.xpath("div");
	/**
	 * Locator Type = Xpath*/
	public static final String LIST_RESOURCE = "//";
	/**
	 * Locator Type = Xpath**/
	public static final String RESOURCE_NAME = "span/i";
	/**
	 * Locator Type = Xpath*/
	public static final String PAGE_SIZE = "//div[@id='roomsGrid']/div[3]/div/div[2]/div/select";
	/**
	 * Locator Type = Xpath*/
	public static final String NUMBER_PAGE = "//input[@type='number']";
	/**
	 * Locator Type = Xpath*/
	public static final String ROOM_ENABLED = "div/div[2]/div/div/div/span";
	public static final String NEXT_PAGE_BUTTON = "(//button[@type='button'])[4]";
	/*CSS*/
	public static final String BACK_PAGE_BUTTON ="=button.ngPagerButton.firepath-matching-node";
	
}
