package com.sample.controllers;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.sample.exceptions.UserNotFoundException;
import com.sample.model.User;
import com.sample.services.UserService;
import com.sample.utils.UserUtils;

@RestController
@RequestMapping("/authority")
public class UserController {

	@Autowired
	UserService userServiceImpl;

	@GetMapping("/users")
	public List<Resource<User>> retrieveAllUsers() {
		UserUtils userUtils = new UserUtils();
		List<Resource<User>> responseObject = userUtils.userResponse(userServiceImpl.retriveAll());
		return responseObject;
	}

	@GetMapping("/user/{id}")
	public User retrieveUser(@PathVariable int id) {
		User user = userServiceImpl.retriveOne(id);
		if (user == null)
			throw new UserNotFoundException("id-" + id);
		return user;
	}
	
	@PostMapping("/create/user")
	public User createUserold(@Valid @RequestBody User user) {
		User savedUser = userServiceImpl.saveUser(user);
		return savedUser;
	}

	// We use ResponseEntity which has HttpStatus code and HttpStatus Messages
	// Best Practice
	@PostMapping("/users")
	public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
		User savedUser = userServiceImpl.saveUser(user);
		URI location = ServletUriComponentsBuilder // This returns a location of new user /users/{id}
				.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getUserid()).toUri();
		return ResponseEntity.created(location).build();
	}

	@DeleteMapping("/users/{id}")
	public void deleteUser(@PathVariable int id) {
		User user = userServiceImpl.deleteUser(id);
		if (user == null)
			throw new UserNotFoundException("id-" + id);
	}
}
