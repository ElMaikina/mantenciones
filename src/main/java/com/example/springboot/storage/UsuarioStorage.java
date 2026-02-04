package com.example.springboot.storage;

import com.example.springboot.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioStorage extends JpaRepository<Usuario, Long> {
    Usuario findByCorreo(String correo);
    boolean existsByCorreo(String correo);
}