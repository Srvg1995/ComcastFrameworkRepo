package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContactInfoPage {
	
	WebDriver driver;         
	public ContactInfoPage(WebDriver driver) {       
		this.driver=driver;
		  PageFactory.initElements(driver,this);
	}

@FindBy(id = "dtlview_Last Name")
private WebElement headerMsg;

@FindBy(id = "dtlview_Support Start Date")
private WebElement StartDate;

@FindBy(id = "dtlview_Support End Date")
private WebElement EndDate;

public WebElement getHeaderMsg() {
	return headerMsg;
}

public WebElement getStartDate() {
	return StartDate;
}

public WebElement getEndDate() {
	return EndDate;
}

}

