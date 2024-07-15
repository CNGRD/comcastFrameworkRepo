package com.comcast.crm.contactTestWithBaseClass;


import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.comcast.crm.objectrepositoryutility.ContactPage;
import com.comcast.crm.objectrepositoryutility.CreatingNewContactPage;
import com.comcast.crm.objectrepositoryutility.CreatingNewOrganizationPage;
import com.comcast.crm.objectrepositoryutility.HomePage;
import com.crm.comcast.basetest.BaseClass;

public class CreateContactWithBase extends BaseClass

{
	@Test(groups="smokeTest")
	public void createContactTest() throws Throwable {
		String lastName = eLib.getDataFromExcel("contact", 1, 2) + jLib.getRandomNumber();

		// step2:navigate to "Create contact" button
		HomePage hp = new HomePage(driver);
		hp.getContactLink().click();

		// step3:Click On " Create contact" Button
		ContactPage cp = new ContactPage(driver);
		cp.getGetCreateNewOrgBtn().click();

		// step4:enter all the details & create new Contact

		CreatingNewContactPage cncp = new CreatingNewContactPage(driver);
		cncp.getLastNameEdit().sendKeys(lastName);
		cncp.getSubmitBtn().click();
		
		 String actHeader=cp.getHeaderText().getText();
		 boolean status=actHeader.contains(lastName);
		 Assert.assertEquals(status,true);
		 
		 String actLastName=cp.getLastName().getText();
		 
		 SoftAssert soft=new SoftAssert();
		 soft.assertEquals(actLastName,lastName);
		 
		
	}
	@Test(groups="regressionTest")
	public void CreateContactWithSupportDateTest() throws Throwable
	{
		//read test script data from excel
		String lastName = eLib.getDataFromExcel("contact", 4, 2) + jLib.getRandomNumber();
		
		//step 2:navigate to the contact module
		HomePage hp = new HomePage(driver);
		hp.getContactLink().click();
		
		//step 3: Click on "Create contact" button
		ContactPage cp = new ContactPage(driver);
		cp.getGetCreateNewOrgBtn().click();
		
		//step 4:enter all the details & create new contact
		String endDate=jLib.getSystemDateYYYYDDMM();
		String startDate=jLib.getRequiredDate(30);
		CreatingNewContactPage ccp=new CreatingNewContactPage(driver);
		ccp.createContactWithSupportDate(lastName, startDate, endDate);
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]'][1]")).click();
		  
		  //verify the drop down industry an d type info
		  
		  String actStartDate=driver.findElement(By.name("support_start_date")).getText();
		  if (actStartDate.equals(startDate)) 
		  {
			  System.out.println(startDate+" Start date information is verified==PASS"); 
		  }
		  else 
		  {
			  System.out.println(startDate+" start date information is not verified==FAIL"); 
		  }
		  
		  String actEndDate=driver.findElement(By.name("support_end_date")).getText();
		  if (actEndDate.equals(endDate)) 
		  {
			  System.out.println(endDate+" End date information is verified==PASS"); 
		  }
		  else 
		  {
			  System.out.println(endDate+" End date information is not verified==FAIL"); 
		  }
		  
		 
	
	}
	
	@Test(groups="regressionTest")
	public void CreateConWithOrgTest() throws Throwable
	{
		//read test script data from excel file
		String orgName=eLib.getDataFromExcel("contact", 7, 2)+jLib.getRandomNumber();
		//step2: navigate to the organization module
		HomePage hp=new HomePage(driver);
		hp.getOrgLink().click();
				
		//step3: click on "create organization" button
		CreatingNewOrganizationPage cnp=new CreatingNewOrganizationPage(driver);
		cnp.getCreateNewOrgBtn().click();
				
		//step4: enter all the details & create new organization
		CreatingNewOrganizationPage cnop=new CreatingNewOrganizationPage(driver);
		cnop.createOrg(orgName);
		
		String actualOrgNameInfo=driver.findElement(By.id("dtlview_Organization Name")).getText();
		if (actualOrgNameInfo.equals(orgName)) 
		{
			System.out.println(orgName+" information is created==PASS");
		}
		else
		{
			System.out.println(orgName+" information is not created==FAIL");
		}
		
	}
}
