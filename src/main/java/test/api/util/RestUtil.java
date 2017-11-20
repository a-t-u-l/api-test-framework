package test.api.util;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.response.Response;
import groovy.json.JsonException;
import org.json.JSONObject;

import java.io.File;
import java.util.HashMap;
import java.util.Map;


public class RestUtil {

	private static String url="";
	
	public static String getCurrentUrl(){
		return url;
	}

    public static Response getByUrl(String url) {
    	RestUtil.url=url;
        Response response = RestAssured.
                given()
                .log()
                .all()
                .get(url);
        return response;
    }

    public static Response postByUrl(String url) {
    	RestUtil.url=url;
        Response response = RestAssured.
                given()
                .log()
                .all()
                .post(url);
        return response;
    }

    public static Response postByUrl(String url,String body) {
    	RestUtil.url=url;
        Response response = RestAssured.
                given()
                .log()
                .all()
                .body(body)
                .post(url);
        return response;
    }

    public static Response putByUrl(String url) {
    	RestUtil.url=url;
        Response response = RestAssured.
                given()
                .log()
                .all()
                .put(url);
        return response;
    }

    public static Response putByUrl(String url, String body) {
    	RestUtil.url=url;
        Response response = RestAssured.
                given()
                .log()
                .all()
                .body(body)
                .put(url);
        return response;
    }

    public static Response postCallJsonMultipartUrl(String url, String file, String mimeType,
	HashMap<String,String> headers, Map<String, String> params) {
    	RestUtil.url=url;
		Response response =RestAssured
                .given()
				.headers(headers)
				.multiPart("file",new File(file),mimeType)
				.formParams(params)
				.log()
				.all()
				.when()
				.post(url);
		return response;
	}

    public static Response getCallFormUrl(String url, HashMap<String,String> headers, Map<String, String> params) {
    	RestUtil.url=url;
        Response response = RestAssured
                .given()
                .headers(headers)
                .log()
                .all()
                .formParams(params)
                .get(url);
        return response;
    }

    
    public static Response putCallJsonUrl(String url,HashMap<String, String> headers,String body) {
    	RestUtil.url=url;
        Response response = RestAssured.
                given()
                .headers(headers)
                .log()
                .all()
                .body(body)
                .put(url);
        return response;
    }

    public static Response postCallJsonUrl(String url,HashMap<String, String> headers, String body) {
    	RestUtil.url=url;
        Response   response =RestAssured. given()
                .headers(headers)
                .body(body)
                .log()
                .all()
                .when()
                .post(url);
        return response;
    }

    public static Response getCallUrl(String url, HashMap<String, String> headers) {
    	RestUtil.url=url;
        Response response = RestAssured.
                given()
                .headers(headers)
                .log()
                .all()
                .get(url);
        return response;
    }
    
    public static HashMap<String,Object>  getDataString(Response response,String[] param) throws JsonException{
    	HashMap<String, Object >   hm = new HashMap<String, Object>();
    	String responseReceived = response.body().asString();
    	System.out.println("responseReceived: "+responseReceived);
    	JSONObject jsonObject = new JSONObject(responseReceived);
    	for (int i=0;i<param.length;i++){
    		hm.put(param[i], getResponseParam(jsonObject,param[i]));
    	}
    	return hm;
    }

    public static String getResponseParam(Response response,String param) throws JsonException{
    	String responseReceived = response.body().asString();
    	System.out.println("responseReceived: "+responseReceived);
    	JSONObject jsonObject = new JSONObject(responseReceived);
    	JSONObject object=(JSONObject) jsonObject.get("payload");
    	String datavalue= object.getString(param);
    	System.out.println("datavalue: "+datavalue);
    	return datavalue;
    }


    public static String getResponseParam( JSONObject jsonObject,String param) throws JsonException {
        System.out.println("jsonObject: "+jsonObject);
        String resparam = jsonObject.get(param).toString();
        return resparam;
    }
    
}

