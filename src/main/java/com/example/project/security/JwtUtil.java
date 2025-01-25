package com.example.project.security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.example.project.Model.ApplicationUser;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;



@Component
public class JwtUtil {
	
	private static final String secretKey="swapnil";
	
	public static String generateToken(String username) {
		return Jwts.builder()
		    .setSubject(username)
		    .setIssuedAt(new Date())
		    .setExpiration(new Date(System.currentTimeMillis()+1200000))
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