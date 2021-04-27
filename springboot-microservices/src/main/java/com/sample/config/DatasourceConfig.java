package com.sample.config;

import java.util.Base64;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
@PropertySource(value = { "classpath:application.properties" })
public class DatasourceConfig {

	@Autowired
	Environment environment;

	@Bean
	public DataSource getDataSource() {
		DriverManagerDataSource dataSource = null;
		try {
				dataSource = new DriverManagerDataSource();
				dataSource.setDriverClassName(environment.getProperty("db.driverClassName"));
				dataSource.setUrl(environment.getProperty("db.url"));
				dataSource.setUsername(decrypt(environment.getProperty("db.username")));
				dataSource.setPassword(decrypt(environment.getProperty("db.password")));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataSource;
	}

	public String decrypt(String encryptedData) throws Exception {
		Base64.Decoder decoder = Base64.getDecoder();
		String dStr = new String(decoder.decode(encryptedData));
		return dStr;
	}

	public String encrypt(String normalData) throws Exception {
		Base64.Encoder encoder = Base64.getEncoder();
		String str = encoder.encodeToString("postgres".getBytes());
		return str;
	}
}