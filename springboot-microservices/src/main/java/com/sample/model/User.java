package com.sample.model;

import javax.validation.constraints.Size;

/**
 * For Hateros we can extend ResourceSupport in a class
 * where it accepts an .add in User model.. 
 * but this give a confit with .getID of Resource and if any 
 * if .getId is present in the model
 **/
//public class User extends ResourceSupport 
public class User {

	private Integer userid;
	private String username;
	@Size(min=8, message="Password should have 8 characters")
	private String password;
	private String roles;
	private boolean active;

	public User() {

	}

	public User(Integer userid, String username, String password, String roles, boolean active) {
		super();
		this.userid = userid;
		this.username = username;
		this.password = password;
		this.roles = roles;
		this.active = active;
	}

	
	public Integer getUserid() {
		return userid;
	}

	public void setId(Integer userid) {
		this.userid = userid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRoles() {
		return roles;
	}

	public void setRoles(String roles) {
		this.roles = roles;
	}
	
	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}


	@Override
	public String toString() {
		return "User [userid=" + userid + ", username=" + username + ", password=" + password + ", roles=" + roles + "]";
	}

}