package com.sample.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.sample.model.User;

@Component
public class UserDaoService {

	static List<User> users = new ArrayList<>();

	static int usersCount = 3;

	static {
		users.add(new User(1, "admin"));
		users.add(new User(2, "user"));
		users.add(new User(3, "kanth"));
	}

	public User findOne(int id) {
		for (User user : users) {
			if (user.getUserid() == id) {
				return user;
			}
		}
		return null;
	}

	public User findbyName(String name) {
		for (User user : users) {
			if (user.getUsername().equalsIgnoreCase(name)) {
				return user;
			}
		}
		return null;
	}
}
