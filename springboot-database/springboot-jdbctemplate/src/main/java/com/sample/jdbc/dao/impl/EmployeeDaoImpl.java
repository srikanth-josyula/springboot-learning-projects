package com.sample.jdbc.dao.impl;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.sample.jdbc.dao.EmployeeDao;
import com.sample.jdbc.model.Employee;
import com.sample.jdbc.model.EmployeeRowMapper;

@Component
public class EmployeeDaoImpl implements EmployeeDao {

	private final String SQL_GET_ALL = "select * from employee";
	private final String SQL_INSERT_EMPLOYEE = "insert into employee"
			+ "(employeeid, employeename , employeeaddress, employeeemail) " + "values(?,?,?,?)";
	private final String SQL_FIND_EMPLOYEE = "select * from employee where employeeid = ?";
	private final String SQL_DELETE_EMPLOYEE = "delete from employee where employeeid = ?";
	private final String SQL_UPDATE_EMPLOYEE = "update people set employeename = ?, employeeaddress = ?, employeeemail  = ? where employeeid = ?";


	JdbcTemplate jdbcTemplate;

	@Autowired
	public EmployeeDaoImpl(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public List<Employee> findAll() {
		try {
			return jdbcTemplate.query(SQL_GET_ALL, new EmployeeRowMapper());
		} catch (EmptyResultDataAccessException e) {
			throw new RuntimeException(e.getMessage());
		}

	}
	
	public Employee getEmployeeById(String id) {
		return jdbcTemplate.queryForObject(SQL_FIND_EMPLOYEE, new Object[] { id }, new EmployeeRowMapper());
	}


	@Override
	public boolean insertEmployee(Employee emp) {
		return jdbcTemplate.update(SQL_INSERT_EMPLOYEE, emp.getEmployeeId(), emp.getEmployeename(),
				emp.getEmployeeemail(), emp.getEmployeeaddress())>0;
	}

	@Override
	public boolean updateEmployee(Employee emp) {
		return jdbcTemplate.update(SQL_UPDATE_EMPLOYEE, emp.getEmployeename(),
				emp.getEmployeeemail(), emp.getEmployeeaddress(), emp.getEmployeeId()) > 0;
	}

	@Override
	public boolean deleteEmployee(Employee emp) {
		return jdbcTemplate.update(SQL_DELETE_EMPLOYEE, emp.getEmployeeId()) > 0;
	}

}
