package com.selenium.module2;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Assignment1 {
	
	
	WebDriver driver;
	WebDriverWait wait;
	
	@BeforeMethod
	
	public void setup() {
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		
	}
	
	@Test
	
	public void companyNames() {
		
		driver.get("https://demo.guru99.com/test/web-table-element.php#");
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		
		List <WebElement> firstRowColumns = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//table[@class='dataTable']//tbody//tr//td[1]")));
		
		final String BOLD = "\033[1m";
		final String RESET = "\033[0m";
		
		System.out.println(BOLD + "Columns of first row:" + RESET);
		for (WebElement col : firstRowColumns) {
		    System.out.println(col.getText());
		}
		
		
	}
	
	
	
	@AfterMethod
	
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
