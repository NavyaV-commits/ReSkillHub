package com.example.api_gateway.security;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

	 @Bean
	    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
	        return http
	            .csrf(csrf -> csrf.disable())
	            .authorizeExchange(auth -> auth
	                .pathMatchers("/auth/**").permitAll()
	                .anyExchange().authenticated()
	            )
	            .oauth2ResourceServer(oauth2 -> oauth2.jwt())
	            
	            .build();
	    }
    
 }
