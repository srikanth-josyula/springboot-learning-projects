package com.sample.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	/*@Autowired
	DataSource dataSource; */ // for JDBC 
	
	@Autowired
	UserDetailsService userDetailsService;

	// Using AuthenticationManagerBuilder for Authentication
	
	/**
	 * CASE-1 :: Using inMemory Authentication, this can be replaced by JDBC, LDAP, OAUth etc 
	 **/
	/*@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		//set configurations on the auth object
		
		auth.inMemoryAuthentication()
					.withUser("blah")
					.password("blah") //don't use clear text passwords
					.roles("USER")
					.and()
					.withUser("admin")
					.password("admin") 
					.roles("ADMIN");
	}*/
	
	/**
	 * CASE-2 :: Using jdbc Authentication 
	 * Tables are created by Spring boot itself https://docs.spring.io/spring-security/site/docs/3.0.x/reference/appendix-schema.html
	 **/
	/*@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication()
		.dataSource(dataSource)
		// .withDefaultSchema(); // this uses the default schema for User and authorities 
		.usersByUsernameQuery("select username,password,enable "
				+ "from users "
				+ "where username = ?")
		.authoritiesByUsernameQuery("select username,authority "
				+ "from authorities "
				+ "where username =?");
	}*/
	
	/**
	 * CASE-3 :: Using JPA custom table Authentication 
	 **/
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}
	
	// Using HttpSecurity for Authorization
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.antMatchers("/admin/**").hasRole("ADMIN")
			.antMatchers("/user/**").hasAnyRole("USER","ADMIN")
			.anyRequest().fullyAuthenticated()
			.and()
			.formLogin()
				.permitAll()
				.and()
		    .logout()
		    	.permitAll()
		    	.logoutSuccessUrl("/login")
		    	.and()
		    .exceptionHandling()
		    	.accessDeniedPage("/error/accessDenied")
		    	.and()
		     .csrf().disable();
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	};
}
