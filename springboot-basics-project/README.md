# Spring-Boot Basics Projects

This project covers the fundamentals of Spring Boot, including application setup, basic configurations, and simple endpoints.

## Annotations in Springboot
- The `@SpringBootApplication` annotation is a convenience annotation that combines `@SpringBootConfiguration`, `@EnableAutoConfiguration`, and `@ComponentScan` into one.
- `@ComponentScan:` automatically scans and registers beans from specified packages during application initialization. If your components are in packages outside the main application package, you need to specify those explicitly.
- `@Configuration:` Declares a class as a source of @Bean definitions enabling Spring to manage application context initialization, ensuring singleton behavior for beans.
- `@Autowired:` Automatically injects beans into Spring components.
  - Constructor Injection: Inject dependencies via constructors for clarity and immutability.
  - Setter Injection: Uses setter methods for flexibility in changing dependencies.
  - Field Injection: Directly injects dependencies into fields, convenient for simplicity.

## Externalizing configuration
- Override default settings for production and use command-line switches for testing (e.g., `java -jar app.jar --name="Spring"`).
- Command-line properties take precedence unless explicitly disabled (`SpringApplication.setAddCommandLineProperties(false)`).
