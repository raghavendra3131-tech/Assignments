package presentation1;  // Declares the package where this class belongs

// Importing Selenium, WebDriver, and TestNG libraries
import org.openqa.selenium.By;                    // For locating elements
import org.openqa.selenium.Keys;                  // For keyboard key constants (CTRL, A, C, V, etc.)
import org.openqa.selenium.WebDriver;             // Main Selenium interface for browser automation
import org.openqa.selenium.WebElement;            // Represents elements on a web page
import org.openqa.selenium.firefox.FirefoxDriver; // To use Firefox browser
import org.openqa.selenium.interactions.Actions;  // For performing keyboard/mouse actions
import org.openqa.selenium.support.ui.ExpectedConditions; // For explicit waits
import org.openqa.selenium.support.ui.WebDriverWait;      // Explicit wait class
import org.testng.Assert;                         // For assertions in tests
import org.testng.annotations.*;                  // For TestNG annotations like @Test, @BeforeMethod, @AfterMethod

import java.time.Duration;  // To specify wait timeout duration

// Test class to demonstrate keyboard actions
public class KeyboardActions {

    WebDriver driver;   // WebDriver instance to control the browser
    Actions act;        // Actions class instance to handle keyboard events
    WebDriverWait wait; // Explicit wait object

    // Setup method that runs before each test case
    @BeforeMethod
    public void setUp() {
        driver = new FirefoxDriver();        // Launch Firefox browser
        driver.manage().window().maximize(); // Maximize browser window
        driver.get("https://text-compare.com/"); // Navigate to test website
        act = new Actions(driver);           // Create Actions object for keyboard actions
        wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Set explicit wait (10 seconds)
    }

    // Test method to demonstrate Copy-Paste using keyboard shortcuts
    @Test
    public void testKeyboardCopyPaste() {
        // Wait until the first textbox (inputText1) is visible
        WebElement textBox1 = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("inputText1"))
        );
        textBox1.sendKeys("Hi everyone"); // Type text into first textbox

        // Perform Ctrl + A to select all text in textbox1
        act.keyDown(Keys.CONTROL).sendKeys("A").keyUp(Keys.CONTROL).perform();

        // Perform Ctrl + C to copy selected text
        act.keyDown(Keys.CONTROL).sendKeys("C").keyUp(Keys.CONTROL).perform();

        // Wait until second textbox (inputText2) is clickable
        WebElement textBox2 = wait.until(
                ExpectedConditions.elementToBeClickable(By.id("inputText2"))
        );
        textBox2.click(); // Focus/click inside second textbox

        // Perform Ctrl + V to paste text into textbox2
        act.keyDown(Keys.CONTROL).sendKeys("V").keyUp(Keys.CONTROL).perform();

        // Wait until the second textbox contains the pasted text
        wait.until(ExpectedConditions.attributeToBe(textBox2, "value", "Hi everyone"));

        // Get the value from second textbox and verify
        String copiedText = textBox2.getAttribute("value");
        Assert.assertEquals(copiedText, "Hi everyone", "Text was not copied correctly!");
        // Assertion ensures that the copy-paste worked
    }

    // Cleanup method that runs after each test case
//    @AfterMethod
//    public void tearDown() {
//        if (driver != null) {   // If browser is open
//            driver.quit();      // Close all browser windows and end WebDriver session
//        }
//    }
}
