package com.sample.utils;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("springboot-microservices")
public class PropertiesConfigReader {

	private String value; // this name needs to be same as that of springboot-microservices.value i.e  

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
