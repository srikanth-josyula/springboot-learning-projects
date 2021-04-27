package com.sample.services;

import org.apache.commons.lang3.StringUtils;
import org.json.XML;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.sample.POJOs.PostMethodResponseVO;
import com.sample.constants.CommonConstants;
import com.sample.utils.RestUtils;

public class AlfrescoUtilServices {
	public String getRestResponse(String resp) {
		String response = null;
		String[] responseComponents = resp.split("##");
		String method = responseComponents[0];

		if ("ALFRESCO_TOKEN".equalsIgnoreCase(method)) {
			String alf_ticket = getAlfrescoTicket().get("ticket").toString();
			response = responseComponents[1] + alf_ticket;
		} else if ("RAISED_CLAIMS".equalsIgnoreCase(method)) {
			String claimantName = StringUtils.substringBetween(responseComponents[1], "{$", "$}");
			response = "The Claimant has Following Claims <br/>" + getTaskDetails(claimantName);
		} else if ("CLAIM_DOCUMENTS".equalsIgnoreCase(method)) {
			String claimNumber = StringUtils.substringBetween(responseComponents[1], "{$", "$}");
			System.out.println(claimNumber);
			String nodeId = getClaimLocation(claimNumber.replaceAll(" underscore ", "_"));
			System.out.println(nodeId);
			response = "Please click below link to view documents <br/> " + "<a href='"
					+ CommonConstants.adffunctionalUrl + "/#/personal-files/" + nodeId + "' target='_blank'>"
					+ claimNumber.replaceAll(" underscore ", "_") + "</a>";
		} else if ("CLAIM_STATUS".equalsIgnoreCase(method)) {
			String claimNumber = StringUtils.substringBetween(responseComponents[1], "{$", "$}")
					.replaceAll(" underscore ", "_");
			response = "Status of the Claim is " + getClaimStatus(claimNumber);
		} else {
			response = "Invalid Request";
		}

		return response;
	}

	public JSONObject getAlfrescoTicket() {
		JSONObject responseObject = new JSONObject();
		try {
			PostMethodResponseVO postMethodResponseVO = RestUtils
					.executeAlfTicketGetMethod(CommonConstants.alffunctionalUrl + "api/login?u="
							+ CommonConstants.alffunctionalUsername + "&pw=" + CommonConstants.alffunctionalPassword);
			String response = postMethodResponseVO.getResponseString();
			String parsedReponse = XML.toJSONObject(response).toString();
			JSONParser jsonParser = new JSONParser();
			responseObject = (JSONObject) jsonParser.parse(parsedReponse);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return responseObject;
	}

	public String getTaskDetails(String claimant) {
		String response = null;
		try {
			JSONObject alfResponse = RestUtils.executeGetMethod(CommonConstants.alffunctionalUrl
					+ "sample/chatbot?queryParam=getTaskDetails&queryString=" + claimant.trim());
			System.out.println(alfResponse);
			response = (String) alfResponse.get("response");
			// response = "[Claimant_Insurance_Policy_1600847373295]"
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}

	public String getClaimLocation(String claimNumber) {
		String response = null;
		try {
			System.out.println(CommonConstants.alffunctionalUrl
					+ "sample/chatbot?queryParam=getFolderNodeId&queryString=" + claimNumber.trim());
			JSONObject alfResponse = RestUtils.executeGetMethod(CommonConstants.alffunctionalUrl
					+ "sample/chatbot?queryParam=getFolderNodeId&queryString=" + claimNumber.trim());
			System.out.println(alfResponse);
			response = (String) alfResponse.get("response");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}

	public String getClaimStatus(String claimNumber) {
		String response = null;
		try {
			JSONObject alfResponse = RestUtils.executeGetMethod(CommonConstants.alffunctionalUrl
					+ "sample/chatbot?queryParam=getClaimStatus&queryString=" + claimNumber.trim());
			System.out.println(alfResponse);
			response = (String) alfResponse.get("response");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}
}
