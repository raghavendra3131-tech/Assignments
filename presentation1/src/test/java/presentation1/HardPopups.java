package presentation1;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;

public class HardPopups {
    WebDriver driver;   // WebDriver instance to control the browser

    @BeforeMethod
    public void setUp() {
        // Launch Chrome browser
        driver = new ChromeDriver();

        // Maximize browser window
        driver.manage().window().maximize();
    }

    @Test
    public void testFileUpload() throws Exception {
        // Open the target web page
        driver.get("https://www.ilovepdf.com/pdf_to_word");

        // Click the "Select PDF file" button
        //  This opens the OS-level file chooser popup (a Hard Popup)
        driver.findElement(By.id("pickfiles")).click();

        // Full file path that we want to upload
        // Note: In Java, backslashes must be escaped (\\ instead of \)
        String filePath = "C:\\Users\\Last Safezone\\Videos\\Hardpopup files\\sample.pdf";

        // Copy the file path to the system clipboard
        // (this is like pressing CTRL+C on that file path manually)
        StringSelection selection = new StringSelection(filePath);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(selection, null);

        // Create a Robot instance to handle keyboard simulation
        Robot robot = new Robot();

        // Small delay to ensure the popup is in focus
        robot.delay(1000);

        // ---- PASTE the file path into the popup ----
        // Press and hold CTRL
        robot.keyPress(KeyEvent.VK_CONTROL);
        // Press V (while CTRL is held down)
        robot.keyPress(KeyEvent.VK_V);
        // Release V
        robot.keyRelease(KeyEvent.VK_V);
        // Release CTRL
        robot.keyRelease(KeyEvent.VK_CONTROL);

        // ---- Press ENTER to confirm file selection ----
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
    }

//////    @AfterMethod
//////    public void tearDown() {
//////        // Close the browser after test execution
//////        if (driver != null) {
//////            driver.quit();
////        }
//    }
}
