package com.swaglabs.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.swaglabs.actiondriver.Action;

public class LoginPage {

	Action action = new Action();
	private WebDriver driver;

	@FindBy(id = "user-name")
	WebElement username;

	@FindBy(id = "password")
	WebElement password;

	@FindBy(id = "login-button")
	WebElement loginBtn;

	@FindBy(css = ".login_logo")
	WebElement logo;

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public ProductsPage login(String uname, String passwd) {
		action.explicitWait(driver, loginBtn, 10);
		action.type(username, uname);
		action.type(password, passwd);
		action.click(driver, loginBtn);
		return new ProductsPage(driver);
	}

}
