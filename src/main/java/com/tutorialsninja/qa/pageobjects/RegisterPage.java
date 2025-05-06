package com.tutorialsninja.qa.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegisterPage {

	WebDriver driver;

	@FindBy(id = "input-firstname")
	private WebElement firstNameField;

	@FindBy(id = "input-lastname")
	private WebElement lastNameField;

	@FindBy(id = "input-email")
	private WebElement emailAddressField;

	@FindBy(id = "input-telephone")
	private WebElement telephoneField;

	@FindBy(id="input-password")
	private WebElement passwordField;

	@FindBy(id="input-confirm")
	private WebElement confirmPasswordField;

	@FindBy(name = "agree")
	private WebElement privacyPolicyCheckboxField;

	@FindBy(xpath = "//input[@value=\"Continue\"]")
	private WebElement continueButton;

	@FindBy(xpath = "//input[@name=\"newsletter\"][@value=\"1\"]")
	private WebElement yesNewsLetter;
	
	@FindBy(xpath= "//div[@class=\"alert alert-danger alert-dismissible\"]")
	private WebElement duplicateEmailAddressWarning;
	
	@FindBy(xpath = "//div[@class=\"alert alert-danger alert-dismissible\"]")
	private WebElement privacyPolicyWarning;
	
	@FindBy(xpath = "//input[@id=\"input-firstname\"]/following-sibling::div")
	private WebElement firstNameWarning;
	
	@FindBy(xpath="//input[@name=\"lastname\"]/following-sibling::div")
	private WebElement lastNameWarning;
	
	@FindBy(xpath = "//div[text()=\"E-Mail Address does not appear to be valid!\"]")
	private WebElement emailWarning;
	
	@FindBy(xpath = "//input[@id=\"input-telephone\"]/following-sibling::div")
	private WebElement telephoneWarning;
	
	@FindBy(xpath = "//div[text()=\"Password must be between 4 and 20 characters!\"]")
	private WebElement passwordWarning;
	
	public RegisterPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	

//ActionMethods
	public void enterFirstName(String firstNameText) {
		firstNameField.sendKeys(firstNameText);
	}

	public void enterLastName(String lastNameText) {
		lastNameField.sendKeys(lastNameText);
	}

	public void enterEmail(String emailText) {
		emailAddressField.sendKeys(emailText);
	}

	public void enterTelephoneNumebr(String telephone) {
		telephoneField.sendKeys(telephone);
	}

	public void enterPassword(String password) {
		passwordField.sendKeys(password);
	}

	public void enterConfirmPassword(String confirmPassword) {
		confirmPasswordField.sendKeys(confirmPassword);
	}

	public void checkboxPrivacyPolicy() {
		privacyPolicyCheckboxField.click();
	}

	public AccountSuccessPage  clickOnContinueButton() {
		continueButton.click();
		return new AccountSuccessPage(driver);
	}

	public void selectNewsLetter() {
		yesNewsLetter.click();
	}
	public String retrieveDuplicateEmailAddressWarning() {
		String duplicateEmailAddressWarningText =  duplicateEmailAddressWarning.getText();
		return duplicateEmailAddressWarningText;
		
	}
	public String retrievePrivacyPolicyWarning() {
		String privacyPolicyWarningText = privacyPolicyWarning.getText();
		return privacyPolicyWarningText;
	}
	
	public String retrieveFirstNameWarning() {
		String firstNameWarningText = firstNameWarning.getText();
		return firstNameWarningText;
	}
	public String retrieveLastNameWarning() {
		String lastNameWarningText = lastNameWarning.getText();
		return lastNameWarningText;
	}
	public String retrieveEmailWarning() {
		String emailWarningText = emailWarning.getText();
		return emailWarningText;
	}
	public String retrieveTelephoneWarning() {
		String telephoneWarningText = telephoneWarning.getText();
		return telephoneWarningText;
	}
	public String retrievePasswordWarning() {
		String passwordWarningText = passwordWarning.getText();
		return passwordWarningText;
	}
	public AccountSuccessPage registerWithMandatoryFields(String firstNameText, String lastNameText, String emailText, String telephone, String password, String confirmPassword)
	{
		firstNameField.sendKeys(firstNameText);
		lastNameField.sendKeys(lastNameText);
		emailAddressField.sendKeys(emailText);
		telephoneField.sendKeys(telephone);
		passwordField.sendKeys(password);
		confirmPasswordField.sendKeys(confirmPassword);
		privacyPolicyCheckboxField.click();
		continueButton.click();
		return new AccountSuccessPage(driver);
		
	}
	public AccountSuccessPage registerWithAllFields(String firstNameText, String lastNameText, String emailText, String telephone, String password, String confirmPassword) {
		firstNameField.sendKeys(firstNameText);
		lastNameField.sendKeys(lastNameText);
		emailAddressField.sendKeys(emailText);
		telephoneField.sendKeys(telephone);
		passwordField.sendKeys(password);
		confirmPasswordField.sendKeys(confirmPassword);
		yesNewsLetter.click();
		privacyPolicyCheckboxField.click();
		continueButton.click();
		return new AccountSuccessPage(driver);
		
		
	}
	
	
}