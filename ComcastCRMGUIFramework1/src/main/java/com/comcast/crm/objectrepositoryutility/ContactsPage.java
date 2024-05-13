package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContactsPage {

	WebDriver driver;         
	public ContactsPage(WebDriver driver) {       
		this.driver=driver;
		  PageFactory.initElements(driver,this);
	}
	
	@FindBy(className = "dvHeaderText")
	private WebElement headerMsg;
	
	
	
	public WebElement getHeaderMsg() {
		return headerMsg;
	}

	@FindBy(xpath= "//img[@alt='Create Contact...']")
	private WebElement createNewContactBtn;
	
	public WebElement getCreateNewContactBtn() {
		return createNewContactBtn;
	}

	
	
	
	
	

}
