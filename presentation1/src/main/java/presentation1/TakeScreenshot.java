package presentation1;




	import java.io.File;
	import java.io.IOException;

	import org.openqa.selenium.OutputType;
	import org.openqa.selenium.TakesScreenshot;
	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.chrome.ChromeDriver;

	public class TakeScreenshot {

	    public static void main(String[] args) throws InterruptedException, IOException {
	        
	        // Launch Chrome browser
	        WebDriver driver = new ChromeDriver();
	        driver.manage().window().maximize();
	        
	        // Open the website
	        driver.get("https://demo.nopcommerce.com/");
	        Thread.sleep(3000);
	        
	        // Take Screenshot
	        TakesScreenshot ts = (TakesScreenshot) driver;
	        File sourceFile = ts.getScreenshotAs(OutputType.FILE);
	        
	        // Save screenshot to project folder
	        File targetFile = new File(System.getProperty("user.dir") + "\\screenshot\\fullpage.png");
	        
	        sourceFile.renameTo(targetFile);
	        
	        System.out.println("Screenshot captured at: " + targetFile.getAbsolutePath());
	        
	        driver.quit();
	    }
	

	}


