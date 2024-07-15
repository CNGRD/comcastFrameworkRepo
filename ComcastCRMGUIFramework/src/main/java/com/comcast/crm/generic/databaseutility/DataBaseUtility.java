package com.comcast.crm.generic.databaseutility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.cj.jdbc.Driver;
import com.mysql.cj.protocol.Resultset;

public class DataBaseUtility 
{
	Connection conn;
	public void getConnection(String url,String username,String password) throws SQLException
	{
		
		try 
		{
			Driver driver=new Driver();
			DriverManager.registerDriver(driver);
			
			conn=DriverManager.getConnection(url,username,password);
			
		} 
		catch (Exception e) 
		{
			System.out.println("Sql Exception Caught");
		}
	}
	public void getDbConnection() throws SQLException
	{
		
		try 
		{
			Driver driver=new Driver();
			DriverManager.registerDriver(driver);
			
			conn=DriverManager.getConnection("jdbc:mysql://106.51.90.215:3333/projects","root@%", "root");
			
		} 
		catch (Exception e) 
		{
			System.out.println("Sql Exception Caught");
		}
	}
	public void closeDbconnection() throws SQLException
	{
		conn.close();
	}

	public ResultSet executeNonSelectQuery(String query) throws SQLException
	{
		ResultSet res=null;
		try {
			java.sql.Statement stat = conn.createStatement();
			res= stat.executeQuery(query);
			
		} catch (Exception e) 
		{
			System.out.println("SQL Exception Caught");
		}
		return res;
	}
	
	public int nonSelectQuery(String query)
	{
		int res=0;
		try 
		{
			Statement stat=conn.createStatement();
			 res=stat.executeUpdate(query);
			
		} 
		catch (Exception e) 
		{
			System.out.println("SQL exception caught");
		}
		return res;
	}
	

}
