 package com.myriamfournier.olympics_ticket_office.configuration;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Value;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.security.Key;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import io.jsonwebtoken.Jwts;;

@Component
public class JwtUtils {

    @Value("${jwt.secret}")
    private String secretKey;

    @Value("${jwt.expiration}")
    private long expirationTime;
    
    // Set to store blacklisted/invalidated tokens
    private final Set<String> tokenBlacklist = new HashSet<>();
    
    // Map to track tokens by username
    private final Map<String, Set<String>> userTokensMap = new HashMap<>();

    public String generateToken(String username) {
        Map<String, Object> claims = new HashMap<>();
        String token = createToken(claims, username);
        
        // Track this token for the user
        userTokensMap.computeIfAbsent(username, k -> new HashSet<>()).add(token);
        
        return token;
    }

    private String createToken(Map<String, Object> claims, String subject) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expirationTime))
                .signWith(getSignKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    private Key getSignKey() {
        byte[] keyBytes = secretKey.getBytes();
        return new SecretKeySpec(keyBytes, SignatureAlgorithm.HS256.getJcaName());
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        // First, check if the token is blacklisted
        if (isTokenBlacklisted(token)) {
            return false;
        }
        
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
    
    /**
     * Checks if token is valid regardless of expiration
     * Used for validating structure during logout
     */
    public Boolean isTokenValid(String token) {
        try {
            extractAllClaims(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    /**
     * Add a token to the blacklist to invalidate it
     * @param token The token to blacklist
     */
    public void blacklistToken(String token) {
        if (token == null) return;
        
        tokenBlacklist.add(token);
        
        // Also track by username if possible
        try {
            String username = extractUsername(token);
            if (username != null) {
                userTokensMap.computeIfAbsent(username, k -> new HashSet<>()).add(token);
            }
        } catch (Exception e) {
            // Ignore extraction errors for invalid tokens
            System.err.println("Could not extract username from token for tracking");
        }
    }
    
    /**
     * Invalidate all tokens for a specific user
     * @param username The username to invalidate tokens for
     */
    public void invalidateTokensForUser(String username) {
        if (username == null) return;
        
        Set<String> userTokens = userTokensMap.get(username);
        if (userTokens != null) {
            tokenBlacklist.addAll(userTokens);
        }
        
        // Start a fresh set for this user
        userTokensMap.put(username, new HashSet<>());
    }
    
    /**
     * Check if a token is blacklisted
     * @param token The token to check
     * @return true if the token is blacklisted, false otherwise
     */
    public boolean isTokenBlacklisted(String token) {
        return tokenBlacklist.contains(token);
    }

    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date(System.currentTimeMillis()));
    }

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

     //the io.jsonwebtoken (JJWT) library, the API has changed.
       // Jwts.parser() is deprecated and replaced by Jwts.parserBuilder()
    //private Claims extractAllClaims(String token) {
       // return Jwts.parser()
         //   .setSigningKey(getSignKey())
         //   .parseClaimsJws(token)
         //   .getBody();
   // }

  
  /*  private Claims extractAllClaims(String token) {
       return Jwts.parserBuilder()
        .setSigningKey(getSignKey())
        .build()
        .parseClaimsJws(token)
        .getBody(); 
    }*/

public Claims extractAllClaims(String token) {
        /*return ((JwtParser) Jwts.parser().setSigningKey(getSignKey()))
                .parseClaimsJws(token)
                .getBody();*/
        try {
            return Jwts.parserBuilder()
            .setSigningKey(getSignKey())
            .build()
            .parseClaimsJws(token)
            .getBody();
        } catch (Exception e) {
            // Log the exception
            System.err.println("Error extracting claims from token: " + e.getMessage());
            throw e; // Re-throw to be handled by calling methods
        }
    }

private Claims getAllClaimsFromToken(String token) {
    /*return Jwts.parser()
        .verifyWith(getPublicSigningKey())
        .build()
        .parseSignedClaims(token)
        .getPayload();*/
        return Jwts.parserBuilder()
        .setSigningKey(getPublicSigningKey())
        .build()
        .parseClaimsJws(token)
        .getBody();
}

private SecretKey getPublicSigningKey() {
     byte[] keyBytes = secretKey.getBytes();
    return new SecretKeySpec(keyBytes, SignatureAlgorithm.HS256.getJcaName());
    }
}