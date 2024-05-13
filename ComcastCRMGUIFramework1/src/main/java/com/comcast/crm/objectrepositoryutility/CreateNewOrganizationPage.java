package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class CreateNewOrganizationPage {
	
	WebDriver driver;         
	public CreateNewOrganizationPage(WebDriver driver) {       
		this.driver=driver;
		  PageFactory.initElements(driver,this);
	}
@FindBy(name="accountname")
private WebElement orgNameEdt;

@FindBy(xpath="//input[@title='Save [Alt+S]']")
private WebElement saveBtn;

@FindBy( name="industry")
private WebElement industryDD;

@FindBy(name="accounttype")
private WebElement typeDD;

@FindBy( id="phone")
private WebElement phoneEdt;


public WebElement getOrgNameEdt() {
	return orgNameEdt;
}

public WebElement getSaveBtn() {
	return saveBtn;
}

public void createOrg(String orgName) {
	orgNameEdt.sendKeys(orgName);
	saveBtn.click();
}
public void createOrg(String orgName,String industry,String type) {
	orgNameEdt.sendKeys(orgName);
	Select sel=new Select(industryDD);
	sel.selectByVisibleText(industry);
	Select sel1=new Select(typeDD);
	sel1.selectByVisibleText(type);
	saveBtn.click();
}
public void createOrgPhone(String orgName,String phoneNumber) {
	orgNameEdt.sendKeys(orgName);
	phoneEdt.sendKeys(phoneNumber);
	saveBtn.click();

}
}
