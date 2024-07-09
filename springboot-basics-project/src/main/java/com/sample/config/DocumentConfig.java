package com.sample.config;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;

import com.sample.model.DocumentDetails;
import com.sample.properties.DocumentProperties;

@Configuration
@Import({ DocumentCloudStorageConfig.class })
public class DocumentConfig {

	private final DocumentProperties documentProperties;

    public DocumentConfig(DocumentProperties documentProperties) {
        this.documentProperties = documentProperties;
    }
	
	@Bean(name = "createCloudConnection")
	public void CreateCloudConnection() {
		System.out.println("Cloud Connection Established");
	}

	/**
	 * @Bean Usage
	 * @Scope Usage
	 **/
	// Bean for DocumentDetails
	// multiple beans of the same type within the same configuration class
	@Lazy
	@DependsOn("createCloudConnection")
	@Bean(name = "cloudDocumentDetails")
	public DocumentDetails cloudDocumentDetails() {
		System.out.println("Cloud Document Details");
		return new DocumentDetails();
	}


	@Bean(name = "localDocumentDetails")
	// @Scope("prototype")
	@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
	public DocumentDetails localDocumentDetails() {
		System.out.println("Local Document Details");
		return new DocumentDetails();
	}

}
