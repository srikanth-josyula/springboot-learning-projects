package com.sample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.sample.services.ChatbotUtilService;

@SpringBootApplication
@EnableAutoConfiguration
public class ChatBotApplication{

	@Autowired
	ChatbotUtilService chatbot;

	public static void main(String[] args) {
		SpringApplication.run(ChatBotApplication.class, args);
	}

	/*public void run(String... args) throws Exception {
		chatbot.getBotResponseforConsole();
	}*/
}
