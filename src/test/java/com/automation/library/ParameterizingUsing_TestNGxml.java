package com.automation.library;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ParameterizingUsing_TestNGxml 
{
public static WebDriver driver;

   @Test
   @Parameters({"browser","url","emailid","password"})
   public void Rediff(String browser,String url,String emailid,String password)
   {
     if(browser.equals("Chrome"))
	   {
         WebDriverManager.chromedriver().setup();
         driver = new ChromeDriver();     //browser is input parameter
	   }else if(browser.equals("Firefox"))
	   {
		   WebDriverManager.firefoxdriver().setup();
		   driver = new FirefoxDriver();
	   }
      driver.get(url); //url is input parameter
      driver.manage().window().maximize();
      driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
      driver.manage().timeouts().pageLoadTimeout(10,TimeUnit.SECONDS);
      driver.findElement(By.xpath("//*[@id=\"signin_info\"]/a[1]")).click();
      driver.findElement(By.xpath("//*[@id=\"login1\"]")).sendKeys(emailid); //emailid is input parameter	
      driver.findElement(By.xpath("//*[@id=\"password\"]")).sendKeys(password);//input parameter
      driver.findElement(By.name("proceed")).click();
    }
   
	/*
	 * @Test public void Rediff() { WebDriverManager.chromedriver().setup(); driver
	 * = new ChromeDriver(); //browser is input parameter
	 * driver.get("https://rediff.com"); //url is input parameter
	 * driver.manage().window().maximize();
	 * driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
	 * driver.manage().timeouts().pageLoadTimeout(10,TimeUnit.SECONDS);
	 * driver.findElement(By.xpath("//*[@id=\"signin_info\"]/a[1]")).click();
	 * driver.findElement(By.xpath("//*[@id=\"login1\"]")).sendKeys(
	 * "seleniumpanda@rediffmail.com"); //emailid is input parameter
	 * driver.findElement(By.xpath("//*[@id=\"password\"]")).sendKeys("Selenium@123"
	 * );//input parameter driver.findElement(By.name("proceed")).click();
	 * 
	 * 
	 * 
	 * }
	 */
}
