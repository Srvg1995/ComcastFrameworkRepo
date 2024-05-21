package com.comcast.crm.generic.listenerutility;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.comcast.crm.basetest.BaseClass;
import com.comcast.crm.generic.webdriverutility.UtilityClassobject;

public class ListenerImpClass implements ITestListener,ISuiteListener{
	
	
	public ExtentReports report; //If we want to use this report in every TC,make this variable as static.
	public static ExtentTest test; //If we make it as Static variable without threads,this will not participating in parallel execution,so need to create a class like this(public class UtilityClassobject)which is done now in webdriver utility.
	//To get all these below @Override Methods,we should Rightclick-select source-click on override/implement methods option-select for which we need to override-click on OK.
	
	@Override
	public void onStart(ISuite suite) {     //This method is exactly similar to @BS    
		System.out.println("Report configuration");
		String time = new Date().toString().replace(" ", "_").replace(":", "_"); //Here we are using replace methods,otherwise we get the output with space&colon like this(Tue May 07 13:07:51 IST 2024) which can't be used as our file name) 

		//Spark report config
		ExtentSparkReporter spark=new ExtentSparkReporter("./AdvanceReport/report_"+time+".html"); //Here Concatenated with 'time' variable in order to get TimeStamp.
		spark.config().setDocumentTitle("CRM Test Suite Results");
		spark.config().setReportName("CRM Report");
		spark.config().setTheme(Theme.DARK);

		//add Environment information & create test
		report=new ExtentReports();
		report.attachReporter(spark);
		report.setSystemInfo("OS", "Windows-11");
		report.setSystemInfo("BROWSER", "CHROME-100");
	}

	@Override
	public void onFinish(ISuite suite) {  //This method is exactly similar to @AS  
		System.out.println("Report backUp");
		report.flush();  //This method will takecare of storing the result once the execution is done.
	}

	@Override
	public void onTestStart(ITestResult result) {
		System.out.println("=============="+result.getMethod().getMethodName()+"=========START==========");
		test=report.createTest(result.getMethod().getMethodName()); //This is to get the Screenshot on run time
		UtilityClassobject.setTest(test);
		test.log(Status.INFO, result.getMethod().getMethodName()+"===STARTED===");
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		System.out.println("=============="+result.getMethod().getMethodName()+"=========END==========");
		test.log(Status.PASS, result.getMethod().getMethodName()+"===COMPLETED===");
	}

	@Override //This below code of ScreenShot is very older one,so we can use the program which is done by Sandeep sir only.
	public void onTestFailure(ITestResult result) {
		String testName = result.getMethod().getMethodName();  //Here instead of using getMethod().getMethodName(),we can use directly getName()also.
		TakesScreenshot eDriver=(TakesScreenshot) BaseClass.sdriver;
		String filePath = eDriver.getScreenshotAs(OutputType.BASE64);
		
		String time = new Date().toString().replace(" ", "_").replace(":", "_"); //Here we are using replace methods,otherwise we get the output with space&colon like this(Tue May 07 13:07:51 IST 2024) which can't be used as our file name) 
		
		test.addScreenCaptureFromBase64String(filePath, testName);
		test.log(Status.FAIL, result.getMethod().getMethodName()+"===FAILED===");


	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		
	}
}

