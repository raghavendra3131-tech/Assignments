package presentation1;



		import java.util.ResourceBundle.Control;



		import org.openqa.selenium.By;

		import org.openqa.selenium.Keys;

		import org.openqa.selenium.WebDriver;

		import org.openqa.selenium.WebElement;

		import org.openqa.selenium.firefox.FirefoxDriver;

		import org.openqa.selenium.interactions.Actions;



		public class KeyboardActions {



			public static void main(String[] args) throws InterruptedException  {

				

				WebDriver driver = new FirefoxDriver();

				driver.manage().window().maximize();

				driver.get("https://text-compare.com/");

				Thread.sleep(2000);

				

				Actions act = new Actions(driver);

				driver.findElement(By.xpath("//textarea[@id = 'inputText1']")).sendKeys("Hi everyone");

				Thread.sleep(1000);

				

				//pressing ctrl + a  to select all text in field 1

				

				act.keyDown(Keys.CONTROL).sendKeys("A").keyUp(Keys.CONTROL).build().perform();

				

				//ctrl + c to copy text from field 1

				

				//act.keyDown(Keys.CONTROL).sendKeys("c").keyUp(null, null)

				act.keyDown(Keys.CONTROL).sendKeys("C").keyUp(Keys.CONTROL).build().perform();

				

				// ctrl + v to  copy the text into textbox 2

				WebElement textBox2= driver.findElement(By.id("inputText2"));

				

			//WebElement textBox2 =	driver.findElement(By.xpath("//textarea[@id ='inputText2']"));

			

			textBox2.click();

			

			

			act.keyDown(Keys.CONTROL).sendKeys("V").keyUp(Keys.CONTROL).build().perform();

				



			}



		

	}


