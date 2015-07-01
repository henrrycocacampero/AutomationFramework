package org.roommanager.framework.utilities;

import java.io.File; 
import java.io.IOException; 
import java.text.DateFormat; 
import java.text.SimpleDateFormat; 
import java.util.Date; 
import org.apache.commons.io.FileUtils; 
import org.openqa.selenium.OutputType; 
import org.openqa.selenium.TakesScreenshot; 
import org.openqa.selenium.WebDriver; 
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult; 
import org.testng.Reporter; 
import org.testng.TestListenerAdapter; 


public class ScreenShotManager extends TestListenerAdapter { 
	
	@Override 
	public void onTestFailure(ITestResult tr) { 
		
		WebDriver driver = new ChromeDriver();	 
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE); 
		DateFormat dateFormat = new SimpleDateFormat("dd_MMM_yyyy__hh_mm_ssaa"); /**/
		String destDir = "./screenshots"; 
		new File(destDir).mkdirs(); 
		String destFile = tr.getName()+"-"+dateFormat.format(new Date()) + ".png"; 
		try { 
			FileUtils.copyFile(scrFile, new File(destDir + "/" + destFile)); 
			} 
			catch (IOException e) { 
								e.printStackTrace();
								System.out.println("Test Failure - Screenshoot saved!");	
							} 
		Reporter.setEscapeHtml(false); 
		Reporter.log("Saved <a href=../screenshots/" + destFile + ">Screenshot</a>");
	} 
}
