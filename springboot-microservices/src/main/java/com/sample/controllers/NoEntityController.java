package com.sample.controllers;

import java.util.List;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sample.dao.NoEntityRepository;

@RestController
public class NoEntityController {

	@Autowired
	NoEntityRepository noentityRepository;

	
	@SuppressWarnings("unchecked")
	@RequestMapping("/task-instances")
	public JSONObject getDCUsers() {
		JSONObject responseObject = new JSONObject();
		try {
			List<Object[]> userData = noentityRepository.getUserDetails();
			 for(Object[] data : userData) {
				 responseObject.put("id", (String) data[0]);
				 responseObject.put("firstName", (String) data[1]);
				 responseObject.put("lastName", (String) data[2]);
				 
			 }
		} catch (Exception e) {
		}
		return responseObject;

	}

}
