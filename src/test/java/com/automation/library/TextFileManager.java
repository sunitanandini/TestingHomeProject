package com.automation.library;

import static org.testng.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//Pure Java Code
public class TextFileManager 
{
	public static Logger log = LoggerFactory.getLogger(TextFileManager.class);
	
	private String fileName;
	
	//locating the file
	public TextFileManager(String filePath)
	{
		try
		{
			File file = new File(filePath);
			String fullFilePath = file.getAbsolutePath();
			String parentFolder = file.getParent();
			File file2 = new File(parentFolder);
			if(! file2.exists())
			{
				file2.mkdirs();
			}
			fileName =fullFilePath;
				
		} catch(Exception e)
	    	{
			  log.error("Error", e);
			  assertTrue(false);
			}
	 }
	
	//Reading the file
	public String readFile()
	{ 
		String data = null;
 		String line = null;
 		BufferedReader bfrReader = null;
 		FileReader fileReader = null;
 		StringBuffer stringBuffer = null;
 		String newLine =  System.lineSeparator();
 		try
 		{
 			fileReader = new FileReader(fileName);
 			bfrReader = new BufferedReader(fileReader);
 			stringBuffer = new StringBuffer();
 			
 			while((line = bfrReader.readLine()) !=null)
 			{
 			 stringBuffer.append(line+newLine);	
 			}
 			data = stringBuffer.toString();
 			
 		}catch(Exception e)
 		{
 			log.error("Error",e);
 		}finally
 		{
 			if(bfrReader !=null)
 			{
 				try
 				{
 					bfrReader.close();
 					stringBuffer = null;
 				}catch(Exception e)
 				{
 					log.error("Error",e);
 					assertTrue(false);
 				}
 			}
 		}
 		log.info("Reading external file " + fileName);	
		return data;
	}
	
	//receives the input data
	public void WriteFile(String inputData)
	{
		FileWriter fileWriter = null;
		BufferedWriter bfWriter = null;
		try
		{
			fileWriter = new FileWriter(fileName);
			bfWriter = new BufferedWriter(fileWriter);
			bfWriter.write(inputData);
		}catch(Exception e)
		 {
			log.error("Error",e);
		 } finally
		 {
			 try
			 {
				 bfWriter.close();
				 fileWriter.close();
			 }catch(Exception e)
			 {
				 log.error("Error",e);
				 assertTrue(false);
			 }
		 }
		log.info("Creating external file " +fileName);
	}
	
	public static void main(String[] args) 
	 {
		TextFileManager readManager = new TextFileManager("C:/abc/r4/Sunita_Singh/mypet.txt");
		String data = readManager.readFile();
		log.info("Data:-  " + data);
		
		//creating the txt file
		//String inputData = "I Love Java Programing \n" +  "Sunita Got the job as Senior Test Automation Enginer";
		//TextFileManager writeManager = new TextFileManager("C:/abc/r4/Sunita_Singh/mypet.txt");
		//writeManager.WriteFile(inputData);
		
	 }
	
}
