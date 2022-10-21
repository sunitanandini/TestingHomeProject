package com.automation.test;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import com.automation.library.Base;
import com.automation.pages.CheckoutProgressPage;
import com.automation.pages.ChooseAFormatPage;
import com.automation.pages.LandingPage;
import com.automation.pages.ProceedToCheckoutPage;
import com.automation.pages.ScienceCategoryPage;

public class BuyACourseTestScript extends Base
{
    //public LandingPage myLandingPage;
	public LandingPage landingPage;
	public ScienceCategoryPage scienceCategoryPage;
	public ChooseAFormatPage chooseAFormatPage;
	public ProceedToCheckoutPage proceedToCheckoutPage;
	public CheckoutProgressPage checkoutProgressPage;
	
	@Test
	public void buyOurNightSkyCourseTest()
	{
		landingPage = new LandingPage(driver);
		landingPage.gotoTheGreatCoursesWebSite();
		myLibrary.sleep(4);
		
	    scienceCategoryPage = landingPage.click_ExploreCategories_ScienceLink();
	    //myLibrary.sleep(3);
		scienceCategoryPage.searchCourses("Our Night Sky");
		//myLibrary.sleep(3);
		chooseAFormatPage = scienceCategoryPage.selectTheCourse();
		//myLibrary.sleep(3);
		chooseAFormatPage.selectCourseType("Instant Video");
		//myLibrary.sleep(4);
		proceedToCheckoutPage = chooseAFormatPage.clickAddToCartBtn();
		//proceedToCheckoutPage.click_ProceedToChekoutButton();
		checkoutProgressPage =proceedToCheckoutPage.click_ProceedToChekoutButton();
		myLibrary.sleep(5);
	}
	
}
