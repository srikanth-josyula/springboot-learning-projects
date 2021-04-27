package com.sample.jms.listener;

import static com.sample.jms.constants.Constants.ANNOTATE_LISTENER_QUEUE;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class AnnotationListener {
  @JmsListener(destination = ANNOTATE_LISTENER_QUEUE)
  public void handleMessage(String message) {//implicit message type conversion
      System.out.println("received using @JmsListener : "+message);
  }
}