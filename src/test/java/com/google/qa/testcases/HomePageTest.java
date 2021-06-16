package com.google.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.google.qa.base.TestBase;
import com.google.qa.pages.ContactsPage;
import com.google.qa.pages.DealsPage;
import com.google.qa.pages.HomePage;
import com.google.qa.pages.LoginPage;
import com.google.qa.util.TestUtil;

public class HomePageTest extends TestBase{

	LoginPage loginPage;
	HomePage homePage;
	DealsPage dealsPage;
	TestUtil testUtil;
	ContactsPage contactsPage;
	
		//constructor of this class
		public HomePageTest() {
			super(); //keyword used to create PARENT/SUPER class constructor (to initialize super class vars)
		}
		
		//@Before: before every test case
		@BeforeMethod
		public void setup() {
			initialization();
			loginPage = new LoginPage();
			
			//login (returns HomePage obj)
			homePage = loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
		}
		
		@Test(priority = 1)
		public void HomePageTitleTest() {
			String homePageTitle = homePage.verifyHomePageTitle();
			Assert.assertEquals(homePageTitle, "Home Page","Home Page title test");
		}
		
		@Test(priority = 2)
		public void UserNameTest() {
			
			
			//call TestUtil method to switch to the frame
			testUtil.switchToFrame();
					
			boolean userNamePresent= homePage.verifyCorrectUserName();
			Assert.assertTrue(userNamePresent, "Username is not present on the page");
		}
		
		@Test(priority = 3)
		public void goToDealsLink() {
			
			dealsPage = homePage.clickOnDealsLink();
			
		}
		
		@Test(priority = 4)
		public void goToContactsLink() {
			
			contactsPage = homePage.clickOnContactsLink();
			
		}
		
		
		//@After: after every test case
		@AfterMethod
		public void tearDown() {
			driver.quit();
		}
		
	
}
