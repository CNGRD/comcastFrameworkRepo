package practice.testng;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class CreateContact_DP_Test 
{
	@Test(dataProvider = "getData")
	public void createContactTest(String firstName,String lastName,long phoneNum)
	{
		System.out.println("FirstName:"+firstName+", LastName:"+lastName+",PhoneNumber:"+phoneNum);
	}
	
	@DataProvider
	public Object[][] getData()
	{
		Object[][] objArr=new Object[3][3];
		objArr[0][0]="Nithin";
		objArr[0][1]="hr";
		objArr[0][2]=8798987687l;
		
		objArr[1][0]="Stev";
		objArr[1][1]="Singh";
		objArr[1][2]=7658765457l;
		
		objArr[2][0]="Rana";
		objArr[2][1]="sen";
		objArr[2][2]=8618318721l;
		
		
		
		return objArr;
	}

}
