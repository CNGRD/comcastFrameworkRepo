package com.comcast.crm.orgtest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;
import com.comcast.crm.objectrepositoryutility.CreatingNewOrganizationPage;
import com.comcast.crm.objectrepositoryutility.HomePage;
import com.comcast.crm.objectrepositoryutility.LoginPage;
import com.comcast.crm.objectrepositoryutility.OrganizationInfoPage;
import com.comcast.crm.objectrepositoryutility.OrganizationsPage;

public class DeleteOrgTest 
{
	public static void main(String[] args) throws Throwable
	{
	// read common data from properties file
			FileUtility fLib=new FileUtility();
			ExcelUtility eLib=new ExcelUtility();
			JavaUtility jLib=new JavaUtility();
			WebDriverUtility wLib=new WebDriverUtility();

			String BROWSER = fLib.getDataFromPropertiesFile("browser");
			String URL = fLib.getDataFromPropertiesFile("url");
			String USERNAME = fLib.getDataFromPropertiesFile("username");
			String PASSWORD = fLib.getDataFromPropertiesFile("password");

			// read the data from excel file
			String orgName=eLib.getDataFromExcel("contact", 10, 2)+jLib.getRandomNumber();
			
			WebDriver driver = null;
			if (BROWSER.equals("chrome")) 
			{
				driver = new ChromeDriver();
			} 
			else if (BROWSER.equals("firefox"))
			{
				driver = new FirefoxDriver();
			} 
			else if (BROWSER.equals("edge")) 
			{
				driver = new EdgeDriver();
			} else {
				driver = new ChromeDriver();
			}

			//step1: login to app
			//driver.get(URL);
			
			LoginPage lp=new LoginPage(driver);
			lp.loginToApp(USERNAME, PASSWORD, URL);
			//step2: navigate to the organization module
			
			HomePage hp=new HomePage(driver);
			hp.getOrgLink().click();
			
			//step3: click on "create organization" button
			OrganizationsPage cnp=new OrganizationsPage(driver);
			cnp.getCreateNewOrgBtn().click();

			
			//step4: enter all the details & create new organization
			CreatingNewOrganizationPage cnop=new CreatingNewOrganizationPage(driver);
			cnop.createOrg(orgName);
			
			//verify header msg expected result
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
			
			//go back to organizations page
			HomePage hp1=new HomePage(driver);
			hp1.getOrgLink().click();
			
			//search for organization
			cnp.getSearchEdt().sendKeys(orgName);
			wLib.select(cnp.getSearchDD(), "Organization Name");
			cnp.getSubmitBtn().click();
			
			driver.findElement(By.xpath("(//a[text()='"+orgName+"'])[2]/../../td[8]/a[text()='del']")).click();
			
			wLib.switchToAlertAndAccept(driver);
			//In dynamic web table select and delete org
			
			//step 5: logout
			hp1.logOut();
			
			driver.quit();
	}
}
