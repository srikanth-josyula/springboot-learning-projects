package com.sample.model;

public class User {

	private Integer userid;
	private String username;

	public User() {

	}

	public User(Integer userid, String username) {
		super();
		this.userid = userid;
		this.username = username;

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

}