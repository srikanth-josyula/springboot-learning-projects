package com.sample.services.impl;

import org.springframework.stereotype.Service;

import com.sample.dao.SampleDaoService;
import com.sample.services.SampleService;

@Service
public class SampleServiceImpl implements SampleService {
	
	private SampleDaoService sampleDAO;

	public void setSampleDAO(SampleDaoService sampleDAO) {
		this.sampleDAO = sampleDAO;
	}

	@Override
	public String getMessage(String msg) {
		return "Hello " + msg + " !!";
	}

	@Override
	public String getMessagefromDAO() {
		return sampleDAO.fetchMessage();
	}

}
