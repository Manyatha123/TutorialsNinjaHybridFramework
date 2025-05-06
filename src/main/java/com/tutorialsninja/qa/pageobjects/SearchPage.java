package com.tutorialsninja.qa.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchPage {
	WebDriver driver;

	@FindBy(xpath = "//img[@title =\"HP LP3065\"]")
	private WebElement validHPProduct;

	@FindBy(xpath = "//input[@type=\"button\"]/following-sibling::p")
	private WebElement searchMessage;
	
	@FindBy(xpath = "//input[@type=\"button\"]/following-sibling::p")
	private WebElement noProductMessage;

	public SearchPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public boolean displayStatusOfHPValidProduct() {
		boolean displayStatus = validHPProduct.isDisplayed();
		return displayStatus;
	}
	
	public String retrieveNoProductMessageText() {
		String noProductMessageText = noProductMessage.getText();
		return noProductMessageText;
	}
}
