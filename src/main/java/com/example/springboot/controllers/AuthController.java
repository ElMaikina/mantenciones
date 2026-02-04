package com.example.springboot.controllers;

import com.example.springboot.models.Usuario;
import com.example.springboot.repositories.UsuarioRepository;
import com.example.springboot.security.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    AuthenticationManager auth_manager;

    @Autowired
    UsuarioRepository repository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JWTUtil jwt_utils;

    @PostMapping("/signin")
    public String authenticateUsuario(@RequestBody Usuario usuario) {
        Authentication auth = auth_manager.authenticate(
            new UsernamePasswordAuthenticationToken(
                usuario.getCorreo(),
                usuario.getClave()
            )
        );
        UserDetails details = (UserDetails) auth.getPrincipal();
        return jwt_utils.generateToken(details.getUsername());
    }

    @PostMapping("/signup")
    public String registerUsuario(@RequestBody Usuario usuario) {
        if (repository.existsByCorreo(usuario.getCorreo())) {
            return "Error: Ya existe un usuario con ese correo!";
        }
        String contrasena = encoder.encode(usuario.getClave());
        usuario.setClave(contrasena);
        Usuario nuevo = new Usuario(usuario);
        repository.save(nuevo);
        return "Nuevo usuario registrado exitosamente!";
    }

}