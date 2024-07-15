package com.comcast.crm.Listeners;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.crm.comcast.basetest.BaseClass;


//@Listeners(com.comcast.crm.generic.listenerUtility.ListenerImplementationClass.class)
public class InvoiceTest extends BaseClass
{
	@Test(retryAnalyzer = com.comcast.crm.generic.listenerUtility.RetryListenerImp.class)
	public void activateTest() 
	{
		System.out.println("execute activateTest");
		String actTitle=driver.getTitle();
		Assert.assertEquals(actTitle, "Login");
		
		System.out.println("step-1");
		System.out.println("step-2");
		System.out.println("step-3");
		System.out.println("step-4");
		
	}
	/*
	 * @Test public void createInvoiceWithContactTest() {
	 * System.out.println("Execute createInvoiceWithContactTest");
	 * System.out.println("step-1"); System.out.println("step-2");
	 * System.out.println("step-3"); System.out.println("step-4"); }
	 */
}
