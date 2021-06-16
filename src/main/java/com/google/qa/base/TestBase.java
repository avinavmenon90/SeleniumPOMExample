package com.google.qa.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.google.qa.util.TestUtil;
import com.google.qa.util.WebEventListener;

/*
 * Youtube Link: Naveeen AutoomationLabs https://www.youtube.com/watch?v=ea0P7MBQmiM&list=PLFGoYjJG_fqo4oVsa6l_V-_7-tzBnlulT&index=32
 */


//this is the PARENT class of all other classes
public class TestBase {

	public static WebDriver driver; //can be used in child classes
	public static Properties prop; //can be used in child classes
	static EventFiringWebDriver e_driver;
	static WebEventListener eventListener;
	static Logger log = Logger.getLogger(TestBase.class); //generate own logging in console
	public TestBase() {
		try {
			prop = new Properties();
			FileInputStream ip = new FileInputStream("C:\\Users\\Avinav\\eclipse-workspace\\SelenimPOMExample\\src\\main\\java\\com\\google\\qa\\config\\config.properties");
			prop.load(ip);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void initialization() {
		
		//Read from config.properties
		String browserName = prop.getProperty("browser");
		
		if(browserName.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver","C:\\Users\\Avinav\\Downloads\\chromedriver.exe");
			driver = new ChromeDriver();
		}
		
		//create Obj of EventListenerHandler to register it with EventFiringWebDriver
		e_driver = new EventFiringWebDriver(driver);
		eventListener = new WebEventListener();
		e_driver.register(eventListener);
		driver = e_driver;
		
		//To generate logs
		log.info("Launching browser now..");
		
		//regular new browser actions
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(TestUtil.PAGE_LOAD_TIMEOUT,TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICIT_WAIT, TimeUnit.SECONDS);
		
		//get URL from config.properties
		//go to URL on browser window
		driver.get(prop.getProperty("url"));
		log.info("Going to specified URL");
		log.warn("Testing Warning Message");
		log.fatal("Testing FATAL Message");
		log.debug("Testing DEBUG Message");
	}
}
