package com.example.springboot.services;

import com.example.springboot.models.Usuario;
import com.example.springboot.storage.UsuarioStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;
import java.util.Collections;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UsuarioStorage repository;

    @Override
    public UserDetails loadUserByUsername(String correo) throws UsernameNotFoundException {
        Usuario usuario = repository.findByCorreo(correo);
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