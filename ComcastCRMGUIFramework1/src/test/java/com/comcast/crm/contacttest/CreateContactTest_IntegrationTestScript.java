package com.comcast.crm.contacttest;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.comcast.crm.basetest.BaseClass;
import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;
import com.comcast.crm.objectrepositoryutility.ContactInfoPage;
import com.comcast.crm.objectrepositoryutility.ContactsPage;
import com.comcast.crm.objectrepositoryutility.CreateNewContactPage;
import com.comcast.crm.objectrepositoryutility.CreateNewOrganizationPage;
import com.comcast.crm.objectrepositoryutility.HomePage;
import com.comcast.crm.objectrepositoryutility.OrganizationInfoPage;
import com.comcast.crm.objectrepositoryutility.OrganizationsPage;
/**
 * @author gagan
 */
public class CreateContactTest_IntegrationTestScript extends BaseClass {

	@Test(/*groups="smokeTest"*/)
	public void createContactTest() throws Throwable {
		/* read test script data from Excel file */
		String lastName = elib.getDataFromExcel("contact", 1, 2) + jlib.getRandomNumber();

		/*step2:navigate to contact module*/
		HomePage hp=new HomePage(driver);
		hp.getContactLink().click(); 

		/*step3: click on "create contact" Button*/
		ContactsPage cp=new ContactsPage(driver);
		cp.getCreateNewContactBtn().click();

		/*step4:enter all the details & create new contact*/
		CreateNewContactPage cncp=new CreateNewContactPage(driver);
		cncp.createContact(lastName);

		/*verify Header msg Expected result*/
		String actHeader=cp.getHeaderMsg().getText();
		boolean status = actHeader.contains(lastName);
		Assert.assertEquals(status, true);


		ContactInfoPage cip=new ContactInfoPage(driver);
		String actLastName = cip.getHeaderMsg().getText();
		SoftAssert soft=new SoftAssert();
		soft.assertEquals(actLastName, lastName);
		soft.assertAll();



	}

	@Test(groups="regressionTest")
	public void createContactWithSupportDateTest() throws Throwable {

		/* read test script data from Excel file*/
		String lastName = elib.getDataFromExcel("contact", 4, 2) + jlib.getRandomNumber();

		/*step2:navigate to contact module*/
		HomePage hp=new HomePage(driver);
		hp.getContactLink().click(); 

		/*step3: click on "create contact" Button*/
		ContactsPage cp=new ContactsPage(driver);
		cp.getCreateNewContactBtn().click();

		/*step4:enter all the details & create new contact*/
		String startDate = jlib.getSystemDateYYYYMMDD();
		String endDate = jlib.getRequiredDateYYYYMMDD(30);

		CreateNewContactPage cncp=new CreateNewContactPage(driver);
		cncp.createContactSD(startDate);
		cncp.createContactED(endDate);
		cncp.createContact(lastName);


		/*verify header info expected result*/
		ContactInfoPage cip=new ContactInfoPage(driver);
		String actStartDate = cip.getStartDate().getText();
		if(actStartDate.equals(startDate))
		{
			System.out.println(startDate + " information is verified==PASS");
		}
		else {
			System.out.println(startDate + " information is not verified==FAIL");
		}


		String actEndDate = cip.getEndDate().getText();
		if(actEndDate.equals(endDate))
		{
			System.out.println(endDate + " information is verified==PASS");
		}
		else {
			System.out.println(endDate + " information is not verified==FAIL");
		}
	}

	@Test(groups="regressionTest")
	public void createContactWithOrgTest() throws Throwable {

		/* read test script data from Excel file*/
		String orgName = elib.getDataFromExcel("contact", 7, 2) + jlib.getRandomNumber();
		String contactLastName = elib.getDataFromExcel("contact", 7, 3);

		/* step2:navigate to organization module*/
		HomePage hp=new HomePage(driver);
		hp.getOrgLink().click(); 

		/* step3: click on "create organization" Button*/
		OrganizationsPage cnp=new OrganizationsPage(driver);
		cnp.getCreateNewOrgBtn().click();


		/* step4:enter all the details & create new organization*/
		CreateNewOrganizationPage cnop=new CreateNewOrganizationPage(driver);
		cnop.createOrg(orgName);


		/* verify Header orgName info Expected result*/
		OrganizationInfoPage oip=new OrganizationInfoPage(driver);
		String actOrgName = oip.getHeaderMsg().getText();
		if(actOrgName.contains(orgName)) 
		{
			System.out.println(orgName + "name is verified==PASS");
		}
		else {
			System.out.println(orgName + "name is not verified==FAIL");
		}
	}

	/* step5:navigate to contact module*/
	hp.getContactLink().click(); 

	/* step6: click on "create contact" Button*/
	ContactsPage cp=new ContactsPage(driver);
	cp.getCreateNewContactBtn().click();

	/* step7:enter all the details & create new contact*/
	CreateNewContactPage cncp=new CreateNewContactPage(driver);
	cncp.createContact(lastName); 

	/* switch to child window*/
	wlib.switchToTabOnURL(driver, "module=Accounts");

	driver.findElement(By.name("search_text")).sendKeys(orgName);
	driver.findElement(By.name("search")).click();
	driver.findElement(By.xpath("/*a[text()='" + orgName + "']")).click(); /* DYNAMIC XPATH USED HERE-***VVIMP

	/* switch to parent window*/
	wlib.switchToTabOnURL(driver, "Contacts&action");

	driver.findElement(By.xpath("/*input[@title='Save [Alt+S]']")).click();

	/* verify Header info Expected result*/
	headerInfo = driver.findElement(By.xpath("/*span[@class='dvHeaderText']")).getText();
	if (headerInfo.contains(contactLastName)) {
		System.out.println(contactLastName + " header is verified==PASS");
	} else {
		System.out.println(contactLastName + " header is not verified==FAIL");
	}

	/* verify Header orgName info Expected result*/
	String actOrgName = driver.findElement(By.id("mouseArea_Organization Name")).getText();
	System.out.println(actOrgName);
	if (actOrgName.trim().equals(orgName)) {
		System.out.println(orgName + " information is verified==PASS");
	} else {
		System.out.println(orgName + " information is not verified==FAIL");
	}

}

}
