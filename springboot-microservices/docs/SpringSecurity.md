### Properties of Spring Security
* Adds a mandatory authentication for URLs
* If opened in browser, adds login form 
* Handles Login errors
* Creates a user and sets a default password 
	* Once the security jar dependency is added we can see in logs
	* Using generated security password: 470d3a2e-1242-4a15-b196-8c4b22cc7605
	* Once we provide this without password if we try hitting the service we receive unauthorized message 
	* then we need to use Basic-Auth , Username :: user (by default) password :: <sys genearted key in logs>
* Spring Security generates a new password each time you start the app 
* we can override the default values using properties 
	- spring.security.user.name=admin
	- spring.security.user.password=admin
* Provides a LOGIN and LOGOUT Default Page
	
### Working
* In normal Spring applications we add, <filter> tags in web.xml, which does the filtering activity
* This is handled by Spring Security
* Spring Security holds the input and output using a athentication object, the output is a object holding the user details
* Spring Security has AuthenticationProvider interface to do the authentication, using method authenticate()
* Input(credentials) ----> Output(principle of logged in user)
* A Application can have multiple Authentication Providers (LDAP, OAUTH, etc).
* These different Authentication Providers work with each other using Authentication Manager

### Data Source tables OOTB used by spring
* https://docs.spring.io/spring-security/site/docs/3.0.x/reference/appendix-schema.html

| 									User Schema													  |
| ------------------------------------------------------------------------------------------------|
| create table users(																			  |
|      username varchar_ignorecase(50) not null primary key,									  |
|     password varchar_ignorecase(50) not null,													  |
|      enabled boolean not null);																  |
|																								  |
|  create table authorities (																	  |
|      username varchar_ignorecase(50) not null,						      			          |
|      authority varchar_ignorecase(50) not null,												  |
|      constraint fk_authorities_users foreign key(username) references users(username));         |
| 