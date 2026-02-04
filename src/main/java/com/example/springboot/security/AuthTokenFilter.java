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

@Component
public class AuthTokenFilter extends OncePerRequestFilter {

    @Autowired
    private JWTUtil jwt_utils;

    @Autowired
    private CustomUserDetailsService user_details_service;

    @Override
    protected void doFilterInternal(
        HttpServletRequest request,
        HttpServletResponse response,
        FilterChain filterChain
    ) throws ServletException, IOException {
        try {
            String jwt = parseJWT(request);
            if (jwt != null && jwt_utils.validateJWTToken(jwt)) {
                String correo = jwt_utils.getCorreoFromToken(jwt);
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

    private String parseJWT(HttpServletRequest request) {
        String headerAuth = request.getHeader("Authorization");
        if (headerAuth != null && headerAuth.startsWith("Bearer ")) {
            return headerAuth.substring(7);
        }
        return null;
    }
    
}