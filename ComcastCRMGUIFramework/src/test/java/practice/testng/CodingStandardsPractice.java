package practice.testng;
/**
 * test class for contact module
 * @author Nithin
 *
 */

import org.testng.annotations.Test;

import com.comcast.crm.objectrepositoryutility.LoginPage;
import com.crm.comcast.basetest.BaseClass;

public class CodingStandardsPractice extends BaseClass
{
	/**
	 * Scenario: login()--->naviagateContact--->createContact()-----verify
	 */
	
	
	
	@Test
	public void searchContactTest()
	{
		LoginPage lp=new LoginPage(driver);
		/* step 1: login to the app*/
		lp.loginToApp("url", "admin", "root");
	}

}
