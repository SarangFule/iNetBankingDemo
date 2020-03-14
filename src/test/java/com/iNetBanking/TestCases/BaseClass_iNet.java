package com.iNetBanking.TestCases;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.*;

import com.iNetBanking.Utilities.ReadConfig;

public class BaseClass_iNet 
{
	ReadConfig readConfig = new ReadConfig();
	public String baseurl = readConfig.getBaseUrl();
	public String user = readConfig.getUserName();
	public String pass = readConfig.getPassword();
	
	
	/* BEFORE ADDING READ CONFIG FILE */
	
	// public String baseurl = http://demo.guru99.com/V4/
	// public String username = mngr246332
	// public String password = nYjAjuv
	// public String chromepath = ./Drivers\\chromedriver.exe
	// public String firefoxpath = ./Drivers\\geckodriver32bit.exe
	// public String iepath = ./Drivers\\IEDriverServer.exe
	
	protected WebDriver driver;
	public static Logger logger;
	
	@Parameters("browser")
	@BeforeClass
	public void setup(String br)
	{
		logger = Logger.getLogger("iNetBanking");
		PropertyConfigurator.configure("log4j.properties");
		
		if(br.equalsIgnoreCase("chrome"))
		{
			System.setProperty("webdriver.chrome.driver", readConfig.getChromePath());
			driver = new ChromeDriver(); 
			logger.info("url is opened in Chrome browser.");
		}
		else if (br.equalsIgnoreCase("firefox"))
		{
			System.setProperty("webdriver.gecko.driver", readConfig.getFirefoxPath());
			driver = new FirefoxDriver();
			logger.info("url is opened in Firefox browser.");
		}
		else if (br.equalsIgnoreCase("ie"))
		{
			System.setProperty("webdriver.ie.driver", readConfig.getIEPath());
			driver = new InternetExplorerDriver(); 
			logger.info("url is opened in Internet Explorer browser.");
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.get(baseurl);
	}
	
	@AfterClass
	public void tearDown ()
	{
		driver.quit();
	}
	
	public void captureScreenshot (WebDriver driver, String tname) throws IOException
	{
		TakesScreenshot ts = (TakesScreenshot) driver;
		File src = ts.getScreenshotAs(OutputType.FILE);
		File dest = new File (System.getProperty("user.dir") + "\\Screenshots\\" + tname + ".png");
		FileUtils.copyFile (src, dest);
		System.out.println("Screenshot Captured");
	}
		
}

