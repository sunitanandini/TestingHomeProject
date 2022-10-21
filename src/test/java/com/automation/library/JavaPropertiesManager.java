package com.automation.library;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JavaPropertiesManager 
{
	public static Logger log = LoggerFactory.getLogger(JavaPropertiesManager.class);
	private String propertiesFile;
	private Properties prop;
	private FileInputStream input;
	private FileOutputStream output;
	
	public JavaPropertiesManager(String propertiesFilePath)
	{
		try
		{
		if(propertiesFilePath.length()>0)
		 {
			propertiesFile = propertiesFilePath;
			prop = new Properties();		
		 }
		} catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
	
	//Reading properties
	public String readProperty(String key)
	{
		String value = null;
		try
		{
		input = new FileInputStream(propertiesFile);
		prop.load(input);
		value = prop.getProperty(key);
		
		} catch(Exception e)
		{
			e.printStackTrace();
		}
		return value;
	}
	
	//Writing properties
	public void  setProperty(String key, String value)
	{
	  try
		{
			output = new FileOutputStream(propertiesFile);
			prop.setProperty(key, value);
			prop.store(output, null); //if no comment pass null else value
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	/*
	 * public static void main(String[] args) 
	 * { 
	 * //String testFolderPath = "C:/Users/Sunita/Desktop/Testing/MyPropertyFolder/test.properties";
	 * //JavaPropertiesManager myProperty = new  JavaPropertiesManager(testFolderPath); 
	 * ////Create a new property file and  adding new properties 
	 * //myProperty.setProperty("name", "Sunita Singh");
	 * //myProperty.setProperty("kid", "Biyanka");
	 * //myProperty.setProperty("country","USA"); 
	 * //myProperty.setProperty("Fruits", "Apple"); 
	 * 
	 * //////OUTPUT In the folder test.properties
	 * 
	 * //Reading a property using the key 
	 * //String testFolderPath = "C:/Users/Sunita/Desktop/Testing/MyPropertyFolder/test.properties";
	 * //JavaPropertiesManager readMyProperty = new JavaPropertiesManager(testFolderPath); 
	 * //String value1 = readMyProperty.readProperty("name");
	 * //System.out.println("Key: Name, Value: " +value1);
	 * 
	 * //String value2 = readMyProperty.readProperty("kid"); ////OUTPUT in console
	 * System.out.println("Key:kid , Value: "+value2);
	 * 
	 * }
	 */}
