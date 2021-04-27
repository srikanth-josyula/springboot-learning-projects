package com.sample.controllers;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.sample.services.SampleService;

@RunWith(SpringRunner.class)
@WebMvcTest(SampleController.class)
public class SampleControllerTest {

	@Autowired
	private MockMvc mockMvc; //@WebMvcTest binds the required controller class
	
	@MockBean
	SampleService sampleServiceMock;
	
	@Test
	public void printDateTest() throws Exception {
		Date responseDate = new Date();
		
		RequestBuilder request = MockMvcRequestBuilders
				.get("/sample/getdate")
				.accept(MediaType.APPLICATION_JSON);
		
		MvcResult result = mockMvc.perform(request)
				.andExpect(status().isOk())
				.andExpect(content().string(responseDate.toString()))
				.andReturn();
		assertEquals(responseDate.toString(), result.getResponse().getContentAsString());
	}
	
	@Test
	public void printMessageTest() throws Exception {
		String responseData = "Hello Srikanth !!";
		when(sampleServiceMock.getMessage("Srikanth")).thenReturn("Hello Srikanth !!");
		
		RequestBuilder request = MockMvcRequestBuilders
				.get("/sample/showmessage/{msg}","Srikanth")
				.accept(MediaType.APPLICATION_JSON);
		
				MvcResult result = mockMvc.perform(request)
						.andExpect(status().isOk())
						.andExpect(content().string(responseData))
						.andReturn();
		assertEquals(responseData.toString(), result.getResponse().getContentAsString());
	}
}
