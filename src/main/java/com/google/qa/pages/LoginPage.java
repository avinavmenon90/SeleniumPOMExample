package com.google.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.google.qa.base.TestBase;

public class LoginPage extends TestBase {

	//Page Factory - OR:
	@FindBy(name="username")
	@CacheLookup
	WebElement username;
	
	@FindBy(name="password")
	WebElement password;
	
	@FindBy(xpath="//input[@type='submit']")
	WebElement loginBtn;
	
	@FindBy(xpath="//img[contains(@class,'img')]")
	WebElement googleLogo;
	
	
	public LoginPage() {
		
		//Initialize page factory
		//this - points to current class (LoginPage) obj
		PageFactory.initElements(driver, this);
	}
	
	
	//Action methods
	public String validateLoginPageTitle() {
		return driver.getTitle();
	}
	
	public boolean validateGoogleLogo() {
		return googleLogo.isDisplayed();
	}
	
	public HomePage login(String un, String pwd) {
		username.sendKeys(un);
		username.sendKeys(pwd);
		loginBtn.click();
		
		//Return object of landing page
		return new HomePage();
	}
}




