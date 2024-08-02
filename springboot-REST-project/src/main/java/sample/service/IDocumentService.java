package sample.service;

import java.util.List;
import java.util.UUID;

import sample.model.Document;

public interface IDocumentService {
	public List<Document> getAllDocuments();

	public Document getDocumentById(UUID id);

	public Document saveDocument(Document document);

	public void deleteDocument(UUID id);

	List<Document> findDocumentsByFilename(String filenamePattern);
}
