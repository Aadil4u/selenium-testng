package com.swaglabs.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.swaglabs.actiondriver.Action;

public class InformationPage {

	private Action action = new Action();
	private WebDriver driver;

	@FindBy(id = "first-name")
	WebElement firstName;

	@FindBy(id = "last-name")
	WebElement lastName;

	@FindBy(id = "postal-code")
	WebElement postalCode;

	@FindBy(id = "cancel")
	WebElement cancelBtn;

	@FindBy(id = "continue")
	WebElement continueBtn;

	@FindBy(css = "h3[data-test='error']")
	WebElement requiredError;

	public InformationPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void fillInformation(String fName, String lName, String pCode) {
		action.type(firstName, fName);
		action.type(lastName, lName);
		action.type(postalCode, pCode);
	}

	public SummaryPage clickOnCancelBtn() {
		action.click(driver, cancelBtn);
		return new SummaryPage(driver);
	}

	public OverviewPage clickOnContinueBtn() {
		action.click(driver, continueBtn);
		return new OverviewPage(driver);
	}

	public void verifyErrorDisplayed() {
		Assert.assertTrue(requiredError.isDisplayed());
	}

}
