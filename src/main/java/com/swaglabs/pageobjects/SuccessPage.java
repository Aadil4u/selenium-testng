package com.swaglabs.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.swaglabs.actiondriver.Action;

public class SuccessPage {
	Action action = new Action();
	private WebDriver driver;

	@FindBy(css = ".complete-header")
	WebElement successHeader;

	@FindBy(id = "back-to-products")
	WebElement backToHomeBtn;

	public SuccessPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public ProductsPage clickOnBackToHomeBtn() {
		action.click(driver, backToHomeBtn);
		return new ProductsPage(driver);
	}

	public void verifySuccessMessage() {
		String msg = successHeader.getText();
		Assert.assertEquals(msg, "THANK YOU FOR YOUR ORDER");
	}

}
