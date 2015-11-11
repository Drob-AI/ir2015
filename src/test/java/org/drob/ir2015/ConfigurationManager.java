package org.drob.ir2015;

import java.io.IOException;
import java.util.Properties;

public final class ConfigurationManager {
	private static final String ConfigLoadError = "Could not load config.properties";
	private static final String KeysLoadError = "Could not load config.properties";
	private static final Properties properties = new Properties();
	private static final Properties keys = new Properties();
	
	private static ExceptionInInitializerError createConfigError(IOException error, String message) {
		ExceptionInInitializerError configLoadError = 
				new ExceptionInInitializerError(message);
		configLoadError.addSuppressed(error);
		return configLoadError;
	}
	
	static {
		try {
			ClassLoader loader = Thread.currentThread().getContextClassLoader();
            properties.load(loader.getResourceAsStream("org/drob/ir2015/config.properties"));
		} catch (IOException e) {
			throw createConfigError(e, ConfigLoadError);
        }
		
		String keyConfigFile = getSetting("ir2015.keyConfig");
		
		try {
			ClassLoader loader = Thread.currentThread().getContextClassLoader();
			keys.load(loader.getResourceAsStream(keyConfigFile));
		} catch (IOException e) {
			throw createConfigError(e, KeysLoadError);
		}
	}
	
	public static String getSetting(String key) {
        return properties.getProperty(key);
    }
	
	public static String getKey(String key) {
		return keys.getProperty(key);
	}
}
