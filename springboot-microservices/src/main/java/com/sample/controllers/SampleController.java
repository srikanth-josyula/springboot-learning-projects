package com.sample.controllers;

import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sample.POJOs.CustomDate;
import com.sample.model.PropertiesReader;
import com.sample.services.SampleService;
import com.sample.utils.PropertiesConfigReader;

@RestController
@RequestMapping("/sample")
public class SampleController {

	@Autowired
	SampleService sampleServiceImpl;

	@Autowired
	private MessageSource messageSource;

	@Autowired
	PropertiesConfigReader propReader;

	/**
	 * GET http://localhost:8181/sample/show-internaltional-greetings REqUEST HEADER
	 * :: Accept-Language - fr/nl/en
	 **/
	@GetMapping(path = "/show-internaltional-greetings")
	public String sampleInternationalized() {
		return messageSource.getMessage("good.morning.message", null, LocaleContextHolder.getLocale());
	}

	@RequestMapping("/getdate")
	public String printDate() {
		Date date = new Date();
		return date.toString();
	}

	@GetMapping("/getCalendar")
	public CustomDate getTodayCalendar() {
		Calendar c = Calendar.getInstance();
		return new CustomDate(c);
	}

	@RequestMapping(value = "/showmessage/{msg}", method = RequestMethod.GET)
	public String printMessage(@PathVariable String msg) {
		return sampleServiceImpl.getMessage(msg);
	}

	@GetMapping("/getPropValue")
	public PropertiesReader getPropValue() {
		PropertiesReader configValue = new PropertiesReader(propReader.getValue());
		return configValue;
	}
}
