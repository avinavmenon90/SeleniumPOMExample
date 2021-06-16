package com.google.qa.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.google.qa.base.TestBase;

/*
 * This class stores common variables and methods to be used in the project
 */

public class TestUtil extends TestBase {

		//public vars to be used in other classes
		public static long PAGE_LOAD_TIMEOUT = 20;
		public static long IMPLICIT_WAIT = 20;
		
		//Excel utility variables
		static Workbook book;
		static Sheet sheet;
		public static String TESTDATA_SHEET_PATH = "C:\\Users\\Avinav\\eclipse-workspace\\SelenimPOMExample\\src\\main\\java\\com\\google\\qa\\testdata\\SeleniumPOMTestData.xlsx";
		
		//common method to switch to a particular frame
		public void switchToFrame() {
			driver.switchTo().frame("mainpanel");
			
		}
		
		//Excel utility - Method to read Test Data from Excel file
		//returns a 2D obj for row-col combination
		public static Object[][] getTestData(String sheetName) {
			FileInputStream file = null;
			try {
				file = new FileInputStream(TESTDATA_SHEET_PATH);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			try {
				book = WorkbookFactory.create(file);
			} catch (InvalidFormatException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			sheet = book.getSheet(sheetName);
			Object[][] data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
			// System.out.println(sheet.getLastRowNum() + "--------" +
			// sheet.getRow(0).getLastCellNum());
			for (int i = 0; i < sheet.getLastRowNum(); i++) { //row loop
				for (int k = 0; k < sheet.getRow(0).getLastCellNum(); k++) { //col loop
					data[i][k] = sheet.getRow(i + 1).getCell(k).toString();
					// System.out.println(data[i][k]);
				}
			}
			return data;
		}

		//Method to take a screenshot (called from WebEventLisetener class whenever an Exception occurs)
		public static void takeScreenshotAtEndOfTest() throws IOException {
			File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			String currentDir = System.getProperty("user.dir");
			FileUtils.copyFile(scrFile, new File(currentDir + "/screenshots/" + System.currentTimeMillis() + ".png"));
		}
}
