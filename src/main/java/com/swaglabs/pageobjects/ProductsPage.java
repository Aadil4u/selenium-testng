package com.swaglabs.pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.swaglabs.actiondriver.Action;

public class ProductsPage {

	Action action = new Action();
	private WebDriver driver;

	@FindBy(css = ".title")
	WebElement productHeader;

	@FindBy(css = ".shopping_cart_link")
	WebElement cartBtn;

	@FindBy(id = "react-burger-menu-btn")
	WebElement SliderBtn;

	@FindAll({
			@FindBy(xpath = "//div[@class='inventory_item_name']/parent::a/parent::div//following-sibling::div //button") })
	List<WebElement> addToCartBtn;

	@FindAll({ @FindBy(css = ".inventory_item_name") })
	List<WebElement> productsName;

	@FindAll({ @FindBy(css = ".inventory_item_price") })
	List<WebElement> productsPrice;

	public ProductsPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public boolean verifyProductHeaderDisplayed() {
		return action.isDisplayed(driver, productHeader);
	}

	public ProductDetailPage clickOnProduct(String productName) {
		for (int i = 0; i < productsName.size(); i++) {
			String productText = productsName.get(i).getText();
			if (productText.equals(productName)) {
				action.scrollByVisibilityOfElement(driver, productsName.get(i));
				action.click(driver, productsName.get(i));
				break;
			}
		}
		return new ProductDetailPage(driver);
	}

	public void addProductToCart(String productName) {
		for (int i = 0; i < productsName.size(); i++) {
			String productText = productsName.get(i).getText();
			if (productText.equals(productName)) {
				action.scrollByVisibilityOfElement(driver, addToCartBtn.get(i));
				addToCartBtn.get(i).click();
				break;
			}
		}
	}

	public SummaryPage clickOnShoppingCart() {
		action.scrollByVisibilityOfElement(driver, cartBtn);
		action.click(driver, cartBtn);
		return new SummaryPage(driver);
	}

	public String getCurrUrl() {
		return driver.getCurrentUrl();
	}

	public double getProductPrice(String productName) {
		double rawPrice = 0;
		for (int i = 0; i < productsName.size(); i++) {
			String productText = productsName.get(i).getText();
			if (productText.equals(productName)) {
				rawPrice = Double.parseDouble(productsPrice.get(i).getText().replace("$", ""));
				break;
			}
		}
		return rawPrice;
	}

}
