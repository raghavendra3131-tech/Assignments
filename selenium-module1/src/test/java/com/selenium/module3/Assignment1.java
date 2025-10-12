package com.selenium.module3;

import java.time.Duration;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class Assignment1 {

	
	WebDriver driver;
	WebDriverWait wait;
	
	@BeforeTest
	
	public void setup() {
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--incognito");
		options.addArguments("--disable-features=PasswordCheck,AutofillServerCommunication,AutofillCreditCard");
		driver = new ChromeDriver(options);
		wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // 
		
	

}
	
	 @DataProvider(name = "loginData")
	 
	    public Object[][] getLoginData() {
	        return new Object[][] {
	            { "deo", "deo" },
	            { "adn", "adin" },
	            {"user123", "pword123"},
	            {"testUser", "testPsword"},
	            {"superAdmin", "superPassword"},
	            {"wrerfe", "2232"},
	        };
	    }
	 
	    // Test annotation to perform the login functionality
	    @Test(dataProvider = "loginData")
	    
	    public void testLogin(String username, String password) {
	    	
	    	driver.get("https://demo.guru99.com/test/login.html");
	    	WebElement usernameTextbox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email")));
	    	WebElement passwordTextbox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("passwd")));
	    	WebElement loginButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#SubmitLogin")));
	    
	    	usernameTextbox.sendKeys(username);
	    	passwordTextbox.sendKeys(password);
	    	loginButton.click();
	    	
	    	// Wait briefly and try to close Chrome's native popup if it appears
	    	try {
	    	    WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(2));
	    	    WebElement okButton = shortWait.until(
	    	        ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(.,'OK')]"))
	    	    );
	    	    okButton.click();
	    	} catch (Exception e) {
	    	    // Popup did not appear; do nothing
	    	}
	    	
	    	String expectedUrl= "https://demo.guru99.com/test/success.html";
	    	Assert.assertEquals(driver.getCurrentUrl(), "https://demo.guru99.com/test/success.html" , "Login failed with username and passwors : " +username + " " +password );
	    }
	    
	    @AfterTest
	    
	    public void teardown() {
	    	if (driver != null) {
	    		driver.quit();
	    	}
	    }
}