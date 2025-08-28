package com.example.auth_service.security;

import java.security.*;
import java.util.Base64;
import java.io.*;

public class RsaKeyGenerator {

    public static void main(String[] args) throws Exception {
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
        keyGen.initialize(2048);
        KeyPair pair = keyGen.generateKeyPair();

        PrivateKey privateKey = pair.getPrivate();
        PublicKey publicKey = pair.getPublic();

        try (FileWriter out = new FileWriter("private.pem")) {
            out.write("-----BEGIN PRIVATE KEY-----\n");
            out.write(Base64.getEncoder().encodeToString(privateKey.getEncoded()));
            out.write("\n-----END PRIVATE KEY-----\n");
        }

        try (FileWriter out = new FileWriter("public.pem")) {
            out.write("-----BEGIN PUBLIC KEY-----\n");
            out.write(Base64.getEncoder().encodeToString(publicKey.getEncoded()));
            out.write("\n-----END PUBLIC KEY-----\n");
        }

        System.out.println("Keys generated.");
    }
}

