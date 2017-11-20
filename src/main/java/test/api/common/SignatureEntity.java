package test.api.common;

public class SignatureEntity {

	private String key;
	private String value;
	
	public SignatureEntity(String key, String value){
		this.key=key;
		this.value=value;
	}
	
	public String getKey(){
		return this.key;
	}
	
	public String getValue(){
		return this.value;
	}
}
