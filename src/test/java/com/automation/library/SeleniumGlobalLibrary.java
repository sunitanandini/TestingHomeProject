package com.automation.library;

import static org.testng.Assert.assertTrue;

import java.io.File;
import java.net.URL;
import java.nio.file.Path;
import java.sql.Timestamp;
import java.time.Duration;
import java.util.Date;
import java.util.Optional;
import java.util.function.Function;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.io.Files;

import io.github.bonigarcia.wdm.WebDriverManager;

/***
 * This class is to create Wrapper methods for basic selenium functions.
 * @author Sunita
 *Class is created on 14March
 */


public class SeleniumGlobalLibrary 
{
   //static final Logger log = LogManager.getLogger(SeleniumGlobalLibrary.class);
	public static Logger log = LoggerFactory.getLogger(SeleniumGlobalLibrary.class);
	
   private WebDriver driver;
   private boolean isDemoMode = false;  //By default its false   //flag
   private boolean isRemote = false;   //By default its false   //flag
   private boolean isHeadless = false;
   
    public boolean getIsHeadless() 
    {
		return isHeadless;
	}

	public void setIsHeadless(boolean _isHeadless) 
	{
		this.isHeadless = isHeadless;
	}
   
      public boolean getDemoMode() 
       {
        return isDemoMode;
       }

      public void setDemoMode(boolean isDemoMode) 
      {
	   this.isDemoMode = isDemoMode;
      }
      
      
      public boolean getIsRemote() 
      {
       return isRemote;
      }

     public void setIsRemote(boolean _isRemote) 
     {
	   this.isRemote =  _isRemote;
     }

//Getter & Setter for driver
   public WebDriver getDriver() 
   {
	return driver;
   }

   public void setDriver(WebDriver driver) 
   {
	this.driver = driver;
   }
   

private int waitTimeInSec = 30;
   
   // /*** now press enter
   /***
    * This enum is for browser options
    * @author Sunita
    *
    */
   
   public enum Browser
   {
	   CHROME,SAFARI,FIREFOX,EDGE_CHROMIUM
   }
   
   
   /***
    * This code is written on July 26 , 2022
    */
   //hubURL  --> IP Address of the remotecomputer
   public WebDriver startARemoteBrowser(String hubURL, Browser browser)
   {
	   try
	   {
		  switch (browser) 
		   {
		   case CHROME:
			driver = startARemoteChromeBrowser(hubURL);
			break;
			
		   case FIREFOX:
			driver = startARemoteFireFoxBrowser(hubURL);
		   break;
		   
		   case SAFARI:
		   driver = startARemoteSafariBrowser(hubURL);
		   break;
		   
		   case EDGE_CHROMIUM:
		   driver = startARemoteEdgeChromiumBrowser(hubURL);
		   break;
		   

		   default:
			    log.info("Currently framework do not support this type of browser !");
		        log.info("Default browser set to 'Remote Chrome' Browser");
		        driver= startARemoteChromeBrowser(hubURL);
	            break;
		     }
       		  driver.manage().deleteAllCookies(); 
		   } catch(Exception e)
	      {
			  log.error("Error",e);
			  assertTrue(false);
		  }
	   
	   
	   
	   return driver;
   }

	private WebDriver startARemoteChromeBrowser(String hubURL) 
     {
    	try
    	{
    		DesiredCapabilities cap = new DesiredCapabilities();
    		//cao.setPlatform("Operating System");
    		//DesiredCapabilities are a set of key-value pairs encoded as a JSON object. 
    		//It helps QAs define basic test requirements such as operating systems, browser combinations, 
    		// browser versions, etc. within Selenium test scripts.
    		ChromeOptions chromeOps = new ChromeOptions();
    		//Running it headless mode --> Test Run but we cant see browser is launching
    		//Headless mode works only on Chrome, Firefox n EdgeChromium
    		//we want to run on headless mode here
    		if(isHeadless)
    		{
    			chromeOps.setHeadless(true);
    		}
    		chromeOps.merge(cap);
    		driver = new RemoteWebDriver(new URL(hubURL), chromeOps);
    	} catch(Exception e)
    	{
    		log.error("Error",e);
    		assertTrue(false);
    	}

	    return driver;
     }
   
