package com.selenium.module2;

import java.io.File;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.Duration;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Assignment3 {

	private WebDriver driver;
	private WebDriverWait wait;
	
	@BeforeMethod
	
	public void setUp() {
		
		
		driver = new ChromeDriver(); // Launch browser
		driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Explicit wait
	}
	
	 @Test
	 public void dragBoxAndTakeScreenshot() throws Exception {
		    driver.get("https://jqueryui.com/");
		    wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Draggable"))).click();
		    wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.className("demo-frame")));
		    WebElement dragBox =wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("draggable")));

		    JavascriptExecutor js = (JavascriptExecutor) driver;
		    js.executeScript("arguments[0].style.top='150px'; arguments[0].style.left='150px';", dragBox);
		    wait.until(ExpectedConditions.attributeContains(dragBox, "style", "150px"));

		    // Take the screenshot
		    File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

		    // Ensure the screenshots directory exists
		    File destFolder = new File(System.getProperty("user.dir") + "\\src\\test\\resources\\test-data\\screenshots");
		    if (!destFolder.exists()) {
		        destFolder.mkdirs();  // Create the directory if it doesn't exist
		    }

		    // Create a new file with the timestamp in the filename
		    String ts = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date(0));
		    File dest = new File(destFolder, "DraggedBox_" + ts + ".png");  // Ensure this is a valid file path

		    // Copy the screenshot to the destination file
		    FileUtils.copyFile(src, dest);

		    // Optionally, switch back to the default content after taking the screenshot
		    driver.switchTo().defaultContent();
		}
	    @AfterMethod
	    public void tearDown() {
	        // Close the browser after each test
	        driver.quit();
	    }
	}
