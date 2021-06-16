package com.google.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.google.qa.base.TestBase;

public class HomePage extends TestBase{
	
	//Page Factory - OR:
		@FindBy(xpath="//td[contains(text(),'User: Avinav)]")
		WebElement userNameLabel;
		
		@FindBy(xpath="//a[contains(text(),'contacts')]")
		WebElement contactsLink;
		
		@FindBy(xpath="//a[contains(text(),'New Contact')]")
		WebElement newContactLink;
		
		@FindBy(xpath="//a[contains(text(),'Deals')]")
		WebElement dealsLink;
		
		@FindBy(xpath="//a[contains(text(),'Tasks')]")
		WebElement tasksLink;
		
		//constructor of HomePage
		public void HomePage() {
			
			//Initialize page factory
			//this - points to current class (HomePage) obj
			PageFactory.initElements(driver, this);
		}
		
		//Actions
		public String verifyHomePageTitle() {
			return driver.getTitle();
		}
		
		public boolean verifyCorrectUserName() {
			return userNameLabel.isDisplayed();
		}
		
		public void clickOnNewContactLink() {
			
			//mouse-over Contacts link and click on New Contact
			Actions action = new Actions(driver);
			action.moveToElement(contactsLink).build().perform();
			
			//click on New Contact link after mouse-over
			newContactLink.click();
		}
		
		/*
		 * Page-Chaining model given below
		 */
		public ContactsPage clickOnContactsLink() {
			contactsLink.click();
			return new ContactsPage();
		}
		
		public DealsPage clickOnDealsLink() {
			dealsLink.click();
			return new DealsPage();
		}
		
		public TasksPage clickOnTaskLink() {
			tasksLink.click();
			return new TasksPage();
		}

}
