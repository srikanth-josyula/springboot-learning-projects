package sample.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import sample.model.Document;
import sample.service.impl.DocumentServiceImpl;

@RestController
@RequestMapping("/api/documents") // Same mapping with multiple classes work
public class DocumentDeleteController {

	@Autowired
	private DocumentServiceImpl documentService;
	@DeleteMapping(value = "/delete/{id}")
	public ResponseEntity<Void> deleteDocument(@PathVariable String id) {
		try {
			UUID uuid = UUID.fromString(id);
			Document document = documentService.getDocumentById(uuid);
			if (document == null) {
				return ResponseEntity.notFound().build();
			}
			documentService.deleteDocument(uuid);
			return ResponseEntity.noContent().build();
		} catch (IllegalArgumentException e) {
			return ResponseEntity.badRequest().build();
		}
	}


}
