package com.example.project.security;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;





@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ApiSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired 
	private JwtAuthenticationFilter jwtAuthenticationFilter;
	
public void configure(HttpSecurity httpSecurity) throws Exception {
		
		httpSecurity.csrf().disable()
		            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		            .and()
		            .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
		            .authorizeRequests()
		            .antMatchers("/signin","/register").permitAll()
		            .anyRequest().authenticated()
		            .and()
		            .formLogin().disable();
	    
	}

}