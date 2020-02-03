package com.project.university.dao;

import java.io.InputStream;
import java.util.Properties;

public class Settings {
	
	private final Properties properties = new Properties();
	
	public void load(InputStream inputStream) {
		try {
			this.properties.load(inputStream);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public String getValue(String key) {
		return this.properties.getProperty(key);
	}
}

