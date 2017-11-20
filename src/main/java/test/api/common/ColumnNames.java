package test.api.common;

import java.util.ArrayList;
import java.util.Arrays;

public enum ColumnNames {

	DATA_PROVIDER_NAME("Data Provider Name"),
	METHOD_SIGNATURE("Method Signature"),
	INPUT("Input"),
	EXPECTED_OUTPUT("Expected Output"),
	FLAG("Flag");
	
	private final String value;
	
	private ColumnNames(String value){
		this.value=value;
	}
	
	/******************************************************
	 * @return The value of ENUM Const. in String 
	 * ****************************************************/
	public String getValue() {
		return this.value;
	}
	
	public static ArrayList<ColumnNames> getValues(){
		ArrayList<ColumnNames> list=new ArrayList<ColumnNames>(Arrays.asList(ColumnNames.values()));
		return list;
	}
	
}
