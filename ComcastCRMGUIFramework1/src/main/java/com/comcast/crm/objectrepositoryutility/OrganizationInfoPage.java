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
 private WebElement headerMsg1;
 
 @FindBy(xpath = "//td[@id='mouseArea_Type']")  //xpath by attribute-Done by Sanjay sir
 private WebElement headerMsg2;
 
 @FindBy(id="dtlview_Phone")
 private WebElement headerMsg3;
 
 
 
 public WebElement getHeaderMsg() {
		return headerMsg;
	}
 
public WebElement getHeaderMsg1() {
	return headerMsg1;
}

public WebElement getHeaderMsg2() {
	return headerMsg2;
}

public WebElement getHeaderMsg3() {
	return headerMsg3;
}


 
}

