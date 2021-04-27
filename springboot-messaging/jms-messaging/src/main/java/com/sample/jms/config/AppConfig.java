package com.sample.jms.config;

import static com.sample.jms.constants.Constants.LISTENER_QUEUE;

import javax.jms.ConnectionFactory;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.listener.DefaultMessageListenerContainer;
import org.springframework.jms.listener.MessageListenerContainer;

import com.sample.jms.listener.CustomJmsListener;

@EnableJms
@ComponentScan
@Configuration
public class AppConfig {

	/**
	 * CASE-1 :: Just with Default Container to send messages and we need a receiver
	 * to receive the messages
	 * 
	 **/
	@Bean
	public ConnectionFactory connectionFactory() {
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("vm://localhost");
		return connectionFactory;
	}

	/**
	 * CASE-2 :: MessageListener implementation
	 **/
	@Bean
	public MessageListenerContainer listenerContainer() {
		DefaultMessageListenerContainer container = new DefaultMessageListenerContainer();
		container.setConnectionFactory(connectionFactory());
		container.setDestinationName(LISTENER_QUEUE);
		container.setMessageListener(new CustomJmsListener());
		return container;
	}

	/**
	 * CASE-2 :: Using @JmsListener to listen JMS messages for this we need to place
	 * @EnableJms @ComponentScan as we need to scan the @ annotation
	 **/
	@SuppressWarnings("rawtypes")
	@Bean
	public JmsListenerContainerFactory jmsListenerContainerFactory() {
		DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
		factory.setConnectionFactory(connectionFactory());
		// core poll size=4 threads and max poll size 8 threads
		factory.setConcurrency("4-8");
		return factory;
	}

}