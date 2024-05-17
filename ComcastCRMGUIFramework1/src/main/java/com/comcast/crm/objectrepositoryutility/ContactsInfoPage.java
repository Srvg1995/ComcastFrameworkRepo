package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.comcast.crm.generic.webdriverutility.WebDriverUtility;

public class ContactsInfoPage extends WebDriverUtility {

	@FindBy (id = "dtlview_Last Name")
	private WebElement actualLastName;
	
	@FindBy (className = "dvHeaderText")
	private WebElement headerContact;
	
	@FindBy (id = "mouseArea_Organization Name")
	private WebElement actualOrgName;
	
	public ContactsInfoPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	public WebElement getHeaderContact() {
		return headerContact;
	}

	public WebElement getActualLastName() {
		return actualLastName;
	}

	public WebElement getActualOrgName() {
		return actualOrgName;
	}
	
	
}
