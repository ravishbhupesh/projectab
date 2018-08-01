/**
 * 
 */
package com.projectab.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.web.context.request.RequestContextListener;
import org.thymeleaf.extras.springsecurity4.dialect.SpringSecurityDialect;

import com.projectab.app.service.ABUserDetailsService;

/**
 * @author Bhupesh
 *
 */
@SpringBootApplication
@EnableWebSecurity
public class Application extends WebSecurityConfigurerAdapter {

	@Autowired
	private ABUserDetailsService userDetailsService;

	public static void main(String[] args) {
		System.out.println("Application::main");
		SpringApplication.run(Application.class, args);
	}

	protected void configure(HttpSecurity http) throws Exception {
		System.out.println("Application::configure");
		http.authorizeRequests().antMatchers("/webjars/**", "/project-ab/", "/project-ab/**").permitAll().anyRequest()
				// replaced the below line by .antMatcher(...)
				// .anyRequest()
				.authenticated().and().formLogin().permitAll().loginPage("/project-ab/login")
				.loginProcessingUrl("/project-ab/loginAuthenticate").defaultSuccessUrl("/project-ab")
				.failureUrl("/project-ab/login").and().exceptionHandling().and().csrf()
				.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()).and().logout().permitAll();
		// below line will generate a default Spring form
		// .and().httpBasic();
		// .loginPage("/project-ab/login").permitAll();
	}

	@Bean
	public RequestContextListener requestContextListener() {
		System.out.println("Application::requestContextListener");
		return new RequestContextListener();
	}

	@Bean
	public SpringSecurityDialect springSecurityDialect() {
		System.out.println("Application::springSecurityDialect");
		return new SpringSecurityDialect();
	}

	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		System.out.println("Application::authenticationProvider");
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(userDetailsService);
		authProvider.setPasswordEncoder(encoder());
		return authProvider;
	}

	@Bean
	public PasswordEncoder encoder() {
		System.out.println("Application::encoder");
		return NoOpPasswordEncoder.getInstance();
		// return new BCryptPasswordEncoder(11);
	}

}
