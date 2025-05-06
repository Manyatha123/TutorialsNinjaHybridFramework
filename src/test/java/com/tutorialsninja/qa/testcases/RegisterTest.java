package com.tutorialsninja.qa.testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tutorialsninja.qa.base.Base;
import com.tutorialsninja.qa.pageobjects.AccountSuccessPage;
import com.tutorialsninja.qa.pageobjects.HomePage;
import com.tutorialsninja.qa.pageobjects.RegisterPage;
import com.tutorialsninja.qa.utils.Utilities;

public class RegisterTest extends Base {

	public RegisterTest() {
		super();
	}

	public WebDriver driver;
	RegisterPage registerPage;
	AccountSuccessPage accountSuccessPage;

	@AfterMethod
	public void tearDown() {
		driver.quit();

	}

	@BeforeMethod
	public void setup() {

		driver = initializeBrowserAndOpenApplicationURL(prop.getProperty("browserName"));
		HomePage homePage = new HomePage(driver);
		registerPage = homePage.navigateToRegisterPage();

	}

	@Test(priority = 1)
	public void verifyRegisteringAnAccountWithMandatoryFields() {

		accountSuccessPage = registerPage.registerWithMandatoryFields(dataProp.getProperty("firstName"),
				dataProp.getProperty("lastName"), Utilities.generateEmailWithTimeStamp(),
				dataProp.getProperty("telephoneNumber"), prop.getProperty("validPassword"),
				prop.getProperty("validPassword"));
		Assert.assertTrue(
				accountSuccessPage.retrieveAccountSuccessPageHeading()
						.contains(dataProp.getProperty("accountSuccessfullyCreatedHeading")),
				"Account success page is not displayed");

	}

	@Test(priority = 2)
	public void verifyRegisteringAccountByProvidingAllFields() {

		accountSuccessPage = registerPage.registerWithAllFields(dataProp.getProperty("firstName"),
				dataProp.getProperty("lastName"), Utilities.generateEmailWithTimeStamp(),
				dataProp.getProperty("telephoneNumber"), prop.getProperty("validPassword"),
				prop.getProperty("validPassword"));
		Assert.assertTrue(
				accountSuccessPage.retrieveAccountSuccessPageHeading()
						.contains(dataProp.getProperty("accountSuccessfullyCreatedHeading")),
				"Account success page is not displayed");

	}

	@Test(priority = 3)
	public void verifyRegisteringAccountWithExistingEmailAddress() {

		accountSuccessPage = registerPage.registerWithAllFields(dataProp.getProperty("firstName"),
				dataProp.getProperty("lastName"), prop.getProperty("validEmail"),
				dataProp.getProperty("telephoneNumber"), prop.getProperty("validPassword"),
				prop.getProperty("validPassword"));
		Assert.assertTrue(
				registerPage.retrieveDuplicateEmailAddressWarning()
						.contains(dataProp.getProperty("duplicateEmailWarning")),
				"Warning message regarding duplicate Email address is not displayed");

	}

	@Test(priority = 4)
	public void verifyRegisterAccountwithoutFillingAnyDetails() {

		accountSuccessPage = registerPage.clickOnContinueButton();

		Assert.assertTrue(registerPage.retrievePrivacyPolicyWarning().contains(dataProp.getProperty("privacyPolicy")),
				"Privacy policy message is not displayed");

		Assert.assertEquals(registerPage.retrieveFirstNameWarning(), dataProp.getProperty("firstNameWarning"),
				"First Name Warning message is not displayed");

		Assert.assertEquals(registerPage.retrieveLastNameWarning(), dataProp.getProperty("lastNameWarning"),
				"Last Name Warning message is not displayed");

		Assert.assertEquals(registerPage.retrieveEmailWarning(), dataProp.getProperty("emailWarning"),
				"Email Warning message isn't displayed");

		Assert.assertEquals(registerPage.retrieveTelephoneWarning(), dataProp.getProperty("telephoneWarning"),
				"Telephone Warning message isn't displayed");

		Assert.assertEquals(registerPage.retrievePasswordWarning(), dataProp.getProperty("passwordWarning"),
				"Password Warning message isn't displayed");

	}
}
