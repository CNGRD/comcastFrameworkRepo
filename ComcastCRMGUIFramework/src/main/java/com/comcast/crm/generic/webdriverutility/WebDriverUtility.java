package com.comcast.crm.generic.webdriverutility;

import java.time.Duration;
import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebDriverUtility 
{
	public void waitForPageToLoad(WebDriver driver)
	{
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
	}
	
	public void waitForWebElementPresent(WebDriver driver,WebElement element)
	{
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOf(element));
		
	}
	public void switchToTabOnUrl(WebDriver driver,String partialUrl)
	{
		Set<String> set = driver.getWindowHandles();
		Iterator<String> it = set.iterator();
		
		while(it.hasNext())
		{
			String windowId=it.next();
			driver.switchTo().window(windowId);
			
			String actUrl=driver.getCurrentUrl();
			if (actUrl.contains(partialUrl)) 
			{
				break;
			}
		}
		
	}
	
	public void switchToTabOnTitle(WebDriver driver,String partialTitle)
	{
		Set<String> set = driver.getWindowHandles();
		Iterator<String> it = set.iterator();
		
		while(it.hasNext())
		{
			String windowId=it.next();
			driver.switchTo().window(windowId);
			
			String actTitle=driver.getTitle();
			if (actTitle.contains(partialTitle)) 
			{
				break;
			}
		}
		
	}

	
	public void switchToFrame(WebDriver driver,int index)
	{
		driver.switchTo().frame(index);
	}
	
	public void switchToFrame(WebDriver driver,String nameId)
	{
		driver.switchTo().frame(nameId);
	}
	
	public void switchToFrame(WebDriver driver,WebElement element)
	{
		driver.switchTo().frame(element);
	}
	
	public void switchToAlertAndAccept(WebDriver driver)
	{
		driver.switchTo().alert().accept();
	}
	
	public void switchToAlertAndDismiss(WebDriver driver)
	{
		driver.switchTo().alert().dismiss();
	}
	
	public void select(WebElement element,String text)
	{
		Select sel=new Select(element);
		sel.selectByVisibleText(text);
	}
	
	public void select(WebElement element,int index)
	{
		Select sel=new Select(element);
		sel.deselectByIndex(index);
	}
	
	
	public void mouseMoveOnElement(WebDriver driver,WebElement element)
	{
		Actions act=new Actions(driver);
		act.moveToElement(element).perform();
	}
	
	public void doubleClick(WebDriver driver,WebElement element)
	{
		Actions actions=new Actions(driver);
		actions.doubleClick(element).perform();
	}
	
	
	
	
	
}
