package SeleniumGrid;

import org.jspecify.annotations.Nullable;
import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Test;

import bsh.Capabilities;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

public class GoogleSearch {

	@Test
	public void googleHome() throws MalformedURLException, URISyntaxException {

		DesiredCapabilities caps = new DesiredCapabilities();
		caps.setBrowserName("chrome"); // this will sets, which browser wants to trigger.
		// another way of setting up
		// caps.setCapability(CapabilityType.BROWSER_NAME, "chrome");

		// caps.setPlatform(Platform.WIN11); // here we can set, which platform we want,
		// either windows or MAC
		// caps.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);// we can
		// handle advanced actions with help of capabilities.

		/**
		 * Since Java 20, URL is deprecated, so convert the URI to URL. Less then 20
		 * version, we can use URL.
		 */

		WebDriver driver = new RemoteWebDriver(new URI("https://192.168.1.139.4444").toURL(), caps);

		driver.get("https://www.google.com/");
		driver.findElement(By.name("q")).sendKeys("Deepak");

		System.out.println(driver.getTitle());
		driver.close();

	}

}
