package com.example.springSecurity.util;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.util.Date;

public class JwtUtil {

    private static final String SECRET = "mysecretkeymysecretkeymysecretkey123";

    private static final Key key = Keys.hmacShaKeyFor(SECRET.getBytes());

    // Generate JWT Token
    public static String generateToken(String username) {

        return Jwts.builder()
                .setSubject(username)
                .setIssuer("springSecurity")
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60)) // 1 hour
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    // Extract username
    public static String getUsername(String token) {

        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    // Validate token
    public static boolean validateToken(String token) {

        try {
            Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token);
            return true;

        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }
}
