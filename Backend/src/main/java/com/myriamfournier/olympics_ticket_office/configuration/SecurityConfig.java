package com.myriamfournier.olympics_ticket_office.configuration;
import com.myriamfournier.olympics_ticket_office.service.impl.UserDetailsServiceImpl;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import java.beans.BeanProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;

import com.myriamfournier.olympics_ticket_office.service.impl.UserServiceImpl;
import com.myriamfournier.olympics_ticket_office.service.UserService;


// import com.myriamfournier.olympics_ticket_office.configuration.JwtUtils;
// import com.myriamfournier.olympics_ticket_office.configuration.JwtFilter;

import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final UserDetailsService userService;
    /*private final JwtUtils jwtUtils;*/


   

    public SecurityConfig(UserDetailsService userService/* , JwtUtils jwtUtils*/) {
        this.userService = userService;
        /*this.jwtUtils = jwtUtils;*/
    }

    @Autowired
    private UserDetailsServiceImpl userDetailsServiceImpl;

    @Bean
    public UserDetailsService userDetailsService() {
        return userDetailsServiceImpl;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // This build allows only login and register endpoints to be accessed without authentication
        return http
            .cors(cors -> cors.configurationSource(request -> new CorsConfiguration().applyPermitDefaultValues()))
            .csrf(csrf -> csrf.disable())
            .csrf(csrf -> csrf.ignoringRequestMatchers("/api/user/register","/api/user/login","/api/user/username/{username}"))
            .authorizeHttpRequests(auth -> auth
                .requestMatchers(HttpMethod.OPTIONS, "/api/**").permitAll()
                .requestMatchers("/api/user/register").permitAll()
                .requestMatchers("/api/user/login").permitAll()
                .requestMatchers("/api/user/username/**").permitAll()
                .requestMatchers("/api/offer", "/api/offer/**").permitAll()
                .requestMatchers("/api/event", "/api/event/**").permitAll()
                .requestMatchers("/api/policy", "/api/policy/**").permitAll()
                .requestMatchers("/api/country", "/api/country/**").permitAll()
                .requestMatchers("/api/user").permitAll()
                .anyRequest().authenticated())
                // .addFilterBefore(new JwtFilter(userService, jwtUtils), UsernamePasswordAuthenticationFilter.class)
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


   

    // This Beans allows application to use CRUDS operations on the database 
    // and explicitly allows CORS for the specified endpoints
    // This is important for the frontend to be able to communicate with the backend
    @Bean
public WebMvcConfigurer corsConfigurer() {
    return new WebMvcConfigurer() {
        @Override
        public void addCorsMappings(CorsRegistry registry) {
            registry.addMapping("/api/**")
                .allowedOrigins("http://localhost:4200")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("*")
                .allowCredentials(true);
        }
    };
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
