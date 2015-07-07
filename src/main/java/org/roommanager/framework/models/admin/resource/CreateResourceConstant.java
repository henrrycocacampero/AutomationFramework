package org.roommanager.framework.models.admin.resource;
/**
 * The CreateResourceConstant class provides the locators from 
 * Create Resource Tab.
 * 
 * @author Qadev02
 * 
 */
public class CreateResourceConstant {
        
	/** RESOURCE_NAME_FIELD locator to Resource Name text field*/
    public static final String RESOURCE_NAME_FIELD="(//input[@type='text'])[3]";
    
    /** RESOURCE_DISPLAY_NAME_FIELD locator to Resource Display Name text field*/
    public static final String RESOURCE_DISPLAY_NAME_FIELD="(//input[@type='text'])[4]";
    
    /** RESOURCE_DESCRIPTION_AREA locator to Resource Description text area*/
    public static final String RESOURCE_DESCRIPTION_AREA="//textarea";
    
    /** SAVE_BUTTON locator to Save button*/
    public static final String SAVE_BUTTON="button.info";
}
	   