package com.example.springboot.controller;

import com.example.springboot.models.Vehiculo;
import com.example.springboot.models.Mantencion;
import com.example.springboot.repositories.VehiculoRepository;
import com.example.springboot.repositories.MantencionRepository;
import com.example.springboot.dto.MantencionDTO;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.*;
import java.util.List;

@RestController
@RequestMapping("/vehiculos/{vehiculoId}/mantenciones")
public class MantencionController {

    private final VehiculoRepository vehiculoRepo;
    private final MantencionRepository mantencionRepo;

    public MantencionController(VehiculoRepository vehiculoRepo, MantencionRepository mantencionRepo) {
        this.vehiculoRepo = vehiculoRepo;
        this.mantencionRepo = mantencionRepo;
    }

    @PostMapping
    public Mantencion create(@PathVariable Long vehiculoId, @RequestBody Mantencion mantencion) {
        Vehiculo vehiculo = vehiculoRepo.findById(vehiculoId).orElseThrow();
        mantencion.setVehiculo(vehiculo);
        return mantencionRepo.save(mantencion);
    }

    @GetMapping
    public List<MantencionDTO> list(@PathVariable Long vehiculoId) {
        return mantencionRepo.findByVehiculoId(vehiculoId).stream()
            .map(m -> {
                MantencionDTO dto = new MantencionDTO(m);
                return dto;
            })
            .toList();
    }
}