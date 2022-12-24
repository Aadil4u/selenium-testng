package com.swaglabs.testcases;

import java.util.Arrays;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.swaglabs.base.BaseClass;
import com.swaglabs.pageobjects.LoginPage;
import com.swaglabs.pageobjects.ProductsPage;
import com.swaglabs.pageobjects.SummaryPage;

public class SummaryPageTest extends BaseClass {
	private WebDriver driver;
	private LoginPage lp;
	private ProductsPage pp;
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
	
	@Test(groups = "Regression")
	public void VerifySummaryDetails() {
		lp = new LoginPage(driver);
		pp = lp.login(prop.getProperty("username"), prop.getProperty("password"));
		for (String product : productsList) {
			pp.addProductToCart(product);
		}
		SummaryPage sp = pp.clickOnShoppingCart();
		sp.verifyAllProducts(Arrays.asList(productsList));
		
	}
}
