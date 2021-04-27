package com.sample.utils;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.io.IOUtils;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.sample.POJOs.PostMethodResponseVO;

public class RestUtils {
	public static  JSONObject executeGetMethod(String urlString) {

		GetMethod getMethod = null;
		JSONObject responseObj = null;
		InputStream responseBody = null;
		try {
			HttpClient client = new HttpClient();
			getMethod = new GetMethod(urlString);
			getMethod.setRequestHeader("Content-type", "application/json");
			int statusCode = client.executeMethod(getMethod);
			responseBody = getMethod.getResponseBodyAsStream();
			System.out.println(responseBody);

			if (statusCode == 200) {
				ByteArrayOutputStream baos = new ByteArrayOutputStream(1024 * 1024);
				IOUtils.copy(responseBody, baos);
				byte[] bytes = baos.toByteArray();
				String responseData = new String(bytes);
				JSONParser parser = new JSONParser();
				responseObj = (JSONObject) parser.parse(responseData);

			} else {
				String resp = getMethod.getResponseBodyAsString();
				JSONParser parser = new JSONParser();
				responseObj = (JSONObject) parser.parse(resp);
			}

		} catch (Exception e) {
			System.out.println("Exception occured :" + e);
		} finally {
			getMethod.releaseConnection();
		}
		return responseObj;
	}
	
	public static PostMethodResponseVO executeAlfTicketGetMethod(String urlString) {
		PostMethodResponseVO postMethodResponseVO = new PostMethodResponseVO();
		GetMethod getMethod = null;
		try {
			HttpClient client = new HttpClient();
			getMethod = new GetMethod(urlString);

			getMethod.setRequestHeader("Content-type", "application/json");
			int statusCode = client.executeMethod(getMethod);
			String responseBody = getMethod.getResponseBodyAsString();

			if (statusCode == 200) {
				postMethodResponseVO.setResponseString(responseBody);
				postMethodResponseVO.setStatusCode(statusCode);
				postMethodResponseVO.setMessage(responseBody);
			} else {
				JSONParser parser = new JSONParser();
				JSONObject responseObj = (JSONObject) parser.parse(responseBody);
				String msg = responseObj.get("message") != null ? (String) responseObj.get("message").toString() : "";
				postMethodResponseVO.setMessage(msg);
				postMethodResponseVO.setResponseString(msg);
				postMethodResponseVO.setStatusCode(statusCode);
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			getMethod.releaseConnection();
		}

		return postMethodResponseVO;
	}
}
