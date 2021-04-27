package com.rapidpro.messaging.service.impl;

import java.nio.charset.Charset;
import java.util.Base64;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.rapidpro.messaging.constants.CommonConstants;
import com.rapidpro.messaging.service.AlfrescoOperations;

@Service
public class AlfrescoOperationsImpl implements AlfrescoOperations {

	@Value("${alfresco.host}")
	private String alfHost;

	@Value("${alfresco.username}")
	private String alfUsername;

	@Value("${alfresco.password}")
	private String alfPassword;

	/*
	 * @Autowired private RestTemplate restTemplateBuilder;
	 */

	private RestTemplate restTemplate() {
		return new RestTemplate();
	}

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public void performBulkImport(String targetPath, String replaceExisting, String dryRun, String sourceDirectory) {
		logger.info("Initiating the Bulk Import");
		// RestTemplate restTemplate =
		// restTemplateBuilder.basicAuthentication(alfUsername, alfPassword).build();
		HttpHeaders headers = createHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

		MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
		map.add("targetPath", targetPath);
		map.add("replaceExisting", replaceExisting);
		map.add("dryRun", dryRun);
		map.add("sourceDirectory", sourceDirectory);

		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, headers);
		restTemplate().postForLocation(alfHost + CommonConstants.BULK_IMPORT_URL, request);
	}

	public HttpHeaders createHeaders() {
		return new HttpHeaders() {
			private static final long serialVersionUID = 1L;
			{
				String auth = alfUsername + ":" + alfPassword;
				byte[] encodedAuth = Base64.getEncoder().encode(auth.getBytes(Charset.forName("US-ASCII")));
				String authHeader = "Basic " + new String(encodedAuth);
				set("Authorization", authHeader);
			}
		};
	}

	@Override
	public JSONObject getBulkImportStatus() {
		JSONObject respJson = null;
		try {
			RestTemplate restTemplate = new RestTemplate();
			ResponseEntity<String> response = restTemplate.exchange(
					alfHost + "/alfresco/s/bulk/import/status?format=json", HttpMethod.GET,
					new HttpEntity<String>(createHeaders()), String.class);
			JSONParser parser = new JSONParser();
			respJson = (JSONObject) parser.parse(response.getBody());
		} catch (Exception e) {
			e.printStackTrace();
		}

		return respJson;
	}

}
