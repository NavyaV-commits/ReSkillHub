package com.example.auth_service.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.auth_service.dto.RegisterRequest;
import com.example.auth_service.exception.UnauthenticatedAccessException;
import com.example.auth_service.exception.UserNotFoundException;
import com.example.auth_service.model.User;
import com.example.auth_service.repository.UserRepository;
import com.example.auth_service.security.JwtUtil;
import com.example.auth_service.service.UserService;

@RestController
@RequestMapping("/auth")
public class authController {
	
	@Autowired
	public JwtUtil jwtUtil;
	
	@Autowired
	public UserRepository userRepository;
	
	@Autowired
	public UserService userService;
	
	@Autowired
	public PasswordEncoder passwordEncoder;

	@GetMapping("/hello")
    public ResponseEntity<String> get() {
       
        return ResponseEntity.ok("Hello from Auth server");
    }
	
	@PostMapping("/register")
	public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
	    if (userRepository.existsByEmail(request.getEmail())) {
	        return ResponseEntity.status(HttpStatus.CONFLICT).body("Email already in use");
	    }

	    User res = userService.registerUser(request);


	    return ResponseEntity.status(HttpStatus.CREATED).body("User registered successfully");
	}
	
	@PostMapping("/token")
    public ResponseEntity<Map<String, String>> getToken(@RequestBody Map<String, String> request) {
        String username = request.get("username");
        String password =  request.get("password");
        
        Optional<User> userOptional = userRepository.findByEmail(username);
        if (userOptional.isEmpty() || 
            !passwordEncoder.matches(password, userOptional.get().getPassword())) {
        	Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", "Invalid credentials");
            new UserNotFoundException("User not found with email: " + username);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);
        }

        String token = jwtUtil.generateToken(username);

        Map<String, String> response = new HashMap<>();
        response.put("token", token);
        
        return ResponseEntity.ok(response);
    }
	
	@GetMapping("/profile")
	public String profile(@AuthenticationPrincipal OAuth2User principal) {
		if (principal == null) {
	        throw new UnauthenticatedAccessException("No authenticated user found");
	    }
	    return "Hello, " + principal.getAttribute("name");
	}
	
	
}
