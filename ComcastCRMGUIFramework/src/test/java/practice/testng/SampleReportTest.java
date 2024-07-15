package practice.testng;

import java.time.Duration;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class SampleReportTest 
{
	public ExtentReports report;
	
	@BeforeSuite
	public void configBS()
	{
		ExtentSparkReporter spark=new ExtentSparkReporter("./advanceReports/report.html");
		spark.config().setDocumentTitle("CRM Test Suite Results");
		spark.config().setReportName("CRM Report");
		spark.config().setTheme(Theme.STANDARD);
		
		//add environment info and create test
		 report=new ExtentReports();
		report.attachReporter(spark);
		report.setSystemInfo("OS","Windows 10 home");
		report.setSystemInfo("BROWSER", "CHROME-126");
	}
	@AfterSuite
	public void configAS()
	{
		report.flush();
	}
	
	
	
	@Test
	public void createContactTest()
	{
		WebDriver driver=new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.manage().window().maximize();
		driver.get("http://localhost:8888");
		
		TakesScreenshot eDriver=(TakesScreenshot)driver;
		
		String filePath=eDriver.getScreenshotAs(OutputType.BASE64);
		
	
	ExtentTest test=report.createTest("createcontactTest");
		
		test.log(Status.INFO,"login to app");
		test.log(Status.INFO,"Navigate to contact page");
		test.log(Status.INFO,"Create contact");
		if ("HDFC".equals("HFDC")) 
		{
			test.log(Status.PASS,"Contact is created");
		}
		else
		{
			test.addScreenCaptureFromBase64String(filePath,"ErrorFile");
		}
		
	driver.close();	
	}
	

	/*
	 * @Test public void createContactWithOrgTest() { //spark report config
	 * 
	 * ExtentTest test=report.createTest("createcontactTest");
	 * 
	 * test.log(Status.INFO,"login to app");
	 * test.log(Status.INFO,"Navigate to contact page");
	 * test.log(Status.INFO,"Create contact"); if ("HDFC".equals("HDFC")) {
	 * test.log(Status.PASS,"Contact is created"); } else {
	 * test.log(Status.FAIL,"Contact is not created"); }
	 * 
	 * 
	 * }
	 * 
	 * @Test public void createContactWithPhoneNumTest() { //spark report config
	 * 
	 * ExtentTest test=report.createTest("createcontactTest");
	 * 
	 * test.log(Status.INFO,"login to app");
	 * test.log(Status.INFO,"Navigate to contact page");
	 * test.log(Status.INFO,"Create contact"); if ("HDFC".equals("HDFC")) {
	 * test.log(Status.PASS,"Contact is created"); } else {
	 * test.log(Status.FAIL,"Contact is not created"); }
	 * 
	 * }
	 */
}
