package practice.hometest;

import java.lang.reflect.Method;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class HomePageVerificationTest 
{
	@Test
	public void homePageTest(Method mtd)
	{
		System.out.println(mtd.getName()+" Test Start");
		String expectedPage="Home";
		WebDriver driver=new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.manage().window().maximize();
		driver.get("http://localhost:8888");
		
		driver.findElement(By.name("user_name")).sendKeys("admin");
		driver.findElement(By.name("user_password")).sendKeys("root");
		driver.findElement(By.id("submitButton")).click();
		
		String actTitle=driver.findElement(By.xpath("//a[contains(text(),'Home')]")).getText();
		
		//Hard Assert
		Assert.assertEquals(actTitle, expectedPage);
		driver.close();
		System.out.println(mtd.getName()+" Test End");
		
	}
	
	@Test
	public void verifyLogoPageTest(Method mtd)
	{
		System.out.println(mtd.getName()+" Test start");
		
		WebDriver driver=new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.manage().window().maximize();
		driver.get("http://localhost:8888");
		
		driver.findElement(By.name("user_name")).sendKeys("admin");
		driver.findElement(By.name("user_password")).sendKeys("root");
		driver.findElement(By.id("submitButton")).click();
		
		boolean status=driver.findElement(By.xpath("//img[@src='themes/softed/images/Home.PNG']")).isEnabled();
		
		//Assert.assertEquals(true, status);
		//Hard Assert
		Assert.assertTrue(status);
		driver.close();
		System.out.println(mtd.getName()+" Test End");
		
	}

}
