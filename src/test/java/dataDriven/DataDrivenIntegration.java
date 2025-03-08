package dataDriven;

import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class DataDrivenIntegration {
	
	//we created utility, than we can use it for base class.

	public static String[][] getExcelSheetData() throws IOException {

		String fileLocation = "./test-data/DataDrivernTesting.xlsx";
		XSSFWorkbook workbook = new XSSFWorkbook(fileLocation);
		XSSFSheet Sheet = workbook.getSheetAt(0);

		int lastRowNum = Sheet.getLastRowNum();
		short lastCellNum = Sheet.getRow(0).getLastCellNum();

		// Declaring two dimesional Array
		String[][] data = new String[lastRowNum][lastCellNum];
		
		for (int i = 1; i <= lastRowNum; i++) {
			XSSFRow row = Sheet.getRow(i);

			for (int j = 0; j < lastCellNum; j++) {
				XSSFCell cell = row.getCell(j);

				DataFormatter format = new DataFormatter();
				String formatCellValue = format.formatCellValue(cell);
				data[i-1][j] = formatCellValue;
				
				/**
				 * Since we skipped the first header in row. So we have to reduce the count of "i".
				 */

			}
		}

		workbook.close();
		return data;
	}

}
