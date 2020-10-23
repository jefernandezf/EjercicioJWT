package com.example.jwt.jwtdemo;

import javax.servlet.Filter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.jwt.jwtdemo.security.JWTAuthorizationFilter;

@SpringBootApplication
public class JwtDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(JwtDemoApplication.class, args);
	}
	
	@EnableWebSecurity
	@Configuration
	class WebSecurityConfig extends WebSecurityConfigurerAdapter{
		
   @Override
	protected void configure(HttpSecurity http) throws Exception{
		http.csrf().disable()
		.addFilterAfter(new JWTAuthorizationFilter(),UsernamePasswordAuthenticationFilter.class )
		.authorizeRequests()
		.antMatchers(HttpMethod.POST, "/user").permitAll()
		.anyRequest().authenticated();
	}
	}

}
