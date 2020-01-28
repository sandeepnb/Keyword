package keyword.Base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Base {
	
	
	public WebDriver driver;
	public Properties prop;
	
	public WebDriver init_driver(String browserName)
	{
		
		if(browserName.equalsIgnoreCase(prop.getProperty("browserName")))
		{
			System.setProperty("webdriver.chrome.driver","C:\\Users\\DELL\\Downloads\\chromedriver_win32\\chromedriver.exe");
			driver=new ChromeDriver();
			driver.manage().window().maximize();
			driver.get(prop.getProperty("siteUrl"));
			
		}
		
		
		return driver;
		
	}
	
	
	public Properties init_properties()
	{
		prop=new Properties();
		
		try {
			FileInputStream fis=new FileInputStream("C:\\Users\\DELL\\eclipse-workspace\\keyword\\src\\main\\java\\keyword\\config\\config.properties");
			prop.load(fis);
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		return prop;
	}
	
	
}
	
	

	
