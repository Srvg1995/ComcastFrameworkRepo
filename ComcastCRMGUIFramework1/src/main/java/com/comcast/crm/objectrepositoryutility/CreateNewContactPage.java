package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class CreateNewContactPage {

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

	public void createContact(String lastName) {
		lastNameEdt.sendKeys(lastName);
		saveBtn.click();
	}
	public void createContactSD(String startDate) throws InterruptedException {
		startDateEdt.clear();
		Thread.sleep(1000);
		startDateEdt.sendKeys(startDate);
	}
	public void createContactED(String endDate) throws InterruptedException {
		endDateEdt.clear();
		Thread.sleep(1000);
		endDateEdt.sendKeys(endDate);
	}
}

