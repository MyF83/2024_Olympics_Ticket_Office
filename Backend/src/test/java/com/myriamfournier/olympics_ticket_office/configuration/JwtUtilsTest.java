package com.myriamfournier.olympics_ticket_office.configuration;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Date;

/**
 * Test class for JwtUtils utility
 * Tests JWT token generation, validation, and blacklisting functionality
 */
public class JwtUtilsTest {

    private JwtUtils jwtUtils;
    private UserDetails mockUserDetails;

    @BeforeEach
    void setUp() {
        jwtUtils = new JwtUtils();
        
        // Set test values using reflection since they're @Value injected
        ReflectionTestUtils.setField(jwtUtils, "secretKey", "testSecretKeyThatIsLongEnoughForHS256Algorithm");
        ReflectionTestUtils.setField(jwtUtils, "expirationTime", 86400000L); // 24 hours
        
        // Create mock UserDetails
        mockUserDetails = mock(UserDetails.class);
        when(mockUserDetails.getUsername()).thenReturn("testuser");
    }

    @Test
    void testGenerateToken() {
        // Test token generation
        String username = "testuser";
        String token = jwtUtils.generateToken(username);
        
        assertNotNull(token);
        assertFalse(token.isEmpty());
        assertTrue(token.contains(".")); // JWT tokens contain dots
        
        // Verify the token contains the username
        String extractedUsername = jwtUtils.extractUsername(token);
        assertEquals(username, extractedUsername);
    }

    @Test
    void testGenerateTokenForDifferentUsers() {
        // Test token generation for multiple users
        String user1 = "user1";
        String user2 = "user2";
        
        String token1 = jwtUtils.generateToken(user1);
        String token2 = jwtUtils.generateToken(user2);
        
        assertNotNull(token1);
        assertNotNull(token2);
        assertNotEquals(token1, token2);
        
        assertEquals(user1, jwtUtils.extractUsername(token1));
        assertEquals(user2, jwtUtils.extractUsername(token2));
    }

    @Test
    void testExtractUsername() {
        // Test username extraction from token
        String username = "testuser";
        String token = jwtUtils.generateToken(username);
        
        String extractedUsername = jwtUtils.extractUsername(token);
        
        assertEquals(username, extractedUsername);
    }

    @Test
    void testValidateTokenValid() {
        // Test token validation with valid token
        String username = "testuser";
        String token = jwtUtils.generateToken(username);
        
        Boolean isValid = jwtUtils.validateToken(token, mockUserDetails);
        
        assertTrue(isValid);
    }

    @Test
    void testValidateTokenInvalidUsername() {
        // Test token validation with mismatched username
        String username = "testuser";
        String token = jwtUtils.generateToken(username);
        
        UserDetails wrongUserDetails = mock(UserDetails.class);
        when(wrongUserDetails.getUsername()).thenReturn("wronguser");
        
        Boolean isValid = jwtUtils.validateToken(token, wrongUserDetails);
        
        assertFalse(isValid);
    }

    // @Test
    // void testValidateTokenExpired() {
    //     // Test token validation with expired token
    //     jwtUtils = new JwtUtils();
    //     ReflectionTestUtils.setField(jwtUtils, "secretKey", "testSecretKeyThatIsLongEnoughForHS256Algorithm");
    //     ReflectionTestUtils.setField(jwtUtils, "expirationTime", -1000L); // Expired token
    //     
    //     String username = "testuser";
    //     String token = jwtUtils.generateToken(username);
    //     
    //     // Wait a moment to ensure expiration
    //     try { Thread.sleep(100); } catch (InterruptedException e) {}
    //     
    //     Boolean isValid = jwtUtils.validateToken(token, mockUserDetails);
    //     
    //     assertFalse(isValid);
    // }

    @Test
    void testIsTokenValid() {
        // Test token structure validation
        String username = "testuser";
        String token = jwtUtils.generateToken(username);
        
        Boolean isValid = jwtUtils.isTokenValid(token);
        
        assertTrue(isValid);
    }

    @Test
    void testIsTokenValidInvalidToken() {
        // Test token structure validation with invalid token
        String invalidToken = "invalid.token.structure";
        
        Boolean isValid = jwtUtils.isTokenValid(invalidToken);
        
        assertFalse(isValid);
    }

    @Test
    void testBlacklistToken() {
        // Test token blacklisting
        String username = "testuser";
        String token = jwtUtils.generateToken(username);
        
        // Verify token is valid before blacklisting
        assertTrue(jwtUtils.validateToken(token, mockUserDetails));
        assertFalse(jwtUtils.isTokenBlacklisted(token));
        
        // Blacklist the token
        jwtUtils.blacklistToken(token);
        
        // Verify token is now blacklisted and invalid
        assertTrue(jwtUtils.isTokenBlacklisted(token));
        assertFalse(jwtUtils.validateToken(token, mockUserDetails));
    }

    @Test
    void testBlacklistNullToken() {
        // Test blacklisting null token (should not throw exception)
        assertDoesNotThrow(() -> jwtUtils.blacklistToken(null));
    }

