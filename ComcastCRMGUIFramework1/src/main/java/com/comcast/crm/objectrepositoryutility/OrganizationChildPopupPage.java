package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.comcast.crm.generic.webdriverutility.WebDriverUtility;

public class OrganizationChildPopupPage extends WebDriverUtility {

	@FindBy (id = "search_txt")
	private WebElement orgSearchEdt;
	
	@FindBy (name = "search_field")
	private WebElement selectEleDD;
	
	@FindBy (name = "search")
	private WebElement searchBtn;
	
	WebDriver driver;
	
// this is how we need to initialise the web elements without pageFactory.initElements() method
//	public OrganizationChildPopupPage(WebDriver driver, WebElement orgSearchEdt, 
//			WebElement orgSearchDD, WebElement selectEle, WebElement searchBtn) {
//		this.driver=driver;
//		this.orgSearchEdt=orgSearchEdt;
//		this.orgSearchDD=orgSearchDD;
//		this.selectEle=selectEle;
//		this.searchBtn=searchBtn;
//	}
	
	
	
	
	public OrganizationChildPopupPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public WebElement getOrgSearchEdt() {
		return orgSearchEdt;
	}


	public WebElement getSelectEleDD() {
		return selectEleDD;
	}

	public WebElement getSearchBtn() {
		return searchBtn;
	}

	public void selectSearchDD(String orgName, String orgIn) {
		orgSearchEdt.sendKeys(orgName);
		select(getSelectEleDD(), orgIn);
		getSearchBtn().click();
		driver.findElement(By.xpath("//a[text()='"+orgName+"']")).click();
	}
	
}
