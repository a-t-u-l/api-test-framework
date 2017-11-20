package test.api.common;

import java.util.ArrayList;
import java.util.HashMap;

/***********************************************************
 * This class is designed as a API header container.
 * It supports setup state header backup and retrieval.
 * @author atulsharma
 * ********************************************************/
public class APIHeaders {

	private static HashMap<String,String> hm= new HashMap<String,String>();
	private static HashMap<String,String> hmbkp= new HashMap<String,String>();
	private static APIHeaders instance=null;

	private APIHeaders(){
	}

	public static APIHeaders getInstance(){
		if(!hmbkp.isEmpty()){
			hm.clear();
			hm.putAll(hmbkp);
		}
		if(instance == null) {
			instance = new APIHeaders();
		}
		return instance;
	}

	public void clearHeaders() throws EmptyHeadersMapException{
		if(!hm.isEmpty()){
			if(hmbkp.isEmpty())
				hmbkp.putAll(hm);
			else
				System.out.println("Backup set contains setup values, hence flushing current set.");
		}
		else{
			throw new EmptyHeadersMapException("Header values were not set, nothing to clear");
		}
		hm.clear();
	}

	public void reInitializeSetupHeaders(){
		if(!hmbkp.isEmpty()){
			hm.putAll(hmbkp);
		}
		else
			System.out.println("Header map already contains original values");
	}


	public void setHeaders(ArrayList<HeaderEntity> headers) {
		if(!hm.isEmpty()){
			if(hmbkp.isEmpty())
				hmbkp.putAll(hm);
		}
		hm.clear();
		for(HeaderEntity header :headers){
			hm.put(header.getHeaderKey(),header.getHeaderValue() );
		}
	}

	public boolean changeHeaderValue(String key,String newValue){
		boolean success=false;
		if(hm.get(key)!=null){
			if(hmbkp.isEmpty())
				hmbkp.putAll(hm);
			else
				hm.putAll(hmbkp);
			hm.put(key, newValue);
			success=true;
		}
		return success;
	}

	public HashMap<String,String> getHeaders() throws EmptyHeadersMapException{
		if(hm.isEmpty()){
			throw new EmptyHeadersMapException("Please set header values before trying to access them.");
		}
		return hm;
	}

}
