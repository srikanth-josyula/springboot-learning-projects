package com.sample.config;

import org.springframework.context.annotation.Bean;

import com.sample.properties.AzureStorageProperties;

public class DocumentCloudStorageConfig {
	
	
	private final AzureStorageProperties azureStorageProperties;

    public DocumentCloudStorageConfig(AzureStorageProperties azureStorageProperties) {
        this.azureStorageProperties = azureStorageProperties;
    }

    @Bean
    public String blobContainerClient() {
        System.out.println("Initializing BlobContainerClient with connection string: " + azureStorageProperties.getConnectionString() + " and container name: " + azureStorageProperties.getContainerName());
        return "BlobContainerClient"; // Replace with actual BlobContainerClient initialization
    }
}
