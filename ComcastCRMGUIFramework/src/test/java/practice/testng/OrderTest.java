package practice.testng;

import org.testng.annotations.Test;

public class OrderTest
{
	@Test(invocationCount = 10)
	public void createOrderTest()
	{
		System.out.println("Execute createOrderTest==>123");
		
	}
	
	@Test(enabled = true,dependsOnMethods = "createOrderTest")
	public void billingAnOrderTest()
	{
		System.out.println("Execute billingAnOrderTest==>123");
	}
}
