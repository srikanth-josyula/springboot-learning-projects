package sample.model;

import java.util.Date;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class Document {

	@JsonProperty("doc_id")
	private UUID id;

	@JsonProperty("doc_name")
	@NotNull
	private String name;

	@JsonProperty("doc_title")
	private String title;

	@JsonProperty("created_by")
	private String createdBy;

	@JsonProperty("version")
	@Min(value = 1, message = "Version must be at least 1")
    @Max(value = 2, message = "Version must be at most 2")
	private Integer version;

	@JsonProperty("created_date")
	private Date createdDate;

	@JsonProperty("document_content")
	private String documentContent;

	@JsonProperty("metadata")
	private sample.model.Metadata metadata;

	@JsonProperty("content_type")
	private String contentType; // Indicates if content is binary or base64

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getDocumentContent() {
		return documentContent;
	}

	public void setDocumentContent(String documentContent) {
		this.documentContent = documentContent;
	}

	public Metadata getMetadata() {
		return metadata;
	}

	public void setMetadata(Metadata metadataJson) {
		this.metadata = metadataJson;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
}
