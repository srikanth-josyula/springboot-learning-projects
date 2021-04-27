package com.sample.jms.messaging.jmstemplate;

import java.util.Enumeration;

import javax.annotation.PostConstruct;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Queue;
import javax.jms.QueueBrowser;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
public class JMSMessageReceiver {

	@Autowired
	private ConnectionFactory connectionFactory;
	private JmsTemplate jmsTemplate;

	@PostConstruct
	public void init() {
		this.jmsTemplate = new JmsTemplate(connectionFactory);
	}

	public void receiveMessage(String queueName) {
		Message message = jmsTemplate.receive(queueName);
		TextMessage textMessage = (TextMessage) message;
		try {
			String text = textMessage.getText();
			System.out.println("received: " + text);
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	public int getNumMessages(String jmsQueue) throws JMSException {
		Connection connection = null;
		Session session = null;
		QueueBrowser browser = null;

		try {
			connection = connectionFactory.createConnection();
			connection.start();
			session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			Queue queue=session.createQueue(jmsQueue);
			browser = session.createBrowser(queue);
			Enumeration<Message> messages = browser.getEnumeration();
			int actualNum = 0;
			while (messages.hasMoreElements()) {
				actualNum++;
				messages.nextElement();
			}
			return actualNum;
		} finally {
			try {
				if (browser != null)
					browser.close();
			} catch (JMSException e1) {
			}
			try {
				if (session != null)
					session.close();
			} catch (JMSException e1) {
			}
			try {
				if (connection != null)
					connection.close();
			} catch (JMSException e1) {
			}
		}

	}
}