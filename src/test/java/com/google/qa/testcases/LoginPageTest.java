package com.google.qa.testcases;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.google.qa.base.TestBase;
import com.google.qa.pages.HomePage;
import com.google.qa.pages.LoginPage;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class LoginPageTest extends TestBase{

	//initialize obj of LoginPage class
	LoginPage loginPage;
	HomePage homePage;
	Logger log;
	
	//Extent report variables
	public ExtentReports extent;
	public ExtentTest extentTest;
	
	//constructor of this class
	public LoginPageTest() {
		super(); //keyword used to create PARENT/SUPER class constructor (to initialize super class vars)
	}
	
	@BeforeTest
	public void setExtent() {
		
		//'true' - replace any previous Extent report with this one
		extent = new ExtentReports(System.getProperty("user.dir")+"/test-output//ExtentReport.html",true);
		extent.addSystemInfo("Host Name", "Avinav-Win10");
		extent.addSystemInfo("User Name", "Avinav");
		extent.addSystemInfo("Environment", "QA");
	}
	
	@AfterTest
	public void endReport() {
		
		//close connection with extent report
		extent.flush();
		extent.close();
	}
	
	//method is STATIC so that it can be called using the classname
	public static String getScreenShot(WebDriver driver, String screenshotName) throws IOException {
		
		String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		
	//Screenshot
		 File src = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		 //copy the screenshot src object to desired location using copyFile method
		 String destination = System.getProperty("user.dir")+"/FailedTestsScreenshots"+ screenshotName + dateName+ ".png";
		 File finalDestination = new File(destination);
		 FileUtils.copyFile(src, finalDestination);
		 
		 return destination;
		 
		
	}
	
	@BeforeMethod
	public void setup() {
		
		initialization(); //calling parent class method
		loginPage = new LoginPage();
	}
	
	@Test(priority = 1)
	public void loginPageTitleTest() {
		
		log.info("*****************Starting Test Case****************************");
		log.info("*****************loginPageTitleTest****************************");
		
	//initialize Extent variable	
		extentTest = extent.startTest("loginPageTitleTest");
				
		String loginPageTitle = loginPage.validateLoginPageTitle();
		Assert.assertEquals(loginPageTitle, "Google"); //validate title of page
	}
	
	@Test(priority = 2)
	public void googleLogoImageTest() {
		
		log.info("*****************Starting Test Case****************************");
		log.info("*****************googleLogoImageTest****************************");
		
		boolean googleLogoPresent = loginPage.validateGoogleLogo();
		Assert.assertTrue(googleLogoPresent); //validate google logo
	}
	
	@Test(priority = 3)
	public void loginTest() {
		
		
		log.info("*****************Starting Test Case****************************");
		log.info("*****************loginTest****************************");
		
		//pass username & pwd from config.properties
		homePage = loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
	
	public void tearDown(ITestResult result) throws IOException {
		
		if((result).getStatus()==ITestResult.FAILURE){
			
			//add name in Extent report
			extentTest.log(LogStatus.FAIL, "Test Case that FAILED is: "+ result.getName());
			
			//add error/exception in Extent report
			extentTest.log(LogStatus.FAIL, "Exception: "+ result.getThrowable());
			
			String screenshotPath = LoginPageTest.getScreenShot(driver, result.getName());
			
			//add Screenshot in Extent report
			extentTest.log(LogStatus.FAIL, extentTest.addScreenCapture(screenshotPath));
		}
		else if ((result).getStatus()==ITestResult.SKIP) {
			
			extentTest.log(LogStatus.SKIP, "Test Case that SKIPPED is: "+ result.getName());
			
		}
		else if ((result).getStatus()==ITestResult.SUCCESS) {
			
			extentTest.log(LogStatus.PASS, "Test Case that PASSED is: "+ result.getName());
			
		}
		
		//ending test and prepare HTML report
		extent.endTest(extentTest);
		
		driver.quit();
		log.info("*****************Browser Closed****************************");
		
	}
}
