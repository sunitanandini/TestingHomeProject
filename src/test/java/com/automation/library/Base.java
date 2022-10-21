package com.automation.library;

import java.sql.Timestamp;
import java.util.Date;

import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import com.automation.library.SeleniumGlobalLibrary.Browser;

public class Base 
{
   // static final Logger log = LogManager.getLogger(Base.class);
	public static Logger log = LoggerFactory.getLogger(Base.class); 
	
	public static WebDriver driver;
	public static SeleniumGlobalLibrary myLibrary ;
	
	private  String browserType;
	private String demoMode;
	
	@BeforeClass 
	public void beforeAllTestMethods()
	{
		log.info("Automated Test Suit started........");
		JavaPropertiesManager writeManager = new JavaPropertiesManager("src/test/resources/session.properties");
		writeManager.setProperty("session", getCurrentTime());
		
		//Read config file here
		JavaPropertiesManager readmanager = new JavaPropertiesManager("src/test/resources/config.properties");
		 browserType = readmanager.readProperty("browser");
		 demoMode = readmanager.readProperty("demoMode");
	}
	
	@AfterClass
	public void cleanAfterAllTestMethods() 
	{
		if(driver != null)
		{
			driver.quit();
		}
		log.info("Automated test suit ended.......");
	}
	
	//setUpBeforeEachTest
	@BeforeMethod
	public void setUp()
	{
		try
		{
		 log.info("Running before test setUp..............");
		  myLibrary = new SeleniumGlobalLibrary();
		 
		  if(browserType.toLowerCase().contains("chrome"))
		  {
			  driver = myLibrary.startABrowser(Browser.CHROME); 
		  }
		  else if(browserType.toLowerCase().contains("edge"))
		  {
			  driver = myLibrary.startABrowser(Browser.EDGE_CHROMIUM);
		  }
		  else
		  {
			  log.error("not implemented yet !", new NullPointerException());
		  }
		  
		  //driver = myLibrary.startABrowser(Browser.CHROME);
		 myLibrary.setDriver(driver);
		 if(demoMode.toLowerCase().contains("on"))
		 {
			 myLibrary.setDemoMode(true);
		 }else
		 {
			  
		 }
		 
		// driver = myLibrary.startABrowser(Browser.EDGE_CHROMIUM);
	    } catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	//cleanUpAfterEachTest
	@AfterMethod
	public void cleanUp(ITestResult iResult)
	{
		log.info("Starting after test cleanup..............");
		
		if(ITestResult.FAILURE == iResult.getStatus())
		{
			//If test fail, capture Screenshot Method
			System.out.println("Test Name : " +iResult.getName());
			myLibrary.takeScrnShot("src/test/resources/ScreenShot",iResult.getName());
			
		}
		
		if(driver != null)
    	{
    	   driver.close();
    	}
	}
	
	//temporarily we copied here from SeleniumGlobalLibrary
	public String  getCurrentTime()
	   {
		   String finalTimeStamp = null;
		
		   Date date = new Date();
		   date.getTime();
		   String tempTime = new Timestamp(date.getTime()).toString();
		   log.info("Original Time : [" + tempTime + " ]");
		   
		  finalTimeStamp = tempTime.replace("-", "").replace(":", "").replace(".","").replace(" ", "");
		  log.info("Final timeStamp : [" + finalTimeStamp + " ]");
		  
		  return finalTimeStamp ;
	   }
	   
	
}
