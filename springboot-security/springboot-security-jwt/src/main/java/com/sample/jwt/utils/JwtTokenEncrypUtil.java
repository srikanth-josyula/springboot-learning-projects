package com.sample.jwt.utils;

import java.nio.charset.StandardCharsets;
import java.util.Calendar;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtTokenEncrypUtil {

	@Value("${jwt.signing.key.secret}")
	private String secretKey;
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	public String createJWT() {
		SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
		JwtBuilder builder = null;

		// We will sign our JWT with our ApiKey secret
		/*
		 * byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(SECRET_KEY);
		 * Key signingKey = new SecretKeySpec(apiKeySecretBytes,
		 * signatureAlgorithm.getJcaName());
		 */

		try {
			builder = Jwts.builder()
					.setSubject("1234567890")
					.setId("1b6d1902-38a8-41d1-b6a4-ca36af28c40a")
					.setIssuedAt(new Date())
					.setExpiration(addHoursToDate(new Date(),20))
					.claim("name", "John Doe")
					.claim("admin", true)
					.signWith(signatureAlgorithm,
					secretKey.getBytes(StandardCharsets.UTF_8));
			// .signWith(signatureAlgorithm, signingKey);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return builder.compact();
	}

	public Claims decodeJWT(String jwt) {
		logger.debug("Decoding the JWT Token");
		Claims claims = null;
		DecodedJWT decoded = null;
		Algorithm algorithmString = Algorithm.HMAC256(secretKey);
		Algorithm algorithmBytes = Algorithm.HMAC256(secretKey.getBytes(StandardCharsets.UTF_8));

		try {
			decoded = JWT.decode(jwt);
			algorithmString.verify(decoded);
			algorithmBytes.verify(decoded);
			logger.debug("Provided token is a valid token of " + decoded.getAlgorithm());
			claims = Jwts.parser().setSigningKey(secretKey.getBytes(StandardCharsets.UTF_8))
					// .setSigningKey(DatatypeConverter.parseBase64Binary(SECRET_KEY))
					.parseClaimsJws(jwt).getBody();
			logger.debug("Value of decoded jwt token is " + claims.toString());

		} catch (Exception e) {
			e.printStackTrace();
		}
		return claims;
	}
	
	public Date addHoursToDate(Date date, int hours) {
	    Calendar calendar = Calendar.getInstance();
	    calendar.setTime(date);
	    calendar.add(Calendar.HOUR_OF_DAY, hours);
	    return calendar.getTime();
	}
}
