package com.myriamfournier.olympics_ticket_office.configuration;
import com.myriamfournier.olympics_ticket_office.service.impl.UserDetailsServiceImpl;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import java.beans.BeanProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;



import com.myriamfournier.olympics_ticket_office.service.impl.UserServiceImpl;

import java.util.Arrays;

import com.myriamfournier.olympics_ticket_office.service.UserService;


import com.myriamfournier.olympics_ticket_office.configuration.JwtUtils;
import com.myriamfournier.olympics_ticket_office.configuration.JwtFilter;

import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
public class SecurityConfig implements WebMvcConfigurer{

    
    private final UserDetailsService userService;
    private final JwtUtils jwtUtils;
    
    @Autowired
     private JwtFilter jwtFilter; // <-- Injection of the JwtFilter here

   

    public SecurityConfig(UserDetailsService userService , JwtUtils jwtUtils) {
        this.userService = userService;
        this.jwtUtils = jwtUtils;
    }

    @Autowired
    private UserDetailsServiceImpl userDetailsServiceImpl;

    @Bean
    public UserDetailsService userDetailsService() {
        return userDetailsServiceImpl;
    }

    /*@Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // Use the global CorsConfigurationSource bean by just calling .cors()
        return http
            .cors() // <-- Use the global CORS configuration bean I have defined in my Bean CorsConfigurationSource()
            .and()
            .csrf(csrf -> csrf.disable())
            .csrf(csrf -> csrf.ignoringRequestMatchers("/api/user/register","/api/user/login","/api/user/username/{username}","/api/user/{user_id}","/api/user/carts/user"))
            .authorizeHttpRequests(auth -> auth
                .requestMatchers(HttpMethod.OPTIONS, "/api/**").permitAll()
                .requestMatchers("/api/user/register").permitAll()
                .requestMatchers("/api/user/login").permitAll()
                .requestMatchers("/api/user/**").permitAll()
                .requestMatchers("/api/user/username/**").permitAll()
                .requestMatchers("/api/offer", "/api/offer/**").permitAll()
                .requestMatchers("/api/event", "/api/event/**").permitAll()
                .requestMatchers("/api/policy", "/api/policy/**").permitAll()
                .requestMatchers("/api/country", "/api/country/**").permitAll()
                .requestMatchers("/api/user").permitAll()
                .requestMatchers("/api/user/carts/user").authenticated()
                .anyRequest().authenticated())
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                //.addFilterBefore(new JwtFilter(userService, jwtUtils), UsernamePasswordAuthenticationFilter.class)
            .build();
    }*/

@Bean
public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    return http
        .cors()
        .and()
        .csrf(csrf -> csrf.disable())
        .sessionManagement(session -> session
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .authorizeHttpRequests(auth -> auth
            .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
            .requestMatchers("/").permitAll()
            .requestMatchers("/error").permitAll() // Add this line
            .requestMatchers("/api/user/register").permitAll()
            .requestMatchers("/api/user/login").permitAll()
            .requestMatchers("/api/user/**").permitAll()
            .requestMatchers("/api/offer/**").permitAll()
            .requestMatchers("/api/event/**").permitAll()
            .requestMatchers("/api/policy/**").permitAll()
            .requestMatchers("/api/country/**").permitAll()
            .requestMatchers("/api/user/carts/user").authenticated()
            .anyRequest().permitAll()) // Changed from authenticated() to permitAll()
        .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
        .build();
}

    // This Bean allows the application to use BCryptPasswordEncoder for password encoding
    @Bean 
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // This Bean allows the application to use the AuthenticationManagerBuilder to build an authentication manager
    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http, PasswordEncoder passwordEncoder) throws Exception{
        AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.userDetailsService(userService).passwordEncoder(passwordEncoder);
        return authenticationManagerBuilder.build();
    }

    /*@Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("http://localhost", "http://localhost:80", "http://localhost:4200"));
        configuration.setAllowedMethods(Arrays.asList("GET","POST","PUT","DELETE","OPTIONS"));
        configuration.setAllowedHeaders(Arrays.asList("Authorization", "Cache-Control", "Content-Type", "Accept", "X-Requested-With"));
        configuration.setExposedHeaders(Arrays.asList("Authorization"));
        configuration.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/api/**", configuration);
        return source;
    }*/
@Bean
public CorsConfigurationSource corsConfigurationSource() {
    CorsConfiguration configuration = new CorsConfiguration();
    configuration.setAllowedOrigins(Arrays.asList("*")); // Allow all origins temporarily for testing
    configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS", "HEAD"));
    configuration.setAllowedHeaders(Arrays.asList(
        "*"  // Allow all headers temporarily for testing
    ));
    configuration.setExposedHeaders(Arrays.asList("Authorization"));
    configuration.setAllowCredentials(false); // Changed to false since we're using "*"
    configuration.setMaxAge(3600L);

    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", configuration);
    return source;
}

    // Old version :
    /* 
    .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
    .and()
    .formLogin().disable()
    .authorizeHttpRequests(registry -> registry
        .requestMatchers("/api/auth/login", "/api/**", "/").permitAll()
        .anyRequest().authenticated() // or .permitAll() if you want to protect other endpoints
    )
    .exceptionHandling()
        .authenticationEntryPoint((request, response, authException) -> {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
        })
    ; // Disable default login page for REST

    return http.build();*/

}
