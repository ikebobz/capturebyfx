
package webapps;

import java.io.FileInputStream;
import java.io.FileNotFoundException; 
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell; 
import org.apache.poi.hssf.usermodel.HSSFRow; 
import org.apache.poi.hssf.usermodel.HSSFSheet; 
import org.apache.poi.hssf.usermodel.HSSFWorkbook; 
import org.apache.poi.ss.usermodel.Cell; 
import org.apache.poi.ss.usermodel.CellStyle; 
import org.apache.poi.ss.usermodel.DataFormat; 
import org.apache.poi.ss.usermodel.Row; 
import org.apache.poi.ss.usermodel.Sheet; 
import org.apache.poi.ss.usermodel.Workbook;

public class ExcelReader 
{
   public ExcelReader()
   {
	   
   }

public List<String> readFromExcel(String filename,int rowsize) throws IOException
{
	List<String> entries = new ArrayList<String>();
	HSSFWorkbook myExcelBook = new HSSFWorkbook(new FileInputStream(filename)); 
	HSSFSheet myExcelSheet = myExcelBook.getSheet("Sheet1"); 
	for(int index = 0;index < rowsize; index++) {
	HSSFRow row = myExcelSheet.getRow(index); 
	if(row.getCell(0).getCellType() == HSSFCell.CELL_TYPE_STRING)
	{ 
	  entries.add(row.getCell(0).getStringCellValue()); 
	
	} 
	}
	
	myExcelBook.close();
	return entries;
 }
 public int getRecordCount()
 {
  int count = 0;
  
  return count;
 }

}

