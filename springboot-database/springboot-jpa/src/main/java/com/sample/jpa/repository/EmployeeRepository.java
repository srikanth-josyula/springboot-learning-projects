package com.sample.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sample.jpa.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, String> {

	
}
