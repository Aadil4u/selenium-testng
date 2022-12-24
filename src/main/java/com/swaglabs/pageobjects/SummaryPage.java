package com.swaglabs.pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.swaglabs.actiondriver.Action;

public class SummaryPage {

	Action action = new Action();
	private WebDriver driver;

	@FindAll({ @FindBy(css = ".inventory_item_name") })
	List<WebElement> productsName;

	@FindAll({ @FindBy(xpath = "//button[text()='Remove']") })
	List<WebElement> removeCartBtn;

	@FindBy(css = ".title")
	WebElement cartHeader;

	@FindBy(id = "continue-shopping")
	WebElement continueShoppingBtn;

	@FindBy(id = "checkout")
	WebElement checkoutBtn;

	public SummaryPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void removeProduct(String productName) {
		for (int i = 0; i < productName.length(); i++) {
			String productText = productsName.get(i).getText();
			if (productText.equals(productName)) {
				removeCartBtn.get(i).click();
			}
		}
	}

	public void verifyAllProducts(List<String> productsAddedToCart) {
		for (WebElement product : productsName) {
			String productText = product.getText();
			Assert.assertTrue(productsAddedToCart.contains(productText));
		}
	}

	public InformationPage clickOnCheckout() {
		action.scrollByVisibilityOfElement(driver, checkoutBtn);
		action.click(driver, checkoutBtn);
		return new InformationPage(driver);
	}

	public ProductsPage clickOnContinueShoppingBtn() {
		action.click(driver, continueShoppingBtn);
		return new ProductsPage(driver);
	}
}
