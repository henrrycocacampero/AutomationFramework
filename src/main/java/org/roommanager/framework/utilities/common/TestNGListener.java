package org.roommanager.framework.utilities.common;

import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

public class TestNGListener extends TestListenerAdapter{
	  
	  @Override
	  public void onTestSuccess(ITestResult tr){
		  String testName = tr.getTestName();
		  LogsMessages.info("Test : \"" + testName + "\" PASSED" );
		  ReportManager.appendTestCaseName(testName);
		  
	  }
	  
	  @Override
	  public void onTestFailure(ITestResult tr){
		  String testName = tr.getTestName();
		  LogsMessages.info("Test : \"" + testName + "\" FAILED" );
		  ReportManager.appendTestCaseName(testName);
	  }
	  
	  @Override
	  public void onTestStart(ITestResult tr){
		  String testName = tr.getTestName();
		  LogsMessages.info("Test : \"" + testName + "\" STARTED" );
		  ReportManager.appendTestCaseName(testName);
	  }
	  
}
