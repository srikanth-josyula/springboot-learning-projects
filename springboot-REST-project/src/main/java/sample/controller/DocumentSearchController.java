package sample.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import sample.model.Document;
import sample.service.impl.DocumentServiceImpl;

@RestController
@RequestMapping("/api/documents") // Same mapping with multiple classes work
public class DocumentSearchController {

	@Autowired
	private DocumentServiceImpl documentService;

	
	@RequestMapping(value = "/search", method = { RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public ResponseEntity<Object> handleDocumentOperations(
			@RequestBody(required = false) Document documentDetails,
			@RequestParam(value = "id", required = false) UUID documentId) {

		if (documentDetails != null) {
			// Handle POST request: Create a new document
			Document createdDocument = documentService.saveDocument(documentDetails);
			return ResponseEntity.ok(createdDocument);
		} else if (documentId != null) {
			// Handle GET request: Retrieve a document by ID
			Document document = documentService.getDocumentById(documentId);
			if (document == null) {
				return ResponseEntity.notFound().build();
			}
			return ResponseEntity.ok(document);
		} else {
			// Return bad request if neither documentDetails nor documentId is provided
			return ResponseEntity.badRequest()
					.body("Invalid request: Provide document details for POST or ID for GET.");
		}
	}

	
}
