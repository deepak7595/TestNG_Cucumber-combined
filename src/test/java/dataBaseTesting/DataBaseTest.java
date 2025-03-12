package dataBaseTesting;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.sql.Connection;

public class DataBaseTest {
	
	
	
	/**
	 * To Pratice SQL Queries in online, withbase database. Go and visit the below url
	 * 
	 * https://www.w3schools.com/sql/trysql.asp?filename=trysql_select_like_not
	 * 
	 */
	

	public static void main(String[] args) throws SQLException {
		// download SQL Jar from google, and add those in Project build path. This example is for MYSQL server. So downloaded MYSQL connector for java jar


		/**
		 * To Make Connection, need to specify these details properly
		 */
		String Connectionurl = "jdbc:mysql://localhost:3306/";
		String dbname = "Sample";
		String username = "Kanini";
		String password = "K@nini";
		

		Connection conn =DriverManager.getConnection(Connectionurl, username, password);  // we need to provide the username and password to access the server

		//Path
		Statement statement = conn.createStatement();

		//Query
		ResultSet result = statement.executeQuery("Select * from dashboard where Scenario ='zeropayment' ");

		//Mandatory
	while(result.next()) {;
		
		//from the Query, we fetch the values from the below columns. username and password are the columns 
		System.out.println(result.getString("username"));	
		System.out.println(result.getString("password"));	
	}
	
	
	// Using the values in Automation Testcase
	
	WebDriver driver = new ChromeDriver();
	driver.get("https://google.com");
	//Passing the values into Teststeps
	driver.findElement(By.id("user")).sendKeys(result.getString("username"));
	driver.findElement(By.id("pass")).sendKeys(result.getString("password"));
	driver.findElement(By.id("submit")).click();
	
	
	}

}
