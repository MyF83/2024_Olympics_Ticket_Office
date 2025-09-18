 package com.myriamfournier.olympics_ticket_office.configuration;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Value;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
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

    public String generateToken(String username) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, username);
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
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
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
         return Jwts.parserBuilder()
        .setSigningKey(getSignKey())
        .build()
        .parseClaimsJws(token)
        .getBody();
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