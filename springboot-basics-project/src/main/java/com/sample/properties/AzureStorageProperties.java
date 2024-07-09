package com.sample.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.constraints.NotNull;

@Configuration
@ConfigurationProperties(prefix = "azure.storage")
@Validated
@Profile("dev")
public class AzureStorageProperties {

	@NotNull
	private String connectionString;
	
	@NotNull
	private String containerName;

	// Getters and setters
	public String getConnectionString() {
		return connectionString;
	}

	public void setConnectionString(String connectionString) {
		this.connectionString = connectionString;
	}

	public String getContainerName() {
		return containerName;
	}

	public void setContainerName(String containerName) {
		this.containerName = containerName;
	}
}
