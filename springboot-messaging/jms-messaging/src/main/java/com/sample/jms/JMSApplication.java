package com.sample.jms;

import static com.sample.jms.constants.Constants.JMS_QUEUE;
import static com.sample.jms.constants.Constants.LISTENER_QUEUE;
import static com.sample.jms.constants.Constants.ANNOTATE_LISTENER_QUEUE;


import javax.jms.JMSException;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.jms.listener.DefaultMessageListenerContainer;

import com.sample.jms.messaging.annotationlistener.AnnotationMessageSender;
import com.sample.jms.messaging.jmstemplate.JMSMessageReceiver;
import com.sample.jms.messaging.jmstemplate.JMSMessageSender;
import com.sample.jms.messaging.messagelistener.SimpleMessageSender;

@ComponentScan
public class JMSApplication {

	public static void main(String[] args) throws InterruptedException {
		@SuppressWarnings("resource")
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(JMSApplication.class);

		/**
		 * CASE-1 Sending and Receiving messages with JmsTemplate
		 **/
		try {
			JMSMessageSender ms = context.getBean(JMSMessageSender.class);
			JMSMessageReceiver mr = context.getBean(JMSMessageReceiver.class);
			ms.sendMessage(JMS_QUEUE, "test message");
			// get the Count of Messages
			System.out.println("QUEUE SIZE ::" + mr.getNumMessages(JMS_QUEUE));
			mr.receiveMessage(JMS_QUEUE);
		} catch (JMSException e) {
			e.printStackTrace();
		}

		/**
		 * CASE-2 Using a MessageListener to receive messages and JmsTemplate to send
		 * messages
		 **/
		SimpleMessageSender sender = context.getBean(SimpleMessageSender.class);
		sender.sendMessage(LISTENER_QUEUE, "test message for listener");

		/**
		 * CASE-3 Using @JmsListener to listen JMS messages
		 **/
		AnnotationMessageSender annotatesender = context.getBean(AnnotationMessageSender.class);
		annotatesender.sendMessage(ANNOTATE_LISTENER_QUEUE, "test message for @JmsListener");
		
		
		System.out.println("-- shutting down listener container --");
		DefaultMessageListenerContainer container = context.getBean(DefaultMessageListenerContainer.class);
		container.shutdown();
	}
}