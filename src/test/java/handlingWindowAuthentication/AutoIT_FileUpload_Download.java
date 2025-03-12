package handlingWindowAuthentication;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class AutoIT_FileUpload_Download {

	public static void main(String[] args) throws InterruptedException, IOException {
		/**
		 * we can upload the file from windows, by using selenium send keys. refer datadriven package. But that element should have filetype attribute.
		 * Example
		 * sendKeys("C:/Users/DeepakVaithylingam/Downloads/download.xlsx");
		 * we can also handle the desktop windows by using AutoIT
		 * 
		 * we have to download AutoIT. Go to google, search autoIT. 
		 * https://www.autoitscript.com/site/autoit/downloads/
		 * download the software from the first section. Install the software
		 * 
		 * Go to installed location. 
		 * programfile(*86)/autoit3/Scite/scite.exe => double click, it will open the editor.
		 * 
		 * come back to autoit3 folder-> open Au3info_*64 -> This will open one spicy tool. click on Finder Tool open.
		 * this will give one tool to scan the filename path. As like we do for TOSCA.
		 * one we place that tool, on the, we will get the required field option. 
		 * now go to opened editor. and type the code.
		 * 
		 * upload file from desktop
		 * -----------------------
		 * 
		 * ControlFocus accepts 3 arguments. first one is Title, we can get it from the Spicy tool. Second Text is optional. Third is combination of class+Instance 
		 * ControlFocus("Open", "" , "Edit1") -----> Step 1 is done{steps mentioned below}
		 * ControlSetText("Open", "" , "Edit1", "C:\Users\DeepakVaithylingam\Downloads\download.xlsx") --> Step 2 is done. we gave the path of the file
		 * 
		 * drag the finder tool and place it one open button, we will get the details
		 * ControlClick("Open", "" , "Button1") -->Step 3 is done. 
		 * 
		 * Save the file in somewhere. or within autoIt. 
		 * 
		 * After save-> we need to compile the file
		 * 
		 * Go to the file, where we saved. Right click -> Compile script (*86)-> this will generate the .txt file for us 
		 * We need to invoke the file, through selenium code.
		 * 
		 * 
		 * download file from web and save it in desktop
		 * ---------------------------------------------
		 * by default, if we download somthing, that will stored in downloads automatically. So we need to set up, to downloading file should store into our project directory.
		 * Thos we can do by using ChromeOptions.
		 * 
		 */


		/**
		 * Steps
		 * ------
		 * 
		 * 1) Shift the focus to the file upload windows
		 * 2) set the exact path of the file, include fileName.extensions
		 * 3) click on open
		 * 
		 */

		/**
		 * if we use the static path, then the script will fail for others, who execute this script on their systems.
		 * To make it dynamic. So we can use this happily
		 */

		String downloadPath = System.getProperty("user.dir"); // this will get the projectPath dynamically for all users.


		/**
		 * These lines of code, helps to store the downloaded file into projectlevel for all users
		 */

		HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
		chromePrefs.put("profile.default_content_settings.popups", 0);
		chromePrefs.put("download.default_directory", downloadPath);
		ChromeOptions options=new ChromeOptions();
		options.setExperimentalOption("prefs", chromePrefs);





		WebDriver driver = new ChromeDriver(options);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.manage().window().maximize();
		driver.get("https://smallpdf.com/pdf-converter");
		driver.findElement(By.cssSelector("span.sc-8s01yt-4.dNifye")).click();
		Thread.sleep(4000);

		//Actual code to invoke the saved file. 
		// give the path of the .txt saved file
		// this will select the file from the desktop window for upload
		// we can change this path too
		Runtime.getRuntime().exec("C:\\Users\\dvaithylinga\\document\\fileupload.txt");

		// downloading the file from web
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); 
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("button[class*='medium']")));
		driver.findElement(By.cssSelector("button[class*='medium']")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Download Now")));
		driver.findElement(By.linkText("Download Now")).click();
		Thread.sleep(5000);


		// To verify the downloaded file exit in machine. 
		File file = new File(downloadPath+"/converted.zip"); // we need to specify the file getting stored path, along with filename
		if(file.exists())

		{
			Assert.assertTrue(file.exists()); // this confirms the file present in the defined path
			if(file.delete()) // this will delete the file, once verified
			System.out.println("file deleted");
		}


























		/**
		 * Rahulshetty's Notes
		 */

		/*Automating window Controls with Selenium:



	    	· Handling Window Authentication Pop Up
	    	http://Username:Password@SiteURL

	    	· Driver.get();

	    	· Handling File Upload from Windows using AutoIT


	    	What is AutoIT
	    	Install AutoIT
	    	AutoIT Scripting
	    	Integrating AutoIT with Selenium



	    	//Shift focus to the file upload windows

	    	//set text/path into file name edit box

	    	//click open to upload file



	    	Au3info- record window component objects

	    	Build Script -scite.exe

	    	Save it- .au3 extenstion

	    	Convert file into .exe by compiling .au3 file

	    	Call .exe file with Runtime class in java into your selenium tests





	    	ControlFocus("Open","","Edit1")

	    	ControlSetText("Open","","Edit1","C:\Users\rahul\Documents\check\visit.pdf")

	    	ControlClick("Open","","Button1")

		 */


	}

}
