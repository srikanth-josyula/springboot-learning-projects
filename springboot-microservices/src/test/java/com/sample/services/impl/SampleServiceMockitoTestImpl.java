package com.sample.services.impl;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.sample.dao.SampleDaoService;
import com.sample.services.SampleServiceMockitoTest;

@RunWith(MockitoJUnitRunner.class)
public class SampleServiceMockitoTestImpl implements SampleServiceMockitoTest {

	@InjectMocks
	SampleServiceImpl service;

	@Mock
	SampleDaoService daoObject;

	@Test
	public void getMessagefromDAOTest() {
		when(daoObject.fetchMessage()).thenReturn(new String("Message from DAO Layer"));
		assertEquals("Message from DAO Layer", service.getMessagefromDAO());
	}

}
