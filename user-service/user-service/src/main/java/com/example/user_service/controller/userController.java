package com.example.user_service.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.user_service.repository.UserRepository;

@RestController
@RequestMapping("/user")
public class userController {

	@Autowired
	public UserRepository userRepository;
	
	@GetMapping("/hello")
    public ResponseEntity<String> get() {
       
        return ResponseEntity.ok("Hello user");
    }
	
	@GetMapping("/profile")
    public ResponseEntity<?> getProfile(Authentication authentication) {
        String email = authentication.getName();  // JWT subject (sub)

//        return userRepository.findByEmail(email)
//            .map(user -> Map.of(
//                "email", user.getEmail(),
//                "fullName", user.getFullName(),
//                "role", user.getRole()
//            ))
//            .map(ResponseEntity::ok);
        return ResponseEntity.ok("Hello user"+email);
            //.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found"));
    }
}
