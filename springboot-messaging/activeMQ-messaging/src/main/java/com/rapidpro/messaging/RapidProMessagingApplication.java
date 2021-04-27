package com.rapidpro.messaging;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class RapidProMessagingApplication {

  public static void main(String[] args) {
    SpringApplication.run(RapidProMessagingApplication.class, args);
  }
}