/*************************
 * Author AtulSharma
 ************************/

package test.api.util;

import com.google.gson.Gson;

import test.api.common.APIHeaders;
import test.api.common.Commons;
import test.api.common.EmptyHeadersMapException;
import test.api.common.HeaderEntity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;


public class FunctionalUtility {


	@SuppressWarnings("rawtypes")
	Map page_properties = Commons.getPageProperties(PropertiesUtil
			.getEnvProperty("data_load")
			+ "/page_elements.properties");

	public static ArrayList<String> dataSplitter(String entry, String splitKey){
		ArrayList<String> str = new ArrayList<String>();
		try{
			String [] store=entry.split(splitKey);
			if(store!=null){
				for(int i=0;i<store.length;i++){
					System.out.println("values after splitting : "+store[i]);
					str.add(store[i]);	
				}
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return str;
	}

	public static ArrayList<String> entrySplitter(String entry){

		ArrayList<String> arr=new ArrayList<String>();
		try{
			String [] store=entry.split("//");
			for(int i=0;i<store.length;i++){
				System.out.println("values after splitting : "+store[i]);
				arr.add(store[i]);	
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return arr;
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

	 public static String replaceLast(String string, String substring, String replacement)
		{
			int index = string.lastIndexOf(substring);
			if (index == -1)
				return string;
			return string.substring(0, index) + replacement
					+ string.substring(index+substring.length());
		}
	 
	 public static long createRandomInteger(int aStart, long aEnd){
		 Random aRandom=new Random();
		    if ( aStart > aEnd ) {
		      throw new IllegalArgumentException("Start cannot exceed End.");
		    }
		    //get the range, casting to long to avoid overflow problems
		    long range = aEnd - (long)aStart + 1;
		    
		    // compute a fraction of the range, 0 <= frac < range
		    long fraction = (long)(range * aRandom.nextDouble());
		    
		    long randomNumber =  fraction + (long)aStart;    
		    return randomNumber;

		  }
	 
	 public final static String createRandomNumber(long len) {
		    if (len > 18)
		        throw new IllegalStateException("To many digits");
		    long tLen = (long) Math.pow(10, len - 1) * 9;

		    long number = (long) (Math.random() * tLen) + (long) Math.pow(10, len - 1) * 1;

		    String tVal = number + "";
		    if (tVal.length() != len) {
		        throw new IllegalStateException("The random number '" + tVal + "' is not '" + len + "' digits");
		    }
		    return tVal;
		}
	 
	 public static String epochDateConverter(String normalDate) throws ParseException{
			System.out.println("Given date  : "+normalDate);
			//Sample date format : String str = "2016-03-06";
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			Date date = df.parse(normalDate);
			long epoch = date.getTime();
			String date_to_pass= Long.toString(epoch);
			System.out.println("Epoch date : "+epoch);
			return date_to_pass;
		}
	 
	 public static APIHeaders setNewHeaders(HashMap<String,String> KVPairs){
		 APIHeaders header=APIHeaders.getInstance();
		 try {
				header.clearHeaders();
			} catch (EmptyHeadersMapException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			ArrayList<HeaderEntity> headersVal=new ArrayList<HeaderEntity>();
			for(String key:KVPairs.keySet()){
				headersVal.add((new HeaderEntity(key, KVPairs.get(key))));
			}
			header.setHeaders(headersVal);
			return header;
	 }
}
