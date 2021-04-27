BASIC LOGS
----------
Starting Application on Srikanth_Josyula with PID 660 (F:\Workspace\Spring-Projects\springboot-microservices\target\classes started by SriKanth in F:\Workspace\Spring-Projects\springboot-microservices)
Tomcat initialized with port(s): 8181 (http)
Initializing Spring embedded WebApplicationContext
Servlet dispatcherServlet mapped to [/]
Mapping filter: 'characterEncodingFilter' to: [/*]
Mapping filter: 'hiddenHttpMethodFilter' to: [/*]
Mapping filter: 'httpPutFormContentFilter' to: [/*]
Mapping filter: 'requestContextFilter' to: [/*]
Mapped URL path [/**/favicon.ico] onto handler of type [class org.springframework.web.servlet.resource.ResourceHttpRequestHandler]
Looking for @ControllerAdvice: org.springframework.boot.web.servlet.context.AnnotationConfigServletWebServerApplicationContext@667a738: startup date [Wed Mar 25 17:20:54 IST 2020]; root of context hierarchy
Mapped "{[/sample/getCalendar],methods=[GET]}" onto public com.sample.POJOs.CustomDate com.sample.controller.SampleController.getTodayCalendar()
Mapped "{[/sample/getdate]}" onto public java.lang.String com.sample.controller.SampleController.printDate()
Mapped "{[/error]}" onto public org.springframework.http.ResponseEntity<java.util.Map<java.lang.String, java.lang.Object>> org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController.error(javax.servlet.http.HttpServletRequest)
Mapped "{[/error],produces=[text/html]}" onto public org.springframework.web.servlet.ModelAndView org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController.errorHtml(javax.servlet.http.HttpServletRequest,javax.servlet.http.HttpServletResponse)
Mapped URL path [/webjars/**] onto handler of type [class org.springframework.web.servlet.resource.ResourceHttpRequestHandler]
Mapped URL path [/**] onto handler of type [class org.springframework.web.servlet.resource.ResourceHttpRequestHandler]
Tomcat started on port(s): 8181 (http) with context path ''
Started Application in 4.516 seconds (JVM running for 6.057)

--------
QUESTIONS

1. What is dispacher servlet?
2. Who is configuring dispacher servlet?
3. What does dispacher servlet do?
4. How are the objects converted in JSON ?
5. Who is configuring the error mapping

-----------
ANSWERS

Once we place "logging.level.org.springframework = debug" in the application.properties, we see a lot of logs where we see
1. "DispatcherServletAutoConfiguration Matched" application has found the dispatcher servlet in the classpath , this added when we provided "springboot-starter-web"
2. Similarly we see "ErrorMvcAutoConfiguration" is added, as spring boot uses @EnableAutoconfiguration uses the configuartions that come with its dependencies
3. "HttpMessageConvertersAutoConfiguration" and "JacksonAutoConfiguration" are also autoconfigured with springboot "Jackson2ObjectMapperBuilderCustomizerConfiguration" this is does
4. "Servlet dispatcherServlet mapped to [/]" this handles the prime requests

FOLLOWING THE FRONT CONTROLLER PATTERN "dispatcherServlet is the front Controller"

5. @ResponseBody present inside @RestController, this is converts to the JSON body