/**
 * 
 */
package com.projectab.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

/**
 * @author Bhupesh
 *
 */
@SpringBootApplication
@EnableWebSecurity
public class Application extends WebSecurityConfigurerAdapter {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/", "/webjars/**", "/project-ab/", "/project-ab/**").permitAll()
				.anyRequest()
				// replaced the below line by .antMatcher(...)
				// .anyRequest()
				.authenticated().and().exceptionHandling().and().csrf()
				.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());
		// below line will generate a default Spring form
		// .and().httpBasic();
		// .loginPage("/project-ab/login").permitAll();
	}

}
