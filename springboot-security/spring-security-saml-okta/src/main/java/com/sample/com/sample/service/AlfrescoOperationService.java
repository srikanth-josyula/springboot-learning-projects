package com.sample.com.sample.service;

import com.sample.com.sample.exception.CustomGenericException;


public interface AlfrescoOperationService  {
	public String getAlfrescoTicket(String userName, String password) throws CustomGenericException;
}
