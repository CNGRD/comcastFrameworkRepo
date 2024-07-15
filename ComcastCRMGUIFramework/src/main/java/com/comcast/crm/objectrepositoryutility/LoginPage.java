package com.comcast.crm.objectrepositoryutility;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.comcast.crm.generic.webdriverutility.WebDriverUtility;

public class LoginPage extends WebDriverUtility
{
	//rule1 : create a separate java class
	//rule2 : object creation
	
	WebDriver driver;
	public LoginPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(name="user_name")
	 private WebElement userNameEdt;
	
	@FindBy(name="user_password")
	private WebElement passwordEdt;
	  
	@FindBy(id="submitButton")
	private WebElement loginBtn;

	//rule 3: object Initialization
	
	//rule 4:Object Encapsulation
	
	public WebElement getUserNameEdt()
	{
		return userNameEdt;
	}

	public WebElement getPasswordEdt() 
	{
		return passwordEdt;
	}

	public WebElement getLoginBtn()
	{
		return loginBtn;
	}
	
	//rule 5: Provide Action
	public void loginToApp(String url,String username , String password)
	{
		waitForPageToLoad(driver);
		driver.get(url);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		userNameEdt.sendKeys(username);
		passwordEdt.sendKeys(password);
		loginBtn.click();
	}
	
	
	
	

}
