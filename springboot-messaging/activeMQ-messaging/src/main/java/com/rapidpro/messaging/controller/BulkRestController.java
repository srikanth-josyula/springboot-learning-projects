package com.rapidpro.messaging.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rapidpro.messaging.jms.BulkRSConsumer;
import com.rapidpro.messaging.jms.BulkRSProducer;
import com.rapidpro.messaging.model.BulkImportModel;
import com.rapidpro.messaging.service.AlfrescoOperations;

@RestController
@RequestMapping("/api/bulk-rs")
public class BulkRestController {

	Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	BulkRSProducer rsProducer;

	@Autowired
	BulkRSConsumer rsConsumer;

	@Autowired
	AlfrescoOperations alfrescoOperations;

	@PostMapping(value = "/transformer/send", produces = MediaType.APPLICATION_JSON_VALUE)
	public BulkImportModel publishStagingMessage(@RequestBody BulkImportModel message, HttpServletRequest request) {
		try {

			ObjectMapper mapper = new ObjectMapper();
			message.setURL(request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
					+ "/api/bulk-rs/bulk/import/status");
			String stagingMessageAsJson = mapper.writeValueAsString(message);
			logger.debug("Message Received for Publishing " + stagingMessageAsJson);
			rsProducer.send(stagingMessageAsJson, message.getBulkId().toString());

			// get the status of the import

			/*
			 * if (!isImportRunning) { performBulkImport(); }
			 */

		} catch (Exception e) {
			e.printStackTrace();
		}
		return message;
	}

	@PostMapping(value = "/bulk/import/initiate", produces = MediaType.APPLICATION_JSON_VALUE)
	public void performBulkImport() {

		JSONObject respJson = alfrescoOperations.getBulkImportStatus();
		logger.info("Current status of Bulk Import is 'inProgress' :" + respJson.get("inProgress").toString());
		if ("false".equalsIgnoreCase(respJson.get("inProgress").toString())) {
			List<BulkImportModel> allPendingMsgs = rsConsumer.readMessageFromQueue();

			if (allPendingMsgs.size() > 0) {
				logger.info("Total No.of messages in queue for bulk Import are " + allPendingMsgs.size());
				for (BulkImportModel message : allPendingMsgs) {

					System.out.println("###################");

					if ("false".equalsIgnoreCase(respJson.get("inProgress").toString())) {
						logger.info("Bulk import is idle stating the next message");
						logger.info("Reading the message " + message);

						// consumer the message
						BulkImportModel model = rsConsumer.receive(message.getBulkId());
						alfrescoOperations.performBulkImport(model.getTargetPath(), model.getReplaceExisting(),
								model.getDryRun(), model.getSourceDirectory());
						
						// response will the DB

						logger.info("Bulk import completed for " + message);
					}

				}
			} else {
				logger.info("No new messages in Queue will terminate now");
			}
		}
	}

}
