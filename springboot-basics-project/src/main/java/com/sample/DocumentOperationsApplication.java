package com.sample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.sample.config.DocumentConfig;
import com.sample.properties.AzureStorageProperties;
import com.sample.properties.DocumentProperties;
import com.sample.service.DocumentService;

@SpringBootApplication
@EnableConfigurationProperties({ DocumentProperties.class, AzureStorageProperties.class })
public class DocumentOperationsApplication implements CommandLineRunner {

	@Value("${app.name}")
	private String appName;

	@Value("${app.description}")
	private String appDescription;

	// @Autowired on Field
	@Autowired
	private DocumentConfig docConfig;

	// @Autowire on constructor
	private final DocumentService documentService;

	@Autowired
	public DocumentOperationsApplication(DocumentConfig docConfig, DocumentService documentService) {
		this.docConfig = docConfig;
		this.documentService = documentService;
	}

	public static void main(String[] args) {
		SpringApplication.run(DocumentOperationsApplication.class, args);
	}

	// implement CommandLineRunner interface to execute code when the application
	// starts up:
	@Override
	public void run(String... args) {
		printWelcomeMessage();
	}

	public void printWelcomeMessage() {
		System.out.println("Welcome Message: ");

	}

	// Getters and setters
	public String getAppName() {
		return appName;
	}

	public String getAppDescription() {
		return appDescription;
	}

}
