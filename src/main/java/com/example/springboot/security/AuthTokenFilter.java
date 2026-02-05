package com.example.springboot.security;

import com.example.springboot.services.CustomUserDetailsService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.authentication.*;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;

/*
AuthTokenFilter:
    Clase que implementa "UserDetailsService" para la recuperacion de
    los registros en la base de datos y posteriormente transforma el
    registro en un objeto validable por Spring Boot.

	Campos:
	* jwt_utils: Herramienta para lectura de los JWT.
	* user_details_service: Implementacion personalizada para validacion.

    Documentacion:
    https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/web/filter/OncePerRequestFilter.html
    https://www.baeldung.com/spring-onceperrequestfilter
*/

@Component
public class AuthTokenFilter extends OncePerRequestFilter {

    @Autowired
    private JWTUtil jwt_utils;

    @Autowired
    private CustomUserDetailsService user_details_service;

    // Lee el JWT desde el header, obtiene el correo, corrobora 
    // que existe un usuario con ese correo y finalmente genera
    // una autenticacion para dicho usuario.
    @Override
    protected void doFilterInternal(
        HttpServletRequest request,
        HttpServletResponse response,
        FilterChain filterChain
    ) throws ServletException, IOException {
        try {
            String jwt = parseJWT(request);
            if (jwt != null && jwt_utils.validateToken(jwt)) {
                String correo = jwt_utils.extractSecret(jwt);
                UserDetails user_details = user_details_service.loadUserByUsername(correo);
                UsernamePasswordAuthenticationToken auth =
                    new UsernamePasswordAuthenticationToken(
                        user_details,
                        null,
                        user_details.getAuthorities()
                    );
                auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(auth);
                System.out.println("El usuario pudo autenticarse exitosamente!");
            }
        }
        catch (Exception e) {
            System.out.println("El usuario no pudo autenticarse: " + e);
        }
        filterChain.doFilter(request, response);
    }

    // Extrae el token desde el header de la respuesta HTTP.
    // Corrobora que el string cumpla con el formato basico.
    private String parseJWT(HttpServletRequest request) {
        String headerAuth = request.getHeader("Authorization");
        if (headerAuth != null && headerAuth.startsWith("Bearer ")) {
            return headerAuth.substring(7);
        }
        return null;
    }
    
}