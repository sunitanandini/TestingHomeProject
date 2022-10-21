package com.automation.pages;

import static org.testng.Assert.assertNotNull;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.automation.library.Base;

public class ScienceCategoryPage extends Base
{
	private WebDriver driver;
	public ScienceCategoryPage(WebDriver _driver) 
	{
		 this.driver = _driver;
         WebElement scienceHeaderElem = myLibrary.waitForElementVisibility(By.cssSelector("div.SearchPage-HeaderTitle > h1 > span"));
	    // myLibrary.highlightElement(scienceHeaderElem);
         assertNotNull(scienceHeaderElem);
	     String pageTitle = driver.getTitle();
	     System.out.println("Science Category Page:--> " +pageTitle);
	}

	public ScienceCategoryPage searchCourses(String inputCourseName )
	{
	WebElement searchElem = driver.findElement(By.cssSelector("#search-field"));
	searchElem.clear();
	searchElem.sendKeys(inputCourseName);
	searchElem.sendKeys(Keys.ENTER);
		
		return this;
	}
	
	public ChooseAFormatPage selectTheCourse()
	{
	   WebElement firstCourseSecElem = driver.findElement(By.cssSelector("div.grid.row > div:nth-child(1)"));
	   String courseTxt = firstCourseSecElem.getText();
	   //System.out.println("Selected Course Txt is: -->  " + courseTxt);
	   firstCourseSecElem.click();
		
	   ChooseAFormatPage cafPage = new ChooseAFormatPage(driver);
	   return cafPage;
	}
	
}
