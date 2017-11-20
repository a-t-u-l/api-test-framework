package test.api.common;

import com.google.gson.Gson;

import test.api.util.PropertiesUtil;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Logger;

public class Commons {
	public static final String homedir =System.getProperty("user.dir")+ File.separator;
	

	private final static Logger log = Logger.getLogger(Commons.class.getName()); 
	
	public static Logger getLogger() {
		return log;
	}

	public static String convertEpochToDate(Long epoch){
		Date date = new Date(epoch);
		System.out.println("date:: "+date);
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		return (formatter.format(date));
	}
	
	 public static boolean isJSONValid(String JSON_STRING) {
		 Gson requestBody = new Gson();
	      try {
	    	  requestBody.fromJson(JSON_STRING, Object.class);
	    	  System.out.println("JSON format is valid");
	          return true;
	      } catch(com.google.gson.JsonSyntaxException ex) {
	    	  System.out.println("JSON format is invalid");
	          return false;
	      }
	  }
	
	/**************************************************************
	 * set wait global variable
	 **************************************************************/
	public static void Wait() {
		try {
			Thread.sleep(100);
		} catch (Exception e) {
		}
	}

	/**************************************************************
	 * set wait global variable
	 **************************************************************/
	public static void WaitTenSeconds() {
		try {
			Thread.sleep(10000);
		} catch (Exception e) {
		}
	}

	

	/**************************************************************
	 * generate random number.
	 **************************************************************/
	public static String genarateRandom() {
		Random r = new Random();
		int number = r.nextInt();
		String random_ = Integer.toString(number);
		if (random_.startsWith("-")) {
			return random_.replaceFirst("-", "7");
		}
		return random_;
	}


	/***********************************************************************
	 * @return returns number of lines in the CSV file
	 ***********************************************************************/

