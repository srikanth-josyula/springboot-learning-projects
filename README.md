# Spring Boot Learning Projects

Welcome to the Spring Boot Learning Projects repository! This collection contains multiple Spring Boot projects, each focusing on different basic concepts such as REST APIs, JDBC, and general Spring Boot fundamentals.

## Introduction

This repository is designed for anyone interested in learning Spring Boot. Each project focuses on a specific aspect of Spring Boot to provide a comprehensive learning experience.

## Projects
### Basic Spring Boot

This project covers the fundamentals of Spring Boot, including application setup, basic configurations, and simple endpoints.
#### Externalizing configuration
- Override default settings for production and use command-line switches for testing (e.g., `java -jar app.jar --app.message="Command-Test"`).
- Command-line properties take precedence unless explicitly disabled (`SpringApplication.setAddCommandLineProperties(false)`).
- Use `spring.config.name` to specify a different base name for your configuration file. For example:
  ```bash
  java -jar myproject.jar --spring.config.name=myproject
- Use `spring.config.location` to specify explicit locations for configuration files
  ```bash
  java -jar myproject.jar --spring.config.location=classpath:/default.properties,classpath:/override.properties


### Spring Boot REST API

In this project, you will learn how to create RESTful web services using Spring Boot. It includes examples of GET, POST, PUT, and DELETE endpoints.

