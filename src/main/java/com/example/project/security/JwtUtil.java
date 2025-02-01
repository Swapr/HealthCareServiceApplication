package com.example.project.security;

import java.util.Date;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;



@Component
public class JwtUtil {
	
	@Autowired
	private Environment env;
	
	private static  String secretKey;
	private static long timeout;
	
	@PostConstruct
	public void init() {
		secretKey=env.getProperty("jwt.secret");
		timeout=Long.parseLong(env.getProperty("jwt.token.validity"));
	}
	
	
	public static String generateToken(String username) {
		return Jwts.builder()
		    .setSubject(username)
		    .setIssuedAt(new Date())
		    .setExpiration(new Date(System.currentTimeMillis()+timeout))
		    .signWith(SignatureAlgorithm.HS256, secretKey)
		    .compact();
	}
	
	
	public static boolean validateToken(String token) {
		   Claims claims=getClaims(token);
		   
		   if(claims.getExpiration().before(new Date(System.currentTimeMillis()))){
			   System.out.println("token is expired");
			   return false;
		   }
		   return true;
		   	   
	}
	
    public static Claims getClaims(String token) {
	 
    	return   Jwts.parser()
	             .setSigningKey(secretKey)
	             .parseClaimsJws(token)
	             .getBody();
    }
	
    public static String extractUserName(String token) {
    	return getClaims(token).getSubject();
    }
	

}