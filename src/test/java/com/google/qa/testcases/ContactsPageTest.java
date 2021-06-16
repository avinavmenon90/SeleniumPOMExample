package com.google.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.google.qa.base.TestBase;
import com.google.qa.pages.ContactsPage;
import com.google.qa.pages.DealsPage;
import com.google.qa.pages.HomePage;
import com.google.qa.pages.LoginPage;
import com.google.qa.util.TestUtil;

public class ContactsPageTest extends TestBase{

	LoginPage loginPage;
	HomePage homePage;
	DealsPage dealsPage;
	TestUtil testUtil;
	ContactsPage contactsPage;
	String sheetName="contacts";
	
	//constructor of this class
	public ContactsPageTest() {
		super(); //keyword used to create PARENT/SUPER class constructor (to initialize super class vars)
	}
	
	//@Before: before every test case
			@BeforeMethod
			public void setup() {
				initialization();
				loginPage = new LoginPage();
				
				//login (returns HomePage obj)
				homePage = loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
				
				//goto Contacts Page BEFORE performing the tests
				testUtil.switchToFrame(); //Contacts link is on a frame, so switch to this frame before clicking
				contactsPage = homePage.clickOnContactsLink();
				
			}
			
	
			@Test(priority = 1)
			public void verifyContactsPageLabel() {
				boolean contactsPageLabelVisible = contactsPage.verifyContactsLabel();
				Assert.assertTrue(contactsPageLabelVisible);
			}
			
			@Test(priority = 2)
			public void selectSingleContactTest() {
				contactsPage.selectContacts("name in table");
			}
			
			@Test(priority = 3)
			public void selectMultipleContactsTest() {
				contactsPage.selectContacts("first name in table");
				contactsPage.selectContacts("second name in table");
			}
			
//			@Test(priority = 4)
//			public void validateCreateNewContact() {
//				
//				//go to New Contact page
//				homePage.clickOnNewContactLink();
//				
//				contactsPage.createNewContact("Mr", "Tom", "Latham", "Tesla");		
//			}
			
			//specify method name under @DataProvider annotation
			@Test(priority = 4, dataProvider="getTestDataFromExcel")
			public void validateCreateNewContact(String title, String firstName, String lastName, String Company) {
				
				//go to New Contact page
				homePage.clickOnNewContactLink();
				
				//pass on value from excel
				contactsPage.createNewContact(title, firstName, lastName, Company);		
			}
			
			
			
			//@DataProvider - read data from extl source
			@DataProvider
			public Object getTestDataFromExcel() {
				
				Object data[][] = TestUtil.getTestData(sheetName);
				return data; //retrun a 2D array of data from Excel
			}
	//@After: after every test case
			@AfterMethod
			public void tearDown() {
				driver.quit();
			}
}
