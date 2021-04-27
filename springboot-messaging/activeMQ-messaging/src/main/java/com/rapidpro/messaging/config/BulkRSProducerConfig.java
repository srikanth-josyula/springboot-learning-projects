package com.rapidpro.messaging.config;

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
import org.springframework.context.annotation.Configuration;

@Configuration
public class BulkRSProducerConfig {

	@Autowired
	private ActiveMQConfig activemqConfig;

	@Value("${transform.queue.name}")
	private String transformQueue;

	Logger logger = LoggerFactory.getLogger(getClass());

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

}