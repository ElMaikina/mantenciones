package com.example.springboot.security;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import java.io.IOException;

/*
AuthEntryPointJWT:
    Clase que implementa "AuthenticationEntryPoint" y hace de punto
    de entrada en Spring Security para validar si un usuario esta
    autenticado y autorizado para acceder a un recurso.

    Documentacion:
    https://docs.spring.io/spring-security/reference/api/java/org/springframework/security/web/AuthenticationEntryPoint.html
    https://stackoverflow.com/questions/57426668/what-is-the-purpose-of-authenticationentrypoint-in-spring-web-security
*/

@Component
public class AuthEntryPointJWT implements AuthenticationEntryPoint {

    // Funcion que autentica y autoriza a un cliente para acceder
    // a un recurso en especifico. Normalmente no returna nada
    // pero arroja una excepcion si falla.
    @Override
    public void commence(
        HttpServletRequest request,
        HttpServletResponse response,
        AuthenticationException authException
    ) throws IOException {
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Error: No autorizado");
    }

}