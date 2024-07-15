package practice.hometest;

import java.lang.reflect.Method;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class HomePageSampleTest
{
	@Test
	public void homePageTest(Method mtd)
	{
		System.out.println(mtd.getName()+" Test Start");
		SoftAssert assertObj=new SoftAssert();
		System.out.println("step-1");
		System.out.println("step-2");
		assertObj.assertEquals("Home", "Home");
		System.out.println("step-3");
		assertObj.assertEquals("Home-CRM", "Home-CRM");
		System.out.println("step-4");
		assertObj.assertAll();
		System.out.println(mtd.getName()+" Test End");
		
	}
	
	@Test
	public void verifyLogoPageTest(Method mtd)
	{
		System.out.println(mtd.getName()+" Test start");
		SoftAssert assertObj=new SoftAssert();
		System.out.println("step-1");
		System.out.println("step-2");
		assertObj.assertTrue(true);
		System.out.println("step-3");
		System.out.println("step-4");
		
		System.out.println(mtd.getName()+" Test End");
		
	}
}
