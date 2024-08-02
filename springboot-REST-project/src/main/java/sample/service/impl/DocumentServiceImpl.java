package sample.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import sample.model.Document;
import sample.service.IDocumentService;

@Service
public class DocumentServiceImpl implements IDocumentService {
    private Map<UUID, Document> documentStorage = new HashMap<>();

    @Override
    public List<Document> getAllDocuments() {
        return new ArrayList<>(documentStorage.values());
    }

    @Override
    public Document getDocumentById(UUID id) {
        return documentStorage.get(id);
    }

    @Override
    public Document saveDocument(Document document) {
        if (document.getId() == null) {
            document.setId(UUID.randomUUID());
        }
        // Ensure createdBy is set if not provided (could be set to a default value or required to be set by the caller)
        if (document.getCreatedBy() == null) {
            throw new IllegalArgumentException("Document must have a creator.");
        }
        documentStorage.put(document.getId(), document);
        return document;
    }

    @Override
    public void deleteDocument(UUID id) {
        documentStorage.remove(id);
    }
    
    @Override
    public List<Document> findDocumentsByFilename(String filenamePattern) {
        return documentStorage.values().stream()
            .filter(doc -> doc.getName() != null && doc.getName().contains(filenamePattern))
            .collect(Collectors.toList());
    }

	public List<Document> findDocumentsByAllConditions(String creator, String filename, String version, boolean b) {
		// TODO Auto-generated method stub
		return null;
	}

	public void saveDocumentBinary(Document document) {
		// TODO Auto-generated method stub
		
	}
}
