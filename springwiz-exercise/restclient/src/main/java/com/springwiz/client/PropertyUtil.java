package com.springwiz.client;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertyUtil {
	
	private static Properties properties = new Properties();
	
	public static void init() throws Exception {
		FileInputStream file;
	    String path = "./client.properties";
	    file = new FileInputStream(path);

	    try {
			properties.load(file);
		} finally {
			try {
				file.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static String getProperty(String key) {
		return properties.getProperty(key);
	}
}
