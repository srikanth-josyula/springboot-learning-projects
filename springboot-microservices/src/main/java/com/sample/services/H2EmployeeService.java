package com.sample.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sample.dao.H2EmployeeRepository;
import com.sample.model.H2Employee;

@Service
public class H2EmployeeService {

	@Autowired
	H2EmployeeRepository repository;

	public void save(final H2Employee employee) {
		repository.save(employee);
	}

	// Get all students from the h2 database.
	public List<H2Employee> getAll() {
		final List<H2Employee> employees = new ArrayList<>();
		repository.findAll().forEach(employee -> employees.add(employee));
		return employees;
	}
}
