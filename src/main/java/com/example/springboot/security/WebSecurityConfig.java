package com.example.springboot.security;

import com.example.springboot.services.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class WebSecurityConfig {

    @Autowired
    CustomUserDetailsService user_details_service;

    @Autowired
    private AuthEntryPointJWT unauthorized_handler;

    @Bean
    public AuthTokenFilter authenticationJWTTokenFilter() {
        return new AuthTokenFilter();
    }

    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration authenticationConfiguration
    ) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // Updated configuration for Spring Security 6.x
        http
            .csrf(csrf -> csrf.disable()) // Disable CSRF
            .cors(cors -> cors.disable()) // Disable CORS (or configure if needed)
            .exceptionHandling(exceptionHandling ->
                exceptionHandling.authenticationEntryPoint(unauthorized_handler)
            )
            .sessionManagement(sessionManagement ->
                sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            )
            .authorizeHttpRequests(authorizeRequests ->
                authorizeRequests
                    .requestMatchers("/auth/**", "/").permitAll() // Use 'requestMatchers' instead of 'antMatchers'
                    .anyRequest().authenticated()
            );
        // Add the JWT Token filter before the UsernamePasswordAuthenticationFilter
        http.addFilterBefore(authenticationJWTTokenFilter(), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}