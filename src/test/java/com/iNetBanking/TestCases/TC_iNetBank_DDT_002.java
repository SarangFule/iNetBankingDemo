package com.iNetBanking.TestCases;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.testng.annotations.*;
import com.iNetBanking.PageObjects.LoginPageObject;
import com.iNetBanking.Utilities.XLUtils;

public class TC_iNetBank_DDT_002 extends BaseClass_iNet
{
	@Test (dataProvider = "LoginData")
	public void LoginDDT(String user, String pwd)
	{
		LoginPageObject lp = new LoginPageObject(driver);
		
		lp.setUserID(user);
		logger.info("User ID entered");
		lp.setPassword(pwd);
		logger.info("Password entered");
		lp.clickLogin();
		logger.info("User logged in to app successfully");
		
		if (isAlertPresent() == true)
		{
			driver.switchTo().alert().accept();
			driver.switchTo().defaultContent();
		}
		else 
		{
			driver.findElement(By.xpath("/html/body/div[3]/div/ul/li[15]/a")).click();;
			driver.switchTo().alert().accept();
			driver.switchTo().defaultContent();
		}
	}
	
	public boolean isAlertPresent ()
	{
		try
		{
			driver.switchTo().alert();
			return true;
		}
		catch (NoAlertPresentException e)
		{
			return false;
		}
	}
	
	@DataProvider (name = "LoginData")
	String[][] getData () throws IOException
	{
		String path = System.getProperty("user.dir")+"/src/test/java/com/iNetBanking/TestData/LoginData.xlsx";
		
		int rownum = XLUtils.getRowCount(path, "Sheet1");
		int colnum = XLUtils.getCellCount(path, "Sheet1", 1);
		
		String logindata [][] = new String [rownum] [colnum];
		
		for (int i=1; i<=rownum; i++)
		{
			for (int j=0; j<colnum; j++)
			{
				logindata [i-1][j] = XLUtils.getCellData(path, "Sheet1", i, j);
			}
		}
		System.out.println(logindata);
		return logindata;
	}
}
