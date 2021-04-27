package com.sample.com.sample.service.impl;

import org.json.XML;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.sample.com.sample.exception.CustomGenericException;
import com.sample.com.sample.service.AlfrescoOperationService;

@Service
public class AlfrescoOperationServiceImpl implements AlfrescoOperationService  {

	@Value("${alf.host}")
	private String alfreso_host;

	@Autowired
	RestTemplate restTemplate;

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public String getAlfrescoTicket(String userName, String password) throws CustomGenericException {
		String alf_ticket = null;
		try {
			HttpHeaders headers = new HttpHeaders();
			HttpEntity<String> entity = new HttpEntity<String>(headers);
			String response = restTemplate
					.exchange(alfreso_host + "/alfresco/service/api/login?u=" + userName + "&pw=" + password,
							HttpMethod.GET, entity, String.class)
					.getBody();
			alf_ticket = XML.toJSONObject(response).getString("ticket");
		} catch (Exception e) {
			logger.error("Exception occured in parsing:", e);
			throw new CustomGenericException(e.getLocalizedMessage());
		}
		return alf_ticket;
	}

	
}
