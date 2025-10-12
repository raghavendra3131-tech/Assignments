package clas.remaining;

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

public class WebTableHandling {
	
	WebDriver driver;
	WebDriverWait wait;
	
	@BeforeMethod
	
	public void setup() {
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		
		
	}
	
	@Test
	
	public void findRows() {
		
		driver.get("https://cosmocode.io/automation-practice-webtable/");
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		
		WebElement table = wait.until(ExpectedConditions.elementToBeClickable(By.id("countries")));
		
		List <WebElement> rows = table.findElements(By.tagName("tr"));
		System.out.println("total no. of rows = " +rows.size());
	}
	
    @AfterMethod
    public void tearDown() {
        // Close the browser after each test
        driver.quit();
    }
}


