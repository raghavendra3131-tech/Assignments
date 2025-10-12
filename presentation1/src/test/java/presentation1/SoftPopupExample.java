package presentation1;

// Import Selenium Alert class (for handling JavaScript alerts)
import org.openqa.selenium.Alert;
// Import Selenium By class (to locate elements)
import org.openqa.selenium.By;
// Import Selenium WebDriver interface
import org.openqa.selenium.WebDriver;
// Import ChromeDriver implementation of WebDriver
import org.openqa.selenium.chrome.ChromeDriver;
// Import support classes for explicit wait (WebDriverWait, ExpectedConditions)
import org.openqa.selenium.support.ui.*;
// Import TestNG Assert class (for validation)
import org.testng.Assert;
// Import TestNG annotations (@Test, @BeforeMethod, @AfterMethod)
import org.testng.annotations.*;

// Import Java Duration class (for timeouts)
import java.time.Duration;

// Define the public class (test class name must match file name)
public class SoftPopupExample {

    // Declare WebDriver reference (for browser actions)
    WebDriver driver;
    // Declare WebDriverWait reference (for explicit waits)
    WebDriverWait wait;

    // Runs before each test method
    @BeforeMethod
    public void setUp() {
        // Launch Chrome browser
        driver = new ChromeDriver();
        // Maximize browser window
        driver.manage().window().maximize();

        // Create explicit wait (timeout = 10 seconds)
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Open demo site with JavaScript alerts
        driver.get("https://the-internet.herokuapp.com/javascript_alerts");
    }

    
      //   1.  Test case for simple JS Alert (only OK button)
     
    @Test
    public void testJsAlert() {
        // Click button to trigger JS Alert
        driver.findElement(By.xpath("//button[text()='Click for JS Alert']")).click();

        // Wait until alert is present
        wait.until(ExpectedConditions.alertIsPresent());

        // Switch control to the alert
        Alert alert = driver.switchTo().alert();

        // Capture and print alert text
        String alertText = alert.getText();
        System.out.println("JS Alert text: " + alertText);

        // Validate alert text
        Assert.assertEquals(alertText, "I am a JS Alert");

        // Accept the alert (click OK)
        alert.accept();

        // Verify page result message
        String result = driver.findElement(By.id("result")).getText();
        Assert.assertEquals(result, "You successfully clicked an alert");
    }

    
     // 2.    Test case for JS Confirm (OK and Cancel options)
     
    @Test
    public void testJsConfirm() {
        // Click button to trigger Confirm alert
        driver.findElement(By.xpath("//button[text()='Click for JS Confirm']")).click();
        wait.until(ExpectedConditions.alertIsPresent());
        
        //we are creating an Alert object reference here
        //Selenium gives us an Alert object that represents the currently active popup
        Alert alert = driver.switchTo().alert();

        // Capture and verify popup text
        String alertText = alert.getText();
        System.out.println("JS Confirm text: " + alertText);
        Assert.assertEquals(alertText, "I am a JS Confirm");

        // Accept the popup (OK)
        alert.accept();

        // Verify result text for OK
        String result = driver.findElement(By.id("result")).getText();
        Assert.assertEquals(result, "You clicked: Ok");

        // Trigger Confirm popup again → this time Cancel
        driver.findElement(By.xpath("//button[text()='Click for JS Confirm']")).click();
        wait.until(ExpectedConditions.alertIsPresent());
        //Here we are reusing the same variable (alert) that was already declared earlier in line 84
        alert = driver.switchTo().alert();

        // Dismiss the popup (Cancel)
        alert.dismiss();

        // Verify result text for Cancel
        String resultCancel = driver.findElement(By.id("result")).getText();
        Assert.assertEquals(resultCancel, "You clicked: Cancel");
    }

    
     // 3.   Test case for JS Prompt (input + OK / Cancel)
     
    @Test
    public void testJsPrompt() {
        // Click button to trigger Prompt alert
        driver.findElement(By.xpath("//button[text()='Click for JS Prompt']")).click();
        wait.until(ExpectedConditions.alertIsPresent());
        Alert alert = driver.switchTo().alert();

        // Capture and verify popup text
        String alertText = alert.getText();
        System.out.println("JS Prompt text: " + alertText);
        Assert.assertEquals(alertText, "I am a JS prompt");

        // Enter text into prompt + Accept
        String inputText = "Selenium Test";
        alert.sendKeys(inputText);
        alert.accept();

        // Verify page result for entered text
        String result = driver.findElement(By.id("result")).getText();
        Assert.assertEquals(result, "You entered: " + inputText);

        // Trigger Prompt again → this time Cancel
        driver.findElement(By.xpath("//button[text()='Click for JS Prompt']")).click();
        wait.until(ExpectedConditions.alertIsPresent());
        alert = driver.switchTo().alert();

        // Cancel the prompt
        alert.dismiss();

        // Verify result message for Cancel
        String resultCancel = driver.findElement(By.id("result")).getText();
        Assert.assertEquals(resultCancel, "You entered: null");
    }

    // Runs after each test method
//    @AfterMethod
//    public void tearDown() {
//        // Close browser if driver is not null
//        if (driver != null) {
//            driver.quit();
//        }
//    }
}
