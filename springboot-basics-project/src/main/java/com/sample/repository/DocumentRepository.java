package com.sample.repository;

import java.util.List;

import com.sample.model.DocumentDetails;

public interface DocumentRepository {

    void upload(DocumentDetails document);

    void download(Long documentId);

    List<DocumentDetails> search(String keyword);

    void update(DocumentDetails document);
}