package com.google.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.google.qa.base.TestBase;

public class ContactsPage extends TestBase {

	//Page Factory - OR:
			@FindBy(xpath="//td[contains(text(),'Contacts']")
			WebElement contactsLabel;		
			
			@FindBy(xpath="//a[contains(text(),'Deals')]")
			WebElement dealsLink;
			
			@FindBy(xpath="//a[contains(text(),'Tasks')]")
			WebElement tasksLink;
			
			@FindBy(name="title']")
			WebElement newContactTitle;
			
			@FindBy(id="first_name']")
			WebElement newContactFirstName;
			
			@FindBy(id="surname")
			WebElement newContactLastName;
			
			@FindBy(name="client_lookup")
			WebElement newContactCompany;
			
			@FindBy(xpath="//input[@type='submit' and @value='Save']")
			WebElement newContactSaveBtn;
			
			
			
	//constructor of ContactsPage
			public void ContactsPage() {
				
				//Initialize page factory
				//this - points to current class (HomePage) obj
				PageFactory.initElements(driver, this);
			}
			
	//Actions:
			public boolean verifyContactsLabel() {
				return contactsLabel.isDisplayed();
			}
			
			public void selectContacts(String name) {
				
				//pass name to be searched in the table
				driver.findElement(By.xpath("\"//a[contains(text(),'"+name+"')]/parent::td//preceding-sibling::td//input[@name='contact-id']")).click();
								
			}
			
			public void createNewContact(String title, String fname, String lname, String company) {
				
				//dropdown selection
				Select select = new Select(newContactTitle);
				select.selectByVisibleText(title);
				
				//fill in New Contact form
				newContactFirstName.sendKeys(fname);
				newContactLastName.sendKeys(lname);
				newContactCompany.sendKeys(company);
				
				//hit Save button
				newContactSaveBtn.click();
			}

				
	}
			
