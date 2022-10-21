package com.automation.pages;

import static org.testng.Assert.assertNotNull;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.automation.library.Base;
import com.automation.library.TextFileManager;

public class ChooseAFormatPage extends Base
{
	private WebDriver driver;
	private static String addToCartCSS = "div.BuyOptions.BuyOptions_isListTile_undefined.BuyOptions_minimal_undefined > div.BuyOptions-BtnWrapper > button > span";

	public ChooseAFormatPage(WebDriver _driver)
	{
		this.driver = _driver;
		
		WebElement addToCartElem = myLibrary.waitForElementVisibility(By.cssSelector(addToCartCSS));
		
	    assertNotNull(addToCartElem);
		String addToCartPgTitle = driver.getTitle();
		System.out.println("Add To Cart PageTitle -- > " +addToCartPgTitle);
	}
	
	public ChooseAFormatPage selectCourseType(String inputType)
	{
		WebElement chooseFormatSection = driver.findElement(By.cssSelector("div.BuyOptions.BuyOptions_isListTile_undefined.BuyOptions_minimal_undefined"));
		String radioBtnCSS = "div.BuyOptions-Option.BuyOptions-Option_isChosen_undefined.BuyOptions-Option_isProductPage";
		List<WebElement> radioBtnsElems =	chooseFormatSection.findElements(By.cssSelector(radioBtnCSS));
		 
		for(WebElement radioBtn : radioBtnsElems)
		{
			String radioBtnTxt =radioBtn.getText();
			
			{
				if(radioBtnTxt.contains(inputType))
				{
					radioBtn.click();
					log.info("'" + inputType + "'is Selected ");
				    break;
				}
			}
		}
		
		return this;
	}
	
	public ProceedToCheckoutPage clickAddToCartBtn()
	{
		WebElement addToCartElem = myLibrary.waitForElementToBeClickable(By.cssSelector(addToCartCSS));
        addToCartElem.click();
        
         ProceedToCheckoutPage proceedToCheckoutPage = new ProceedToCheckoutPage(driver);
         return proceedToCheckoutPage;
         
	}

}
