package dataDriven;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class dataProvider {
	
	// Example from letcodewithkoushik

	/**
	 * either we can specify data provider name. that name should be specified next to dataProvider. this will be useful when we have multiple data providers.
	 * Or else we can go with this approach, we simply specify the method name, where we receive the data @Test(dataProvider = "getData")
	 * 
	 */
	
	
	//@DataProvider
	
	@DataProvider(name = "login")
	public String[][] getData() throws IOException {
		
//	String[][] data = new String[2][2];
//		
//		//row1 [row][column]
//		data[0][0] = "Deepak";
//		data[0][1] = "Deepak";
//		
//		//row2
//		data[1][0] = "Deepak";
//		data[1][1] = "Deepak";
//		
//		//row3
//		
//		
//		
//		
//		return data;
		
		
		/**
		 * we can also sending this format too
		 */
		
		/*
		 * public String [][] getData(){
		 * 
		 * String[][] data ={{"deepak", "1234"}, {"karpagam", "5678"}, {"Dhakshith", "9101"}};
		 * return data;
		 * }
		 * 
		 * 
		 * 
		 */
		
		
		
		/**
		 * we call the method, since its static. So we call by using classname.methodname.
		 */
		
		 String[][] excelSheetData = DataDrivenIntegration.getExcelSheetData();
		 return excelSheetData;
		

	}
	
	
	@Test(dataProvider = "login")
	
	public void Login(String data[] ) {
		
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.get("https://www.saucedemo.com/v1/");
		driver.findElement(By.id("user-name")).sendKeys(data[0]);
		driver.findElement(By.id("password")).sendKeys(data[1]);
		driver.findElement(By.id("login-button")).click();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(driver.getTitle());
		driver.close();
		

	}

}
