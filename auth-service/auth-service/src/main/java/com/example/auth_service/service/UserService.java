package com.example.auth_service.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.auth_service.dto.RegisterRequest;
import com.example.auth_service.model.User;
import com.example.auth_service.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	public PasswordEncoder passwordEncoder;
	
	@Autowired
	public UserRepository userRepository;
	
	public User registerUser(RegisterRequest request) {
		
		User user = new User();
	    user.setEmail(request.getEmail());
	    user.setFullName(request.getUserName());
	    user.setPassword(passwordEncoder.encode(request.getPassword()));
	    
		 return userRepository.save(user);
	}
}
