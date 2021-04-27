package com.sample.jdbc.dao;

import java.util.List;
import com.sample.jdbc.model.Employee;


public interface EmployeeDao {
	List<Employee> findAll();
	boolean insertEmployee(Employee emp);
	boolean updateEmployee(Employee emp);
	Employee getEmployeeById(String id);
	boolean deleteEmployee(Employee emp);
}