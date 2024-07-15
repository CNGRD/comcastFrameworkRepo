package com.comcast.crm.contacttest;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Iterator;
import java.util.Properties;
import java.util.Random;
import java.util.Set;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CreateConWithOrgTest {

	public static void main(String[] args) throws Exception
	{
		FileInputStream fis = new FileInputStream("./testScriptData/commondata.properties");
		Properties pObj = new Properties();
		pObj.load(fis);

		String BROWSER = pObj.getProperty("browser");
		String URL = pObj.getProperty("url");
		String USERNAME = pObj.getProperty("username");
		String PASSWORD = pObj.getProperty("password");

		System.out.println(BROWSER);
		System.out.println(URL);
		System.out.println(USERNAME);
		System.out.println(PASSWORD);

		// generate the random number
		Random randomInt = new Random();
		int random = randomInt.nextInt(1000);

		// read the data from excel file
		FileInputStream fis1 = new FileInputStream("./testScriptData/testscriptdata.xlsx");
		Workbook wb = WorkbookFactory.create(fis1);
		Sheet sh = wb.getSheet("contact");
		/*
		 * Row row = sh.getRow(7); String orgName = row.getCell(2).toString() + random;
		 * String contactLastName = row.getCell(2).toString() + random;
		 */
		String contactLastName=sh.getRow(7).getCell(2).getStringCellValue()+random;
		String orgName=sh.getRow(7).getCell(3).getStringCellValue()+random;
		wb.close();

		WebDriver driver = null;
		if (BROWSER.equals("chrome")) {
			driver = new ChromeDriver();
		} else if (BROWSER.equals("firefox")) {
			driver = new FirefoxDriver();
		} else if (BROWSER.equals("edge")) {
			driver = new EdgeDriver();
		} else {
			driver = new ChromeDriver();
		}

		//step1: login to app
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(15));
		driver.manage().window().maximize();
		driver.get(URL);

		driver.findElement(By.name("user_name")).sendKeys(USERNAME);
		driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
		driver.findElement(By.id("submitButton")).click();
		
		//step2: navigate to the organization module
		driver.findElement(By.linkText("Organizations")).click();
		
		//step3: click on "create organization" button
		driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();
		
		//step4: enter all the details & create new organization
		driver.findElement(By.name("accountname")).sendKeys(orgName);
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]'][1]")).click();
		
		//verify header msg expected result
		String headerInfo=driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		if (headerInfo.contains(orgName)) 
		{
			System.out.println(orgName+" Header is verified==PASS");
			
		}
		else
		{
			System.out.println(orgName+" Header is not Verified==FAIL");
		}
		
		
		//verify header orgname expected result
		String actualOrgNameInfo=driver.findElement(By.id("dtlview_Organization Name")).getText();
		if (actualOrgNameInfo.equals(orgName)) 
		{
			System.out.println(orgName+" information is created==PASS");
		}
		else
		{
			System.out.println(orgName+" information is not created==FAIL");
		}
		
		
		//step 5:Navigate to contact module
		
		driver.findElement(By.linkText("Contacts")).click();
		  
		  //step6: click on "create organization" button
		  driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();
		  
		  //step7: enter all the details & create new organization
		  driver.findElement(By.name("lastname")).sendKeys(contactLastName);
		  
		  driver.findElement(By.xpath("//img[@src='themes/softed/images/select.gif']")).click();
		  
		  //switch to child window
		  Set<String> set = driver.getWindowHandles();
		  Iterator<String> it = set.iterator();
		  
		  while(it.hasNext())
		  {
			 String windowId= it.next();
			 driver.switchTo().window(windowId);
			 
			 String actUrl=driver.getCurrentUrl();
			 if (actUrl.contains("module=Accounts")) 
			 {
				 break;
				
			}
		  }
		  
		  
		  
		  
		  
		  
		  
		  driver.findElement(By.name("search_text")).sendKeys(orgName);
		  driver.findElement(By.name("search")).click();
		  driver.findElement(By.xpath("//a[text()='"+orgName+"']")).click();
		  
		  //switch back to parent window
		  Set<String> set1 = driver.getWindowHandles();
		  Iterator<String> it1 = set1.iterator();
		  
		  while(it1.hasNext())
		  {
			 String windowId= it1.next();
			 driver.switchTo().window(windowId);
			 
			 String actUrl=driver.getCurrentUrl();
			 if (actUrl.contains("Contacts&action")) 
			 {
				 break;
				
			}
		  }
		  
		  
		  
		  driver.findElement(By.xpath("//input[@title='Save [Alt+S]'][1]")).click();
		  
		  
		//verify header msg expected result
			 headerInfo=driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
			if (headerInfo.contains(contactLastName)) 
			{
				System.out.println(contactLastName+" Header is verified==PASS");
				
			}
			else
			{
				System.out.println(contactLastName+" Header is not Verified==FAIL");
			}
			
			
			//verify header orgname expected result
			headerInfo=driver.findElement(By.name("dtlview_Organization.Name")).getText();
			if (headerInfo.equals(orgName)) 
			{
				System.out.println(orgName+" information is created==PASS");
			}
			else
			{
				System.out.println(orgName+" information is not created==FAIL");
			}
			
		
		//step 5: logout
		explicitWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//td[text()='Organization Information']")));
		Actions act=new Actions(driver);
		act.moveToElement(driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"))).build().perform();
		driver.findElement(By.xpath("(//a[@class='drop_down_usersettings'])[2]")).click();
		driver.quit();
		
	}

}
