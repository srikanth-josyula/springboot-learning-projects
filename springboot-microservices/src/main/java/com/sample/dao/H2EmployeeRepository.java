package com.sample.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.sample.model.H2Employee;

@Repository
public interface H2EmployeeRepository extends CrudRepository<H2Employee, Integer>{

}
