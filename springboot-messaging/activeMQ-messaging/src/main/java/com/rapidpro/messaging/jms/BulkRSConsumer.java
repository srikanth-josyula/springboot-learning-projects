package com.rapidpro.messaging.jms;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.QueueBrowser;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.BrowserCallback;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rapidpro.messaging.model.BulkImportModel;
import com.rapidpro.messaging.service.AlfrescoOperations;

@Component
public class BulkRSConsumer {

	Logger logger = LoggerFactory.getLogger(getClass());

	ObjectMapper mapper;

	@Autowired
	private JmsTemplate jmsTemplate;

	@Value("${transform.queue.name}")
	private String transformQueue;

	@Autowired
	AlfrescoOperations alfrescoOperations;

	public BulkRSConsumer() {
		mapper = new ObjectMapper();
	}

	public List<BulkImportModel> readMessageFromQueue() {
		final List<BulkImportModel> allMessages = new ArrayList<BulkImportModel>();
		try {
			jmsTemplate.browse(transformQueue, new BrowserCallback<TextMessage>() {
				public TextMessage doInJms(Session session, QueueBrowser browser) throws JMSException {
					@SuppressWarnings("unchecked")
					Enumeration<TextMessage> messages = browser.getEnumeration();
					while (messages.hasMoreElements()) {
						String payload = messages.nextElement().getText();
						BulkImportModel stagingMessage = null;
						try {
							stagingMessage = mapper.readValue(payload, BulkImportModel.class);
						} catch (JsonProcessingException e) {
							e.printStackTrace();
						}
						allMessages.add(stagingMessage);
					}
					return null;
				}
			});
		} catch (Exception e) {
			logger.error("error converting to person", e);
		}
		return allMessages;
	}

	public BulkImportModel receive(String id) {
		Message response = jmsTemplate.receiveSelected(transformQueue, "id = '" + id + "'");
		BulkImportModel msgResp = null;
		try {
			String payload = ((TextMessage) response).getText();
			try {
				msgResp = mapper.readValue(payload, BulkImportModel.class);
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
			logger.info("Message received : " + msgResp);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return msgResp;
	}

	@Async
	@JmsListener(destination = "${transform.queue.name}")
	public String listener(String message) {
		System.out.println("###########");
		try {
			JSONObject respJson = alfrescoOperations.getBulkImportStatus();
			logger.info("Current status of Bulk Import is 'inProgress' :" + respJson.get("inProgress").toString());
			
			if ("false".equalsIgnoreCase(respJson.get("inProgress").toString())) {
				
				BulkImportModel payload = null;
				payload = mapper.readValue(message, BulkImportModel.class);

				System.out.println("Bulk Import Started for " + payload.getBulkId());
				
				logger.info("Bulk import is idle stating the next message");
				logger.info("Reading the message " + message);
				TimeUnit.SECONDS.sleep(10);
				// consumer the message

				alfrescoOperations.performBulkImport(payload.getTargetPath(), payload.getReplaceExisting(),
						payload.getDryRun(), payload.getSourceDirectory());

				logger.info("Bulk import completed for " + payload);
				System.out.println("Bulk Import Endeded for " + payload.getBulkId());
				System.out.println();

				System.out.println("DB Insertion Started for " + payload.getBulkId());
				TimeUnit.SECONDS.sleep(10);
				System.out.println("DB Insertion Ended for " + payload.getBulkId());

			}

		} catch (Exception e1) {
			e1.printStackTrace();
		}

		System.out.println("###########");
		return message;
	}

}