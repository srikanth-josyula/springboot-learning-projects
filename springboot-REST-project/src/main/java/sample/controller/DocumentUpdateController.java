package sample.controller;

import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import sample.model.Document;
import sample.model.Metadata;
import sample.service.impl.DocumentServiceImpl;

@RestController
@RequestMapping("/api/documents") // Same mapping with multiple classes work
public class DocumentUpdateController {

	@Autowired
	private DocumentServiceImpl documentService;

	// http://localhost:8080/documents/update?uuid=123e4567-e89b-12d3-a456-426614174000
	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	public ResponseEntity<Document> updateDocument(
			@RequestParam("id") UUID uuid,
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
	 * "http://localhost:5501/api/documents/update/metadata?id=f05b639c-6e8d-4999-970e-fa7fdfd71639&version=1"
	 * Body JSON
	 * {
	 * 	  "metadata": {
	 * 	    "propertyKey1": "value2",
	 * 	    "propertyKey2": "value2"
	 * 	  }
	 * 	}
	 **/

	@PatchMapping("/update/metadata")
	public ResponseEntity<Document> updateDocumentProperty(
			@RequestParam(name = "id") UUID uuid,
			@RequestBody Map<String, Object> requestBody) {

		if (uuid == null) {
			return ResponseEntity.badRequest().body(null); // Bad request if no id is provided
		}

		Document existingDocument = documentService.getDocumentById(uuid);

		if (requestBody != null) {

			@SuppressWarnings("unchecked")
			Map<String, Object> metadataMap = (Map<String, Object>) requestBody.get("metadata");
			Metadata metadata = new Metadata();

			if (metadataMap.containsKey("propertyKey1")) {
				metadata.setPropertyKey1((String) metadataMap.get("propertyKey1"));
			}
			if (metadataMap.containsKey("propertyKey2")) {
				metadata.setPropertyKey2((String) metadataMap.get("propertyKey2"));
			}

			if (metadata != null) {
				existingDocument.setMetadata(metadata);
			}
		}

		// Save the updated document
		Document updatedDocument = documentService.saveDocument(existingDocument);
		return ResponseEntity.ok(updatedDocument); // Return the updated document with HTTP 200 OK
	}

	/**
	 * "http://localhost:5501/api/documents/update/version?id={id}&version={version}"
	 **/
	@PatchMapping("/update/version")
	public ResponseEntity<Document> updateDocumentProperty(
			@RequestParam(name = "id") UUID uuid,
			@RequestParam int version) //without giving name/value it will pick if same name
	{

		// Validate the version if necessary
		if (version <= 0) {
			return ResponseEntity.badRequest().body(null);
		}

		// Retrieve the existing document by ID
		Document existingDocument = documentService.getDocumentById(uuid);
		if (existingDocument == null) {
			return ResponseEntity.notFound().build();
		}

		existingDocument.setVersion(version);
		// Save the updated document
		Document updatedDocument = documentService.saveDocument(existingDocument);
		return ResponseEntity.ok(updatedDocument);
	}
}
