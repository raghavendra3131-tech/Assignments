package presentation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;



public class CombinedPopupsAndKeyboard {
	
	Web

	@Test
	public void toastDemo_bootstrap() {
	    driver.get("https://getbootstrap.com/docs/5.3/components/toasts/");

	    // Click the button to trigger a toast (first demo on the page)
	    WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(
	        By.xpath("//button[contains(text(),'Show live toast')]")));
	    btn.click();

	    // Locate the toast (first toast example)
	    By toastLocator = By.cssSelector(".toast.show");
	    WebElement toast = wait.until(ExpectedConditions.visibilityOfElementLocated(toastLocator));

	    // Verify toast contains expected text
	    Assert.assertTrue(toast.getText().contains("Hello, world!"), "Toast message text not found!");

	    // Wait for toast to disappear
	    wait.until(ExpectedConditions.invisibilityOfElementLocated(toastLocator));
	}


}
