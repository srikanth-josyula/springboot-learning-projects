package com.sample.repository.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import com.sample.model.DocumentDetails;
import com.sample.repository.DocumentRepository;


/**
 * @Repository Usage
 * @Primary Usage
 **/
@Repository
@Primary
public class CloudDocumentRepository implements DocumentRepository {

    private final Map<Long, DocumentDetails> documentMap = new HashMap<>();

    @Override
    public void upload(DocumentDetails document) {
        // Dummy implementation: Log message and update in-memory map
        System.out.println("Uploading document: " + document.getFilename());
        documentMap.put(document.getId(), document);
    }

    @Override
    public void download(Long documentId) {
        // Dummy implementation: Log message for downloading
        System.out.println("Downloading document with ID: " + documentId);
    }

    @Override
    public List<DocumentDetails> search(String keyword) {
        // Dummy implementation: Search in in-memory map (simplified for illustration)
        List<DocumentDetails> results = new ArrayList<>();
        for (DocumentDetails document : documentMap.values()) {
            if (document.getFilename().contains(keyword)) {
                results.add(document);
            }
        }
        return results;
    }

    @Override
    public void update(DocumentDetails document) {
        // Dummy implementation: Update in-memory map
        if (documentMap.containsKey(document.getId())) {
            documentMap.put(document.getId(), document);
            System.out.println("Document updated: " + document.getFilename());
        } else {
            System.out.println("Document not found for update with ID: " + document.getId());
        }
    }
}