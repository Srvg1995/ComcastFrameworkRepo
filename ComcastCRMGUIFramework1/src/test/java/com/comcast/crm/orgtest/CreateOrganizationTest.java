package com.comcast.crm.orgtest;

import java.io.File;
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
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.comcast.crm.basetest.BaseClass;
import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.generic.webdriverutility.UtilityClassobject;
import com.comcast.crm.objectrepositoryutility.CreateNewOrganizationPage;
import com.comcast.crm.objectrepositoryutility.HomePage;
import com.comcast.crm.objectrepositoryutility.LoginPage;
import com.comcast.crm.objectrepositoryutility.OrganizationInfoPage;
import com.comcast.crm.objectrepositoryutility.OrganizationsPage;
@Listeners(com.comcast.crm.generic.listenerutility.ListenerImpClass.class)
public class CreateOrganizationTest extends BaseClass {
	@Test(groups = "smokeTest")
	public void createOrganizationTest() throws Throwable {
		UtilityClassobject.getTest().log(Status.INFO, "read data from Excel");

		// read test script data from Excel file
		String orgName = elib.getDataFromExcel("org", 1, 2) + jlib.getRandomNumber();

		// step2:navigate to organization module
		UtilityClassobject.getTest().log(Status.INFO, "navigate to Org Page");
		HomePage hp = new HomePage(driver);
		hp.getOrgLink().click();

		// step3: click on "create organization" Button
		UtilityClassobject.getTest().log(Status.INFO, "navigate to  CreateOrg Page");
		OrganizationsPage cnp = new OrganizationsPage(driver);
		cnp.getCreateNewOrgBtn().click();
//Assert.fail();
		// step4:enter all the details & create new organization
		UtilityClassobject.getTest().log(Status.INFO, "Create a new Org");
		CreateNewOrganizationPage cnop = new CreateNewOrganizationPage(driver);

		cnop.createOrg(orgName);
		UtilityClassobject.getTest().log(Status.INFO, orgName + "===>Created new Org");
        
		// verify Header msg Expected result
		OrganizationInfoPage oip = new OrganizationInfoPage(driver);
		String actOrgName = oip.getHeaderMsg().getText();
		Assert.assertEquals(true, actOrgName.contains(orgName));

	}

	@Test(groups = "regressionTest")
	public void createOrganizationWithIndustriesTest() throws Throwable {
		// read test script data from Excel file
		String orgName = elib.getDataFromExcel("org", 4, 2) + jlib.getRandomNumber();
		String industry = elib.getDataFromExcel("org", 4, 3);
		String type = elib.getDataFromExcel("org", 4, 4);

		// step2:navigate to organization module
		HomePage hp = new HomePage(driver);
		hp.getOrgLink().click();

		// step3: click on "create organization" Button
		OrganizationsPage cnp = new OrganizationsPage(driver);
		cnp.getCreateNewOrgBtn().click();

		// step4:enter all the details & create new organization
		CreateNewOrganizationPage cnop = new CreateNewOrganizationPage(driver);
		cnop.createOrg(orgName, industry, type);

		// verify the industries and type info
		OrganizationInfoPage oip = new OrganizationInfoPage(driver);
		String actIndustries = oip.getindustry().getText();
		if (actIndustries.equals(industry)) {
			System.out.println(industry + "information is verified==PASS");
		} else {
			System.out.println(industry + "information is not verified==FAIL");
		}

		String actType = oip.getindType().getText();
		if (actType.equals(type)) {
			System.out.println(type + "information is verified==PASS");
		} else {
			System.out.println(type + "information is not verified==FAIL");
		}
	}

	@Test(groups = "regressionTest")
	public void createOrgWithPhoneNumberTest() throws Throwable {
		// read test script data from Excel file
		String orgName = elib.getDataFromExcel("org", 7, 2) + jlib.getRandomNumber();
		String phoneNumber = elib.getDataFromExcel("org", 7, 3);

		// step2:navigate to organization module
		HomePage hp = new HomePage(driver);
		hp.getOrgLink().click();

		// step3: click on "create organization" Button
		OrganizationsPage cnp = new OrganizationsPage(driver);
		cnp.getCreateNewOrgBtn().click();

		// step4:enter all the details & create new organization
		CreateNewOrganizationPage cnop = new CreateNewOrganizationPage(driver);
		cnop.createOrgPhone(orgName, phoneNumber);

		// verify Header phone Number info Expected result
		OrganizationInfoPage oip = new OrganizationInfoPage(driver);
		String actPhoneNumber = oip.getphoneNum().getText();
		if (actPhoneNumber.equals(phoneNumber)) {
			System.out.println(phoneNumber + "information is verified==PASS");
		} else {
			System.out.println(phoneNumber + "information not verified==FAIL");
		}

	}

}
