package com.automation.library;

import java.io.File;
import java.io.FileInputStream;
import java.util.Iterator;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExcelManager 
{
	public static Logger log = LoggerFactory.getLogger(Base.class); 
	
	private static String filePath;
	private static Workbook wb;
	private static Sheet sh;
	
	public ExcelManager(String excelFile, String sheetName)
	{
	  try
	  {
		  File excelDataFile = new File(excelFile);
		  filePath = excelDataFile.getAbsolutePath();
		  log.info("Reading Excel File --> " +filePath);
		  
		  //create a workbook
		  FileInputStream fis = new FileInputStream(filePath);
		 wb =  getWorkBook(fis, filePath);
		 sh =  wb.getSheet(sheetName);
		  
	  } catch(Exception e)
	  {
		  log.error("Error :-->",e);
	  }
	  
	}
	
	public String readExcelDataCell(int rowIndex,int colIndex)
	{
		String cellData = null;
		try
		{
		 Row row = sh.getRow(rowIndex);
		 if(row != null)
		 {
			Cell cell = row.getCell(colIndex) ;
			cellData = formatDataCellToString(cell);
		 }
						
		}catch(Exception e)
		{
			log.error("Error :-->",e);
		}		
		return cellData;	
	}
	

	 public String[][] getExcelData() 
	 {
		String[][] arrayExcelData = null;
			try {
				Iterator<Row> iterator = sh.iterator();
				Row tempRow = sh.getRow(0);
				if (tempRow != null) {
					int totalCols = tempRow.getPhysicalNumberOfCells();
					int totalRows = sh.getPhysicalNumberOfRows();
					arrayExcelData = new String[totalRows - 1][totalCols];

					int iRowCount = 0;
					while (iterator.hasNext()) {
						Row row = iterator.next();
						// skipping row 1, because it's table header info
						if (iRowCount > 0) {
							Iterator<Cell> colIterator = row.iterator();
							int iColCount = 0;
							while (colIterator.hasNext()) {
								Cell cell = colIterator.next();
								// need to format the cells before read it as a string
								String data = formatDataCellToString(cell);
								arrayExcelData[iRowCount - 1][iColCount] = data;
								log.info("Row: " + iRowCount + ", Col: " + iColCount + ", Data: " + data);
								iColCount++;
							}
						}
						iRowCount++;
					}
				}
			} catch (Exception e) {
				log.error("Error: ", e);
			} finally {
				if (wb != null) {
					try {
						wb.close();
					} catch (Exception e) {
						log.error("Error: ", e);
					}
				}
			}
			return arrayExcelData;
		}

	
	
	private String formatDataCellToString(Cell cell)
	{
		String cellString = null;
		try
		{
		DataFormatter formatter = new DataFormatter();
		cellString = formatter.formatCellValue(cell);
		
		}catch(Exception e)
		{
			log.error("Error :-->",e);
		}
		return cellString;
	}
	
	public ExcelManager(String excelFile, int sheetIndex)
	{
	  try
	  {
		  File excelDataFile = new File(excelFile);
		  filePath = excelDataFile.getAbsolutePath();
		  log.info("Reading Excel File --> " +filePath);
		  
		  //create a workbook
		  FileInputStream fis = new FileInputStream(filePath);
		 wb =  getWorkBook(fis, filePath);
		 sh =  wb.getSheetAt(sheetIndex);
		  
	  } catch(Exception e)
	  {
		  log.error("Error :-->",e);
	  }
	  
	}

	 private Workbook getWorkBook(FileInputStream fis, String excelFilePath)
	 {
		 Workbook workbook = null;
	
		 try
		 {
			if( excelFilePath.toLowerCase().endsWith(".xlsx"))
			 {
				workbook = new XSSFWorkbook(fis); 
			 }
			else if(excelFilePath.toLowerCase().endsWith(".xls"))
			{
				workbook = new HSSFWorkbook(fis);
			}
			else
			{
				throw new IllegalArgumentException("The specified file is not Excel data file");
			}
		 }catch (Exception e)
		 {
			 log.error("Error: ", e);
		 }
		 
		 
		 return workbook;
	 }
	 
	 

	 public static void main(String[] args)
	 {			
	//	 ExcelManager excelM = new
	//	 ExcelManager("src/test/resources/testData/CalculaterTestData.xlsx", 0);
	//	 String myData = excelM.readExcelDataCell(3, 3); log.info("Cell 3,3 data is: " + myData);
		 
		 ExcelManager excelM2 = new ExcelManager("src/test/resources/testData/CalculaterTestData-old.xls", 0);
			String myData2 = excelM2.readExcelDataCell(4, 4);
			log.info("Cell 4,4 data is: " + myData2);

			log.info("All Excel Data ------");
			log.info("" + excelM2.getExcelData());

			 

		 
	}
	 
}
