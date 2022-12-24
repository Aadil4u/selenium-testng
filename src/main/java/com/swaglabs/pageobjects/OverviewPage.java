package com.swaglabs.pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.swaglabs.actiondriver.Action;

public class OverviewPage {

	Action action = new Action();
	private WebDriver driver;

	@FindAll({ @FindBy(css = ".inventory_item_name") })
	List<WebElement> productsName;

	@FindAll({ @FindBy(css = ".inventory_item_price") })
	List<WebElement> productsPrice;

	@FindBy(css = ".summary_subtotal_label")
	WebElement subtotal;

	@FindBy(css = ".summary_tax_label")
	WebElement taxAmt;

	@FindBy(css = ".summary_total_label")
	WebElement totalPrice;

	@FindBy(id = "cancel")
	WebElement cancelBtn;

	@FindBy(id = "finish")
	WebElement finishBtn;

	public OverviewPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void verifyAllProducts(List<String> productsAddedToCart) {
		for (WebElement product : productsName) {
			String productText = product.getText();
			Assert.assertTrue(productsAddedToCart.contains(productText));
		}
	}

	public ProductsPage clickOnCancelBtn() {
		action.click(driver, cancelBtn);
		return new ProductsPage(driver);
	}

	public SuccessPage clickOnFinishBtn() {
		action.scrollByVisibilityOfElement(driver, finishBtn);
		action.click(driver, finishBtn);
		return new SuccessPage(driver);
	}

	public void verifyItemTotalBeforeTax(double expectedTotal) {
		String subTotalStr = subtotal.getText();
		String[] subTotalArr = subTotalStr.split(" ");
		double actualTotal = Double.parseDouble(subTotalArr[2].replace("$", ""));
		Assert.assertEquals(actualTotal, expectedTotal);
	}

	public double getTaxAmount() {
		String taxStr = taxAmt.getText();
		String[] taxAmtArr = taxStr.split(" ");
		double tax = Double.parseDouble(taxAmtArr[1].replace("$", ""));
		return tax;
	}

	public void verifyTotalAfterTax(double expectedTotal) {
		String totalPriceStr = totalPrice.getText();
		String[] totalPriceArr = totalPriceStr.split(" ");
		double actualTotal = Double.parseDouble(totalPriceArr[1].replace("$", ""));
		Assert.assertEquals(actualTotal, expectedTotal);
	}

}
