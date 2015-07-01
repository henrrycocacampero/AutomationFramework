package org.roommanager.framework.utilities;

import org.testng.Reporter;

public class ReportManager {
	private static String REPORTNG_PROPERTY = "org.uncommons.reportng.escape-output";
	
	public static void appendImageHyperLink(String filePath){
		System.setProperty(REPORTNG_PROPERTY , "false");
		String failedTestImageLinkTag = "<a href=\"[filePath]"
	            + "\"><img src=\"file:///[filePath]"
	            + "\" alt=\"\"" + "height='100' width='100'/> <br />";
		failedTestImageLinkTag = failedTestImageLinkTag
				.replace("[filePath]", filePath);
		
		enableHtmlReportEdition();
		Reporter.log(failedTestImageLinkTag); 
	}
	
	public static void appendTestCaseErrorMessage(String testName, String message){

		String errorMessageTag = "<a href=\"javascript:toggleElement('[testCaseName]', 'block')\" title=\"Click to expand/collapse\"><b>[testCaseName]</b></a>";
		errorMessageTag = errorMessageTag + "<div class=\"stackTrace\" id=\"[testCaseName]\" style=\"display: none;\">[message]</div>";
		errorMessageTag = errorMessageTag
				.replace("[testCaseName]", testName)
				.replace("[message]", message);
		
		enableHtmlReportEdition();
		Reporter.log(errorMessageTag); 
	}
	
	public static void appendTestCaseName(String testName){

		String errorMessageTag = "<b>[testCaseName]</b>";
		errorMessageTag = errorMessageTag
				.replace("[testCaseName]", testName);
		
		enableHtmlReportEdition();
		Reporter.log(errorMessageTag); 
	}
	
	private static void enableHtmlReportEdition(){
		System.setProperty(REPORTNG_PROPERTY , "false");
	}
	
	
	
	
}