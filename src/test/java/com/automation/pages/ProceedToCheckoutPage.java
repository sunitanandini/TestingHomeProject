package com.automation.pages;

import static org.testng.Assert.assertNotNull;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.automation.library.Base;

public class ProceedToCheckoutPage extends Base 
{
   	private WebDriver driver;
	private static String proceedToCheckOutPartialLink =  "Proceed to Checkout";

	public ProceedToCheckoutPage(WebDriver _driver) 
	{
		this.driver = _driver;
	WebElement proceedToCheckOutElem =	myLibrary.waitForElementVisibility(By.partialLinkText(proceedToCheckOutPartialLink));
	assertNotNull(proceedToCheckOutElem);
	
	String pageTitle = driver.getTitle();
	System.out.println("Proceed To Checkout Page > page tile: " + pageTitle);
		
	}
	
	public CheckoutProgressPage click_ProceedToChekoutButton()
	{
		WebElement proceedToCheckOutElem =	driver.findElement(By.partialLinkText(proceedToCheckOutPartialLink));
		proceedToCheckOutElem.click();
		
		CheckoutProgressPage checkoutProgressPage = new CheckoutProgressPage(driver);

		return checkoutProgressPage;
	}

}
