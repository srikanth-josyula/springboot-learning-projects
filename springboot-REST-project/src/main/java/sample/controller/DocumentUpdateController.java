package sample.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
public class DocumentUpdateController {

	@Autowired
	private DocumentServiceImpl documentService;

	// http://localhost:8080/documents/update?uuid=123e4567-e89b-12d3-a456-426614174000
	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	public ResponseEntity<Document> updateDocument(@RequestParam("id") UUID uuid,
			@RequestBody Document documentDetails) {
		try {
			Document document = documentService.getDocumentById(uuid);
			if (document == null) {
				return ResponseEntity.notFound().build();
			}
			document.setTitle(documentDetails.getTitle());
			document.setName(documentDetails.getName());
			Document updatedDocument = documentService.saveDocument(document);
			return ResponseEntity.ok(updatedDocument);
		} catch (IllegalArgumentException e) {
			return ResponseEntity.badRequest().body(null);
		}
	}

	/**
	 * Partially updates an existing document.
	 *
	
	@PatchMapping("/update")
	public ResponseEntity<Document> updateDocumentProperty(@RequestParam(name = "id") UUID uuid,
			@RequestBody Document documentUpdates) {
		Document existingDocument = documentService.getDocumentById(uuid);
		if (existingDocument == null) {
			return ResponseEntity.notFound().build();
		}

		// Apply updates to existing document
		if (documentUpdates.getTitle() != null) {
			existingDocument.setTitle(documentUpdates.getTitle());
		}
		if (documentUpdates.getName() != null) {
			existingDocument.setName(documentUpdates.getName());
		}
		// Save updated document
		Document updatedDocument = documentService.saveDocument(existingDocument);
		return ResponseEntity.ok(updatedDocument);
	}

	@PatchMapping("/update")
	public ResponseEntity<Document> updateDocumentProperty(@RequestParam(name = "id") UUID uuid,
			@RequestParam int version) {

		// Validate the version if necessary
		if (version <= 0) {
			return ResponseEntity.badRequest().body(null);
		}

		// Retrieve the existing document by ID
		Document existingDocument = documentService.getDocumentById(uuid);
		if (existingDocument == null) {
			return ResponseEntity.notFound().build();
		}

		// Save the updated document
		Document updatedDocument = documentService.saveDocument(existingDocument);
		return ResponseEntity.ok(updatedDocument);
	} */
}
