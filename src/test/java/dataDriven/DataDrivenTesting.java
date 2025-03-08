package dataDriven;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class DataDrivenTesting {

	public static void main(String[] args) throws IOException {

		/**
		 * Scenario: Identify Testcase column by scanning the entire 1st row, and find
		 * out purchase testcase row. and use the data for testcases.
		 */

		// FileInputStream file = new
		// FileInputStream("C://Users//DeepakVaithylingam//Downloads//DataDrivernTesting.xlsx");
		// // XSSFworkbook is a class, its accept fileinputstream
		//
		// XSSFWorkbook workbook = new XSSFWorkbook(file);
		//
		// // go to desired sheet in Excel.
		//
		// int sheets = workbook.getNumberOfSheets();
		//
		// for (int i = 0; i < sheets; i++) {
		// if (workbook.getSheetName(i).equalsIgnoreCase("Subject")) {
		//
		// XSSFSheet sheet = workbook.getSheetAt(i);
		// Iterator<Row> rows = sheet.iterator(); // sheet is collection of rows
		// Row firstRow = rows.next(); // => got the access for 1st row
		//
		// Iterator<Cell> cell = firstRow.cellIterator(); // row is collection of cells.
		//
		// int k = 0;
		// int column = 0;
		//
		// while (cell.hasNext()) { // this will check, next cell has the value or not
		//
		// Cell value = cell.next(); // this takes the value of the cell
		// if (value.getStringCellValue().equalsIgnoreCase("Data2")) {
		//
		// //desired column
		// column =k; // we are getting the column number
		//
		// }
		// k++;
		// }
		// System.out.println(column);
		//
		// }
		//
		// }
		// one way

		// File file = new
		// File("C://Users//DeepakVaithylingam//Downloads//DataDrivernTesting.xlsx");
		// FileInputStream stream = new FileInputStream(file);
		// Workbook workkbook = new XSSFWorkbook(stream);
		// Sheet sheet = workkbook.getSheet("Subject");
		// Row row = sheet.getRow(3);
		// Cell cell = row.getCell(2);
		//
		// String stringCellValue = cell.getStringCellValue();
		// System.out.println(stringCellValue);
		// workkbook.close();
		// stream.close();

		// letcodewithKowshik

		// String fileLocation = "./test-data/DataDrivernTesting.xlsx";
		// XSSFWorkbook workbook = new XSSFWorkbook(fileLocation);

		/**
		 * here we have two ways to specify the sheet. either we can go with string name
		 * or index. Stringname we can directly mention sheet name. index we have to
		 * pass the index.
		 */
		// XSSFSheet sheet = workbook.getSheet("Subject"); => by using sheetName

		// XSSFSheet sheet = workbook.getSheetAt(2); //=> by using index

		/*
		 * // getting row XSSFRow row = sheet.getRow(1); // getting cell XSSFCell cell =
		 * row.getCell(0); // fetching the value String value =
		 * cell.getStringCellValue(); System.out.println(value);
		 * 
		 */

		// To get the values by using loop

		// row // to skip header, we have to set i=1. it will start from 1st row. 0 will
		// be excluded.
		// for (int i = 1; i <2 ; i++) {
		// XSSFRow row = sheet.getRow(i);
		// //column
		// for (int j = 0; j <3; j++) {
		// XSSFCell cell = row.getCell(j);
		// String stringCellValue = cell.getStringCellValue();
		// System.out.println(stringCellValue);
		// }
		// }
		//
		// // we should close the browser at the end.
		// workbook.close();

		// Dynamically handle the rows and columns

		String fileLocation = "./test-data/DataDrivernTesting.xlsx";
		XSSFWorkbook workbook = new XSSFWorkbook(fileLocation);

		XSSFSheet sheet = workbook.getSheet("Sheet1");

		/**
		 * To get last row number, with excluding header
		 */
		int lastRowNum = sheet.getLastRowNum();
		System.out.println("Last row exclusion of header " + lastRowNum);

		/**
		 * To get last row number, with including of header
		 */

		int physicalNumberOfRows = sheet.getPhysicalNumberOfRows();
		System.out.println("Last row including of header " + physicalNumberOfRows);

		/**
		 * To get last cell number. we should take 1st row as reference, bcz that holds the headers. by using we can get the last cell
		 */

		short lastCellNum = sheet.getRow(0).getLastCellNum();
		/**
		 * using dynamic rows and columns
		 */

		System.out.println("Last cell number " + lastCellNum);
		for (int i = 1; i <=lastRowNum; i++) {
			XSSFRow row = sheet.getRow(i);

			for (int j = 0; j < lastCellNum; j++) {
				XSSFCell cell = row.getCell(j);
				/**
				 * getStringCellValue cant handle numerics values, so we are converting the values, when we receive from excel, since we have to pass the values to our testcase in string format only
				 *To convert the cell value, we have dataformatter. This way we can handle boolean, numberics and special characters
				 * 
				 */
				
				//String stringCellValue = cell.getStringCellValue();
				
				DataFormatter format = new DataFormatter();
				String formatCellValue = format.formatCellValue(cell);				
				System.out.println(formatCellValue);
			}
		}
		
		workbook.close();
		
		
		
		
		
		
		
		
		
		
		
		
		

	}

}
