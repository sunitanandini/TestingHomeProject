package com.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.automation.library.Base;

public class LandingPage extends Base 
{
	private String url = "https://www.thegreatcourses.com/";
	private WebDriver driver;
	
	//Default constructor	
	public LandingPage()
	{
		
	}
    //User define constructor
	public LandingPage(WebDriver _driver)
	{
		this.driver = _driver;
	}
	
	public LandingPage gotoTheGreatCoursesWebSite()
	{
		driver.get(url);
		String pageTitle = driver.getTitle();
		System.out.println("Page Title is --> " + pageTitle);
		return this;
	}
	
	public ScienceCategoryPage click_ExploreCategories_ScienceLink()
	{
		
	    WebElement scienceLinkElem =driver.findElement(By.partialLinkText("Science ("));
	    scienceLinkElem.click();
	    String pageTitle = driver.getTitle();
	    System.out.println("page title: " + pageTitle);
	
		myLibrary.setDriver(driver);
		
		ScienceCategoryPage scPage = new ScienceCategoryPage(driver);
		return scPage;
	}

}
