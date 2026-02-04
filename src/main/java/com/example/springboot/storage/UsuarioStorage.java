package com.example.springboot.storage;

import com.example.springboot.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

/*
UsuarioStorage:
    Clase que sirve para definir los metodos para la extraccion, carga
    y transformacion de los registros de los usuarios.
*/

public interface UsuarioStorage extends JpaRepository<Usuario, Long> {
    Usuario findByCorreo(String correo);
    boolean existsByCorreo(String correo);
}