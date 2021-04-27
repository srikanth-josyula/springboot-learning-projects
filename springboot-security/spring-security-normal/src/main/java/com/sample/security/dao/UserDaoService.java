package com.sample.security.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Component;

import com.sample.security.model.User;

@Component
public class UserDaoService {

	static List<User> users = new ArrayList<>();

	static int usersCount = 3;

	static {
		users.add(new User(1, "admin", "admin", "ADMIN", true));
		users.add(new User(2, "user", "user", "USER", true));
		users.add(new User(3, "kanth", "kanth", "ADMIN", true));
	}

	public List<User> findAll() {
		return users;
	}

	public User save(User user) {
		if (user.getUserid() == null) {
			user.setId(++usersCount);
		}
		users.add(user);
		return user;
	}

	public User findOne(int id) {
		for (User user : users) {
			if (user.getUserid()== id) {
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

	public User deleteById(int id) {
		Iterator<User> iterator = users.iterator();
		while (iterator.hasNext()) {
			User user = iterator.next();
			if (user.getUserid() == id) {
				iterator.remove();
				return user;
			}
		}
		return null;
	}

}
