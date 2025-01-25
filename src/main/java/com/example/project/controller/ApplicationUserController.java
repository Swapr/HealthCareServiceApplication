package com.example.project.controller;


import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.project.Model.ApplicationUser;
import com.example.project.service.ApplicationUserService;


@RestController
public class ApplicationUserController {
	
	private ApplicationUserService applicationUserService;
	
	@Autowired
	public ApplicationUserController(ApplicationUserService applicationUserService) {
		this.applicationUserService=applicationUserService;
	}
	
	@PostMapping("/register")
	public ResponseEntity<String> registerApplicationUser(@RequestBody ApplicationUser applicationUser){
		
		return applicationUserService.registerAplicationUser(applicationUser);
	}
	
	
	@PostMapping("/signin")
	public ResponseEntity<Map<String, String>> signin(@RequestBody ApplicationUser applicationUser ){
		return applicationUserService.signin(applicationUser);
	}
	
	
	@GetMapping("/profile/{user}")
	public ApplicationUser getUSer(@PathVariable String user) {
		ApplicationUser user1=new ApplicationUser();
		user1.setUser_name(user);
		return user1;
	}

}
