package com.comcast.crm.generic.webdriverutility;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class JavaUtility 
{
	public int getRandomNumber()
	{
		Random rand=new Random();
		int randomNumber=rand.nextInt(5000);
		return randomNumber;
	}
	public String getSystemDateYYYYDDMM()
	{
		Date dateObj=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		String date=sdf.format(dateObj);
		return date;
	}
	
	public String getRequiredDate(int days)
	{
		SimpleDateFormat sim=new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal=sim.getCalendar();
		cal.add(Calendar.DAY_OF_MONTH,30);
		String endDate=sim.format(cal.getTime());
		return endDate;
		
	}
	


}
