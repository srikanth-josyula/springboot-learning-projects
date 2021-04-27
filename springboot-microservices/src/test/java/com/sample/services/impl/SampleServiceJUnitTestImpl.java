package com.sample.services.impl;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.sample.dao.SampleDaoService;
import com.sample.services.SampleService;
import com.sample.services.SampleServiceJUnitTest;

/**
 * Here in JUnit to create a dataObject we are using new Object() creation which
 * takes memory in heap to avoid this Mockito is introduced
 **/

public class SampleServiceJUnitTestImpl implements SampleServiceJUnitTest {

	@Test
	public void basicgetMessageTest() {
		SampleService sampleServiceImpl = new SampleServiceImpl();
		String actualResult = sampleServiceImpl.getMessage("Srikanth");
		String expectedResult = "Hello Srikanth !!";
		assertEquals(expectedResult, actualResult);
	}

	@Test
	public void emptygetMessageTest() {
		SampleService sampleServiceImpl = new SampleServiceImpl();
		String actualResult = sampleServiceImpl.getMessage("");
		String expectedResult = "Hello  !!";
		assertEquals(expectedResult, actualResult);
	}

	@Test
	public void getMessagefromDAOTest() {
		SampleService sampleServiceImpl = new SampleServiceImpl();
		sampleServiceImpl.setSampleDAO(new SampleDaoService());
		String actualResult = sampleServiceImpl.getMessagefromDAO();
		String expectedResult = "Message from DAO Layer";
		assertEquals(expectedResult, actualResult);
	}
}
