package test.api.testset;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.google.gson.Gson;
import com.jayway.restassured.response.Response;

import groovy.json.JsonException;
import test.api.common.APIHeaders;
import test.api.common.ConfigDataAnnotations;
import test.api.common.Constants;
import test.api.common.SignatureEntity;
import test.api.common.TestBase;
import test.api.common.URLMaker;
import test.api.entity.SearchNumberRequestDTO;
import test.api.util.FunctionalUtility;
import test.api.util.RestUtil;

public class TestPlivoAPI extends TestBase implements Constants{

	static Gson gson = new Gson();

	@DataProvider(name = "SearchNumberData")
	public Object[][] addressData() throws Exception{
		switch(TestBase.getTestEnv()){
		case ITE :
			SearchNumberRequestDTO entity = new SearchNumberRequestDTO();
			entity.setCountryIso("US");
			entity.setPattern(234);
			return new Object[][]{{"MAODUZYTQ0Y2FMYJBLOW", entity}};
		default :
			throw new Exception("Not valid Environment");
		}
	}

	@ConfigDataAnnotations(testName="Search Number API", expectedResult="Status Code 200 with proper response", labels="Search API", scope={"Sanity","Regression Test"})
	@Test(groups = { "Sanity","Regression Test" }, description="Search Number API",dataProvider="SearchNumberData")
	public static void validatePlivoAPIFlow(String auth_id, SearchNumberRequestDTO entity){
		Response response=getNumbers(auth_id, entity);
		Assert.assertEquals(response.getStatusCode(),STATUS_CODE_200);
		Assert.assertTrue(FunctionalUtility.isJSONValid(response.getBody().asString()));
	}

	public static Response getNumbers(String auth_id, SearchNumberRequestDTO entity){
		Response response = null;
		try{
			String path=PLIVO_SEARCH_API;
			SignatureEntity pathEntity = new SignatureEntity("{auth_id}", auth_id);
			List<SignatureEntity> pathParams = new ArrayList<>();
			pathParams.add(pathEntity);
			Map<String, String> queryParms = new HashMap<>();
			queryParms.put("country_iso", entity.getCountryIso());
			queryParms.put("pattern", String.valueOf(entity.getPattern()));
			URLMaker um=new URLMaker(baseURI, path, pathParams, queryParms);
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

}
