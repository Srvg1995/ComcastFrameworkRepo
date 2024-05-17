package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrganizationInfoPage {
	
	WebDriver driver;         
	public OrganizationInfoPage(WebDriver driver) {       
		this.driver=driver;
		  PageFactory.initElements(driver,this);
	}

@FindBy(className = "dvHeaderText")
private WebElement headerMsg;

 @FindBy(id="dtlview_Industry")
 private WebElement industry;
 
 @FindBy(xpath = "//td[@id='mouseArea_Type']")  //xpath by attribute-Done by Sanjay sir
 private WebElement indType;
 
 @FindBy(id="dtlview_Phone")
 private WebElement phoneNum;
 
 @FindBy(linkText = "Contacts")
 private WebElement ContactLink;
 
 
 
 
 public WebElement getContactLink() {
	return ContactLink;
}

public WebElement getHeaderMsg() {
		return headerMsg;
	}
 
public WebElement getindustry() {
	return industry;
}

public WebElement getindType() {
	return indType;
}

public WebElement getphoneNum() {
	return phoneNum;
}


 
}

