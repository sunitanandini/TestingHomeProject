package com.automation.pages;

import static org.testng.Assert.assertNotNull;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.automation.library.Base;


public class CheckoutProgressPage extends Base {
	private WebDriver driver;

	public CheckoutProgressPage(WebDriver driver) {

		WebElement emailAdressElem = myLibrary.waitForElementVisibility(By.id("guest_email"));
		// myLibrary.highlightElement(emailAdressElem);
		assertNotNull(emailAdressElem);

		String pageTitle = driver.getTitle();
		log.info("CheckOutProgress page --> Page Title--> " + pageTitle);

	}

}
