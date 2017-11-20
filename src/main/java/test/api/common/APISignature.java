package test.api.common;

//CAPTIALS IN PATH DENOTES PATH PARAM
//HENCE PLEASE USE SMALL CHARACTERS AS VALUES FOR PATH OTHER THAN PATH PARAMS
public interface APISignature {

		//feed result for all updates
		final String GOOGLE_MAPS_API="maps/api/geocode/json";
		
		//fExample of path param
		final String GOOGLE_MAP_PATH_PARM="maps/geocode/{CODE}";	
		
		final String PLIVO_SEARCH_API = "v1/Account/{auth_id}/PhoneNumber/";
}
