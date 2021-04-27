package com.sample.services;

import com.sample.dao.SampleDaoService;

public interface SampleService {

	public String getMessage(String msg);
	public String getMessagefromDAO();
	public void setSampleDAO(SampleDaoService sampleDAO);

}
