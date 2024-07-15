package com.comcast.crm.contacttest;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;

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

import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;

public class CreateContactTest {

	public static void main(String[] args) throws Throwable
	{
		//create an object of FileUtility
		FileUtility fLib=new FileUtility();
		ExcelUtility eLib=new ExcelUtility();
		JavaUtility jLib=new JavaUtility();
		

				String BROWSER = fLib.getDataFromPropertiesFile("browser");
				String URL = fLib.getDataFromPropertiesFile("url");
				String USERNAME = fLib.getDataFromPropertiesFile("username");
				String PASSWORD = fLib.getDataFromPropertiesFile("password");


				

				// read the data from excel file
					String lastName=eLib.getDataFromExcel("contact", 1, 2)+jLib.getRandomNumber();

				  WebDriver driver = null; 
				  if (BROWSER.equals("chrome")) 
				  { driver = new
				  ChromeDriver(); 
				  }
				  else if (BROWSER.equals("firefox")) 
				  { driver = new
				  FirefoxDriver(); 
				  
				  } else if (BROWSER.equals("edge")) 
				  { driver = new
				  EdgeDriver(); } else { driver = new ChromeDriver(); }
				  
				  //step1: login to app
				  driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
				  WebDriverWait explicitWait = new WebDriverWait(driver,Duration.ofSeconds(15)); 
				  driver.manage().window().maximize();
				  driver.get(URL);
				  
				  driver.findElement(By.name("user_name")).sendKeys(USERNAME);
				  driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
				  driver.findElement(By.id("submitButton")).click();
				  
				 //step2: navigate to the organization module
				  driver.findElement(By.linkText("Contacts")).click();
				  
				  //step3: click on "create organization" button
				  driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();
				  
				  //step4: enter all the details & create new organization
				  driver.findElement(By.name("lastname")).sendKeys(lastName);
				  driver.findElement(By.xpath("//input[@title='Save [Alt+S]'][1]")).click();
				  
				  //verify the drop down industry an d type info
				  
				  String actLastName=driver.findElement(By.id("dtlview_Last Name")).getText();
				  if (actLastName.equals(lastName)) 
				  {
					  System.out.println(lastName+" lastaname information is verified==PASS"); 
				  }
				  else 
				  {
					  System.out.println(lastName+" lastname information is not verified==FAIL"); 
				  }
				  
				 //step 5: logout
				  explicitWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//td[text()='Contact Information']"))); 
				  Actions act=new Actions(driver); 
				  act.moveToElement(driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"))).build().perform();
				  driver.findElement(By.xpath("(//a[@class='drop_down_usersettings'])[2]")).click(); 
				  driver.quit();
				 
		
	}

}
