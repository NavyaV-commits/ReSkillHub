package com.example.api_gateway.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GatewayController {
	@GetMapping("/hello")
    public String hello() {
       
        return "Hello from gatwway server";
    }
	
	 @GetMapping("/login")
	    public String login() {
	        return "login"; // maps to login.html in templates
	    }
}
