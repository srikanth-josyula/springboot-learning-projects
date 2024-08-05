# Spring-Boot REST Projects

This project covers the fundamentals of Spring Boot REST, including application setup, basic configurations, and simple endpoints.
REST (Representational State Transfer) is an architectural style that uses standard HTTP methods for scalable, stateless client-server communication

## Maven Configuration for Spring Boot-REST 
The spring-boot-starter-web dependency is essential for building RESTful web services with Spring Boot, it includes Spring MVC components, auto-configuration, and embedded servers, streamlining the development of web applications and REST APIs.
```
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>    
</dependency>
```
## Basic Annotations in Spring Boot REST
- `@RestController` combines `@Controller` and `@ResponseBody`, enabling automatic serialization of return objects to JSON/XML, ideal for APIs and client-server communication.
- `@RequestMapping` maps web requests to handler methods, allowing for detailed URL mapping, HTTP method restriction (GET, POST, PUT, DELETE), dynamic value extraction, and content type negotiation via headers and parameters.
- Spring supports five types of inbuilt annotations for handling different types of incoming HTTP request methods `@GetMapping`, `@PostMapping`, `@PutMapping`, `@DeleteMapping`, `@PatchMapping`

## Path Variables and Request Parameters
- `@PathVariable` binds method parameters to URI template variables for dynamic URI handling, supports multiple and map-based variables, can specify variable names, defaults to required but can be set to optional.
  - `/api/test/{id}`
- `@RequestParam` extracts query parameters from URLs, supports required/optional params, custom names, default values, multiple values, maps, and validation, but avoid conflicts with path variables and handle URL encoding. Best for straightforward form data submissions, where you are uploading files along with other simple form fields. (e.g., String, int).
  - `/api/test?id={id}`
- `@RequestPart` handles multipart file uploads in RESTful services by binding specific parts of a multipart request, allowing processing of both form data and file data in a single request, commonly used for uploading files and accompanying metadata. Handles more complex content, like JSON or XML. Uses HttpMessageConverters to convert the request part based on the Content-Type
- `@RequestHeader` annotation in Spring Boot extracts HTTP request header values and binds them to method parameters in controller methods

## Request and Response Bodies
- `@ResponseBody` binds method return values to the HTTP response body, leveraging message converters (like Jackson) for JSON/XML serialization, and is implicitly applied to all methods in `@RestController`.
- `@RequestBody` annotation in Spring Boot maps the HTTP request body to a Java object, enabling automatic deserialization, typically used for handling JSON payloads in POST, PUT, and PATCH requests,
- **RequestEntity** : Provides detailed information about an HTTP request, including headers, method, URL, and body.
- **ResponseEntity**: Represents an HTTP response with full control over status, headers, and body, allowing customization of the response.

## Exception Handling in REST
- `@ResponseStatus`: Customizes the HTTP status code for a method or exception, setting default response status or handling exceptions with specific status codes.
- **ResponseStatusException**: A RuntimeException for applying HTTP status codes to responses programmatically, with constructors for setting status and reason.
- `@ExceptionHandler`: Defines methods to handle specific exceptions thrown by controller methods with customized response strategies.
- `@ControllerAdvice`: Provides centralized exception handling across all controllers, allowing for a unified approach to managing exceptions.

## Validations in REST APIs
- `@Valid`: Triggers validation on fields or method parameters based on Java Bean Validation (JSR 380) rules, working with annotations like @NotNull, @Size, @Min, and @Max.
- `@Validated`: Marks a class or method for validation, supports group validation for different scenarios, and enables both method-level and class-level validation.




