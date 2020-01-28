package keyword.executionEngine;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import keyword.Base.Base;

public class KeywordExecution {

	public WebDriver driver;
	public Properties prop;
	public Workbook book;
	public Sheet sheet;
	public Base base;
	public WebElement element;

	public void start_Execution(String sheetName) {

		FileInputStream fis = null;

		try {
			fis = new FileInputStream(
					"C:\\Users\\DELL\\eclipse-workspace\\keyword\\src\\main\\java\\keyword\\scenarios\\fw_scenarios.xlsx");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			book = WorkbookFactory.create(fis);
		} catch (InvalidFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		sheet = book.getSheet(sheetName);

		int k = 0;

		for (int i = 0; i < sheet.getLastRowNum(); i++) {

			String locatorType = sheet.getRow(i + 1).getCell(k + 1).toString().trim();
			String locatorValue = sheet.getRow(i + 1).getCell(k + 2).toString().trim();
			String action = sheet.getRow(i + 1).getCell(k + 3).toString().trim();
			String value = sheet.getRow(i + 1).getCell(k + 4).toString().trim();

			System.out.println("locatortype   " + locatorType);
			System.out.println("locatorValue  " + locatorValue);
			System.out.println("action  " + action);
			System.out.println("value    " + value);

			switch (action) {
			case "open browser":
				base = new Base();
				base.init_properties();

				if (value.isEmpty() || value.equalsIgnoreCase("NA")) {
					driver = base.init_driver(prop.getProperty("browserName"));
				} else {
					driver = base.init_driver(value);
				}
				break;

			case "enter url":
				if (value.isEmpty() || value.equalsIgnoreCase("NA")) {
					driver.get(prop.getProperty("siteUrl"));
				} else {
					driver.get(value);
				}
				break;
			default:
				break;
			}

			switch (locatorType) {

			case "id":
				element = driver.findElement(By.id(locatorValue));

				if (action.equalsIgnoreCase("sendKeys")) {
					element.clear();
					element.sendKeys(value);
				} else if (action.equalsIgnoreCase("click")) {
					element.click();
				}
				// locatorType=null;
				break;

			case "xpath":
				element = driver.findElement(By.xpath(locatorValue));
				if (action.equalsIgnoreCase("sendKeys")) {
					element.clear();
					element.sendKeys(value);
				} else if (action.equalsIgnoreCase("click")) {
					element.click();
				}
				// locatorType=null;
				break;

			case "name":
				element = driver.findElement(By.name(locatorValue));
				if (action.equalsIgnoreCase("sendKeys")) {
					element.clear();
					element.sendKeys(value);
				} else if (action.equalsIgnoreCase("click")) {
					element.click();
				}
				// locatorType=null;
				break;

			default:
				break;
			}

		}

	}

}