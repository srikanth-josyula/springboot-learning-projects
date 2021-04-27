package com.sample.security.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SampleController {

	@RequestMapping("/admin")
	public String admin() {
		return "Hello Admin";
	}
	
	@RequestMapping("/user")
	public String user() {
		return "Hello User";
	}

}
