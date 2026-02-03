package com.example.springboot.controller;

import com.example.springboot.model.Mantencion;
import com.example.springboot.repository.MantencionRepository;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.*;
import java.util.List;

@RestController
@RequestMapping("/mantenciones")
@CrossOrigin(origins="*")
public class MantencionController {

    private final MantencionRepository repository;

    public MantencionController(MantencionRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Mantencion> getAll() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Mantencion getOne(@PathVariable Long id) {
        return repository.findById(id).orElseThrow();
    }

    @PostMapping
    public Mantencion create(@Valid @RequestBody Mantencion body) {
		Mantencion Mantencion = new Mantencion(body);
        return repository.save(Mantencion);
    }

    @PutMapping("/{id}")
    public Mantencion update(@Valid @PathVariable Long id, @RequestBody Mantencion body) {
		Mantencion Mantencion = new Mantencion(body);
        Mantencion.setId(id);
        return repository.save(Mantencion);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        repository.deleteById(id);
    }
}

