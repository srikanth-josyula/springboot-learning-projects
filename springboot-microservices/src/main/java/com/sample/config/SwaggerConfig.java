package com.sample.config;

/**
 * As SOAP as WSDL for documentation 
 * in REST we have swagger
 * for getResponse = http://localhost:8181/v2/api-docs (springfox-swagger2 dependency)
 * for UI format = http://localhost:8181/swagger-ui.html (springfox-swagger-ui dependency) 
 **/

import org.springframework.context.annotation.Configuration;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2 
public class SwaggerConfig {
	
}