	private WebDriver startARemoteEdgeChromiumBrowser(String hubURL) 
    {
    	try
   	      {
   		    DesiredCapabilities cap = new DesiredCapabilities();
   		    FirefoxOptions edgeOps = new FirefoxOptions();
   		    if(isHeadless)
   		      {
   		    	edgeOps.setHeadless(true);
   		      }
   		    edgeOps.merge(cap);
   		    driver = new RemoteWebDriver(new URL(hubURL), edgeOps);
     		
   	 }catch(Exception e)
   	 {
   	 	log.error("Error",e);
 		  assertTrue(false);
   	 }
   	return driver;

			
    }

    private WebDriver startARemoteFireFoxBrowser(String hubURL) 
    {
    	try
    	 {
    		
    		DesiredCapabilities cap = new DesiredCapabilities();
        	FirefoxOptions firefoxOps = new FirefoxOptions();
        	if(isHeadless)
    		{
    			firefoxOps.setHeadless(true);
    		}
        	firefoxOps.merge(cap);
    		driver = new RemoteWebDriver(new URL(hubURL), firefoxOps);
        	
    		
    	 }catch(Exception e)
    	 {
    	 	log.error("Error",e);
  		  assertTrue(false);
    	 }
    	return driver;
    }
    
    private WebDriver startARemoteSafariBrowser(String hubURL) 
    {
    	try
   	 	 {
   		
   		  DesiredCapabilities cap = new DesiredCapabilities();
        	FirefoxOptions safariOps = new FirefoxOptions();
       	   if(isHeadless)
   		     {
       		safariOps.setHeadless(true);
   		     }
       	  safariOps.merge(cap);
   		  driver = new RemoteWebDriver(new URL(hubURL), safariOps);
       	
   		
   	 }catch(Exception e)
   	 {
   	 	log.error("Error",e);
 		  assertTrue(false);
   	 }
   	return driver;
    }


/***
    * This method starts browser and receives Browser enum
    * @param browser
    * @return WebDriver
    */
	
	public WebDriver startABrowser(Browser browser)
	{
	  try
	  {
		  switch(browser)
		  {
		  case CHROME:
		      driver = startChromeBrowser(browser);
		      break;
		  
		  case FIREFOX:
			  driver = startFirefoxBrowser(browser);
			  break;
			  
		  case SAFARI:
			  driver = startSafariBrowser(browser);
			  break;
			  
		  case EDGE_CHROMIUM:
			  driver = startEdgeChromiumBrowser(browser);
			  break;
			  
			  default:
				  log.info("Currently framework do not support this type of browser !");
				  log.info("Default browser set to Chrome Browser");
				  driver= startChromeBrowser(browser);
			  break;
		 } 
		  printDriverManagerInfo();
		  driver.manage().deleteAllCookies();
	  }catch (Exception e)
	  {
		  log.error("Error",e);
		  assertTrue(false);
	  }
	  return driver;
	}
	
	/***
	 * This methods Microsoft EdgeChromium Browser
	 * @return WebDriver
	 */

	private WebDriver startEdgeChromiumBrowser(Browser browser) 
	{
		//System.setProperty("webdriver.edge.driver", "src\\test\\resources\\Drivers\\msedgedriver.exe");
		startWebDriverManager(browser);
		driver = new EdgeDriver();
		driver.manage().window().maximize();
		//sleep();
		setWebsiteWaits();
		return driver;
	}

	/***
	 * This methods Apple Safari Browser
	 * @return WebDriver
	 */
	private WebDriver startSafariBrowser(Browser browser) 
	{
		log.error("This method is not implemented yet! Start Safari");
		startWebDriverManager(browser);
		driver = new SafariDriver();
		setWebsiteWaits();
		return driver;
	}

	/***
	 * This methods Firefox Browser
	 * @return WebDriver
	 */
	private WebDriver startFirefoxBrowser(Browser browser) 
	{
		log.error("This method is not implemented yet! Start Firefox");
		startWebDriverManager(browser);
		driver = new FirefoxDriver();
		setWebsiteWaits();
		return driver;
	}

