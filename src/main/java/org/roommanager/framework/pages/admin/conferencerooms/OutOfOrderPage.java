package org.roommanager.framework.pages.admin.conferencerooms;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.roommanager.framework.models.admin.conferencerooms.ConferenceRoomConstant;
import org.roommanager.framework.models.admin.conferencerooms.OutOfOrderConstant;
import org.roommanager.framework.utilities.common.LogManager;


public class OutOfOrderPage {

	WebDriver driver;	
	
	@FindBy (xpath = OutOfOrderConstant.START_HOUR_OUT_OF_ORDER) 
	private WebElement startHourOutOfOrder;
	@FindBy (xpath = OutOfOrderConstant.START_MINS_OUT_OF_ORDER) 
	private WebElement startMinsOutOfOrder  ;
	@FindBy (xpath = OutOfOrderConstant.START_AM_PM_OUT_OF_ORDER) 
	private WebElement startAmPmOutOfOrder;
	@FindBy (xpath = OutOfOrderConstant.END_HOUR_OUT_OF_ORDER) 
	private WebElement endHourOutOfOrder;
	@FindBy (xpath = OutOfOrderConstant.END_MINS_OUT_OF_ORDER) 
	private WebElement endMinsOutOfOrder;
	@FindBy (xpath = OutOfOrderConstant.END_AM_PM_OUT_OF_ORDER) 
	private WebElement endAmPmOutOfOrder;
	@FindBy (xpath = OutOfOrderConstant.TITLE_OUT_OF_ORDER) 
	private WebElement titleOutOfOrder;
	@FindBy (xpath = OutOfOrderConstant.DESCRIPTIOM_OUT_OF_ORDER) 
	private WebElement descriptionOutOfOrder;
	@FindBy (xpath = OutOfOrderConstant.SAVE_BUTTON_OUT_OF_ORDER) 
	private WebElement saveButtonOutOfOrder;
	
	@FindBy (css = ConferenceRoomConstant.TITLE_TABLE_ROOMS) 
	private WebElement titleTableRooms;
		

