package com.sample.security.services;

import java.util.List;

import com.sample.security.model.User;

public interface UserService {

	public List<User> retriveAll();
	public User saveUser(User user);
	public User retriveOne(int id);
	public User retrivebyName(String name);
	public User deleteUser(int id);
}
