package com.sample.jms.messaging.annotationlistener;

import javax.annotation.PostConstruct;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

@Component
public class AnnotationMessageSender {

  @Autowired
  private ConnectionFactory connectionFactory;
  private JmsTemplate jmsTemplate;

  @PostConstruct
  public void init() {
      this.jmsTemplate = new JmsTemplate(connectionFactory);
  }

  public void sendMessage(String queueName, String message) {
      System.out.println("sending using @JmsListener : " + message);
      jmsTemplate.send(queueName, new MessageCreator() {
          @Override
          public Message createMessage(Session session) throws JMSException {
              return session.createTextMessage(message);
          }
      });
  }
}