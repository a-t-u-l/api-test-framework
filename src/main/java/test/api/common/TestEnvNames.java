package test.api.common;

public enum TestEnvNames {

	
	ITE_PUBLIC("Request.BaseURI.Ite");
	
	private final String env;
	
	private TestEnvNames(String env){
		this.env=env;
	}
	
	/******************************************************
	 * @return The value of ENUM Const. in String 
	 * ****************************************************/
	public String getEnv() {
		return this.env;
	}
	
	public static String [] getEnvList(){
		String [] envList={TestEnvNames.ITE_PUBLIC.toString()};
		return envList;
	}
	
}
