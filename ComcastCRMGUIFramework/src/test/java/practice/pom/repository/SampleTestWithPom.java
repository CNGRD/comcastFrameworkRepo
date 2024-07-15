package practice.pom.repository;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

public class SampleTestWithPom 
{
	@FindBy(name="user_name")
	WebElement ele1;
	
	@FindBy(name="user_password")
	WebElement ele2;
	
	@FindAll({@FindBy(id="submitButtonsss"),@FindBy(xpath="(//input[@value='Login'])[2]")})
	private WebElement ele3;//autohealing:Works like OR opr
	 
	//@FindBys({@FindBy(id="submitButtonsss"),@FindBy(xpath="(//input[@value='Login'])[2]")})
	//private WebElement ele3;//works like AND opr
	
	@Test
	public void sampleTest()
	{
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		driver.get("http://localhost:8888");
		
		SampleTestWithPom s=PageFactory.initElements(driver,SampleTestWithPom.class);
		
		s.ele1.sendKeys("admin");
		s.ele2.sendKeys("root");
		
		driver.navigate().refresh();
		
		s.ele1.sendKeys("admin");
		s.ele2.sendKeys("root");
		
		s.ele3.click();
		 
	}
	
}
