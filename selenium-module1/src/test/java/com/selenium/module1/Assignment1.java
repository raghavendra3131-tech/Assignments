package com.selenium.module1;

import java.time.Duration;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Assignment1 {
	

	
	private WebDriver driver;
	
	private WebDriverWait wait;
	
	@BeforeMethod
	
	public void setUp() {
		// Hide Selenium logs
		 Logger.getLogger("org.openqa.selenium").setLevel(Level.SEVERE); 
		// Hide TestNG internal logs
		    Logger.getLogger("org.testng").setLevel(Level.SEVERE);
		driver = new ChromeDriver(); // Launch browser
		driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Explicit wait
        
	
	
	}
	
	@Test
	
	public void verifyPageTitle() {
		driver.get("https://demo.guru99.com/test/radio.html");
		
		String actualTitle = driver.getTitle();
		System.out.println("title" + "="  +actualTitle);
		Assert.assertEquals(actualTitle, "Radio Button & Check Box Demo","Actual and expected Tittle do not match");
		//Assert.assertEquals(actualTitle, "Radio Button & Check Box Demo");
	}
	@Test
	public void selectOptionsAndCheckboxes() {
		
		driver.get("https://demo.guru99.com/test/radio.html");
		
	WebElement option1 =	wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("vfb-7-1")));
	option1.click();
	WebElement checkbox2 =	wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("vfb-6-1")));
	checkbox2.click();
	WebElement checkbox3 =	wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("vfb-6-2")));
	checkbox3.click();
	}
	
	@Test 
	
	public void selectDropdown() {
		
		driver.get("https://demo.guru99.com/test/newtours/register.php");
		
	WebElement dropdown= wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//select[@name='country']")));
	
	Select select = new Select(dropdown);
	
	select.selectByVisibleText("KUWAIT");
		
		
	}
	
	
	@AfterMethod
	
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

}
