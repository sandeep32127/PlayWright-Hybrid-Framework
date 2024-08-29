package com.qa.opencart.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
	
	private Properties prop;
	
	public ConfigReader() {
		try {
			loadProperties();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void loadProperties() throws FileNotFoundException, IOException {
		prop = new Properties();
		prop.load(new FileInputStream(new File("./src/main/resources/config/config.properties")));
	}
	
	public String getProperty(String propertyKey) {
		return prop.getProperty(propertyKey);
	}
	
	/*
	 * public void setProperty(String propertyKey,String propertyValue) {
	 * 
	 * }
	 */

}
