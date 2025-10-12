package presentation;

import java.io.File;
import java.time.Duration;

// Import all required Selenium, TestNG, and Java classes
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class HardPopupExample2 {
    // Declare WebDriver and WebDriverWait at class level (so all tests can use them)
    WebDriver driver;
    WebDriverWait wait;

    // This method runs before each @Test
    @BeforeMethod
    public void setUp() {
        // Launch Chrome browser (Selenium 4.6+ auto-handles driver binaries)
        driver = new ChromeDriver();
        driver.manage().window().maximize(); // maximize browser window
        // Initialize explicit wait with 10 seconds timeout
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // 1️⃣ Example 1: Handle JavaScript Alert
    @Test
    public void testJavaScriptAlert() {
        driver.get("https://the-internet.herokuapp.com/javascript_alerts");

        // Click button to open JavaScript Alert
        driver.findElement(By.xpath("//button[text()='Click for JS Alert']")).click();

        // Wait until alert is present, then switch to it
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());

        // Verify alert text
        Assert.assertEquals(alert.getText(), "I am a JS Alert");

        // Accept the alert (click OK)
        alert.accept();

        // Verify the result message displayed on page
        String result = driver.findElement(By.id("result")).getText();
        Assert.assertTrue(result.contains("You successfully clicked an alert"));
    }

    // 2️⃣ Example 2: Handle HTML Modal (Bootstrap demo)
    @Test
    public void testHtmlModal() {
        driver.get("https://getbootstrap.com/docs/4.0/components/modal/");

        // Open modal (by clicking trigger button)
        driver.findElement(By.cssSelector("#exampleModalButton")).click();

        // Wait for modal to become visible
        WebElement modal = wait.until(
            ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".modal.show"))
        );

        // Assert modal has expected role attribute (dialog)
        Assert.assertEquals(modal.getAttribute("role"), "dialog");

        // Close modal using ESC key (keyboard action)
        new Actions(driver).sendKeys(Keys.ESCAPE).perform();

        // Wait until modal is invisible (closed)
        wait.until(ExpectedConditions.invisibilityOf(modal));
    }

    // 3️⃣ Example 3: Handle File Upload (workaround for OS popup)
    @Test
    public void testFileUpload() {
        driver.get("https://the-internet.herokuapp.com/upload");

        // Create a temporary file for upload demo
        File tempFile = new File(System.getProperty("java.io.tmpdir"), "demo.txt");
        try {
            if (!tempFile.exists()) {
                java.nio.file.Files.write(tempFile.toPath(), "Hello Selenium".getBytes());
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to create demo file", e);
        }

        // Use sendKeys() to directly provide file path to <input type="file">
        // This bypasses the OS-native file chooser dialog
        WebElement uploadInput = driver.findElement(By.id("file-upload"));
        uploadInput.sendKeys(tempFile.getAbsolutePath());

        // Click upload button
        driver.findElement(By.id("file-submit")).click();

        // Verify upload success message
        String result = driver.findElement(By.tagName("h3")).getText();
        Assert.assertEquals(result, "File Uploaded!");
    }

    // This method runs after each @Test
    @AfterMethod
    public void tearDown() {
        // Close browser if driver is not null
        if (driver != null) {
            driver.quit();
        }
    }
}
