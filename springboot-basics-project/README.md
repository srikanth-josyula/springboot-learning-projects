# Spring-Boot Basics Projects

This project covers the fundamentals of Spring Boot, including application setup, basic configurations, and simple endpoints.

## Annotations in Spring Boot
### Bean Definition & Creation Annotations
- `@SpringBootApplication:` annotation is a convenience annotation that combines `@SpringBootConfiguration`, `@EnableAutoConfiguration`, and `@ComponentScan` into one.
- `@ComponentScan:` automatically scans and registers beans from specified packages during application initialization. If your components are in packages outside the main application package, you need to specify those explicitly.
- `@EnableAutoConfiguration:` simplifies application setup by automatically configuring beans based on the classpath, leveraging the Spring Factories Loader to locate configurations,it is automatically enabled with @SpringBootApplication.
- `@Configuration:` Declares a class as a source of @Bean definitions enabling Spring to manage application context initialization, ensuring singleton behavior for beans.
- `@Component:` marks a class as a Spring-managed bean, with variants like @Service, @Repository, and @Controller, enabling automatic registration and dependency injection.
   - `@Service:` Denotes that the class provides some services. Our utility classes can be marked as Service classes.
  - `@Repository:` This annotation indicates that the class deals with CRUD operations, usually it’s used with DAO implementations that deal with database tables.
  - `@Controller:` Used with web or REST web services to specify that the class is a front controller and responsible to handle user requests and return appropriate responses.
- `@Bean:` annotation declares a method as a Spring-managed bean, supporting custom initialization and destruction, unique naming, and handling dependencies with @DependsOn.
- `@Import:` annotation in Spring imports additional configuration classes into the current one, enhancing modularity and reusability of application configurations.

### Bean Dependency Injection Annotations
- `@Autowired:` Automatically injects beans into Spring components.
  - Constructor Injection: Inject dependencies via constructors for clarity and immutability.
  - Setter Injection: Uses setter methods for flexibility in changing dependencies.
  - Field Injection: Directly injects dependencies into fields, convenient for simplicity.
- `@Qualifier:` annotation in Spring resolves ambiguity by specifying which bean to inject when multiple candidates exist, often used with @Autowired
- `@Primary:` Designates a bean as the default choice for injection when no other qualifier is specified, resolving ambiguity in cases with multiple beans of the same type.
- `@Scope:` annotation in Spring defines the scope of a bean, enabling customization beyond the default singleton behavior.
- `@Inject:` annotation in Spring, part of the (JSR-330) standard, injects dependencies into a Spring-managed bean, serving as an alternative to @Autowired.
- `@Resource:` Injects dependencies by name, following the Java EE (JSR-250) standard and supported by Spring for dependency injection in fields, setters, and constructors.

### Bean Life Cycle Annotations
- `@PostConstruct:` This annotation is used on a method that needs to be executed after dependency injection is done to perform any initialization.
- `@PreDestroy:` This annotation is used on a method that needs to be executed just before the bean is removed from the container to perform any cleanup.
- `@Lazy:` Defers bean initialization until first requested, optimizing resource usage and improving application startup time by avoiding the eager instantiation of beans.
- `@DependsOn:` annotation in Spring specifies bean dependencies or initialization order, ensuring beans annotated with it are initialized before others.

### Spring Boot Property Annotations
- `@ConfigurationProperties:` annotation in Spring maps external configuration properties to Java objects
- `@PropertySource:` loads external property files into the application's environment, enabling flexible and centralized configuration management.
- `@Value:` Injects values from external sources into fields, method parameters, or constructor parameters, supporting SpEL for evaluating expressions.
- `@Validated:` Enables method-level validation for Spring beans, utilizing Bean Validation (JSR-380) annotations like @NotNull, @Size, @Min, @Max, etc., to validate
- `@Profile:` Enables conditional bean registration based on active profiles, facilitating environment-specific configurations and ensuring beans are instantiated selectively.

## Externalizing configuration
- Override default settings for production and use command-line switches for testing (e.g., `java -jar app.jar --name="Spring"`).
- Command-line properties take precedence unless explicitly disabled (`SpringApplication.setAddCommandLineProperties(false)`).
