package com.rapidpro.messaging.jms;

import java.util.Set;

import javax.jms.JMSException;
import javax.jms.Queue;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.advisory.DestinationSource;
import org.apache.activemq.command.ActiveMQQueue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import com.rapidpro.messaging.config.ActiveMQConfig;

@Component
public class BulkRSProducer {

	Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private ActiveMQConfig activemqConfig;

	@Autowired
	private JmsTemplate jmsTemplate;

	@Value("${transform.queue.name}")
	private String transformQueue;

	@Bean
	public Queue quque() {
		Queue queue = null;
		try {
			if (existDataQueue(transformQueue) == null) {
				logger.info("The Queue " + transformQueue + " does not exist creating a new Queue");
				queue = new ActiveMQQueue(transformQueue);
			} else {
				queue = existDataQueue(transformQueue);
			}

		} catch (JMSException e) {
			e.printStackTrace();
		}
		System.out.println(queue);
		return queue;
	}

	public ActiveMQQueue existDataQueue(String dataQueue) throws JMSException {
		ActiveMQQueue queue = null;
		ActiveMQConnection connection = (ActiveMQConnection) activemqConfig.activeMQConnectionFactory()
				.createConnection();
		connection.start();

		DestinationSource ds = connection.getDestinationSource();
		Set<ActiveMQQueue> queues = ds.getQueues();

		for (ActiveMQQueue activeMQQueue : queues) {
			try {
				if (activeMQQueue.getQueueName().equalsIgnoreCase(dataQueue)) {
					queue = activeMQQueue;
				}
			} catch (JMSException e) {
				e.printStackTrace();
			}
		}
		connection.close();
		return queue;
	}

	public void send(String message, String id) {

		try {
			logger.info("sending message='{}'", message);
			// jmsTemplate.convertAndSend(queue, message);
			jmsTemplate.convertAndSend(quque(), message, messagePostProcessor -> {
				messagePostProcessor.setStringProperty("id", id);
				return messagePostProcessor;
			});

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}