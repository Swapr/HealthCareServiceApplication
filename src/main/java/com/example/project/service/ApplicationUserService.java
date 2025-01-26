package com.example.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.json.simple.JSONObject;

import com.example.project.Model.ApplicationUser;
import com.example.project.repository.ApplicationUserRepository;
import com.example.project.security.JwtUtil;



@Service
public class ApplicationUserService {
	
	private ApplicationUserRepository applicationUserRepository;
	private UserAuthService userAuthService;
	
	@Autowired
	public ApplicationUserService(ApplicationUserRepository applicationUserRepository,UserAuthService userAuthService) {
		this.applicationUserRepository=applicationUserRepository;
		this.userAuthService=userAuthService;
	}
	
	
	public ResponseEntity<String> registerAplicationUser(ApplicationUser applicationUser){
		
		try {
			applicationUserRepository.save(applicationUser);
			return ResponseEntity.status(HttpStatus.OK).body("Registration successful");
		} catch (Exception e) {
			// TODO: handle exception
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Password or username policy failed ");
		}
		
	}
	
	
	public ResponseEntity<Map<String,String>> signin(ApplicationUser applicationUser){
		try {
			String username= applicationUser.getUser_name();
			String token=userAuthService.GenerateToken(applicationUser);
			
			Map<String ,String > response=new HashMap<>();
			response.put("token", token);
			response.put("user_name", username);
			
			if(token!=null) {
				
				response.put("message", "Authentication successful!");
				
				return ResponseEntity.status(HttpStatus.OK).body(response);	
			}
			
			else {
				response.put("message", "Username or Password is Incorrect.");
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
			}
					
		} catch (Exception e) {
			// TODO: handle exception
		    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
		
		
	}
	
	
	public ApplicationUser viewProfile(String userid) {
		try {
			Optional<ApplicationUser> optional= applicationUserRepository.findById(userid);
			if(optional.isPresent()) {
				return optional.get();
			}
			return null;
		} catch (Exception e) {
			return null;
		}
		
	}
	
	

}

