/***********************************************************************************
 * This class defines a HashMap which stores the CSV/Excel value.
 * 
 * @author AtulSharma
 * ********************************************************************************/
package test.api.util;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import test.api.common.ColumnNames;
import test.api.common.Commons;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;

public class ProcessRecord {

	private static int hashIndex=0;
	private static ArrayList<String> columnNames;
	private static String dataFileName="/SampleTemplate";
	private static String dataFileExtension=".csv";
	private static HashMap<String, String> testData;

	/****************************************************
	 *get the file name.
	 * *************************************************/
	public static String getFileName() {
		return dataFileName+dataFileExtension;
	}

	/****************************************************
	 *set the file name.
	 * *************************************************/
	private static void setFileName(String fileName,String extension) {
		ProcessRecord.dataFileName = fileName;
		ProcessRecord.dataFileExtension=extension;
	}

	/****************************************************
	 *set the file name where the data is stored.
	 * *************************************************/
	public static void setFile(String fileName,String extension){
		setFileName("/"+fileName,extension);
	}

	public static void main(String [] args){
		Integer num=0;
		try {
			num = Commons.getTotalRowsInCSV(Commons.homedir
					+ "dataBackup"+getFileName());
			System.out.println("Number of data rows :" + (num-2));
			for ( int i = 0; i < num-1; i++) {
				try {
					xlsReader(i);
				} catch (IndexOutOfBoundsException e) {
					e.printStackTrace();
				}
			}
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
	}

	/****************************************************
	 *creates a HashMap for the row of data.
	 * *************************************************/
	private static void createMap(ArrayList<String> data) {
		testData= new HashMap<String, String>();
		for (int i=0;i<data.size();i++){
			testData.put(columnNames.get(i), data.get(i));
		}
	}

	/*****************************************************
	 * data reader method which will read one CSV row
	 * and create a HashMap for the row data.
	 * @param index starts from 1.
	 * @return HashMap<String,String>
	 ****************************************************/
	public static HashMap<String,String> reader(int index) throws IndexOutOfBoundsException{
		int column = 0;
		ArrayList<String> rowData=new ArrayList<String>();

		String[] data = Commons.ReadCsvLine(Commons.homedir+ "data"+ getFileName(), index);
		if (index == 1) {
			createKey();
		}
		else{
			if(columnNames==null || columnNames.size()==0){
				createKey();
			}
			if(data!=null)
				column=data.length;
			else{
				NullPointerException exceededRowCountException=new NullPointerException("The row data for the "
						+ "row : "+index+" does not exist (for the file: "+getFileName()+"). Please compare the data with back up data");
				throw exceededRowCountException;
			}
			if(column<=hashIndex){
				for(int j=0;j<column;j++){
					rowData.add(data[j]);
				}
				for(int j=column;j<hashIndex;j++){
					rowData.add("$");
				}
			}
			else{
				IndexOutOfBoundsException exceededColumnCountException=new IndexOutOfBoundsException("The column data in the "
						+ "row : "+index+" exceeds the HEADER column count (for the file: "+getFileName()+")");
				throw exceededColumnCountException;
			}
			createMap(rowData);

			/****************************************************************
			 * Console output for the test data. For debugging purposes.*/

			Set<Entry<String, String>> set=testData.entrySet();
			Iterator<Entry<String, String>> itr=set.iterator();
			System.out.println("\n");
			while(itr.hasNext()){
				@SuppressWarnings("rawtypes")
				Entry me = itr.next();
				System.out.print(me.getKey() + " :: ");
				System.out.println(me.getValue());
			}
			/**************************************************************/
		}
		return testData;
	}

	/*****************************************************
	 * data reader method which will read one Excel (.xls)
	 * row and create a HashMap for the row data.
	 * @return HashMap<String,String>
	 ****************************************************/
	public static HashMap<String,String> xlsReader(int index){

		try {

			FileInputStream file = new FileInputStream(new File(Commons.homedir+"data"+getFileName()));

			//Get the workbook instance for XLS file
			HSSFWorkbook workbook = new HSSFWorkbook(file);

			//Get first sheet from the workbook
			HSSFSheet sheet = workbook.getSheetAt(0);

			//Iterate through each rows from first sheet
			//Iterator<Row> rowIterator = sheet.iterator();

			Row row = sheet.getRow(index);

			dataFormatter(row, index);

			file.close();
			workbook.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return testData;
	}

	/*****************************************************
	 * data reader method which will read one Excel (.ods)
	 * row and create a HashMap for the row data.
	 * @param index starts from 1.
	 * @return HashMap<String,String>
	 ****************************************************/
	public static HashMap<String,String> xlsxReader(int index){

		try {

			FileInputStream file = new FileInputStream(new File(Commons.homedir+"data"+getFileName()));

			//Get the workbook instance for ODS file
			XSSFWorkbook workbook = new XSSFWorkbook(file);

			//Get first sheet from the workbook
			XSSFSheet sheet = workbook.getSheetAt(0);

			//Iterate through each rows from first sheet
			//Iterator<Row> rowIterator = sheet.iterator();

			Row row = sheet.getRow(index);

			dataFormatter(row,index);

			file.close();
			workbook.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return testData;
	}

	/*****************************************************
	 * data format method which will create the HashMap.
	 ****************************************************/
	private static void dataFormatter(Row row,int index){
		ArrayList<String> rowData=new ArrayList<String>();
		int column=0;
		if (index == 0) {
			createKey();
		}
		else{
			if(columnNames==null || columnNames.size()==0){
				createKey();
			}
			if (row != null) {
				column=row.getLastCellNum();
			}
			else {
				NullPointerException exceededRowCountException=new NullPointerException("The row data for the "
						+ "row : "+index+" does not exist (for the file: "+getFileName()+"). Please compare the data with back up data");
				throw exceededRowCountException;
			}

			if(column<=hashIndex){

				Iterator<Cell> cellIterator = row.cellIterator();
				while(cellIterator.hasNext()) {
					Cell cell = cellIterator.next();
					rowData.add(cell.getStringCellValue());
				}
			}
			else{
				IndexOutOfBoundsException exceededColumnCountException=new IndexOutOfBoundsException("The column data in the "
						+ "row : "+index+" exceeds the HEADER column count (for the file: "+getFileName()+")");
				throw exceededColumnCountException;
			}
			createMap(rowData);

			/****************************************************************
			 * Console output for the test data. For debugging purposes.*/

			Set<Entry<String, String>> set=testData.entrySet();
			Iterator<Entry<String, String>> itr=set.iterator();
			System.out.println("\n");
			while(itr.hasNext()){
				@SuppressWarnings("rawtypes")
				Entry me = itr.next();
				System.out.print(me.getKey() + " :: ");
				System.out.println(me.getValue());
			}
			/**************************************************************/


			/*while(rowIterator.hasNext()) {
			row = rowIterator.next();

			//For each row, iterate through each columns
			Iterator<Cell> cellIterator = row.cellIterator();
			while(cellIterator.hasNext()) {

				Cell cell = cellIterator.next();

				switch(cell.getCellType()) {
				case Cell.CELL_TYPE_BOOLEAN:
					System.out.print(cell.getBooleanCellValue() + "\t\t");
					break;
				case Cell.CELL_TYPE_NUMERIC:
					System.out.print(cell.getNumericCellValue() + "\t\t");
					break;
				case Cell.CELL_TYPE_STRING:
					System.out.print(cell.getStringCellValue() + "\t\t");
					break;
				}
			}
			System.out.println("");
		}*/


		}

	}

	private static void createKey(){

		ArrayList<ColumnNames> cn=ColumnNames.getValues();
		columnNames=new ArrayList<String>();
		for(ColumnNames name:cn){
			columnNames.add(name.getValue());
		}
		hashIndex=columnNames.size();
		System.out.println("no of columns : "+hashIndex);
		createMap(columnNames);
	}

}