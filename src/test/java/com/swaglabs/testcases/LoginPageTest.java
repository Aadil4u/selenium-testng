package com.swaglabs.testcases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.swaglabs.base.BaseClass;
import com.swaglabs.dataprovider.DataProviders;
import com.swaglabs.pageobjects.LoginPage;
import com.swaglabs.pageobjects.ProductsPage;

public class LoginPageTest extends BaseClass {
	private WebDriver driver;
	private LoginPage lp;
	private ProductsPage pp;

	@Parameters("browser")
	@BeforeMethod(groups = {"Smoke", "Sanity", "Regression"})
	public void setup(String browser) {
		driver = launchApp(browser);
	}

	@AfterMethod(groups = {"Smoke", "Sanity", "Regression"})
	public void tearDown() {
		driver.quit();
	}

	@Test(dataProvider = "credentials", dataProviderClass = DataProviders.class, groups = {"Regression", "Smoke"})
	public void verifyLogin(String username, String password) {
		lp = new LoginPage(driver);
		pp = lp.login(username, password);
		String url = pp.getCurrUrl();
		String expectedUrl = "https://www.saucedemo.com/inventory.html";
		Assert.assertEquals(url, expectedUrl);
	}

}
