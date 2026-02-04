package com.example.springboot.services;

import com.example.springboot.models.Usuario;
import com.example.springboot.storage.UsuarioStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;
import java.util.Collections;

/*
CustomUserDetailsService:
    Clase que implementa "UserDetailsService" para la recuperacion de
    los registros en la base de datos y posteriormente transforma el
    registro en un objeto validable por Spring Boot.

	Campos:
	* usuarios: Conexion con la tabla de usuarios en la base de datos.

    Documentacion:
    https://docs.spring.io/spring-security/reference/servlet/authentication/passwords/user-details-service.html
*/

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UsuarioStorage usuarios;

    // Busca al usuario en la base de datos a traves de su correo
    // Retorna un objeto de tipo "UserDetails" para que Spring Boot
    // pueda usar su propia implementacion de validacion.
    @Override
    public UserDetails loadUserByUsername(String correo) throws UsernameNotFoundException {
        Usuario usuario = usuarios.findByCorreo(correo);
        if (usuario == null) {
            throw new UsernameNotFoundException("No existe usuario con el correo: " + correo);
        }
        return new org.springframework.security.core.userdetails.User(
            usuario.getCorreo(),
            usuario.getClave(),
            Collections.emptyList()
        );
    }

}