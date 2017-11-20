package test.api.util;

import org.apache.commons.io.FileUtils;

import test.api.common.Commons;
import test.api.common.Constants;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

public class PropertiesUtil  implements Constants {

	public static final String propdir = Commons.homedir+RESOURCE_PATH;

	static Properties props = new Properties();
	static {
		try {
			props.load(new FileInputStream(propdir +DEFAULT_PROPERTIES_FILE));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**************************************************************
	 * @return returns properties value from default properties file.
	 * 
	 *************************************************************/

	public static String getLoadedProperty(String value) {
		String propertyValue = null;
		try {
			propertyValue = props.getProperty(value);
		} catch (Exception e) {
			// e.printStackTrace();
			propertyValue = "default";
		}
		return propertyValue;
	}

	/**************************************************************
	 * @return returns properties value from default Environment
	 *  properties file.
	 *************************************************************/

	public static String getEnvProperty(String value) {
		String propertyValue = null;
		String env_prop_to_load = null;
		try {
			env_prop_to_load = props.getProperty(DEFAULT_PROP_ENV_KEY);
			//System.out.print("TESTS RUNNING ON ENV " + env_prop_to_load);
			props.load(new FileInputStream(propdir + env_prop_to_load));
			propertyValue = props.getProperty(value);
		} catch (Exception e) {
			// e.printStackTrace();
			propertyValue = "NOT_FOUND_CHECK_PROPERTY";
		}
		return propertyValue;
	}

	/**************************************************************
	 * @return returns properties value from given properties file.
	 * 
	 *************************************************************/

	public static String getProperty(String fullFilePath,String value) {
		Properties prop=new Properties();
		try {
			prop.load(new FileInputStream(fullFilePath));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		String propertyValue = null;
		try {
			propertyValue = prop.getProperty(value);
		} catch (Exception e) {
			// e.printStackTrace();
			propertyValue = "default";
		}
		return propertyValue;
	}
	
	/************************************************************
	 * sets values in the given properties file.
	 **********************************************************/

	public static void setProperty(String fullFilePath, String key, String value) {
		Properties prop=new Properties();
		try {
			prop.load(new FileInputStream(fullFilePath));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		prop.setProperty(key, value);
		saveProperties(prop, fullFilePath);
	}
	
	/************************************************************
	 * sets values in the default Environment properties file.
	 **********************************************************/

	public static void setEnvProperty(String key, String value) {
		String env_prop_to_load = props.getProperty(DEFAULT_PROP_ENV_KEY);
		props.setProperty(key, value);
		saveProperties(props, propdir + env_prop_to_load);
	}

	/**************************************************************
	 * saves values to the properties file before processing. 
	 * Example set system date in properties file.
	 **************************************************************/

	@SuppressWarnings("unchecked")
	public static void saveProperties(Properties p, String fileName) {
		OutputStream outPropFile;
		try {
			outPropFile = new FileOutputStream(fileName);
			p.store(outPropFile, "Properties File to the Test Application");
			outPropFile.close();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

		/*
		 * A better way to do this is to sort the properties before writing them out. 
		 */
		List<String> unSortedProperties = new ArrayList<String>();
		try {
			unSortedProperties = FileUtils.readLines(new File(fileName), "utf-8");
			Collections.sort(unSortedProperties);
			// now the properties are sorted
			FileUtils.writeLines(new File(fileName+".sorted"), unSortedProperties);
		} catch (IOException e) {
			System.out.println("Error reading properties file:" + e.getMessage());
			e.printStackTrace();
		}
	}

}
