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
import com.comcast.crm.objectrepositoryutility.ContactsInfoPage;
import com.comcast.crm.objectrepositoryutility.ContactsPage;
import com.comcast.crm.objectrepositoryutility.CreateNewContactPage;
import com.comcast.crm.objectrepositoryutility.CreateNewOrganizationPage;
import com.comcast.crm.objectrepositoryutility.HomePage;
import com.comcast.crm.objectrepositoryutility.OrganizationChildPopupPage;
import com.comcast.crm.objectrepositoryutility.OrganizationInfoPage;
import com.comcast.crm.objectrepositoryutility.OrganizationsPage;

/**
 * @author gagan
 */
public class CreateContact_IntegrationTestScriptTest extends BaseClass {

	@Test // (/*groups="smokeTest"*/)
	public void createContactTest() throws Throwable {
		/* read test script data from Excel file */
		String lastName = elib.getDataFromExcel("contact", 1, 2) + jlib.getRandomNumber();

		/* step2:navigate to contact module */
		HomePage hp = new HomePage(driver);
		hp.getContactLink().click();

		/* step3: click on "create contact" Button */
		ContactsPage cp = new ContactsPage(driver);
		cp.getCreateNewContactBtn().click();

		/* step4:enter all the details & create new contact */
		CreateNewContactPage cncp = new CreateNewContactPage(driver);
		cncp.getLastNameEdt().sendKeys(lastName);

		/* step-5: save the contact */
		cncp.getSaveBtn().click();

		/* verify Header msg Expected result */
		String actHeader = cp.getHeaderMsg().getText();
		boolean status = actHeader.contains(lastName);
		Assert.assertEquals(status, true);

		ContactsInfoPage cip = new ContactsInfoPage(driver);
		String actLastName = cip.getHeaderContact().getText();
		SoftAssert soft = new SoftAssert();
		soft.assertTrue(actLastName.contains(lastName));
		soft.assertAll();

	}

	@Test // (/*groups="regressionTest"*/)
	public void createContactWithSupportDateTest() throws Throwable {

		/* read test script data from Excel file */
		String lastName = elib.getDataFromExcel("contact", 4, 2) + jlib.getRandomNumber();

		/* step2:navigate to contact module */
		HomePage hp = new HomePage(driver);
		hp.getContactLink().click();

		/* step3: click on "create contact" Button */
		ContactsPage cp = new ContactsPage(driver);
		cp.getCreateNewContactBtn().click();

		/* step4:enter all the details & create new contact */
		CreateNewContactPage cncp = new CreateNewContactPage(driver);
		cncp.getLastNameEdt().sendKeys(lastName);
		// capture the system date from java.util package and change it as needed
		String startDate = jlib.getSystemDateYYYYMMDD();
		String endDate = jlib.getRequiredDateYYYYMMDD(30);
		
		cncp.getStartDateEdt().clear();
		cncp.getStartDateEdt().sendKeys(startDate);
		cncp.getEndDateEdt().clear();
		cncp.getEndDateEdt().sendKeys(endDate);

		/* step-5: save the contact */
		cncp.getSaveBtn().click();

		/* verify header info expected result */
		String actStartDate = cncp.getActStartDate().getText();
	    Assert.assertEquals(actStartDate, startDate);
		System.out.println(startDate + " information is verified==PASS");
		
		String actEndDate = cncp.getActEndDate().getText();
		 Assert.assertEquals(actEndDate, endDate);
		System.out.println(endDate + " information is verified==PASS");

		
 /*Below are the Verification Process with If-Else statement which is not at all to be used*/
//		if (actStartDate.equals(startDate)) {
//			System.out.println(startDate + " information is verified==PASS");
//		} else {
//			System.out.println(startDate + " information is not verified==FAIL");
//		}

//		if (actEndDate.equals(endDate)) {
//			System.out.println(endDate + " information is verified==PASS");
//		} else {
//			System.out.println(endDate + " information is not verified==FAIL");
//		}
	}

	@Test // (/*groups="regressionTest"*/)
	public void createContactWithOrgTest() throws Throwable {

		/* read test script data from Excel file */
		String orgName = elib.getDataFromExcel("contact", 7, 2) + jlib.getRandomNumber();
		String lastName = elib.getDataFromExcel("contact", 7, 3);
		String orgIn = elib.getDataFromExcel("org", 10, 3);

		/* step2:navigate to organization module */
		HomePage hp = new HomePage(driver);
		hp.getOrgLink().click();

		/* step3: click on "create organization" Button */
		OrganizationsPage onp = new OrganizationsPage(driver);
		onp.getCreateNewOrgBtn().click();

		/* step4:enter all the details & create new organization */
		CreateNewOrganizationPage cnop = new CreateNewOrganizationPage(driver);
		cnop.createOrg(orgName);

		/* step5:navigate to contact module */
		OrganizationInfoPage oip = new OrganizationInfoPage(driver);
		Thread.sleep(2000);
		oip.getContactLink().click();

		/* step6: click on "create contact" Button */
		ContactsPage cp = new ContactsPage(driver);
		cp.getCreateNewContactBtn().click();

		/* step7:enter all the details & create new contact */
		CreateNewContactPage cncp = new CreateNewContactPage(driver);
		cncp.getLastNameEdt().sendKeys(lastName);

        /*step-8: click on look-up window, select organization by searching*/ 
		cncp.getClickOnLookup().click();

	     /*switch to child window*/

		wlib.switchToTabOnURL(driver, "module=Accounts");

	    /*search for dynamic organization name, select organization name that's created during the run-time and save*/ 

		OrganizationChildPopupPage opop = new OrganizationChildPopupPage(driver);
		opop.selectSearchDD(orgName, orgIn);

	    /*switch to parent window*/

		wlib.switchToTabOnURL(driver, "Contacts&action");
		cncp.getSaveBtn().click();

	    /*verify the header message expected result*/ 
		ContactsInfoPage cip = new ContactsInfoPage(driver);
		String headerMessg = cip.getHeaderContact().getText();

		if (headerMessg.contains(lastName)) {
			System.out.println(lastName + " is created===PASS");
		} else {
			System.out.println(lastName + " is not created==FAIL");
		}

	    /*verify the header orgName expected result*/ 
		String actualOrgNameinfo = cip.getActualOrgName().getText();

		if (actualOrgNameinfo.trim().equals(orgName)) {
			System.out.println(orgName + " is created===PASS");
		} else {
			System.out.println(orgName + " is not created===FAIL");
		}

	}
	
}
