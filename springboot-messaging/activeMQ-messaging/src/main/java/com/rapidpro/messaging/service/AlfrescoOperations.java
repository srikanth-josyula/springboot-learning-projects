package com.rapidpro.messaging.service;

import org.json.simple.JSONObject;

public interface AlfrescoOperations {
	public void performBulkImport(String targetPath, String replaceExisting, String dryRun, String sourceDirectory);
	public JSONObject getBulkImportStatus();
}
