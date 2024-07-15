package practice.hometest;

import java.lang.reflect.Method;

import org.testng.Reporter;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class PracticeReportLog
{
	@Test
	public void homePageTest(Method mtd)
	{
		Reporter.log(mtd.getName()+" Test Start");
		Reporter.log("step-1",true);
		Reporter.log("step-2",true);
		Reporter.log("step-3",true);
		Reporter.log("step-4",true);
		Reporter.log(mtd.getName()+" Test End");
		
	}
	@Test
	public void verifyLogoHomePageTest1(Method mtd)
	{
		Reporter.log(mtd.getName()+" Test Start");
		Reporter.log("step-1",true);
		Reporter.log("step-2",true);
		Reporter.log("step-3",true);
		Reporter.log("step-4",true);
		Reporter.log(mtd.getName()+" Test End");
		
	}
}
