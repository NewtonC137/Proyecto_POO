package uts.poo.proyecto.util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

public class HashUtil {

    
    public static String sha256(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashed = md.digest(input.getBytes(StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder();
            for (byte b : hashed) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (Exception e) {
            throw new RuntimeException("Error hashing: " + e.getMessage(), e);
        }
    }

    
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Usage: java HashUtil <password>");
            return;
        }
        System.out.println(sha256(args[0]));
    }
}
