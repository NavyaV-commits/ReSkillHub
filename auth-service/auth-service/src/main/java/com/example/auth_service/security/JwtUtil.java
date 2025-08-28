package com.example.auth_service.security;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;
import java.util.Date;

@Component
public class JwtUtil {

	@Value("${jwt.secret}")
    private String secret; // load from properties ideally
	@Value("${jwt.expiration}")
    private long expirationMs;

	
	private final PrivateKey privateKey;

    public JwtUtil() throws Exception {
        byte[] keyBytes = Files.readAllBytes(Paths.get("private.pem"));
        String keyString = new String(keyBytes)
            .replaceAll("-----\\w+ PRIVATE KEY-----", "")
            .replaceAll("\\s", "");

        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(Base64.getDecoder().decode(keyString));
        privateKey = KeyFactory.getInstance("RSA").generatePrivate(keySpec);
    }
    
    public String generateToken(String username) {
    	System.out.println("Date "+new Date());
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expirationMs))
                .signWith(SignatureAlgorithm.RS256, privateKey)
                .compact();
        
        
    }
    
   
}

