package com.sample.jdbc.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sample.jdbc.dao.EmployeeDao;
import com.sample.jdbc.model.Employee;

@RestController
public class EmployeeController {
	
	@Autowired
	EmployeeDao employeeDao;

	@GetMapping(value = "/getEmployees")
	public List<Employee> getEmployees() {
		return employeeDao.findAll();
	}

	@PostMapping(value = "/create")
	public void createEmployee(@RequestBody Employee emp) {
		employeeDao.insertEmployee(emp);
	}

	@PutMapping(value = "/update")
	public void updateEmployee(@RequestBody Employee emp) {
		employeeDao.updateEmployee(emp);
	}

	@DeleteMapping(value = "/delete")
	public void deleteEmployee(@RequestBody Employee emp) {
		employeeDao.deleteEmployee(emp);
	}

}
