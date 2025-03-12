package handlingWindowAuthentication;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class WindowBasedAuthentication {

	public static void main(String[] args) {
	/**
	 * visit the below page, to work on different selenium topics. and window based authenticator
	 * https://the-internet.herokuapp.com/
	 * 
	 * Window authentication cant be handled by using selenium. pop up which based on java or javascript, we can handle through alerts or javascript.
	 * 
	 * format: 
	 * http://Username:Password@SiteURL
	 * we have to pass our username and password along with url. so we can bypass the login
	 */
		
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.manage().window().maximize();
		/**
		 * This is how usually access the application. but to handle windows authentication, we have to use, the below technique
		 * driver.get("https://the-internet.herokuapp.com/");
		 */
		// we passing the username and password on url itself. 
		driver.get("http://admin:admin@the-internet.herokuapp.com/basic_auth"); // shouldn't provide another http before the url
		//driver.findElement(By.xpath("//a[text()='Basic Auth']")).click();
		
		
		//https://the-internet.herokuapp.com/basic_auth
		String text = driver.findElement(By.tagName("p")).getText();
		System.out.println(text);
		driver.close();
		
		

		
		
		
		
		
	}

}
