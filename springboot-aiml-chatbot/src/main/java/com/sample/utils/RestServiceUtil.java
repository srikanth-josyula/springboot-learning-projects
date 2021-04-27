package com.sample.utils;

import com.sample.dao.UserDaoService;

public class RestServiceUtil {

	public String getRestResponse(String resp) {

		UserDaoService userDao = new UserDaoService();
		String response = null;
		String[] responseComponents = resp.split("##");
		String method = responseComponents[0];
		switch (method) {
		case "USERNAME":
			int userId = Integer.valueOf(responseComponents[2]);
			String namevalue = userDao.findOne(userId).getUsername();
			response = responseComponents[1] + namevalue;
			break;
		case "USERID":
			String Idvalue = userDao.findbyName(responseComponents[2]).getUserid().toString();
			response = responseComponents[1] + Idvalue;
			break;
		default:
			response = "Invalid User Request";
		}

		return response;
	}
}
