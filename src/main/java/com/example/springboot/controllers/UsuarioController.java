package com.example.springboot.controller;

import com.example.springboot.models.Usuario;
import com.example.springboot.storage.UsuarioStorage;
import com.example.springboot.security.JWTUtil;
import com.example.springboot.security.AuthTokenFilter;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.beans.factory.annotation.Autowired;

import jakarta.validation.*;
import java.util.List;

/*
UsuarioController:
	Clase que administra la entrada y salida de los registros de usuarios.
    Esta clase en particular permite: Crear, Leer, Actualizar y Borrar.

	Campos:
	* usuarios: Conexion con la tabla de usuarios en la base de datos.
*/

@RestController
@RequestMapping("/usuarios")
@CrossOrigin(origins="*")
public class UsuarioController {

    @Autowired
    private JWTUtil jwt_utils;

    private final UsuarioStorage usuarios;

    public UsuarioController(UsuarioStorage usuarios) {
        this.usuarios = usuarios;
    }

    @GetMapping("/{id}")
    public Usuario find(@PathVariable Long id,
        @RequestHeader (name="Authorization") String header
    ) {
        try {
            String jwt = header.substring(7); // Quita el "Bearer "
            String correo = jwt_utils.extractSecret(jwt);
            Usuario usuario = usuarios.findById(id).orElseThrow();
            if (correo.equals(usuario.getCorreo()))
                return usuario;
        }
        catch (Exception e) {
            System.out.println("Usuario no autorizado: " + e.getMessage());
        }
        return null;
    }

    /*
    @PostMapping
    public Usuario create(@Valid @RequestBody Usuario body) {
		Usuario vehiculo = new Usuario(body);
        return usuarios.save(vehiculo);
    }

    @PutMapping("/{id}")
    public Usuario update(@Valid @PathVariable Long id, @RequestBody Usuario body) {
		Usuario vehiculo = new Usuario(body);
        vehiculo.setId(id);
        return usuarios.save(vehiculo);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        usuarios.deleteById(id);
    }
    */
}

