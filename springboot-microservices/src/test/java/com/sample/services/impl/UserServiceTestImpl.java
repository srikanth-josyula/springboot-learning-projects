package com.sample.services.impl;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.sample.dao.UserDaoService;
import com.sample.model.User;
import com.sample.services.UserServiceTest;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTestImpl implements UserServiceTest {

	@InjectMocks
	private UserServiceImpl service;

	@Mock
	private UserDaoService repository;

	@Test
	public void retriveAllTest() {
		when(repository.findAll()).thenReturn(Arrays.asList(new User(1, "admin", "admin", "ROLE_ADMIN",true),
				new User(2, "user", "user", "ROLE_USER",true),new User(3, "kanth", "kanth", "ROLE_ADMIN",true)));
		List<User> users = service.retriveAll();
		
		assertEquals("ROLE_ADMIN", users.get(0).getRoles());
		assertEquals("ROLE_USER", users.get(1).getRoles());
	}

}
