package com.iNetBanking.PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPageObject 
{
	@FindBy(name = "uid") 		@CacheLookup	private WebElement userID;
	
	@FindBy(name = "password")	@CacheLookup	private WebElement password;
	
	@FindBy(name = "btnLogin")	@CacheLookup	private WebElement loginbtn;
	
	@FindBy(name = "btnReset")	@CacheLookup	private WebElement resetbtn;
	
	
	public WebDriver driver;
	
	public LoginPageObject (WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void setUserID (String userID)
	{
		this.userID.sendKeys(userID);
	}
	public void setPassword (String password)
	{
		this.password.sendKeys(password);
	}
	public void clickLogin ()
	{
		loginbtn.click();
	}
	public void clickReset ()
	{
		resetbtn.click();
	}
}
