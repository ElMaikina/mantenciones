package com.example.springboot.controller;

import com.example.springboot.models.Vehiculo;
import com.example.springboot.storage.VehiculoStorage;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.*;
import java.util.List;

/*
VehiculoController:
	Clase que administra la entrada y salida de los registros de vehiculos.
    Esta clase en particular permite: Crear, Leer, Actualizar y Borrar.

	Campos:
	* vehiculos: Conexion con la tabla de vehiculos en la base de datos.
*/

@RestController
@RequestMapping("/vehiculos")
@CrossOrigin(origins="*")
public class VehiculoController {

    private final VehiculoStorage vehiculos;

    public VehiculoController(VehiculoStorage vehiculos) {
        this.vehiculos = vehiculos;
    }

    @GetMapping
    public List<Vehiculo> search() {
        return vehiculos.findAll();
    }

    @GetMapping("/{id}")
    public Vehiculo find(@PathVariable Long id) {
        return vehiculos.findById(id).orElseThrow();
    }

    @PostMapping
    public Vehiculo create(@Valid @RequestBody Vehiculo body) {
		Vehiculo vehiculo = new Vehiculo(body);
        return vehiculos.save(vehiculo);
    }

    @PutMapping("/{id}")
    public Vehiculo update(@Valid @PathVariable Long id, @RequestBody Vehiculo body) {
		Vehiculo vehiculo = new Vehiculo(body);
        vehiculo.setId(id);
        return vehiculos.save(vehiculo);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        vehiculos.deleteById(id);
    }
}

