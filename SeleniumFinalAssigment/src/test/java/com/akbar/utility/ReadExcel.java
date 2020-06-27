package com.akbar.utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;



public class ReadExcel {
	XSSFWorkbook workbook;
	XSSFSheet sheet;
	ArrayList<Object[]> Expected=new ArrayList<Object[]>();

	public  ReadExcel(String path) {
		
	try {
		File file=new File(path);
		FileInputStream input=new FileInputStream(file);
		workbook=new XSSFWorkbook(input);
		
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.getMessage();
	} 
}
	
	public ArrayList<Object[]> getData(int StringIndex,int Row_Count,int Column_Count) {
		 sheet=workbook.getSheetAt(StringIndex);
		 if((sheet.getRow(Row_Count).getCell(Column_Count).getCellType()).equals("Numeric")) {
		 Object Data=(Object) sheet.getRow(Row_Count).getCell(Column_Count).getCellType();
		 Expected.add((Object[]) Data);
		 }
		 return Expected;
		
		
	}
	
	public int getRowCount() {
		 int row=5+1;
		 return row;
		
	}

}
