package org.roommanager.framework.models.tablet.scheduler;

public class SchedulerConstant {
	public static final String ORGANIZER_TEXT_FIELD = "//input[@id='txtOrganizer']";
	public static final String SUBJECT_TEXT_FIELD = "//input[@id='txtSubject']";
	public static final String ATTENDEES_TEXT_FIELD = "//rm-tag-input/div/input";
	public static final String CREATE_BUTTON = "//button[1]";
	public static final String UPDATE_BUTTON = "//button[2]";
	public static final String REMOVE_BUTTON = "//button[3]";	
	public static final String CONFIRMATION_MESSAGE = "//div[2]/div[2]/div[2]/div/div[2]/div";
	public static final String BODY_TEXT_AREA = "//textarea[@id='txtBody']";
	public static final String ATTENDEES_LIST = "//ul[contains(@class,'list-inline')]";
	public static final String ATTENDEE_TEXT = "li/span";
	public static final String MEETING_SUBJECT_ERROR_MESSAGE = "//div[2]/div/div/div/div[3]/div/div[1]/div[2]/div/div/div[2]/div[1]/form/div[2]/small";
	public static final String MEETING_ORGANIZER_ERROR_MESSAGE = "//div[2]/div/div/div/div[3]/div/div[1]/div[2]/div/div/div[2]/div[1]/form/div[1]/small";
	public static final String MEETING_BOX = "div/div/div[2]/div/div";
	public static final String ROOM_TIMELINE = "//div[@id='timelinePanel']/rm-vis/div/div[4]";	
}
