package com.tutorialsninja.qa.testcases;  //the class name should contain test in it so as to run the class using maven

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod; // Ctrl+Shift+o removes unused imports
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.tutorialsninja.qa.base.Base;
import com.tutorialsninja.qa.pageobjects.AccountPage;
import com.tutorialsninja.qa.pageobjects.HomePage;
import com.tutorialsninja.qa.pageobjects.LoginPage;
import com.tutorialsninja.qa.utils.Utilities;

public class LoginTest extends Base {

	public LoginTest() {
		super(); // super class constructor --> super class is Base so the methods of Base class
					// are called automatically
	}

	public WebDriver driver;
	LoginPage loginPage;
	AccountPage accountPage;

	@BeforeMethod
	public void setup() {

		driver = initializeBrowserAndOpenApplicationURL(prop.getProperty("browserName"));
		HomePage homePage = new HomePage(driver);
		loginPage = homePage.navigateToLoginPage();
	}

	@AfterMethod
	public void tearDown() {

		driver.quit();
	}

	@Test(priority = 1, dataProvider = "validCredentialsSupplier")
	public void verifyLoginWithValidCredentials(String email, String password) {

		accountPage = loginPage.login(email, password);
		Assert.assertTrue(accountPage.getDisplayStatusOfEditYourAccountInformationOption(),
				"Edit your account information option isn't displayed");

	}

	@DataProvider(name = "validCredentialsSupplier")
	public Object[][] supplyTestData() {
		Object[][] data = Utilities.getTestDataFromExcel("Login");
		// Object[][] data =
		// {{"manyachippa@gmail.com","12345"},{"manyachippa1@gmail.com","12345"},{"manyachippa2@gmail.com","12345"}}
		// ;
		return data;
	}

	@Test(priority = 2)
	public void verifyLoginWithInvalidCredentials() {

		accountPage = loginPage.login(Utilities.generateEmailWithTimeStamp(), dataProp.getProperty("invalidPassword"));
		Assert.assertTrue(loginPage.retriveEmailPasswordNotMatchingWarningMessageText().contains(dataProp.getProperty("expectedWarning")),
				"Expected Warning message is not displayed");

	}

	@Test(priority = 3)
	public void verifyLoginwithInvalidEmailandValidPassword() {

		accountPage = loginPage.login(Utilities.generateEmailWithTimeStamp(), prop.getProperty("validPassword"));
		Assert.assertTrue(loginPage.retriveEmailPasswordNotMatchingWarningMessageText().contains(dataProp.getProperty("expectedWarning")),
				"Expected Warning message is not displayed");

	}

	@Test(priority = 4)
	public void verifyLoginwithValidEmailAndInvalidPassword() {

		accountPage = loginPage.login(prop.getProperty("validEmail"), dataProp.getProperty("invalidPassword"));
		Assert.assertTrue(loginPage.retriveEmailPasswordNotMatchingWarningMessageText().contains(dataProp.getProperty("expectedWarning")),
				"Expected Warning message is not displayed");

	}

	@Test(priority = 5)
	public void verifyLoginWithoutProvidingCredentials() {

		AccountPage accountPage = loginPage.clickOnLogin();
		Assert.assertTrue(loginPage.retriveEmailPasswordNotMatchingWarningMessageText().contains(dataProp.getProperty("expectedWarning")),
				"Expected Warning message is not displayed");

	}

	/*
	 * public String generateEmailWithTimeStamp() {
	 * 
	 * Date date = new Date(); String timeStamp = date.toString().replace(" ",
	 * "_").replace(":", "_"); return "manyachippa"+timeStamp+"@gmail.com";
	 */
}

//TestNG methods run in an alphabetical order. So, verifyLoginWithInvalidCredentials() runs first. We need to give priority.