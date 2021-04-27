package com.sample.jwt.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin()
public class SampleController {

	@RequestMapping({ "/admin" })
	public String admin() {
		return "Hello Admin";
	}
	
	@RequestMapping({ "/user" })
	public String user() {
		return "Hello User";
	}

}
