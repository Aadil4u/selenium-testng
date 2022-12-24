package com.swaglabs.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.swaglabs.actiondriver.Action;

public class ProductDetailPage {
	Action action = new Action();
	private WebDriver driver;

	@FindBy(css = ".inventory_details_name")
	WebElement productName;

	@FindBy(css = ".inventory_details_price")
	WebElement productPrice;

	@FindBy(css = ".btn_inventory")
	WebElement addToCartBtn;

	@FindBy(id = "back-to-products")
	WebElement backToProductsBtn;

	public ProductDetailPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void verifyPrice() {
		String[] priceArray = productPrice.getText().split("$");
		System.out.println(priceArray);
	}

	public void clickOnAddToCartBtn() {
		action.click(driver, addToCartBtn);
	}

	public ProductsPage clickOnBackToProductsBtn() {
		action.click(driver, backToProductsBtn);
		return new ProductsPage(driver);
	}

	public void verfiyProductDetails(String expectedProductName, Double expectedPrice) {
		String actualProductName = productName.getText();
		Assert.assertEquals(actualProductName, expectedProductName);
		Double actualPrice = Double.parseDouble(productPrice.getText().replace("$", ""));
		Assert.assertEquals(actualPrice, expectedPrice);
	}

}