    @Test
    void testInvalidateTokensForUser() {
        // Test invalidating all tokens for a user
        String username = "testuser";
        String token1 = jwtUtils.generateToken(username);
        String token2 = jwtUtils.generateToken(username);
        
        // Verify both tokens are valid
        assertTrue(jwtUtils.validateToken(token1, mockUserDetails));
        assertTrue(jwtUtils.validateToken(token2, mockUserDetails));
        
        // Invalidate all tokens for the user
        jwtUtils.invalidateTokensForUser(username);
        
        // Verify both tokens are now blacklisted
        assertTrue(jwtUtils.isTokenBlacklisted(token1));
        assertTrue(jwtUtils.isTokenBlacklisted(token2));
        assertFalse(jwtUtils.validateToken(token1, mockUserDetails));
        assertFalse(jwtUtils.validateToken(token2, mockUserDetails));
    }

    @Test
    void testInvalidateTokensForNullUser() {
        // Test invalidating tokens for null user (should not throw exception)
        assertDoesNotThrow(() -> jwtUtils.invalidateTokensForUser(null));
    }

    @Test
    void testInvalidateTokensForUserWithNoTokens() {
        // Test invalidating tokens for user with no existing tokens
        String username = "userWithNoTokens";
        
        assertDoesNotThrow(() -> jwtUtils.invalidateTokensForUser(username));
    }

    @Test
    void testTokenTrackingAfterGeneration() {
        // Test that tokens are properly tracked after generation
        String username = "testuser";
        String token1 = jwtUtils.generateToken(username);
        String token2 = jwtUtils.generateToken(username);
        
        // Generate token for different user
        String anotherUser = "anotheruser";
        String token3 = jwtUtils.generateToken(anotherUser);
        
        // Invalidate tokens for first user only
        jwtUtils.invalidateTokensForUser(username);
        
        // Verify first user's tokens are blacklisted
        assertTrue(jwtUtils.isTokenBlacklisted(token1));
        assertTrue(jwtUtils.isTokenBlacklisted(token2));
        
        // Verify other user's token is still valid
        assertFalse(jwtUtils.isTokenBlacklisted(token3));
        
        UserDetails anotherUserDetails = mock(UserDetails.class);
        when(anotherUserDetails.getUsername()).thenReturn(anotherUser);
        assertTrue(jwtUtils.validateToken(token3, anotherUserDetails));
    }

    @Test
    void testExtractAllClaims() {
        // Test claims extraction
        String username = "testuser";
        String token = jwtUtils.generateToken(username);
        
        var claims = jwtUtils.extractAllClaims(token);
        
        assertNotNull(claims);
        assertEquals(username, claims.getSubject());
        assertNotNull(claims.getIssuedAt());
        assertNotNull(claims.getExpiration());
        assertTrue(claims.getExpiration().after(new Date()));
    }

    @Test
    void testExtractAllClaimsInvalidToken() {
        // Test claims extraction with invalid token
        String invalidToken = "invalid.token.structure";
        
        assertThrows(Exception.class, () -> {
            jwtUtils.extractAllClaims(invalidToken);
        });
    }

    // @Test
    // void testMultipleTokenGenerationAndValidation() {
    //     // Test generating and validating multiple tokens
    //     String username = "testuser";
    //     
    //     // Generate multiple tokens
    //     String token1 = jwtUtils.generateToken(username);
    //     String token2 = jwtUtils.generateToken(username);
    //     String token3 = jwtUtils.generateToken(username);
    //     
    //     // All should be valid initially
    //     assertTrue(jwtUtils.validateToken(token1, mockUserDetails));
    //     assertTrue(jwtUtils.validateToken(token2, mockUserDetails));
    //     assertTrue(jwtUtils.validateToken(token3, mockUserDetails));
    //     
    //     // Blacklist only one specific token
    //     jwtUtils.blacklistToken(token2);
    //     
    //     // Verify token2 is blacklisted
    //     assertTrue(jwtUtils.isTokenBlacklisted(token2));
    //     
    //     // Verify only the blacklisted token is invalid
    //     assertTrue(jwtUtils.validateToken(token1, mockUserDetails));
    //     assertFalse(jwtUtils.validateToken(token2, mockUserDetails));
    //     // Don't test token3 validation as it may have side effects
    // }

    @Test
    void testTokenExpirationBoundary() {
        // Test token near expiration boundary
        String username = "testuser";
        String token = jwtUtils.generateToken(username);
        
        // Token should be valid immediately after generation
        assertTrue(jwtUtils.validateToken(token, mockUserDetails));
        
        // Extract expiration to verify it's in the future
        var claims = jwtUtils.extractAllClaims(token);
        Date expiration = claims.getExpiration();
        Date now = new Date();
        assertTrue(expiration.after(now));
    }

    @Test
    void testBlacklistTokenWithInvalidTokenForTracking() {
        // Test blacklisting a token that can't be parsed for username tracking
        String invalidToken = "invalid.token.that.cannot.be.parsed";
        
        // Should not throw exception even though username can't be extracted
        assertDoesNotThrow(() -> jwtUtils.blacklistToken(invalidToken));
        
        // Token should still be blacklisted
        assertTrue(jwtUtils.isTokenBlacklisted(invalidToken));
    }
}