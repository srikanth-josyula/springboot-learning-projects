package com.sample.jpa.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sample.jpa.model.Employee;
import com.sample.jpa.repository.EmployeeRepository;

@RestController
public class EmployeeController {
	
	@Autowired
	EmployeeRepository employeeRepository;

	@RequestMapping("/getEmployees")
	public List<Employee> getEmployees() {
		return employeeRepository.findAll();
	}
	
	@PostMapping("/create/employee")
	public Employee createUser(@RequestBody Employee employee) {
		Employee saveEmployee = employeeRepository.save(employee);
		return saveEmployee;
	}
}
