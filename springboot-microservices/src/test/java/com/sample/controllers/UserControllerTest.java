package com.sample.controllers;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.containsString;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.sample.model.User;
import com.sample.services.UserService;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerTest {

	@Autowired
	private MockMvc mockMvc; //@WebMvcTest binds the required controller class
	
	@MockBean
	UserService userServiceMock;
	
	@Test
	public void retrieveAllUsersTest() throws Exception {
		
		//call GET "/users"  application/json
		String response = "{\"id\": 1,\"username\":\"admin\",\"password\":\"admin\",\"role\":\"ROLE_ADMIN\"},"
				+ "{\"id\": 2,\"username\":\"user\",\"password\":\"user\",\"role\":\"ROLE_USER\"}"
				+ "{\"id\": 3,\"username\":\"kanth\",\"password\":\"kanth\",\"role\":\"ROLE_ADMIN\"}";
		when(userServiceMock.retriveOne(2)).thenReturn(
				new User(1, "admin", "admin", "ROLE_ADMIN",true),
				new User(2, "user", "user", "ROLE_USER",true), 
				new User(3, "kanth", "kanth", "ROLE_ADMIN",true)
		);
		
		RequestBuilder request = MockMvcRequestBuilders
				.get("/authority/users/{id}",2)
				.accept(MediaType.APPLICATION_JSON);
		
		MvcResult result = mockMvc.perform(request)
				.andExpect(status().isOk())
				.andExpect(content().json(response))
				.andReturn();

		JSONAssert.assertEquals(response, result.getResponse().getContentAsString(), false);
		
	}
	
	@Test
	public void getUserTest() throws Exception {
		//call GET "/users"  application/json
		String response = "{\"id\": 2,\"username\":\"user\",\"password\":\"user\",\"role\":\"ROLE_USER\"}";
		when(userServiceMock.retriveOne(2)).thenReturn(new User(2,"user","user","ROLE_USER",true));
		
		RequestBuilder request = MockMvcRequestBuilders
				.get("/authority/users/{id}",2)
				.accept(MediaType.APPLICATION_JSON);
		
		MvcResult result = mockMvc.perform(request)
				.andExpect(status().isOk())
				.andExpect(content().json(response))
				.andReturn();

		JSONAssert.assertEquals(response, result.getResponse().getContentAsString(), false);
		
	}
	
	@Test
	public void createUserTest() throws Exception {
		//call POST "/users"  application/json Failing look later
		String UserRequest = "{\"id\": 4,\"username\":\"userTest\",\"password\":\"userTest\",\"role\":\"ROLE_USER\"}";

		RequestBuilder request = MockMvcRequestBuilders
				.post("/authority/users")
				.content(UserRequest)
				.contentType(MediaType.APPLICATION_JSON);
		
		MvcResult result = mockMvc.perform(request)
				.andExpect(status().isCreated())
				 .andExpect(header()
						 .string("location",containsString("/id/")))
				.andReturn();

		//JSONAssert.assertEquals(response, result.getResponse().getContentAsString(), false);
	}
	
	
}
