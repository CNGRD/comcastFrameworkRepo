package practice.hometest;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.annotations.Test;

public class SampleTestForScreenShot
{
	@Test 
	public void amzonTest() throws Exception 
	{
		WebDriver driver=new  ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.manage().window().maximize();
		driver.get("https://www.amazon.com");
		
		//Step-1: create an object to EventFiring WebDriver
		EventFiringWebDriver eDriver=new EventFiringWebDriver(driver);
		
		//step 2: use getScreenshotAs() method get file type of screenshot
		File srcFile = eDriver.getScreenshotAs(OutputType.FILE);
		
		//step 3: store screen on local driver
		FileUtils.copyFile(srcFile, new File("./screenshots/test.png"));
	}

}

