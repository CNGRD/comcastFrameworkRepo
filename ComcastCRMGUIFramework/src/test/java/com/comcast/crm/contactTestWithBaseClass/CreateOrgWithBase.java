package com.comcast.crm.contactTestWithBaseClass;

import org.openqa.selenium.By;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.comcast.crm.generic.listenerUtility.ListenerImplementationClass;
import com.comcast.crm.generic.webdriverutility.UtilityClassObject;
import com.comcast.crm.objectrepositoryutility.CreatingNewOrganizationPage;
import com.comcast.crm.objectrepositoryutility.HomePage;
import com.comcast.crm.objectrepositoryutility.OrganizationInfoPage;
import com.comcast.crm.objectrepositoryutility.OrganizationsPage;
import com.crm.comcast.basetest.BaseClass;

@Listeners(com.comcast.crm.generic.listenerUtility.ListenerImplementationClass.class)
public class CreateOrgWithBase extends BaseClass
{
	@Test(groups="smokeTest")
	public void createOrgTest() throws Throwable
	{
		UtilityClassObject.getTest().log(Status.INFO,"read data from Excel");
		//read testscript data from the excel
		String orgName=eLib.getDataFromExcel("org", 1, 2)+jLib.getRandomNumber();
		
		//step 2: navigate to organization module
		UtilityClassObject.getTest().log(Status.INFO,"naviagate to ORG module");
		HomePage hp=new HomePage(driver);
		hp.getOrgLink().click();
		
		//step3: click on "Create Organization" Button
		ListenerImplementationClass.test.log(Status.INFO,"Navigate to create org page");
		OrganizationsPage cnp=new OrganizationsPage(driver);
		cnp.getCreateNewOrgBtn().click();
		
		//step 4:enter all the details & create new Organization
		ListenerImplementationClass.test.log(Status.INFO,"Create a new Org");
		CreatingNewOrganizationPage cnop=new CreatingNewOrganizationPage(driver);
		cnop.createOrg(orgName);
		ListenerImplementationClass.test.log(Status.INFO,orgName+" Created successfully");
		
		//verify Header msg expected result
		OrganizationInfoPage oip=new OrganizationInfoPage(driver);
		String actOrgName=oip.getHeaderMsg().getText();
		if (actOrgName.contains(orgName)) 
		{
			System.out.println(orgName+" name is verified ==>PASS");
		}
		else
		{
			System.out.println(orgName+" name is not verified ==>FAIL");
		}
	}
	
	@Test(groups="regressionTest")
	public void CreateOrgWithPhoneNumberTest() throws Throwable
	{
		String orgName=eLib.getDataFromExcel("org", 7, 2)+jLib.getRandomNumber();
		String phoneNumber=eLib.getDataFromExcel("org", 7, 3);
		
		//step 2: Navigate to organization module
		HomePage hp=new HomePage(driver);
		hp.getOrgLink().click();
		
		//step3: click on "Create Organization" Button
		
		OrganizationsPage cnp=new OrganizationsPage(driver);
		cnp.getCreateNewOrgBtn().click();
		
		//step 4: enter all the details and create new organization
		CreatingNewOrganizationPage cnop=new CreatingNewOrganizationPage(driver);
		cnop.createOrgPhoneNumber(orgName, phoneNumber);
		
		String actPhoneNumber=driver.findElement(By.id("dtlview_Phone")).getText();
		  if (actPhoneNumber.equals(phoneNumber)) 
		  {
			  System.out.println(phoneNumber+" Phone Number information is verified==PASS"); 
		  }
		  else 
		  {
			  System.out.println(phoneNumber+" Phone Number information is not verified==FAIL"); 
		  }
		  
		
	}
	
	@Test(groups="regressionTest")
	public void CreateOrganizationWithINdustriesTest() throws Throwable
	{
		//read test script data from excel file
		String orgName=eLib.getDataFromExcel("org", 4, 2)+jLib.getRandomNumber();
		String industry=eLib.getDataFromExcel("org", 4, 3);
		String type=eLib.getDataFromExcel("org", 4, 4);
		
		//step 2: Navigate to organization module
		HomePage hp=new HomePage(driver);
		hp.getOrgLink().click();
		
		//step3: click on "Create Organization" Button
		
		OrganizationsPage cnp=new OrganizationsPage(driver);
		cnp.getCreateNewOrgBtn().click();
		
		//step 4:enter all the details and create new organization
		CreatingNewOrganizationPage cnop=new CreatingNewOrganizationPage(driver);
		cnop.createOrg(orgName, industry,type);
		
		//verify the drop down industry an d type info
		  
		  String actualIndutries=driver.findElement(By.xpath("//option[@value='Engineering']")).getText();
		  
		  if (actualIndutries.equals(industry)) 
		  {
			  System.out.println(industry+" Industry information is verified==PASS"); 
		  }
		  else 
		  {
		  System.out.println(industry+" Industry information is not verified==FAIL");
		  }
		
	}
}
