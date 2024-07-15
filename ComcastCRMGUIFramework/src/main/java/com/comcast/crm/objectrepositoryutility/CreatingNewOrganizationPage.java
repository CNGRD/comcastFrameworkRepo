package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;

public class CreatingNewOrganizationPage 
{
	JavaUtility jLib=new JavaUtility();
	WebDriver driver;
	public CreatingNewOrganizationPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(name="accountname")
	private WebElement orgNameEdt;
	
	@FindBy(xpath="//input[@title='Save [Alt+S]']")
	private WebElement savebtn;
	
	@FindBy(name="industry")
	private WebElement industryDd;
	
	@FindBy(xpath="//img[@src='themes/softed/images/btnL3Add.gif']")
	private WebElement getCreateNewOrgBtn;

	@FindBy(name="phone")
	private WebElement phoneNumber;
	
	@FindBy(name="accounttype")
	private WebElement accType;
	
	public WebElement getAccType() {
		return accType;
	}

	public WebElement getPhoneNumber() {
		return phoneNumber;
	}

	public WebElement getCreateNewOrgBtn() {
		return getCreateNewOrgBtn;
	}

	public WebElement getOrgName() {
		return orgNameEdt;
	}

	public WebElement getSavebtn() {
		return savebtn;
	}
	
	public void createOrg(String orgname)
	{
		orgNameEdt.sendKeys(orgname+jLib.getRandomNumber());
		savebtn.click();
	}
	
	
	public void createOrg(String orgname,String industry,String type)
	{
		orgNameEdt.sendKeys(orgname);
		Select sel=new Select(industryDd);
		sel.selectByVisibleText(industry);
		
		Select sel1=new Select(accType);
		sel1.selectByVisibleText(type);
		savebtn.click();
	}

	public void createOrgPhoneNumber(String orgname,String phoneNum)
	{
		orgNameEdt.sendKeys(orgname);
		phoneNumber.sendKeys(phoneNum);
		savebtn.click();
		
	}
	
	
	
	
	
	
	
	
	
	

}
