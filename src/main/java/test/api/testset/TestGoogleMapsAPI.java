package test.api.testset;

import com.google.gson.Gson;
import com.jayway.restassured.response.Response;

import groovy.json.JsonException;
import test.api.common.APIHeaders;
import test.api.common.ConfigDataAnnotations;
import test.api.common.Constants;
import test.api.common.TestBase;
import test.api.common.URLMaker;
import test.api.entity.Entity;
import test.api.entity.MapApiResponseDTO;
import test.api.util.FunctionalUtility;
import test.api.util.RestUtil;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.util.HashMap;


public class TestGoogleMapsAPI extends TestBase implements Constants{

	static Gson gson = new Gson();

	@DataProvider(name = "addressData")
	public Object[][] addressData() throws Exception{
		switch(TestBase.getTestEnv()){
		case ITE :
			return new Object[][]{{"1600+Amphitheatre+Parkway,+Mountain+View,+CA",37.4216548, -122.0856374}};
		default :
			throw new Exception("Not valid Environment");
		}

	}

	@ConfigDataAnnotations(testName="Validate data for Google Maps API", expectedResult="Status Code 200 with proper lat-long", labels="Maps API", scope={"Sanity","Regression Test"})
	@Test(groups = { "Sanity","Regression Test" }, description="Validating Google Maps API",dataProvider="addressData")
	public static void validateMapDataTest(String address, Double lat, Double lng){
		Entity entity=new Entity();
		entity.setAddress(address);
		Response response=getMapFeed(entity);
		Assert.assertEquals(response.getStatusCode(),STATUS_CODE_200);
		validateJSONStructureContent(response);
		Assert.assertTrue(FunctionalUtility.isJSONValid(response.getBody().asString()));
		MapApiResponseDTO mapData= gson.fromJson(response.asString(), MapApiResponseDTO.class);
		System.out.println("Status : "+mapData.getStatus());
		double latActual = mapData.getResults().get(0).getGeometry().getLocation().getLat();
		double lngActual = mapData.getResults().get(0).getGeometry().getLocation().getLng();
		System.out.println("lattitude : "+latActual+" , longitude : "+lngActual);
		Assert.assertEquals(lat, latActual," Expected : "+lat+"Actual : "+latActual);
		Assert.assertEquals(lng, lngActual," Expected : "+lng+"Actual : "+lngActual);
	}

	public static Response getMapFeed(Entity entity){
		Response response = null;
		try{
			String path=GOOGLE_MAPS_API;
			HashMap<String, String> hm=new HashMap<>();
			hm.put("address",entity.getAddress());
			URLMaker um=new URLMaker(baseURI, path, hm);
			String url=um.getURL();
			System.out.println("invoking API : "+url);
			APIHeaders headers=APIHeaders.getInstance();
			response=RestUtil.getCallUrl(url, headers.getHeaders());
			response.getBody().prettyPrint();

		}catch(JsonException e){
			Assert.fail(e.getMessage(), e.getCause());
		}catch(Exception e){
			Assert.fail(e.getMessage(), e.getCause());
		}
		return response;
	}

	public static void validateJSONStructureContent(Response response){
		String res=response.getBody().asString();
		Assert.assertTrue(res.contains("address_components"));
		Assert.assertTrue(res.contains("long_name"));
		Assert.assertTrue(res.contains("short_name"));
		Assert.assertTrue(res.contains("types"));
		Assert.assertTrue(res.contains("results"));
	}

}
