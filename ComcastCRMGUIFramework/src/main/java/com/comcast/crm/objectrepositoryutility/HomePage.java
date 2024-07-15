package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage 
{
	WebDriver driver;
	//object initialization
		public HomePage(WebDriver driver)
		{
			this.driver=driver;
			PageFactory.initElements(driver,this);
		}
		
	@FindBy(linkText="Organizations")
	private WebElement orgLink;
	
	@FindBy(linkText="Contacts")
	private WebElement contactLink;
	
	@FindBy(linkText="Campaigns")
	private WebElement campaignLink;
	
	@FindBy(linkText="More")
	private WebElement moreLink;
	
	@FindBy(xpath="//img[@src='themes/softed/images/user.PNG']")
	private WebElement adminImg;

	@FindBy(xpath="//a[.='Sign Out']")
	private WebElement signOutLink;
	
	public WebDriver getDriver() {
		return driver;
	}



	public WebElement getAdminImg() {
		return adminImg;
	}



	public WebElement getSignOutLink() {
		return signOutLink;
	}



	public WebElement getOrgLink() 
	{
		return orgLink;
	}



	public WebElement getContactLink() 
	{
		return contactLink;
	}



	public WebElement getCampaignLink()
	{
		return campaignLink;
	}



	public WebElement getMoreLink() 
	{
		return moreLink;
	}



	public void navigateToCampaignPage()
	{
		Actions act=new Actions(driver);
		act.moveToElement(moreLink).build().perform();
		campaignLink.click();
	}
	
	public void logOut()
	{
		Actions act=new Actions(driver);
		
		  try 
		  { 
			  Thread.sleep(2000); 
		  }
		  catch (InterruptedException e)
		  { 
			  e.printStackTrace(); 
		  }
		 
		act.moveToElement(adminImg).perform();
		try 
		{ 
			Thread.sleep(2000); 
		} 
		catch (InterruptedException e) 
		{ 
			e.printStackTrace();
		}
		
		signOutLink.click();
	}
	
	
	
}