	/***
	 * This methods Google Chrome Browser
	 * @return WebDriver
	 */
	private WebDriver startChromeBrowser(Browser browser) 
	{
		//System.setProperty("webdriver.chrome.driver", "src/test/resources/Drivers/chromedriver.exe");
	    //Start a chrome browser                       /TheGreateCoursesJuly/src/test/resources/Drivers/chromedriver.exe
	     
		startWebDriverManager(browser);
		driver =   new ChromeDriver();
	    // Thread.sleep(3000);
	     //sleep();   it was for frank only
	     setWebsiteWaits();
		return driver;
	}
	
	/***
	 * This method is Selenium pageLoad ,implicit wait, scriptTimeout waits.
	 */
	private void setWebsiteWaits()
	{
	try
		{
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(waitTimeInSec));
			driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(waitTimeInSec));
			driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(waitTimeInSec));
			driver.manage().window().maximize();
		}catch(Exception e)
		{
		  e.printStackTrace();	
		}
	}
	
	public void handleCheckBoxRadioBtn(By by, boolean isChecked)
	{
		if(isChecked)   //or if(isChecked == true)
		{
		WebElement checkBoxElem = driver.findElement(by);
		
		boolean checkBoxState = checkBoxElem.isSelected();
		  if(checkBoxState == true)
		  {
			  //do nothing
		  } else 
		  {
			  checkBoxElem.click();
		  }
		} 
		else  //user do not want to check the checkbox
		{
			WebElement checkBoxElem = driver.findElement(by);
			boolean checkBoxState = checkBoxElem.isSelected();
			if(checkBoxState==true)
			{
				checkBoxElem.click();
			} else
			{
				//do nothing
			}		
		}
	}
	
	public String fileUpload(By by,String filePath)
	{
		String fileFullPath = null;
		
		try
		{
		WebElement uploadElem = driver.findElement(by);
	    File file = new File(filePath);
		 //using file we can get the absolute path
		fileFullPath =  file.getAbsolutePath();
	    
		if(isRemote)
		{
          ((RemoteWebDriver) driver).setFileDetector(new LocalFileDetector());
		}
		uploadElem.sendKeys(fileFullPath);
		
		log.info("Uploading File --> "+ fileFullPath);
		} catch(Exception e)
		{
			e.printStackTrace();
		}
		return fileFullPath;
	}
	
	public void highlightElement(WebElement element)
	{
		if(isDemoMode == true)
		{
		 try
		 {
			 for(int i=0;i<4;i++)
			 {
				 JavascriptExecutor js = (JavascriptExecutor) driver;
				 js.executeScript("arguments[0].setAttribute('style',arguments[1];",element,
						          "color:red ; border:2px solid yellow");
				 sleep(.5);
				 js.executeScript("arguments[0].setAttribute('style',arguments[1];",element,"");
				 sleep(.5);
			 }
		 } catch(Exception e)
		 {
			 log.error("Error",e);
		   assertTrue(false);	   
		 }
		}
	}
	
	public String takeScrnShot(String filePath, String screenshotName)
	{
		String finalScreenShotPath = null;
		try
		{
		String timpStamp = getCurrentTime();
		File file = new File(filePath);
		String absFilePath = file.getAbsolutePath();
		
		
		//We are checking the user is doing it remote or not
		if(isRemote)
		{
			driver = new Augmenter().augment(driver);
		}
		String scrnshotFileName= absFilePath +"/"+ screenshotName + timpStamp + ".png";
		File screenshotFile2 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		Files.copy(screenshotFile2, new File (scrnshotFileName));
		finalScreenShotPath = scrnshotFileName.replace("\"","/");
				
		} catch(Exception e)
		{
			e.printStackTrace();
		}
		log.info("ScreenShot---->" +finalScreenShotPath);
		return finalScreenShotPath;		
	}
	
	
	public void enterTxt(By by, String inputString ) 
	{
		WebElement textElem = driver.findElement(by);
		textElem.clear();
		textElem.sendKeys(inputString);
		
	}
	
	public WebElement waitForElementVisibility(By by)
	{
	  WebElement element = null;
	  try
	  {
	    WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(waitTimeInSec));
	    element =  wait.until(ExpectedConditions.visibilityOfElementLocated(by));
	  } catch(Exception e)
	  {
		  e.printStackTrace();
	  }
	  return element;
	}
	
	public WebElement waitForElementToBeClickable(By by)
	{
	  WebElement element = null;
	  try
	  {
	    WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(waitTimeInSec));
	    element =  wait.until(ExpectedConditions.elementToBeClickable(by));
	  } catch(Exception e)
	  {
		  e.printStackTrace();
	  }
	  return element;
	}

	public WebElement fluentWait(final By by)
	{
		WebElement element = null;
		try
		{
			Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(waitTimeInSec))
					                    .pollingEvery(Duration.ofSeconds(3))
					                    .ignoring(NoSuchElementException.class);
			element = wait.until(new Function<WebDriver , WebElement>()
					{
				       public WebElement apply(WebDriver driver)
				       {
				    	   return driver.findElement(by);
				       }
					});
			
		}catch(Exception e)
		{
			log.error("Error",e);
			assertTrue(false);
		}
		
		return element;
	}
	
	
	private void startWebDriverManager(Browser browser)
	{
		if(browser.equals(Browser.CHROME)) //always 32bit
		{
			WebDriverManager.chromedriver().setup();
		}else if(browser.equals(Browser.EDGE_CHROMIUM)) //it have two 32bit n 64bit and we want 32bit only
		{
			//please delete win64bit folder if win32 is not downloaded
			WebDriverManager.edgedriver().arch32().setup();
		}else if(browser.equals(Browser.FIREFOX)) 
		{
			WebDriverManager.firefoxdriver().setup();
		}else if(browser.equals(Browser.SAFARI)) 
		{
			WebDriverManager.safaridriver().setup();
		} else
		{
			log.error("Currently,we dont support [" + browser +"] browser driver implimentation");
		}
	}
	

	private void printDriverManagerInfo()
	{
	String chromeDriverPath = WebDriverManager.chromedriver().getDownloadedDriverPath();
	String edgeDriverPath   = WebDriverManager.edgedriver().getDownloadedDriverPath();
	String firefoxDriverPath= WebDriverManager.firefoxdriver().getDownloadedDriverPath();
	String safariDriverPath = WebDriverManager.safaridriver().getDownloadedDriverPath();
   
	Optional<Path> chromeBrowserPath = WebDriverManager.chromedriver().getBrowserPath();
	Optional<Path> edgeBrowserPath = WebDriverManager.edgedriver().getBrowserPath();
	Optional<Path> firefoxBrowserPath = WebDriverManager.firefoxdriver().getBrowserPath();
	Optional<Path> safariBrowserPath = WebDriverManager.safaridriver().getBrowserPath();
	
	log.info("Chrome Driver Path: " + chromeDriverPath );
	log.info("Chrome Browser Path: " + chromeBrowserPath );

	log.info("Edge Driver Path: " + edgeDriverPath );
	log.info("Edge Browser Path: " + edgeBrowserPath );
	
	log.info("Firefox Driver Path: " + firefoxDriverPath );
	log.info("Firefox Browser Path: " + firefoxBrowserPath );
	
	log.info("Safari Driver Path: " + safariDriverPath );
	log.info("Safari Browser Path: " + safariBrowserPath );

	}
	
	
	
	/***
	    * This sleep method is for hard pause for 5secs
	    */
	  
	
	//Method overloading
	/*
	 * public void sleep() { try { Thread.sleep(5*1000); } catch(Exception e) {
	 * e.printStackTrace(); } }
	 */
	
	   public void sleep(double inSeconds) 
	    {
		   try
		   {
			   Thread.sleep ((long)(inSeconds*1000));
		   } catch(Exception e)
		   {
			   e.printStackTrace();   
		   }
    	}
 
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
	   
		/*
		 * public static void main(String[] args) { SeleniumGlobalLibraryNew sgl = new
		 * SeleniumGlobalLibraryNew(); sgl.getCurrentTime(); }
		 */	 
}
