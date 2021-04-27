package com.sample.controllers;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sample.services.ChatbotUtilService;

@RestController
@RequestMapping("/bot")
public class BotRequestController {

	@Autowired
	ChatbotUtilService chatbot;

	@SuppressWarnings("unchecked")
	@GetMapping(path = "/send/{message}")
	public JSONObject getBotResponse(@PathVariable String message) {
		JSONObject responseObject = new JSONObject();
		try {
			responseObject.put("response", chatbot.getBotResponseforRest(message));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return responseObject;

	}

}