	public static Integer getTotalRowsInCSV(String filePath) throws FileNotFoundException {
		Integer Linenumber = 0;
		try {
			BufferedReader CSVFile = new BufferedReader(new FileReader(
					filePath));
			String dataRow = CSVFile.readLine();
			Linenumber++;
			while (dataRow != null) {
				dataRow = CSVFile.readLine();
				Linenumber++;
			}
			CSVFile.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return Linenumber;
	}
	
	public static Integer getTotalRowsInXLS(String filePath) throws FileNotFoundException{
		Integer totalRows=0;
		FileInputStream file = new FileInputStream(new File(filePath));

		//Get the workbook instance for XLS file 
		HSSFWorkbook workbook;
		try {
			workbook = new HSSFWorkbook(file);
			//Get first sheet from the workbook
			HSSFSheet sheet = workbook.getSheetAt(0);
			totalRows=sheet.getLastRowNum();
			workbook.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return totalRows;
	}
	
	public static Integer getTotalRowsInXLSX(String filePath) throws FileNotFoundException{
		Integer totalRows=0;
		FileInputStream file = new FileInputStream(new File(filePath));

		//Get the workbook instance for XLS file 
		XSSFWorkbook workbook;
		try {
			workbook=new XSSFWorkbook(file);
			//Get first sheet from the workbook
			XSSFSheet sheet = workbook.getSheetAt(0);
			totalRows=sheet.getLastRowNum();
			workbook.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return totalRows;
	}

	/**********************************************************************
	 * @return returns row of csv line
	 * Note: Integer is a wrapper for primitive int, hence for comparison
	 * don't use '==' , use 'equals'.(Old code was using '==', so the code
	 * was breaking at 128th row)
	 *********************************************************************/

	@SuppressWarnings("resource")
	public static String[] ReadCsvLine(String csvfilelocation, Integer line)
			throws IndexOutOfBoundsException,NullPointerException {
		try {
			System.setProperty("file.encoding", "UTF-8");
			// log.info("Enter files...");
			BufferedReader CSVFile = new BufferedReader(new FileReader(
					csvfilelocation));
			Integer Linenumber = 0;
			String dataRow;

			dataRow = CSVFile.readLine();

			while (dataRow != null) {
				Linenumber++;
				if (Linenumber.equals(1)) {
					String[] dataArray1 = dataRow.split(",");
					for (int i = 1; i < dataArray1.length; i++) {
					}
				}
				if (Linenumber.equals(line)) {
					String[] dataArrayRow = dataRow.split(",");
					return dataArrayRow;
				}
				dataRow = CSVFile.readLine();
			}
			CSVFile.close();

		} catch (IOException e) {

		}
		return null;

	}

	/**************************************************************
	 * set variable wait global variable
	 **************************************************************/
	public static void Var_Wait(int num) {
		try {
			Thread.sleep(num);
		} catch (Exception e) {
		}
	}

	/*****************************************************************
	 * @return returns map of sql properties
	 ******************************************************************/
	@SuppressWarnings({ "resource", "rawtypes" })
	public static Map getQueryProperties(String sqlprop) throws Exception {
		Scanner scanner = new Scanner(new FileReader(Commons.homedir
				+ PropertiesUtil.getEnvProperty("Data")+ "/" + sqlprop));
		HashMap<String, String> map = new HashMap<String, String>();
		while (scanner.hasNextLine()) {
			String[] columns = scanner.nextLine().split("==");
			map.put(columns[0], columns[1]);
			log.info(columns[1]);
		}
		return map;
	}

	/*****************************************************************
	 * @return returns map of page elements properties
	 ******************************************************************/
	@SuppressWarnings({ "resource", "rawtypes" })
	public static Map getPageProperties(String property) {
		Scanner scanner;
		try {
			scanner = new Scanner(new FileReader(homedir + property));

			HashMap<String, String> map = new HashMap<String, String>();
			while (scanner.hasNextLine()) {
				String[] columns = scanner.nextLine().split("==");
				map.put(columns[0], columns[1]);

			}
			return map;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return null;

	}

	/*************************************************************
	 * @return returns previous date in yyyymmdd format
	 *************************************************************/

	public static String getSysDatePrevious(int num) {
		SimpleDateFormat DateFormat = new SimpleDateFormat("ddMMyyyy");
		Date today = new Date();
		Calendar cal = new GregorianCalendar();
		cal.setTime(today);
		cal.add(Calendar.DAY_OF_YEAR, +num);
		Date date = cal.getTime();
		String fd = DateFormat.format(date).toString();
		return fd;
	}



	/******************************************************************
	 * Get Number of days
	 ******************************************************************/
	public static int GetNoDays(String date1, String date2)
			throws ParseException {

		Calendar cal1 = new GregorianCalendar(TimeZone.getTimeZone("IST"));
		Calendar cal2 = new GregorianCalendar(TimeZone.getTimeZone("IST"));

		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");

		Date date = sdf.parse(date1);
		cal1.setTime(date);
		date = sdf.parse(date2);
		cal2.setTime(date);

		int day_diff = daysBetween(cal1.getTime(), cal2.getTime());
		System.out.println("Days= " + day_diff);

		return day_diff;
	}

	public static int daysBetween(Date d1, Date d2) {
		return (int) ((d2.getTime() - d1.getTime()) / (1000 * 60 * 60 * 24));
	}


	/******************************************************************
	 * Get System date
	 ******************************************************************/
	public static String getSysDatePST(){
		Calendar pstCal = new GregorianCalendar(TimeZone  
				.getTimeZone("PST"));  

		SimpleDateFormat sdf = new SimpleDateFormat(  
				"MM/dd/yyyy hh:mm:ss");  

		sdf.setCalendar(pstCal);  
		String dateString = sdf.format(pstCal.getTime());  
		System.out.println(dateString); 
		return dateString;
	}

	public static String getSysDateGMT(){
		Calendar gmtCal = new GregorianCalendar(TimeZone  
				.getTimeZone("GMT"));  

		SimpleDateFormat sdf = new SimpleDateFormat(  
				"MM/dd/yyyy hh:mm:ss");  

		sdf.setCalendar(gmtCal);  
		String dateString = sdf.format(gmtCal.getTime());  
		System.out.println(dateString); 
		return dateString;
	}

	/******************************************************************
	 * Get System date
	 ******************************************************************/
	public static String getSysDateIST(){
		Calendar pstCal = new GregorianCalendar(TimeZone  
				.getTimeZone("IST"));  

		SimpleDateFormat sdf = new SimpleDateFormat(  
				"MM/dd/yyyy hh:mm:ss");  

		sdf.setCalendar(pstCal);  
		String dateString = sdf.format(pstCal.getTime());  
		System.out.println(dateString); 
		return dateString;
	}

	/******************************************************************
	 * Get System date
	 ******************************************************************/
	public static String getSysTimeIST(){
		Calendar pstCal = new GregorianCalendar(TimeZone  
				.getTimeZone("IST"));  

		SimpleDateFormat sdf = new SimpleDateFormat(  
				"hh:mm:ss");  

		sdf.setCalendar(pstCal);  
		String dateString = sdf.format(pstCal.getTime());  
		System.out.println(dateString); 
		return dateString;
	}	

}





