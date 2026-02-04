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

/*
WebSecurityConfig:
    Clase que almacena la configuracion de seguridad web para toda la
    aplicacion. Define el uso de CORS, CSRF, handlers de excepciones
    y autorizacion respecto a los endpoints.

	Campos:
	* user_details_service: Implementacion personalizada para validacion.
	* unauthorized_handler: Handler para accesos no autorizados.

    Documentacion:
    https://docs.spring.io/spring-security/reference/servlet/architecture.html
    https://medium.com/@CodeWithTech/understanding-cors-and-csrf-a-guide-for-spring-security-feb34b81a3a4
    https://www.baeldung.com/spring-security-custom-filter
*/

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

    // Metodo de encripcion para la contrasena de los usuarios
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // Establece la configuracion general de seguridad para la aplicacion.
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // Updated configuration for Spring Security 6.x
        http
            .csrf(csrf -> csrf.disable()) // Desabilita CSRF
            .cors(cors -> cors.disable()) // Desabilita CORS
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
        // Agregar el filtro por JWT antes que UsernamePasswordAuthenticationFilter
        http.addFilterBefore(authenticationJWTTokenFilter(), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}