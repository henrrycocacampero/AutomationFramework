package org.roommanager.framework.utilities.common;

import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

public class TestNGListener extends TestListenerAdapter{
	  
	  @Override
	  public void onTestSuccess(ITestResult tr){
		  String testName = tr.getTestContext().getName();
		  LogsMessages.info("Test : \"" + testName + "\" PASSED" );
		  ReportManager.appendTestCaseName(testName);
	  }
	  
	  @Override
	  public void onTestFailure(ITestResult tr){
		  String testName = tr.getTestContext().getName();
		  String errorMessage = tr.getThrowable().getMessage();
		  LogsMessages.info("Test : \"" + testName + "\" FAILED" );
		  String filePath = ScreenShotManager.takeScreenShot(testName);
		  ReportManager.appendTestCaseErrorMessage(testName, errorMessage);
		  ReportManager.appendImageHyperLink(filePath);
	  }
	  
	  @Override
	  public void onTestStart(ITestResult tr){
		  String testName = tr.getTestContext().getName();
		  LogsMessages.info("Test : \"" + testName + "\" STARTED" );
		  ReportManager.appendTestCaseName(testName);
	  }
}
