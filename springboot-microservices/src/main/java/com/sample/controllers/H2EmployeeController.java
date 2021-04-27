package com.sample.controllers;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sample.model.H2Employee;
import com.sample.services.H2EmployeeService;

@RestController		
public class H2EmployeeController {

	private final Logger log = LoggerFactory.getLogger(this.getClass()); 
	@Autowired
	H2EmployeeService service;

	@GetMapping(value= "/employee/getall", produces= "application/vnd.jcg.api.v1+json")
	public List<H2Employee> getAll() {
		log.info("Getting employee details from the H2 database.");
		return service.getAll();
	}
	
	@PostMapping(value= "/employee/save")
	public Long save(final @RequestBody @Valid H2Employee employee) {
		log.info("Saving employee details in the database.");
		service.save(employee);
		return employee.getEmp_id();
	}
}
