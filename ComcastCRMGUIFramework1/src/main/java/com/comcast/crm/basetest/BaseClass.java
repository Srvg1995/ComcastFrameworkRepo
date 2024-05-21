package com.comcast.crm.basetest;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.comcast.crm.generic.databaseutility.DataBaseUtility;
import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.generic.webdriverutility.UtilityClassobject;
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;
import com.comcast.crm.objectrepositoryutility.HomePage;
import com.comcast.crm.objectrepositoryutility.LoginPage;

public class BaseClass {
	/* Create Object*///Below Objects are created to access the respective methods & also we made it as public ,so that we can access it from outside the package (/class also)
	public FileUtility flib=new FileUtility();
	public ExcelUtility elib=new ExcelUtility();
	public JavaUtility jlib=new JavaUtility();
	public DataBaseUtility dblib=new DataBaseUtility();
	public WebDriverUtility wlib=new WebDriverUtility();
	public WebDriver driver;
	public static WebDriver sdriver; //To use it in the ListenerImpClass they made it static variable.

	@BeforeSuite //(groups={"smokeTest","regressionTest"})
	public void configBS() throws Throwable {
		System.out.println("=====Connect to DB , Report Config======");
		dblib.getDbconnection();
	}

	//@Parameters("BROWSER")  //@Parameters-This annotation should be selected from TestNG only.
	@BeforeClass//(groups={"smokeTest","regressionTest"})            
	public void configBC(/*String browser*/) throws Throwable {     //here we have deleted the property file program inorder to sync with @Parameters
		System.out.println("===Launch the BROWSER===");
		String BROWSER = flib.getDataFromPropertiesFile("browser");  //Here this line(flib.getDataFromPropertiesFile("browser");) can be commented because whenever we are getting the parameters from XML Suite Files,so need not to take it from Property file.
		//String BROWSER = System.getProperty("browser");     //This line is to run(recieve run time parameters) with CMD line without Eclipse
		
		if(BROWSER.equals("chrome")) {
			driver=new ChromeDriver();}

		else if(BROWSER.equals("firefox")) {
			driver=new FirefoxDriver();}

		else if(BROWSER.equals("edge")) {
			driver=new EdgeDriver();}
		else  {
			driver=new ChromeDriver();}
		sdriver=driver;
		UtilityClassobject.setDriver(driver);
	}


	@BeforeMethod//(groups={"smokeTest","regressionTest"})
	public void configBM() throws Throwable {
		System.out.println("==Login==");
		String URL = flib.getDataFromPropertiesFile("url");
		String USERNAME = flib.getDataFromPropertiesFile("username");
		String PASSWORD = flib.getDataFromPropertiesFile("password");
		
//		String URL = System.getProperty("url");           //These 3 lines is to run with CMD line without Eclipse
//		String USERNAME = System.getProperty("username");
//		String PASSWORD = System.getProperty("password");
		LoginPage lp=new LoginPage(driver);
		lp.loginToapp(URL, USERNAME, PASSWORD);
	}

	@AfterMethod//(groups={"smokeTest","regressionTest"})
	public void configAM() throws InterruptedException {
		System.out.println("==Logout==");
		HomePage hp = new HomePage(driver);
		hp.logout();
	}
	@AfterClass//(groups={"smokeTest","regressionTest"})
	public void configAC() {
		System.out.println("===Close the BROWSER===");
		driver.quit();
	}
	@AfterSuite//(groups={"smokeTest","regressionTest"})
	public void configAS() throws Throwable {
		System.out.println("=====Close DB , Report BackUp======");
		dblib.closeDbconnection();
	}

}
