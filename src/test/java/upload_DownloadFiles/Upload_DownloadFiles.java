package upload_DownloadFiles;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Upload_DownloadFiles {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		/**
		 * Website for this demo https://rahulshettyacademy.com/upload-download-test/
		 */

		String fruitName = "Apple";
		String setValue = "500";
		
		WebDriver driver = new ChromeDriver();
		driver.get("https://rahulshettyacademy.com/upload-download-test/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

		/**
		 * Find the apple from the table, and get the price of the Apple
		 */
		
		String beforeApplePrice = null;
		List<WebElement> BeforeTableValues = driver
				.findElements(By.xpath("//div[@role='table']//div[@role='rowgroup'][2]//div[@data-column-id=2]"));

		for (int i = 0; i < BeforeTableValues.size(); i++) {
			String table = BeforeTableValues.get(i).getText();
			if (table.equalsIgnoreCase(fruitName)) {
				beforeApplePrice = driver.findElement(By.xpath("//div[@id='row-" + i + "']//div[@data-column-id=4]"))
						.getText();
				System.out.println("Before Change the price of the "+fruitName +" is " + beforeApplePrice);

			}

		}

		/**
		 * To Handle the above scenario, by using Xpath Axes in single line. Example Below
		 */

		// we can define the Productname. Example
		/*
		
		String productPrice = driver
				.findElement(
						By.xpath("//div[text()='" + fruitName + "']/parent::div/parent::div/div[@data-column-id=4]"))
				.getText();
		System.out.println("Before Change the price of the " + fruitName + " is " + productPrice);
*/

		/**
		 * download the excel
		 */

		driver.findElement(By.id("downloadButton")).click();





		/**
		 * edit the excel
		 */
		String filePath = "C:/Users/DeepakVaithylingam/Downloads/download.xlsx";
		FileInputStream file = new FileInputStream(filePath);
		
		
		XSSFWorkbook workbook = new XSSFWorkbook(file);
		XSSFSheet sheet = workbook.getSheetAt(0);
		
		int lastRowNum = sheet.getLastRowNum();
		System.out.println("Last row exclusion of header " + lastRowNum);
		
		int physicalNumberOfRows = sheet.getPhysicalNumberOfRows();
		System.out.println("Last row including of header " + physicalNumberOfRows);
		short lastCellNum = sheet.getRow(0).getLastCellNum();
		System.out.println("Last cell number " + lastCellNum);
		
		
		DataFormatter format = new DataFormatter();
		// Iterate through rows (skip header row, so start from 1)
		for (int i = 1; i <=lastRowNum; i++) {
			XSSFRow row = sheet.getRow(i);
		if (row != null) {
			 // Read fruit name from column 2 (index 1)
			XSSFCell fruitCell = row.getCell(1);
			String fruit = format.formatCellValue(fruitCell);
			 // Check if the fruit is "Apple"
			if (fruit.equalsIgnoreCase("Apple")) {
				
				XSSFCell priceCell = row.getCell(3);
				// To set the value for cell
				priceCell.setCellValue(setValue);
				break;
				/**
				 * To write anything on excel, we have another class
				 */				
				//To get value from cell
				//String price = format.formatCellValue(priceCell);
				//System.out.println("Price of the "+ fruitName +" " + price);
				//break; // Exit loop once Apple is found
			}
			
		}
			
		}
		/**
		 * Write and closing the workbook is mandatory, when update the excel.
		 */
		FileOutputStream fileOut = new FileOutputStream(filePath);
		workbook.write(fileOut);
		fileOut.close();
		
		
		workbook.close();
		file.close();
		
		
		


		/**
		 * upload the excel
		 */
		// Selenium will not handle windows pop up, as like Tosca. To Achieve this,
		// check the element which is having tag called Type. if its there, then we can
		// handle that by using sendKeys
		WebElement upload = driver.findElement(By.xpath("//input[@type='file']"));
		upload.sendKeys("C:/Users/DeepakVaithylingam/Downloads/download.xlsx"); // Provide the path, where the file is
		// present in our local machine


		/**
		 * wait for the success message to show up and disappear
		 */

		// WebElement popUp = driver.findElement(By.className("Toastify__toast-body"));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		String text = wait
				.until(ExpectedConditions.visibilityOf(driver.findElement(By.className("Toastify__toast-body"))))
				.getText();
		Assert.assertEquals("Updated Excel Data Successfully.", text);
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.className("Toastify__toast-body"))));
		/**
		 * or we can go with visibilityofElementLocated
		 */





		/**
		 * verify upload excel data showing in web Table
		 */

		
		String afterApplePrice = null;
		List<WebElement> AfterTableValues = driver
				.findElements(By.xpath("//div[@role='table']//div[@role='rowgroup'][2]//div[@data-column-id=2]"));

		for (int i = 0; i < AfterTableValues.size(); i++) {
			String table = AfterTableValues.get(i).getText();
			if (table.equalsIgnoreCase("Apple")) {
				afterApplePrice = driver.findElement(By.xpath("//div[@id='row-" + i + "']//div[@data-column-id=4]"))
						.getText();
				System.out.println("Before Change the price of the Apple is " + afterApplePrice);

			}

		}
		
		
		/**
		 * Compare the Before and After Values
		 */
		Assert.assertNotEquals(afterApplePrice, beforeApplePrice);
		System.out.println("Both the prices are different");
		
		
		
		/**
		 * close browser
		 */

		driver.close();

	}

}
