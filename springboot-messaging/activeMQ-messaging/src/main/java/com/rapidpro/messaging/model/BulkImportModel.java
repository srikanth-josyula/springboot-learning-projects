package com.rapidpro.messaging.model;

import java.io.Serializable;

public class BulkImportModel implements Serializable {

	private static final long serialVersionUID = 1L;

	private String targetPath; // mandatory
	private String sourceDirectory; // mandatory
	private String replaceExisting;
	private String dryRun;
	private String URL; // attached from RS code
	private String bulkId;
	private String status; // will be part of response not request
	private String username; // Required Username which can be masked while import runs

	private String message; // Required for any exception from response
	private String messageLong; // Required for any exception trace from response

	public BulkImportModel() {
		super();
	}

	public BulkImportModel(String targetPath, String sourceDirectory, String replaceExisting, String dryRun, String uRL,
			String bulkId, String status, String username, String message, String messageLong) {
		super();
		this.targetPath = targetPath;
		this.sourceDirectory = sourceDirectory;
		this.replaceExisting = replaceExisting;
		this.dryRun = dryRun;
		URL = uRL;
		this.bulkId = bulkId;
		this.status = status;
		this.username = username;
		this.message = message;
		this.messageLong = messageLong;
	}

	public String getTargetPath() {
		return targetPath;
	}

	public void setTargetPath(String targetPath) {
		this.targetPath = targetPath;
	}

	public String getSourceDirectory() {
		return sourceDirectory;
	}

	public void setSourceDirectory(String sourceDirectory) {
		this.sourceDirectory = sourceDirectory;
	}

	public String getReplaceExisting() {
		return replaceExisting;
	}

	public void setReplaceExisting(String replaceExisting) {
		this.replaceExisting = replaceExisting;
	}

	public String getDryRun() {
		return dryRun;
	}

	public void setDryRun(String dryRun) {
		this.dryRun = dryRun;
	}

	public String getURL() {
		return URL;
	}

	public void setURL(String uRL) {
		URL = uRL;
	}

	public String getBulkId() {
		return bulkId;
	}

	public void setBulkId(String bulkId) {
		this.bulkId = bulkId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getMessageLong() {
		return messageLong;
	}

	public void setMessageLong(String messageLong) {
		this.messageLong = messageLong;
	}

	@Override
	public String toString() {
		return "ImportModel [targetPath=" + targetPath + ", sourceDirectory=" + sourceDirectory + ", replaceExisting="
				+ replaceExisting + ", dryRun=" + dryRun + ", URL=" + URL + ", bulkId=" + bulkId + ", status=" + status
				+ ", username=" + username + ", message=" + message + ", messageLong=" + messageLong + "]";
	}

}