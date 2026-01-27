package com.example.springboot.controller;

import com.example.springboot.model.Vehicle;
import com.example.springboot.repository.VehicleRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vehicles")
@CrossOrigin(origins="*")
public class VehicleController {

    private final VehicleRepository repository;

    public VehicleController(VehicleRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Vehicle> getAll() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Vehicle getOne(@PathVariable Long id) {
        return repository.findById(id).orElseThrow();
    }

    @PostMapping
    public Vehicle create(@RequestBody Vehicle body) {
		Vehicle vehicle = new Vehicle(body);
        return repository.save(vehicle);
    }

    @PutMapping("/{id}")
    public Vehicle update(@PathVariable Long id, @RequestBody Vehicle body) {
		Vehicle vehicle = new Vehicle(body);
        vehicle.setId(id);
        return repository.save(vehicle);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        repository.deleteById(id);
    }
}

