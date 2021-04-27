package com.sample.com.sample.utils;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.opensaml.Configuration;
import org.opensaml.DefaultBootstrap;
import org.opensaml.xml.ConfigurationException;
import org.opensaml.xml.XMLObject;
import org.opensaml.xml.io.Unmarshaller;
import org.opensaml.xml.io.UnmarshallerFactory;
import org.opensaml.xml.io.UnmarshallingException;
import org.opensaml.xml.util.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.sample.com.sample.constants.CommonConstants;

@Component
public class SAMLEncryptionUtil {

	private static boolean bootStrapped = false;
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	public String getAudience(String samlToken) {
		String audience = null;
		try {
			XMLObject responseXML = processSAMLResponse(samlToken);
			NodeList audiencelist = responseXML.getDOM().getElementsByTagNameNS(CommonConstants.AUDIENCE_NAMESPACE_URI, 
					CommonConstants.AUDIENCE_LOCALNAME);
			if (audiencelist.getLength() > 0) {
				audience = audiencelist.item(0).getTextContent();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return audience;
	}

	public XMLObject processSAMLResponse(String samlToken) {
		// Decoding the extracted encoded SAML Response
		logger.debug("Decoding the extracted encoded SAML Response");
		byte[] base64DecodedResponse = Base64.decode(samlToken);
		if (base64DecodedResponse == null || base64DecodedResponse.length == 0)
			throw new RuntimeException("Invalid assertion encoding");

		XMLObject responseXmlObj = null;

		// Initializing Open SAML Library
		doBootstrap();

		try {
			// Converting the decoded SAML Response string into DOM object
			ByteArrayInputStream inputStreams = new ByteArrayInputStream(base64DecodedResponse);
			DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
			documentBuilderFactory.setNamespaceAware(true);
			DocumentBuilder docBuilder = documentBuilderFactory.newDocumentBuilder();
			Document document = docBuilder.parse(inputStreams);
			Element element = document.getDocumentElement();

			// Unmarshalling the element
			UnmarshallerFactory unmarshallerFactory = Configuration.getUnmarshallerFactory();
			Unmarshaller unmarshaller = unmarshallerFactory.getUnmarshaller(element);
			responseXmlObj = unmarshaller.unmarshall(element);

		} catch (ParserConfigurationException | SAXException | IOException e) {
			throw new RuntimeException("Error while parsing the decoded SAML Response :: " + e.getLocalizedMessage());
		} catch (UnmarshallingException e) {
			throw new RuntimeException(
					"Error while unmarshalling the decoded SAML Response :: " + e.getLocalizedMessage());
		}
		return responseXmlObj;

	}

	public static void doBootstrap() {
		/* Initializing the OpenSAML library */
		if (!bootStrapped) {
			try {
				DefaultBootstrap.bootstrap();
				bootStrapped = true;
			} catch (ConfigurationException e) {
				throw new RuntimeException(e.getLocalizedMessage());
			}
		}
	}

}