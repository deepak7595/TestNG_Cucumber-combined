package launchBrowserIncognitoMode;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class LaunchBrowserIncog {

	public static void main(String[] args) {
		
		// To launch browser in incognito mode, we have to set the arguments 
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--incognito");
		
		
		WebDriver driver = new ChromeDriver(options);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4));
		driver.manage().window().maximize();
		driver.get("https://www.google.com/");
		driver.findElement(By.name("q")).sendKeys("Hello");
		driver.findElement(By.name("q")).sendKeys(Keys.ENTER);
		driver.close();

	}

}
