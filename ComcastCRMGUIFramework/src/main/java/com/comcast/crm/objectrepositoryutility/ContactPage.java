package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContactPage
{
	WebDriver driver;
	@FindBy(xpath = "//img[@title='Create Contact...']")
	WebElement getCreateNewOrgBtn;

	@FindBy(className="dvHeaderText")
	private WebElement headerText;
	
	@FindBy(id="dtlview_Last Name")
	private WebElement lastName; 
	
	public WebElement getHeaderText() {
		return headerText;
	}

	public WebElement getLastName() {
		return lastName;
	}

	public ContactPage(WebDriver driver) 
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	public WebElement getGetCreateNewOrgBtn() 
	{
		return getCreateNewOrgBtn;
	}

	

}
