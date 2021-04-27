package com.sample.constants;

import java.io.File;
import java.io.FileInputStream;
import java.util.Locale;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

public class CommonConstants {

	//public static Locale locale = new Locale("en", "US");
	//public static ResourceBundle resourceBundle = ResourceBundle.getBundle("application", locale);

	public static String bundleFilename = "application.properties";
	public static String bundlePath = System.getProperty("config.location");
	public static ResourceBundle resourceBundle = null;
	
	static {
		try {
			resourceBundle = new PropertyResourceBundle(
					new FileInputStream(bundlePath + File.separator + bundleFilename));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static final String alffunctionalUrl = resourceBundle.getString("alffunctional.url");
	public static final String adffunctionalUrl = resourceBundle.getString("adffunctional.url");
	public static final String alffunctionalUsername = resourceBundle.getString("alffunctional.username");
	public static final String alffunctionalPassword = resourceBundle.getString("alffunctional.password");

}
