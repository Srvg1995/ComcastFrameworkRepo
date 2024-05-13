package com.comcast.crm.basetest;

import java.net.URL;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.comcast.crm.generic.databaseutility.DataBaseUtility;
import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.objectrepositoryutility.HomePage;
import com.comcast.crm.objectrepositoryutility.LoginPage;

public class GridBaseClass {

	/* Create Object*/
	public FileUtility flib=new FileUtility();
	public ExcelUtility elib=new ExcelUtility();
	public JavaUtility jlib=new JavaUtility();
	public DataBaseUtility dblib=new DataBaseUtility();
	public RemoteWebDriver driver=null;
	public static RemoteWebDriver sdriver=null;
	
	@BeforeSuite //(groups={"smokeTest","regressionTest"})
	public void configBS() throws Throwable {
		System.out.println("=====Connect to DB , Report Config======");
//    	dblib.getDbconnection();
	}
	
	//@Parameters("BROWSER")  //@Parameters-This annotation should be selected from TestNG only.
	@BeforeClass //(groups={"smokeTest","regressionTest"})            
	public void configBC(/*String browser*/) throws Throwable {     //here we have deleted the property file program inorder to sync with @Parameters
		System.out.println("===Launch the BROWSER===");
		String BROWSER = //browser;
	     flib.getDataFromPropertiesFile("browser");
		
		URL url = new URL("");
		DesiredCapabilities dc = new DesiredCapabilities();

		if(BROWSER.equalsIgnoreCase("chrome")) {
			dc.setBrowserName("chrome");
			dc.setPlatform(Platform.WINDOWS);}

		else if(BROWSER.equals("firefox")) {
			dc.setBrowserName("firefox");
			dc.setPlatform(Platform.WINDOWS);}

		else if(BROWSER.equals("edge")) {
			dc.setBrowserName("edge");
			dc.setPlatform(Platform.WINDOWS);}
		else  {
			dc.setBrowserName("chrome");
			dc.setPlatform(Platform.WINDOWS);}
		
		driver = new RemoteWebDriver(url, dc);
		
		sdriver=driver;
	}
	
	//@Parameters({"URL","USERNAME","PASSWORD"})
	@BeforeMethod//(groups={"smokeTest","regressionTest"})
	public void configBM(/*String URL,String USERNAME,String PASSWORD*/) throws Throwable {
		System.out.println("==Login==");
		String URL = flib.getDataFromPropertiesFile("url");
		String USERNAME = flib.getDataFromPropertiesFile("username");
		String PASSWORD = flib.getDataFromPropertiesFile("password");
		LoginPage lp=new LoginPage(driver);
		lp.loginToapp(URL, USERNAME, PASSWORD);
	}
	
	@AfterMethod//(groups={"smokeTest","regressionTest"})
	public void configAM() {
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

	
}
