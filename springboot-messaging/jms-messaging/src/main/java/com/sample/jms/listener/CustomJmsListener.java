package com.sample.jms.listener;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

public class CustomJmsListener implements MessageListener {
	
	public void onMessage(Message message) {
		if (message instanceof TextMessage) {
			try {
				String text = ((TextMessage) message).getText();
				System.out.println("received at Listener: " + text);
			} catch (JMSException e) {
				e.printStackTrace();
			}
		}
	}
}
