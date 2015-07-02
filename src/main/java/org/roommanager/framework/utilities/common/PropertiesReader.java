package org.roommanager.framework.utilities.common;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesReader {
	
	private static Properties properties = new Properties();
	private static InputStream propertyFile = null;
	private static Properties getProperties(){
		
	if(propertyFile == null){
				try {
					propertyFile = new FileInputStream("resources\\roommanager.properties");
					properties.load(propertyFile);
					} catch (IOException ex) {
							ex.printStackTrace();
						} finally {
							try {
								propertyFile.close();
					} catch (IOException e) {				
				}
			}
		}
	return properties;
	}	
}
