package com.iNetBanking.TestCases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.iNetBanking.PageObjects.LoginPageObject;


public class TC_iNetBank_001 extends BaseClass_iNet
{
	@Test
	public void LoginTest() throws IOException
	{
		LoginPageObject loginobj = new LoginPageObject(driver);
		
		loginobj.setUserID(user);
		logger.info("User ID entered");
		loginobj.setPassword(pass);
		logger.info("Password entered");
		loginobj.clickLogin();
		logger.info("User logged in to app successfully");
		
		if (driver.getTitle().equals("Guru99 Bank Manager HomePage"))
		{
			Assert.assertTrue(true);
			logger.info("Login Test Passed");
		}
		else
		{
			captureScreenshot (driver, "LoginTest"); //Class Name
			Assert.assertTrue(false);
			logger.info("Login test Failed");
			
		}
		//Assert.assertEquals(driver.getTitle(), "Guru99 Bank Manager HomePage")
		
		
	}
}