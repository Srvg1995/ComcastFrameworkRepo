package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.comcast.crm.generic.webdriverutility.WebDriverUtility;

public class CreateNewContactPage extends WebDriverUtility{

	WebDriver driver;         
	public CreateNewContactPage(WebDriver driver) {       
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	@FindBy(name="lastname")
	private WebElement lastNameEdt;

	@FindBy(xpath="//input[@title='Save [Alt+S]']")
	private WebElement saveBtn;

	@FindBy(name="support_start_date")
	private WebElement startDateEdt;

	@FindBy(name="support_end_date")
	private WebElement endDateEdt;
	
	@FindBy (id = "dtlview_Support Start Date")
	private WebElement actStartDate;
	
	@FindBy (id = "dtlview_Support End Date")
	private WebElement actEndDate;
	
	@FindBy (xpath = "//input[@name='account_name']/following-sibling::img") //new concept
	private WebElement clickOnLookup;
	

	public WebElement getLastNameEdt() {
		return lastNameEdt;
	}

	public WebElement getSaveBtn() {
		return saveBtn;
	}

	public WebElement getStartDateEdt() {
		return startDateEdt;
	}

	public WebElement getEndDateEdt() {
		return endDateEdt;
	}

	public WebElement getActStartDate() {
		return actStartDate;
	}

	public WebElement getActEndDate() {
		return actEndDate;
	}

	public WebElement getClickOnLookup() {
		return clickOnLookup;
	}

	}


