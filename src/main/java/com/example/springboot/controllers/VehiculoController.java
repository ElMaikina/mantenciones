package com.example.springboot.controller;

import com.example.springboot.model.Vehiculo;
import com.example.springboot.repository.VehiculoRepository;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.*;
import java.util.List;

@RestController
@RequestMapping("/vehiculos")
@CrossOrigin(origins="*")
public class VehiculoController {

    private final VehiculoRepository repository;

    public VehiculoController(VehiculoRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Vehiculo> getAll() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Vehiculo getOne(@PathVariable Long id) {
        return repository.findById(id).orElseThrow();
    }

    @PostMapping
    public Vehiculo create(@Valid @RequestBody Vehiculo body) {
		Vehiculo vehiculo = new Vehiculo(body);
        return repository.save(vehiculo);
    }

    @PutMapping("/{id}")
    public Vehiculo update(@Valid @PathVariable Long id, @RequestBody Vehiculo body) {
		Vehiculo vehiculo = new Vehiculo(body);
        vehiculo.setId(id);
        return repository.save(vehiculo);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        repository.deleteById(id);
    }
}

