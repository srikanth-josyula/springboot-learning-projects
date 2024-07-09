package com.sample.properties;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;

@PropertySource("classpath:application.properties")
public class WelcomeProperties {

	// Dynamic Property Injection using SpEL

	@Value("#{ T(java.time.LocalDate).now() }")
	private LocalDate currentDate;

	private String welcomeMessage;

	// Getters and setters

	public LocalDate getCurrentDate() {
		return currentDate;
	}

	public void setCurrentDate(LocalDate currentDate) {
		this.currentDate = currentDate;
	}

	public String getWelcomeMessage() {
		return welcomeMessage;
	}

	public void setWelcomeMessage(String welcomeMessage) {
		this.welcomeMessage = welcomeMessage;
	}
}
