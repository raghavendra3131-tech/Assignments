package com.selenium.module2;

import java.time.Duration;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Assignment2 {

	private WebDriver driver;
	private WebDriverWait wait;
	
	@BeforeMethod
	
	public void setUp() {
		
		
		driver = new ChromeDriver(); // Launch browser
		driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Explicit wait
        
}
	
	@Test
	
	public void openBrowser() {
		driver.get("https://demo.guru99.com/test/delete_customer.php");
		WebElement customerId =  wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("cusid")));
		
		customerId.sendKeys("81555");
		driver.findElement(By.name("submit")).click();
		
	    // First alert
	    Alert firstAlert = wait.until(ExpectedConditions.alertIsPresent());
	    String firstAlertText = firstAlert.getText();
	    System.out.println("First alert says: " + firstAlertText);
	    Assert.assertEquals(firstAlertText, "Do you really want to delete this Customer?"); 
	    firstAlert.accept();

	    // Second alert
	    Alert secondAlert=wait.until(ExpectedConditions.alertIsPresent());
	    String secondAlertText = secondAlert.getText();
	    System.out.println("Second alert says: " +secondAlertText);
	    Assert.assertEquals(secondAlertText, "Customer Successfully Delete!");
	    secondAlert.accept();
	    
	}
	}

