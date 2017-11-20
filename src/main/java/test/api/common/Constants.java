package test.api.common;

public interface Constants extends APISignature {

	/*********ENVIRONMENT CONSTANTS************************/
	final String ITE="ITE";
	final String RESOURCE_PATH="resources/properties/";
	final String DEFAULT_PROPERTIES_FILE="env.properties";
	final String DEFAULT_PROP_ENV_KEY="env_to_use";
	/*****************************************************/
	
	/*************STATUS CODES******************************/
	final int STATUS_CODE_200=200;
	final int STATUS_CODE_202=202;
	final int STATUS_CODE_422=422;
	final int STATUS_CODE_404=404;
	final int STATUS_CODE_400=400;
	final int STATUS_CODE_403=403;
	final int STATUS_CODE_420=420;
	/******************************************************/
	
	/**************STATUS MESSAGES**************************/
	String LimitExceeD="LimitSizeExecceded";
	String InvalidLimit="InvalidLimit";
	String USER_NOT_FOUND="NoUserFound";
	String JSON_EXCEPTION="JSONException";
	/******************************************************/
	
	/************API HEADERS*******************************/
	String CLIENT_ID="Test";
	String CONTENT_TYPE=  "application/json";
	/*****************************************************/

	/**********************************************************/
	public static final String TESTDATA_BASE_URL = "baseUrl";
	public static final String CONFIG_FILE = "configFile";
	public static final String COMPONENT = "component";
	public static final String ENVIORNMENT = "environment";
	public static final String SCOPE = "scope";
	public static final String TESTTYPE = "testtype";
	public static final String RELEASEADDED = "releaseadded";
	public static final String LABELS = "labels";
	public static final String COMMENTS = "comments";
	/*********************************************************/
	
}

