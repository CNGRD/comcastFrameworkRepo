package com.crm.comcast.basetest;

import java.sql.SQLException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentReporter;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.comcast.crm.generic.databaseutility.DataBaseUtility;
import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.generic.webdriverutility.UtilityClassObject;
import com.comcast.crm.objectrepositoryutility.HomePage;
import com.comcast.crm.objectrepositoryutility.LoginPage;

public class BaseClass 
{
	public static WebDriver sDriver=null;
	public WebDriver driver=null;
	public DataBaseUtility dbLib=new DataBaseUtility();
	public ExcelUtility eLib=new ExcelUtility();
	public FileUtility fLib=new FileUtility();
	public JavaUtility jLib=new JavaUtility();
	
	
	@BeforeSuite(groups= {"smokeTest","regressionTest"})
	public void configBS() throws SQLException
	{
		System.out.println("=====Connect DB, Report Config=====");
		dbLib.getDbConnection();
		
	
	}
	
	
	//@Parameters("BROWSER")	
	@BeforeClass(groups= {"smokeTest","regressionTest"})
	public void configBC() throws Exception //we should pass String browser as an arg to configBC() for cross browser testing
	{
		System.out.println("==Launch the BROWSER==");
		String BROWSER1=fLib.getDataFromPropertiesFile("browser");
		//String BROWSER=browser;
		//String BRWOSER="firefox";
		if (BROWSER1.equals("chrome"))
		{
			driver=new ChromeDriver();
			
		}
		else if(BROWSER1.equals("firefox"))
		{
			driver=new FirefoxDriver();
		}
		else if(BROWSER1.equals("edge"))
		{
			driver=new EdgeDriver();
			
		}
		else
		{
			driver=new ChromeDriver();
		}
		sDriver=driver;//temp variable
		
		UtilityClassObject.setDriver(driver);
	}
	
	@BeforeMethod(groups= {"smokeTest","regressionTest"})
	public void configBM() throws Exception 
	{
		
		System.out.println("===Login to APPLICATION===");
			String URL=fLib.getDataFromPropertiesFile("url");
			String USERNAME=fLib.getDataFromPropertiesFile("username");
			String PASSWORD=fLib.getDataFromPropertiesFile("password");
			LoginPage lp=new LoginPage(driver);
			lp.loginToApp(URL, USERNAME, PASSWORD);
		
	}
	
	
	
	@AfterMethod(groups= {"smokeTest","regressionTest"})
	public void configAM() throws InterruptedException 
	{
		Thread.sleep(4000);
		System.out.println("===LOGOUT of Application===");
		HomePage hp=new HomePage(driver);
		hp.logOut();
		
	}
	
	@AfterClass(groups= {"smokeTest","regressionTest"})
	public void configAC()
	{
		System.out.println("==Close the BROWSER==");
		driver.quit();
		
	}
	
	@AfterSuite(groups= {"smokeTest","regressionTest"})
	public void configAS() throws SQLException
	{
		System.out.println("======close Db, Report Backup======");
		dbLib.closeDbconnection();
		
		
	}
	
}
