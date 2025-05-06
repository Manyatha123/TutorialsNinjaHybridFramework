package com.tutorialsninja.qa.testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tutorialsninja.qa.base.Base;
import com.tutorialsninja.qa.pageobjects.HomePage;
import com.tutorialsninja.qa.pageobjects.SearchPage;

public class SearchTest extends Base {

	public SearchTest() {
		super();
	}

	public WebDriver driver;
	SearchPage searchPage;
	HomePage homePage;

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

	@BeforeMethod
	public void setup() {
		driver = initializeBrowserAndOpenApplicationURL(prop.getProperty("browserName"));
		homePage = new HomePage(driver);
	}

	@Test(priority = 1)
	public void verifySearchWithValidProduct() {
		searchPage = homePage.searchForAProduct(dataProp.getProperty("validProduct")); // we are searchpage to the leftside because the rightside value is giving the object of searchPage
		// driver.findElement(By.xpath("//div[@id=\"search\"]/descendant::button")).click();
		// //greatgrandchilds descedant!!
		Assert.assertTrue(searchPage.displayStatusOfHPValidProduct(),
				"Valid product HP is not displayed in the search results");

	}

	@Test(priority = 2)
	public void verifySearchWithInvalidProduct() {

		searchPage = homePage.searchForAProduct(dataProp.getProperty("invalidProduct"));
		Assert.assertEquals(searchPage.retrieveNoProductMessageText(),
				"abcd", "No product in search results is displayed");
	}

	@Test(priority = 3,dependsOnMethods= {"verifySearchWithValidProduct","verifySearchWithInvalidProduct"})
	public void verifySearchWithoutAnyProduct() {

		searchPage = homePage.clickOnSearchButton();
		Assert.assertEquals(searchPage.retrieveNoProductMessageText(),
				dataProp.getProperty("noProductMatchingSearchWarning"), "No product in search results is displayed");
	}

}
