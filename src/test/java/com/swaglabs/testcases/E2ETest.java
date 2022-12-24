package com.swaglabs.testcases;

import java.util.Arrays;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.swaglabs.base.BaseClass;
import com.swaglabs.pageobjects.InformationPage;
import com.swaglabs.pageobjects.LoginPage;
import com.swaglabs.pageobjects.OverviewPage;
import com.swaglabs.pageobjects.ProductsPage;
import com.swaglabs.pageobjects.SuccessPage;
import com.swaglabs.pageobjects.SummaryPage;
import com.swaglabs.utility.Log;

public class E2ETest extends BaseClass {
	private WebDriver driver;
	private LoginPage lp;
	private ProductsPage pp;
	private InformationPage ip;
	private OverviewPage op;
	private SummaryPage sp;
	private SuccessPage successPage;
	private String[] productsList = { "Sauce Labs Backpack", "Sauce Labs Onesie", "Sauce Labs Fleece Jacket",
			"Sauce Labs Bolt T-Shirt" };

	@Parameters("browser")
	@BeforeMethod(groups = {"Smoke", "Sanity", "Regression"})
	public void setup(String browser) {
		driver = launchApp(browser);
	}

	@AfterMethod(groups = {"Smoke", "Sanity", "Regression"})
	public void tearDown() {
		driver.quit();
	}

	@Test (groups = "Regression")
	public void verifyEntToEndFlow() throws InterruptedException {
		Log.startTestCase("verifyEntToEndFlow");
		lp = new LoginPage(driver);
		double expectedTotal = 0;
		pp = lp.login(prop.getProperty("username"), prop.getProperty("password"));
		boolean result = pp.verifyProductHeaderDisplayed();
		Assert.assertTrue(result);
		for (String product : productsList) {
			pp.addProductToCart(product);
			expectedTotal += pp.getProductPrice(product);
		}
		sp = pp.clickOnShoppingCart();
		sp.verifyAllProducts(Arrays.asList(productsList));
		ip = sp.clickOnCheckout();
		ip.fillInformation(prop.getProperty("firstName"), prop.getProperty("lastName"), "");
		ip.clickOnContinueBtn();
		ip.verifyErrorDisplayed();
		ip.fillInformation(prop.getProperty("firstName"), prop.getProperty("lastName"), prop.getProperty("postalCode"));
		op = ip.clickOnContinueBtn();
		op.verifyAllProducts(Arrays.asList(productsList));
		op.verifyItemTotalBeforeTax(expectedTotal);
		double taxAmt = op.getTaxAmount();
		double expectedTotalAfterTax = expectedTotal + taxAmt;
		op.verifyTotalAfterTax(expectedTotalAfterTax);
		successPage = op.clickOnFinishBtn();
		successPage.verifySuccessMessage();
		Log.endTestCase("verifyEntToEndFlow");
	}
}
