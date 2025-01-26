package com.example.project.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.project.Model.ApplicationUser;
import com.example.project.repository.ApplicationUserRepository;
import com.example.project.security.JwtUtil;



@Service
public class UserAuthService  {
	private ApplicationUserRepository applicationUserRepository;
	
	@Autowired
	public UserAuthService(ApplicationUserRepository applicationUserRepository) {
		this.applicationUserRepository=applicationUserRepository;
	}
	
	
	public String GenerateToken(ApplicationUser applicationUser) {
		try {
			String username=applicationUser.getUser_name();
			String password=applicationUser.getPassword();
		    Optional<ApplicationUser> optional= applicationUserRepository.findById(username);
		    if(optional.isPresent()) {
		    	if(password.equals(optional.get().getPassword())) {
		    		return JwtUtil.generateToken(username);    		
		    	}
		    }
		    return null;
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
		
	}

}