package com.sample.jdbc.model;

public class Employee {
	private String employeeid;
	private String employeename;
	private String employeeaddress;
	private String employeeemail;
	
	public Employee() {
	}

	public Employee(String employeeid, String employeename, String employeeaddress, String employeeemail) {
		super();
		this.employeeid = employeeid;
		this.employeename = employeename;
		this.employeeaddress = employeeaddress;
		this.employeeemail = employeeemail;
	}


	public String getEmployeeId() {
		return employeeid;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeid = employeeId;
	}

	public String getEmployeename() {
		return employeename;
	}

	public void setEmployeename(String employeename) {
		this.employeename = employeename;
	}

	public String getEmployeeaddress() {
		return employeeaddress;
	}

	public void setEmployeeaddress(String employeeaddress) {
		this.employeeaddress = employeeaddress;
	}

	public String getEmployeeemail() {
		return employeeemail;
	}

	public void setEmployeeemail(String employeeemail) {
		this.employeeemail = employeeemail;
	}
}