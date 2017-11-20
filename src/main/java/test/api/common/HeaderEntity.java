package test.api.common;

public class HeaderEntity {

	private String headerKey;
	private String headerValue;
	
	public HeaderEntity(String headerKey, String headerValue){
		this.headerKey=headerKey;
		this.headerValue=headerValue;
	}
	
	public String getHeaderKey(){
		return this.headerKey;
	}
	
	public String getHeaderValue(){
		return this.headerValue;
	}
}
