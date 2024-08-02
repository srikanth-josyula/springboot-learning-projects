package sample.controller;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import sample.model.Document;
import sample.service.impl.DocumentServiceImpl;

@RestController
@RequestMapping("/api/documents") // Same mapping with multiple classes work
public class DocumentDownloadController {

	@Autowired
	private DocumentServiceImpl documentService;

	/**
	 * Example cURL commands:
	 * 
	 * 1. Request with content included: curl -X GET
	 * "http://localhost:8080/documents/{id}?content=true"
	 * 
	 * 2. Request without content (or with content=false): curl -X GET
	 * "http://localhost:8080/documents/{id}"
	
	@GetMapping("/{id}")
	public ResponseEntity<Document> getDocumentById(@PathVariable String id,
			@RequestParam(required = false, defaultValue = "false") boolean content) {
		try {
			UUID uuid = UUID.fromString(id);
			Document document = documentService.getDocumentById(uuid);
			if (document == null) {
	            // Throw an exception with a 404 Not Found status
	            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Document not found");
	        }

			if (content) {
				return ResponseEntity.ok(document);
			} else {
				Document responseDocument = new Document(document.getId(), document.getTitle(), document.getName(),
						document.getCreatedBy(), document.getVersion(), document.getCreatedDate(), null, // documentContent
						null // binaryContent
				);
				return ResponseEntity.ok(responseDocument);
			}
		} catch (IllegalArgumentException e) {
			// This exception is thrown if the string cannot be parsed as a UUID
			return ResponseEntity.badRequest().body(null);
		}
	} 
	
	@GetMapping("/download")
	public ResponseEntity<byte[]> downloadFile() {
	    byte[] fileContent = null;
	    HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
	    headers.setContentDispositionFormData("attachment", "filename.ext");
	    return new ResponseEntity<>(fileContent, headers, HttpStatus.OK);
	}

	
	/**
	 * Example cURL command:
	 *
	 * curl -X GET "http://localhost:8080/documents/{filename}"
	 *
	 * Replace `{filename}` with the actual filename or filename pattern you want to search for.
	 * For example, to search for documents with the filename pattern "report":
	 * curl -X GET "http://localhost:8080/documents/report"
	 */
	@RequestMapping(value = "/{filename:[\\w\\d]*}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<List<Document>> getDocumentsByCreatorAndFilename(@PathVariable("filename") String filename) {

		// Logic to retrieve documents by creator and filename pattern
		List<Document> documents = documentService.findDocumentsByFilename(filename);

		if (documents.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(documents);
	}

	/**
	 * Example cURL commands:
	 *
	 * 1. Request with all parameters:
	 * curl -X GET "http://localhost:8080/documents/{creator}/{filename}?version=v1&created_date=true"
	 *
	 * 2. Request without created_date:
	 * curl -X GET "http://localhost:8080/documents/{creator}/{filename}?version=v1"
	 *
	 * Replace `{creator}` and `{filename}` with actual values. For example:
	 * curl -X GET "http://localhost:8080/documents/johnDoe/report?version=v1&created_date=true"
	 */
	@GetMapping("/{creator}/{filename}")
	@ResponseBody
	public ResponseEntity<List<Document>> getDocumentsByAllCondition(
	        @PathVariable Map<String, String> pathVarsMap,
	        @RequestParam Map<String, String> queryParams) {

	    String creator = pathVarsMap.get("creator");
	    String filename = pathVarsMap.get("filename");
	    String version = queryParams.get("version");
	    String createdDate = queryParams.get("created_date");

	    // Implement logic to build query based on available parameters
	    List<Document> documents;
	    if (creator != null && filename != null && version != null) {
	        // Build query based on creator, filename, and version
	        documents = documentService.findDocumentsByAllConditions(creator, filename, version, createdDate != null);
	    } else {
	        return ResponseEntity.badRequest().body(null); // Handle missing parameters or invalid requests
	    }

	    return documents.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(documents);
	}
	// Version 1: Basic retrieval of all documents
	@RequestMapping(value = "/all", headers = "X-API-Version=1")
	@ResponseBody
	public List<Document> getAllDocumentsV1() {
		return documentService.getAllDocuments();
	}

	// curl -H "X-API-Version: 2" http://localhost:8080/api/documents/all
	// Version 2: Enhanced retrieval of all documents
	@RequestMapping(value = "/all", headers = "X-API-Version=2")
	@ResponseBody
	public ResponseEntity<List<Document>> getAllDocumentsV2() {
		// TODO: Implement pagination and sorting for improved retrieval
		// TODO: Add filtering capabilities (e.g., by title or name)
		// TODO: Implement additional security checks (e.g., user authorization)
		// TODO: Optimize performance for large datasets (e.g., caching)
		// TODO: Include metadata in response (e.g., total count, request timestamp)

		List<Document> documents = documentService.getAllDocuments();
		return ResponseEntity.ok(documents);
	}

}
