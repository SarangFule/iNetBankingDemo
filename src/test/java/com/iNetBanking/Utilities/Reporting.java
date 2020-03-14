package com.iNetBanking.Utilities;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class Reporting extends TestListenerAdapter
{
	public ExtentHtmlReporter htmlReporter;
	public ExtentReports extent;
	public ExtentTest logger;
	
	public void onStart(ITestContext testContext) 
	{
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		String repname = "TestReport_"+timeStamp+".html";
		
		htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir")+"/test-output/"+repname); //Specify Location
		htmlReporter.loadXMLConfig(System.getProperty("user.dir")+"/extent-config.xml");
		
		extent = new ExtentReports();
		
		extent.attachReporter(htmlReporter);
		extent.setSystemInfo("Host Name", "Local Host");
		extent.setSystemInfo("Environment", "PROD");
		extent.setSystemInfo("user", "sarang fule");
		
		htmlReporter.config().setDocumentTitle("iNet Banking Test Project"); //Project Title
		htmlReporter.config().setReportName("Functional test reoport"); // Test Reort Name
		htmlReporter.config().setTheme(Theme.DARK);
	}
		public void onTestSuccess(ITestResult tr) 
		{
		    logger = extent.createTest(tr.getName());
		    logger.log(Status.PASS, MarkupHelper.createLabel(tr.getName(), ExtentColor.GREEN));
		}

		public void onTestFailure(ITestResult tr) 
		{
			logger = extent.createTest(tr.getName());
		    logger.log(Status.FAIL, MarkupHelper.createLabel(tr.getName(), ExtentColor.RED));
		    
		    String screenshotpath = System.getProperty("user.dir") + "\\Screenshots\\" + tr.getName() + ".png";
		    
		    File f = new File (screenshotpath);
		    if (f.exists())
		    {
		    	try 
		    	{
		    		logger.fail("Screenshot is below :" + logger.addScreenCaptureFromPath(screenshotpath));
		    	}
		    	catch (Exception e)
		    	{
		    		e.printStackTrace();
		    	}
		    }
		    
		}

		public void onTestSkipped(ITestResult tr) 
		{
			logger = extent.createTest(tr.getName());
		    logger.log(Status.SKIP, MarkupHelper.createLabel(tr.getName(), ExtentColor.BLUE));
		}
		
		public void onFinish(ITestContext testContext) 
		{
			extent.flush();
		}
}
