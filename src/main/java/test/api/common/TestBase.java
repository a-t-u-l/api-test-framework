package test.api.common;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import test.api.util.PropertiesUtil;

import java.util.ArrayList;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

/************************************************************************
 * @author atulsharma
 ************************************************************************/
public class TestBase implements Constants {

	HSSFWorkbook workbook;
	// define an Excel Work sheet
	HSSFSheet sheet;
	protected static String baseURI;
	private static String testEnv=ITE;

	public static String getTestEnv() {
		return testEnv;
	}

	@BeforeSuite(alwaysRun=true)
	public void setup(){
		ArrayList<HeaderEntity> hel=new ArrayList<HeaderEntity>();
		APIHeaders ini=APIHeaders.getInstance();

		String [] headerKeys={"Content-Type","auth_id", "auth_token"};
		String [] headerValues={CONTENT_TYPE, "MAODUZYTQ0Y2FMYJBLOW", "Mzk0MzU1Mzc3MTc1MTEyMGU2M2RlYTIwN2UyMzk1"};

		for(int index=0;index<headerKeys.length;index++){
			System.out.println("index : "+index+", key : "+headerKeys[index]+", value : "+ headerValues[index]);
			hel.add(new HeaderEntity(headerKeys[index], headerValues[index]));
		}
		ini.setHeaders(hel);
	}

	@BeforeClass(alwaysRun = true)
	@Parameters({ "env"  })
	public void beforeSuite(@Optional("ITE") String environment) {
		
		APIHeaders reini=APIHeaders.getInstance();
		reini.reInitializeSetupHeaders();
		
		try {
			String ENV=null;
			String reqEnv;
			try {
				reqEnv = System.getProperty("ENV").toString();
			} catch (Exception e) {
				switch(testEnv){
					case ITE : 
					reqEnv=TestEnvNames.ITE_PUBLIC.toString();
					break;
				default : 
					reqEnv=TestEnvNames.ITE_PUBLIC.toString();
				}
				System.out.println(e.getMessage()+" : The Required Environment was null, Hence choosing default value as :"+reqEnv);
			}
			for(String env:TestEnvNames.getEnvList()){
				if(env.equalsIgnoreCase(reqEnv)){
					ENV=TestEnvNames.valueOf(env).getEnv().toString();
					System.out.println("Environment to refer : "+ENV);
					break;
				}
			}
			if(ENV==null){
				throw new NullPointerException("Requested environment : "+reqEnv+", is not valid.");
			}
			else{
				baseURI = PropertiesUtil.getEnvProperty(ENV);
				if (reqEnv.toUpperCase().contains(ITE)){
					testEnv="ITE";
				}else{
					throw new Exception("Please define proper test environment in ENUM file TestEnvNames and modify the code accordingly.");
				}
				System.out.println("baseURI in testbase:"+baseURI);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}