package com.automation.library;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DataProviderPractice 
{
	@Test(dataProvider = "getData") //3rd Step:--> name of dataprovider has to be mentioned with the name of the
	                                            //method inside the DataProvider annotation
	public void DataMatrix(String username, String password,int phnnumber, String browsertype)
	{              //no. of input parameters inside the method =  no. of columns in the DataProvider object[][]
		
	}
	
	@DataProvider
	//1st Step:--> returns a 2d object array
	//2nd Step:--> rows and columns - you have to specify
	public Object[][] getData()
	{
		Object[][] data = new Object[3][4]; //[rows][column]
		
		data[0][0] = "Username" ;
		data[0][1] = "Password";
		data[0][2] = 123456;
		data[0][3] = "Mozilla" ;
		
		data[1][0] = "Username1" ;
		data[1][1] = "Password1";
		data[1][2] = 123457;
		data[1][3] = "Mozilla1" ;
		
		data[2][0] = "Username2" ;
		data[2][1] = "Password2";
		data[2][2] = 123458;
		data[2][3] = "Mozilla2" ;
		
	return data;
		
 	}
	
	

}
