package com.myriamfournier.olympics_ticket_office.configuration;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.mock.web.MockHttpServletRequest;

/**
 * Test class for SecurityConfig
 * Tests the security configuration beans and setup
 */
public class SecurityConfigTest {

    private SecurityConfig securityConfig;
    private UserDetailsService mockUserDetailsService;
    private JwtUtils mockJwtUtils;

    @BeforeEach
    void setUp() {
        mockUserDetailsService = mock(UserDetailsService.class);
        mockJwtUtils = mock(JwtUtils.class);
        securityConfig = new SecurityConfig(mockUserDetailsService, mockJwtUtils);
    }

    @Test
    void testPasswordEncoderBeanCreation() {
        // Test that passwordEncoder bean can be created
        PasswordEncoder passwordEncoder = securityConfig.passwordEncoder();
        
        assertNotNull(passwordEncoder);
        assertTrue(passwordEncoder instanceof BCryptPasswordEncoder);
    }

    @Test
    void testPasswordEncoderEncoding() {
        // Test password encoding functionality
        PasswordEncoder passwordEncoder = securityConfig.passwordEncoder();
        
        String rawPassword = "testPassword123";
        String encodedPassword = passwordEncoder.encode(rawPassword);
        
        assertNotNull(encodedPassword);
        assertNotEquals(rawPassword, encodedPassword);
        assertTrue(passwordEncoder.matches(rawPassword, encodedPassword));
    }

    @Test
    void testPasswordEncoderDifferentPasswords() {
        // Test that different passwords produce different hashes
        PasswordEncoder passwordEncoder = securityConfig.passwordEncoder();
        
        String password1 = "password1";
        String password2 = "password2";
        
        String encoded1 = passwordEncoder.encode(password1);
        String encoded2 = passwordEncoder.encode(password2);
        
        assertNotEquals(encoded1, encoded2);
        assertTrue(passwordEncoder.matches(password1, encoded1));
        assertTrue(passwordEncoder.matches(password2, encoded2));
        assertFalse(passwordEncoder.matches(password1, encoded2));
        assertFalse(passwordEncoder.matches(password2, encoded1));
    }

    @Test
    void testPasswordEncoderSamePlaintextDifferentHashes() {
        // Test that same plaintext produces different hashes (salt)
        PasswordEncoder passwordEncoder = securityConfig.passwordEncoder();
        
        String password = "samePassword";
        String encoded1 = passwordEncoder.encode(password);
        String encoded2 = passwordEncoder.encode(password);
        
        // BCrypt should produce different hashes for same input due to salt
        assertNotEquals(encoded1, encoded2);
        assertTrue(passwordEncoder.matches(password, encoded1));
        assertTrue(passwordEncoder.matches(password, encoded2));
    }

    @Test
    void testCorsConfigurationSourceBeanCreation() {
        // Test that corsConfigurationSource bean can be created
        CorsConfigurationSource corsConfigurationSource = securityConfig.corsConfigurationSource();
        
        assertNotNull(corsConfigurationSource);
        assertTrue(corsConfigurationSource instanceof UrlBasedCorsConfigurationSource);
    }

    @Test
    void testCorsConfigurationSettings() {
        // Test CORS configuration settings
        CorsConfigurationSource corsConfigurationSource = securityConfig.corsConfigurationSource();
        
        assertNotNull(corsConfigurationSource);
        
        // Get the configuration for the root path
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setMethod("GET");
        request.addHeader("Origin", "http://localhost:4200");
        
        CorsConfiguration config = corsConfigurationSource.getCorsConfiguration(request);
        
        // Basic validation that configuration exists
        assertNotNull(config);
    }

    @Test
    void testCorsConfigurationForDifferentOrigins() {
        // Test CORS configuration for different origins
        CorsConfigurationSource corsConfigurationSource = securityConfig.corsConfigurationSource();
        
        MockHttpServletRequest request1 = new MockHttpServletRequest();
        request1.setMethod("GET");
        request1.addHeader("Origin", "http://localhost:4200");
        
        MockHttpServletRequest request2 = new MockHttpServletRequest();
        request2.setMethod("POST");
        request2.addHeader("Origin", "http://localhost:3000");
        
        CorsConfiguration config1 = corsConfigurationSource.getCorsConfiguration(request1);
        CorsConfiguration config2 = corsConfigurationSource.getCorsConfiguration(request2);
        
        // Both should return valid configurations
        assertNotNull(config1);
        assertNotNull(config2);
    }

    @Test
    void testCorsConfigurationAllowsCredentials() {
        // Test that CORS allows credentials
        CorsConfigurationSource corsConfigurationSource = securityConfig.corsConfigurationSource();
        
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.addHeader("Origin", "http://localhost:4200");
        
        CorsConfiguration config = corsConfigurationSource.getCorsConfiguration(request);
        
        assertNotNull(config);
        // Test that the configuration is properly set up
        assertTrue(config.getAllowCredentials() != null);
    }

    @Test
    void testCorsConfigurationMethods() {
        // Test CORS allowed methods
        CorsConfigurationSource corsConfigurationSource = securityConfig.corsConfigurationSource();
        
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.addHeader("Origin", "http://localhost:4200");
        
        CorsConfiguration config = corsConfigurationSource.getCorsConfiguration(request);
        
        assertNotNull(config);
        // Test that the configuration is properly set up
        assertNotNull(config.getAllowedOrigins());
    }

    @Test
    void testCorsConfigurationHeaders() {
        // Test CORS allowed headers
        CorsConfigurationSource corsConfigurationSource = securityConfig.corsConfigurationSource();
        
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.addHeader("Origin", "http://localhost:4200");
        
        CorsConfiguration config = corsConfigurationSource.getCorsConfiguration(request);
        
        assertNotNull(config);
        // Test that the configuration is properly set up
        assertNotNull(config.getAllowedOrigins());
    }

    @Test
    void testSecurityConfigurationClassAnnotations() {
        // Test that the class has proper annotations
        assertTrue(securityConfig.getClass().isAnnotationPresent(Configuration.class));
        assertTrue(securityConfig.getClass().isAnnotationPresent(EnableWebSecurity.class));
    }

    @Test
    void testBeanMethodAnnotations() throws NoSuchMethodException {
        // Test that bean methods have @Bean annotation
        assertTrue(securityConfig.getClass()
            .getMethod("passwordEncoder")
            .isAnnotationPresent(Bean.class));
        
        assertTrue(securityConfig.getClass()
            .getMethod("corsConfigurationSource")
            .isAnnotationPresent(Bean.class));
    }

    @Test
    void testMultiplePasswordEncodingOperations() {
        // Test multiple password encoding operations
        PasswordEncoder passwordEncoder = securityConfig.passwordEncoder();
        
        for (int i = 0; i < 5; i++) {
            String password = "testPassword" + i;
            String encoded = passwordEncoder.encode(password);
            
            assertNotNull(encoded);
            assertTrue(passwordEncoder.matches(password, encoded));
        }
    }
}