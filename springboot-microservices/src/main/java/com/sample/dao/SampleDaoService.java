package com.sample.dao;

import org.springframework.stereotype.Component;

@Component
public class SampleDaoService {

	public String fetchMessage() {
		return "Message from DAO Layer";
	}
}
