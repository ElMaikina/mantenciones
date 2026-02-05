package com.example.springboot.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

/*
JWTUtil:
    Herramienta para la encripcion, lectura y validacion de los JWT.
    Utiliza criptografia para la inicializacion de las llaves y
    los parametros de configuracion se inyectan a traves de las
    anotaciones.

	Campos:
	* jwt_secret: String que contiene el valor clave para la autenticacion. 
    Con el fin de respetar los principios de REST, decidi usar el id.
	* jwt_expiration_ms: Tiempo de validez en milisegundos para el JWT.
	* key: Llave para la encriptacion y desencriptacion de los datos.
*/

@Component
public class JWTUtil {

    @Value("${jwt.secret}")
    private String jwt_secret;

    @Value("${jwt.expiration}")
    private int jwt_expiration_ms;

    private SecretKey key;

    // Inicializa la llave luego de crear el objeto
    // Se inyecta el JWT, evitando la creacion repetida y mejorando el rendimiento
    @PostConstruct
    public void init() {
        this.key = Keys.hmacShaKeyFor(jwt_secret.getBytes(StandardCharsets.UTF_8));
    }

    // Genera el JWT a partir del correo del usuario
    public String generateToken(String correo) {
        return Jwts.builder()
            .setSubject(correo)
            .setIssuedAt(new Date())
            .setExpiration(new Date((new Date()).getTime() + jwt_expiration_ms))
            .signWith(key, SignatureAlgorithm.HS256)
            .compact();
    }

    // Obtiene el correo del usuario a partir del token
    public String extractSecret(String token) {
        return Jwts.parserBuilder()
            .setSigningKey(key).build()
            .parseClaimsJws(token)
            .getBody()
            .getSubject();
    }

    // Valida el token del JWT
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        }
        catch (SecurityException e) {
            System.out.println("Firma del JWT invalida: " + e.getMessage());
        }
        catch (MalformedJwtException e) {
            System.out.println("Token del JWT invalido: " + e.getMessage());
        }
        catch (ExpiredJwtException e) {
            System.out.println("Token del JWT expirado: " + e.getMessage());
        }
        catch (UnsupportedJwtException e) {
            System.out.println("Token del JWT no soportado: " + e.getMessage());
        }
        catch (IllegalArgumentException e) {
            System.out.println("JWT indica que el string es vacio: " + e.getMessage());
        }
        return false;
    }

}