package com.sample.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.sample.model.DocumentDetails;
import com.sample.repository.DocumentRepository;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

@Service
public class DocumentService {

	private final DocumentRepository documentRepository;
	private final ApplicationContext context;

	@Autowired
	public DocumentService(DocumentRepository documentRepository, ApplicationContext context) {
		this.documentRepository = documentRepository;
		this.context = context;
	}

	/**
	 * @Qualifier Usages
	 **/

	private DocumentDetails cloudDocumentDetails;
	private DocumentDetails localDocumentDetails;

	@Autowired
	public void setCloudDocumentDetails(@Qualifier("cloudDocumentDetails") DocumentDetails cloudDocumentDetails) {
		this.cloudDocumentDetails = cloudDocumentDetails;
	}

	@Autowired
	public void setLocalDocumentDetails(@Qualifier("localDocumentDetails") DocumentDetails localDocumentDetails) {
		this.localDocumentDetails = localDocumentDetails;
	}

	/**
	 * @PostConstruct Usage
	 * @PreDestroy Usage
	 **/
	@PostConstruct
	public void init() {
		System.out.println("DocumentService initialized");
		// Additional initialization code
	}

	public void useDocumentDetails() {
		System.out.println("Using Guest Document Details: " + cloudDocumentDetails.hashCode());
		System.out.println("Using Admin Document Details: " + localDocumentDetails.hashCode());

		// Demonstrating prototype scope by requesting new instances from the context
		DocumentDetails newGuestDocumentDetails = (DocumentDetails) context.getBean("guestDocumentDetails");
		DocumentDetails newAdminDocumentDetails = (DocumentDetails) context.getBean("adminDocumentDetails");

		System.out.println("New instance of Guest Document Details: " + newGuestDocumentDetails.hashCode());
		System.out.println("New instance of Admin Document Details: " + newAdminDocumentDetails.hashCode());
	}

	@PreDestroy
	public void cleanup() {
		System.out.println("DocumentService cleanup");
		// Additional cleanup code
	}
}