	public OutOfOrderPage(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public String getTitle(){
		return getValueTextField(new WebDriverWait(driver,60).
		until(ExpectedConditions.visibilityOf(titleOutOfOrder)));
	}
	
	public void setStartTime(){
		ArrayList<String> startTime= intervalTime();
		System.out.println(startTime);
		new WebDriverWait(driver,60).
		until(ExpectedConditions.visibilityOf(startHourOutOfOrder));
		new WebDriverWait(driver,60).
		until(ExpectedConditions.visibilityOf(startMinsOutOfOrder));
		new WebDriverWait(driver,60).
		until(ExpectedConditions.visibilityOf(startAmPmOutOfOrder));
		startHourOutOfOrder.clear();
		startHourOutOfOrder.sendKeys(startTime.get(0));
		startMinsOutOfOrder.clear();
		startMinsOutOfOrder.sendKeys(startTime.get(1));
		startAmPmOutOfOrder.clear();
		startAmPmOutOfOrder.sendKeys(startTime.get(2));
		    //Logger.info("Set <To: Time Out Of Order> by: "+ startTime.get(0)+":"+ startTime.get(1) +" "+ startTime.get(2));
	}
	public void setEndTime(){
		ArrayList<String> endTime= intervalTime();
		new WebDriverWait(driver,60).
		until(ExpectedConditions.visibilityOf(endHourOutOfOrder));
		new WebDriverWait(driver,60).
		until(ExpectedConditions.visibilityOf(endMinsOutOfOrder));
		new WebDriverWait(driver,60).
		until(ExpectedConditions.visibilityOf(endAmPmOutOfOrder));
		endHourOutOfOrder.clear();
		startHourOutOfOrder.sendKeys(endTime.get(3));
		endMinsOutOfOrder.clear();
		startHourOutOfOrder.sendKeys(endTime.get(4));
		endAmPmOutOfOrder.clear();
		startHourOutOfOrder.sendKeys(endTime.get(5));		
		    //LoggerManager.info("Set <To: Time Out Of Order> by: "
		    					//+ endTime.get(3) +":"+ endTime.get(4)+" "+ endTime.get(5));
	}
	public void setDescription(){
		
		new WebDriverWait(driver,60).
		until(ExpectedConditions.visibilityOf(descriptionOutOfOrder));
		descriptionOutOfOrder.clear();
		descriptionOutOfOrder.sendKeys("OutOfOrder in the room");
		LogManager.info("OutOfOrderPage - Description value in the room ");	
			
	}
	public String setRandomTitle() {
	
	    String [] combo = { "Temporarily Out of Order", 
			        		"Closed for maintenance", 
			        		"Closed for reparations", 
			        		"Reserved for a special event",
	    					"Different: AGARSTSASDFAS"};        
	    Random random = new Random();
	    // randomly selects an index from the combo
	    int select = random.nextInt(combo.length); 
	    new WebDriverWait(driver,80).
	    until(ExpectedConditions.visibilityOf(titleOutOfOrder));        
	            
	    titleOutOfOrder.clear();    
		titleOutOfOrder.sendKeys(combo[select]);
		//LoggerManager.info("Random Title: "+combo[select]);
	return combo[select];
	}	
	public static String getValueTextField(WebElement textFieldName){
		
		String getNameRoom = textFieldName.getAttribute("Value");
		LogManager.info("The value is: <" + getNameRoom+ ">");
		return getNameRoom;
	}	
	public static ArrayList<String> intervalTime() {
		  ArrayList<String> intervalTime = new ArrayList<String>(); 		 
		  Calendar time = Calendar.getInstance();  
		  
		    intervalTime.add((time.get(Calendar.HOUR)+1)+"");
			intervalTime.add(time.get(Calendar.MINUTE)+"");
			if ((time.get(Calendar.AM_PM)+"").equals("0")){				
				intervalTime.add("AM");}
			else{
				intervalTime.add("PM");}
			intervalTime.add((time.get(Calendar.HOUR)+2)+"");
			intervalTime.add(time.get(Calendar.MINUTE)+"");
			if ((time.get(Calendar.AM_PM)+"").equals("0")){				
				intervalTime.add("AM");}
			else{
				intervalTime.add("PM");}	
			
			return intervalTime;
	}
	public OutOfOrderPage clickSaveButtonOutOfOrder(){
		(new WebDriverWait(driver,60)).until(ExpectedConditions.visibilityOf(saveButtonOutOfOrder));
		saveButtonOutOfOrder.click();
		LogManager.info("Was Clicking Save Button Out Of Order");
		return new OutOfOrderPage(driver);
	}
	
	
	
	public boolean existOutOfOrder(String roomName){

		/*Looking the room*/		
		boolean found= false;		
		
		new WebDriverWait(driver,80).until(ExpectedConditions.visibilityOf(titleTableRooms));
		
		WebElement list = driver.findElement(By.xpath(ConferenceRoomConstant.LIST_ROOM));
		List<WebElement> subList = list.findElements((ConferenceRoomConstant.SUBLIST_ROOM)); 		
	    
		for(int i = 0 ; i < subList.size(); i+=1){
	    	String valueGet = (subList.get(i).getText()).trim();
	    	String valueRoom = roomName.trim();	    	
	    	if(valueGet.equals(valueRoom)){
	    		System.out.println("Room:" + subList.get(i).getText()+ " Found!");	  
	    		try{
	    			subList.get(i).
	    			findElement(By.xpath("div[2]/div[2]/out-of-order-icon/div/div/div/span"));
	    			found = true;
	    		} 
	    		catch(Exception e)
	    		{	    			
	    		}   		 
	    		break;
	    	}	    	    	
	    }	
		System.out.println(subList.size());
	    LogManager.info("Out Of Order Planning exist? "+ found);
	    return found;	
	}
}
