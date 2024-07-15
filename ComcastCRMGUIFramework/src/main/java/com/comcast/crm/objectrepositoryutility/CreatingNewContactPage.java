package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;

public class CreatingNewContactPage 
{
	JavaUtility jLib=new JavaUtility();
	ExcelUtility eLib=new ExcelUtility();
	WebDriver driver;
	
	public CreatingNewContactPage(WebDriver driver) 
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath="//img[@title='Create Contact...']")
	private WebElement createConBtn;
	
	@FindBy(name="lastname")
	private WebElement lastNameEdit;
	
	@FindBy(xpath="//input[@type='submit']")
	private WebElement submitBtn;
	
	public WebElement getSubmitBtn() 
	{
		return submitBtn;
	}

	

	public WebElement getLastNameEdit() {
		return lastNameEdit;
	}

	public WebElement getCreateConBtn() 
	{
		return createConBtn;
	}
	
	



	public void createContactWithSupportDate(String lastName, String startDate, String endDate) throws Throwable 
	{
		
		lastName = eLib.getDataFromExcel("contact", 4, 2) + jLib.getRandomNumber();
		 driver.findElement(By.name("lastname")).sendKeys(lastName);
		  driver.findElement(By.name("support_start_date")).clear();
		  driver.findElement(By.name("support_start_date")).sendKeys(startDate);
		  
		  driver.findElement(By.name("support_end_date")).clear();
		  driver.findElement(By.name("support_end_date")).sendKeys(endDate);
		
	}
	

}
