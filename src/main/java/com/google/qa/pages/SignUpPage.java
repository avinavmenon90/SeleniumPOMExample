package com.google.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.google.qa.base.TestBase;

public class SignUpPage extends TestBase{

	
	//Page Factory - OR:
	@FindBy(name="username")
	WebElement username;
	
	@FindBy(name="password")
	WebElement password;
	
	@FindBy(xpath="//button[contains(text(),'sign up')]")
	WebElement signUpBtn;
	
}
