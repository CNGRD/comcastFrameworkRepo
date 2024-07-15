package practice.testng;

import org.testng.annotations.Test;

public class CreatecontactTest 
{
	@Test
	public void createContactTest()
	{
		System.out.println("Execute login");
		System.out.println("Execute navigate to contact ");
		System.out.println("Execute create contact");
		System.out.println("Execute verify contact");
		System.out.println("Execute logout");
	}
	
	@Test
	public void createContactWithMobileNumber()
	{
		System.out.println("Execute createContactWithMobileNumber");
	}
	

}
