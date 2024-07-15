package com.comcast.crm.contacttest;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.Date;
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

public class CreateContactWithSupportDateTest {

	public static void main(String[] args) throws Exception
	{
		// read common data from properties file
		FileInputStream fis = new FileInputStream("./configAppData/commondata.properties");
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
		FileInputStream fis1 = new FileInputStream("./testData/testscriptdata.xlsx");
		Workbook wb = WorkbookFactory.create(fis1);
		Sheet sh = wb.getSheet("contact");
		Row row = sh.getRow(4);
		String lastName = row.getCell(2).toString() + random;

		wb.close();

		  WebDriver driver = null; if (BROWSER.equals("chrome")) { driver = new
		  ChromeDriver(); } else if (BROWSER.equals("firefox")) { driver = new
		  FirefoxDriver(); } else if (BROWSER.equals("edge")) { driver = new
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
		  
		  Date dateObj=new Date();
		  
		  SimpleDateFormat f=new SimpleDateFormat("yyyy-MM-dd");
		  String startDate=f.format(dateObj);
		  
		  Calendar cal=f.getCalendar();
			cal.add(Calendar.DAY_OF_MONTH,30);
			
			String endDate = f.format(cal.getTime());
		  
		  
		  driver.findElement(By.name("lastname")).sendKeys(lastName);
		  driver.findElement(By.name("support_start_date")).clear();
		  driver.findElement(By.name("support_start_date")).sendKeys(startDate);
		  
		  driver.findElement(By.name("support_end_date")).clear();
		  driver.findElement(By.name("support_end_date")).sendKeys(endDate);
		  
		  
		  
		  
		  
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
		  
		 //step 5: logout
		  explicitWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//td[text()='Contact Information']"))); 
		  Actions act=new Actions(driver); 
		  act.moveToElement(driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"))).build().perform();
		  driver.findElement(By.xpath("(//a[@class='drop_down_usersettings'])[2]")).click(); 
		  driver.quit();

	}

}
