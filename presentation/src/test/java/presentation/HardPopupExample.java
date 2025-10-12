package presentation;

import java.time.Duration;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class HardPopupExample {
    WebDriver driver;
    WebDriverWait wait;

    @BeforeMethod
    public void setUp() {
        // Open Chrome browser (Selenium 4.6+ auto-manages driver)
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Open the demo page with JS alerts
        driver.get("https://the-internet.herokuapp.com/javascript_alerts");
    }

    //  Test for JS Alert (OK only)
    @Test
    public void testJsAlert() {
        driver.findElement(By.xpath("//button[text()='Click for JS Alert']")).click();

        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        Assert.assertEquals(alert.getText(), "I am a JS Alert");

        alert.accept(); // click OK

        String result = driver.findElement(By.id("result")).getText();
        Assert.assertTrue(result.contains("You successfully clicked an alert"));
    }

    // 2 Test for JS Confirm (OK/Cancel)
    @Test
    public void testJsConfirm() {
        driver.findElement(By.xpath("//button[text()='Click for JS Confirm']")).click();

        Alert confirm = wait.until(ExpectedConditions.alertIsPresent());
        Assert.assertEquals(confirm.getText(), "I am a JS Confirm");

        confirm.dismiss(); // click Cancel

        String result = driver.findElement(By.id("result")).getText();
        Assert.assertTrue(result.contains("You clicked: Cancel"));
    }

    // 3 Test for JS Prompt (with input box)
    @Test
    public void testJsPrompt() {
        driver.findElement(By.xpath("//button[text()='Click for JS Prompt']")).click();

        Alert prompt = wait.until(ExpectedConditions.alertIsPresent());
        Assert.assertEquals(prompt.getText(), "I am a JS prompt");

        prompt.sendKeys("Hello Selenium"); // enter text
        prompt.accept(); // click OK

        String result = driver.findElement(By.id("result")).getText();
        Assert.assertTrue(result.contains("Hello Selenium"));
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit(); // close browser
        }
    }
}





