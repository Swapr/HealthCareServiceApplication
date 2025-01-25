package com.example.project.security;


import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;





@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter  {
	
	
	
	public void doFilterInternal(HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse, FilterChain filterChain) throws IOException, ServletException {
		
		    String authHeader=httpServletRequest.getHeader("Authorization");
		    if(authHeader!=null  && authHeader.startsWith("Bearer ")) {
		    	
		    	String token=authHeader.substring(7);
		    	
		    	if(JwtUtil.validateToken(token)) {
		    		
		    		String username=JwtUtil.extractUserName(token);
		    		
		    		UsernamePasswordAuthenticationToken authentication=new UsernamePasswordAuthenticationToken(username, null,new ArrayList<>());
		    		
		    		SecurityContextHolder.getContext().setAuthentication(authentication);;
		    		
		    	}
		    	
		    }
		
		filterChain.doFilter(httpServletRequest, httpServletResponse);
	}

}