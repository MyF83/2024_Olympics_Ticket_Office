package com.myriamfournier.olympics_ticket_office.configuration;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Test class for JwtFilter to improve branch coverage
 */
@ExtendWith(MockitoExtension.class)
public class JwtFilterTest {

    @Mock
    private UserDetailsService userDetailsService;

    @Mock
    private JwtUtils jwtUtils;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private FilterChain filterChain;

    @InjectMocks
    private JwtFilter jwtFilter;

    @BeforeEach
    void setUp() {
        SecurityContextHolder.clearContext();
    }

    @Test
    void testDoFilterInternalWithoutAuthorizationHeader() throws ServletException, IOException {
        // Arrange
        when(request.getHeader("Authorization")).thenReturn(null);

        // Act
        jwtFilter.doFilterInternal(request, response, filterChain);

        // Assert
        verify(filterChain).doFilter(request, response);
        verify(jwtUtils, never()).extractUsername(anyString());
    }

    @Test
    void testDoFilterInternalWithInvalidAuthorizationHeader() throws ServletException, IOException {
        // Arrange
        when(request.getHeader("Authorization")).thenReturn("InvalidHeader");

        // Act
        jwtFilter.doFilterInternal(request, response, filterChain);

        // Assert
        verify(filterChain).doFilter(request, response);
        verify(jwtUtils, never()).extractUsername(anyString());
    }

    @Test
    void testDoFilterInternalWithValidBearerToken() throws ServletException, IOException {
        // Arrange
        String token = "validToken";
        String username = "testuser";
        UserDetails userDetails = mock(UserDetails.class);
        
        when(request.getHeader("Authorization")).thenReturn("Bearer " + token);
        when(jwtUtils.extractUsername(token)).thenReturn(username);
        when(jwtUtils.isTokenBlacklisted(token)).thenReturn(false);
        when(userDetailsService.loadUserByUsername(username)).thenReturn(userDetails);
        when(jwtUtils.validateToken(token, userDetails)).thenReturn(true);
        when(userDetails.getAuthorities()).thenReturn(new ArrayList<>());

        // Act
        jwtFilter.doFilterInternal(request, response, filterChain);

        // Assert
        verify(filterChain).doFilter(request, response);
        verify(jwtUtils).extractUsername(token);
        verify(jwtUtils).isTokenBlacklisted(token);
        verify(userDetailsService).loadUserByUsername(username);
        verify(jwtUtils).validateToken(token, userDetails);
    }

    @Test
    void testDoFilterInternalWithBlacklistedToken() throws ServletException, IOException {
        // Arrange
        String token = "blacklistedToken";
        String username = "testuser";
        
        when(request.getHeader("Authorization")).thenReturn("Bearer " + token);
        when(jwtUtils.extractUsername(token)).thenReturn(username);
        when(jwtUtils.isTokenBlacklisted(token)).thenReturn(true);

        // Act
        jwtFilter.doFilterInternal(request, response, filterChain);

        // Assert
        verify(filterChain).doFilter(request, response);
        verify(jwtUtils).extractUsername(token);
        verify(jwtUtils).isTokenBlacklisted(token);
        verify(userDetailsService, never()).loadUserByUsername(anyString());
    }

    @Test
    void testDoFilterInternalWithInvalidToken() throws ServletException, IOException {
        // Arrange
        String token = "invalidToken";
        String username = "testuser";
        UserDetails userDetails = mock(UserDetails.class);
        
        when(request.getHeader("Authorization")).thenReturn("Bearer " + token);
        when(jwtUtils.extractUsername(token)).thenReturn(username);
        when(jwtUtils.isTokenBlacklisted(token)).thenReturn(false);
        when(userDetailsService.loadUserByUsername(username)).thenReturn(userDetails);
        when(jwtUtils.validateToken(token, userDetails)).thenReturn(false);

        // Act
        jwtFilter.doFilterInternal(request, response, filterChain);

        // Assert
        verify(filterChain).doFilter(request, response);
        verify(jwtUtils).extractUsername(token);
        verify(jwtUtils).isTokenBlacklisted(token);
        verify(userDetailsService).loadUserByUsername(username);
        verify(jwtUtils).validateToken(token, userDetails);
    }

    @Test
    void testDoFilterInternalWithExceptionInTokenExtraction() throws ServletException, IOException {
        // Arrange
        String token = "malformedToken";
        
        when(request.getHeader("Authorization")).thenReturn("Bearer " + token);
        when(jwtUtils.extractUsername(token)).thenThrow(new RuntimeException("Invalid token format"));

        // Act
        jwtFilter.doFilterInternal(request, response, filterChain);

        // Assert
        verify(filterChain).doFilter(request, response);
        verify(jwtUtils).extractUsername(token);
        verify(userDetailsService, never()).loadUserByUsername(anyString());
    }

    @Test
    void testDoFilterInternalWithExistingAuthentication() throws ServletException, IOException {
        // Arrange
        String token = "validToken";
        String username = "testuser";
        
        // Set up existing authentication
        SecurityContextHolder.getContext().setAuthentication(
            mock(org.springframework.security.core.Authentication.class)
        );
        
        when(request.getHeader("Authorization")).thenReturn("Bearer " + token);
        when(jwtUtils.extractUsername(token)).thenReturn(username);

        // Act
        jwtFilter.doFilterInternal(request, response, filterChain);

        // Assert
        verify(filterChain).doFilter(request, response);
        verify(jwtUtils).extractUsername(token);
        verify(userDetailsService, never()).loadUserByUsername(anyString());
    }
}