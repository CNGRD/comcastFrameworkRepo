package com.comcast.crm.generic.fileutility;

import java.io.FileNotFoundException;
import java.io.FileReader;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class JsonUtility
{
	public String getDataFromJSONFile(String key) throws Throwable
	{
		FileReader fileR=new FileReader("./configAppData/appCommonData.json");
		
		//step1: parse json physical file into JAVA object using JsonParse class
				JSONParser parser=new JSONParser();
				Object obj=parser.parse(fileR);
				
				//strp2: convert java object in to JsonObject using down casting
				JSONObject map=(JSONObject)obj;
				
				String data=(String) map.get(key);
				return data;
				
	}

}
