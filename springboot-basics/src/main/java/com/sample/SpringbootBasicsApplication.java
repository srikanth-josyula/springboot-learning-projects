package com.sample;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.beans.factory.annotation.Value;

@SpringBootApplication
public class SpringbootBasicsApplication implements CommandLineRunner {
	
	@Value("${app.message}")
    private String message;

    public static void main(String[] args) {
        SpringApplication.run(SpringbootBasicsApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println(message);
    }
}