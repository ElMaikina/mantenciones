package com.example.springboot.controller;

import com.example.springboot.models.Vehiculo;
import com.example.springboot.models.Mantencion;
import com.example.springboot.repositories.VehiculoRepository;
import com.example.springboot.repositories.MantencionRepository;
import com.example.springboot.serializers.MantencionSerial;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.*;
import java.util.List;

@RestController
@RequestMapping("/vehiculos/{vehiculo_id}/mantenciones")
public class MantencionController {

    private final VehiculoRepository vehiculos;
    private final MantencionRepository mantenciones;

    public MantencionController(VehiculoRepository vehiculos, MantencionRepository mantenciones) {
        this.vehiculos = vehiculos;
        this.mantenciones = mantenciones;
    }

    @PostMapping
    public MantencionSerial create(@PathVariable Long vehiculo_id, @Valid @RequestBody Mantencion body) {
        Vehiculo vehiculo = vehiculos.findById(vehiculo_id).orElseThrow();
        body.setVehiculo(vehiculo);
        Mantencion m = mantenciones.save(body);
        MantencionSerial s = new MantencionSerial(m);
        return s;
    }

    @GetMapping
    public List<MantencionSerial> list(@PathVariable Long vehiculo_id) {
        return mantenciones.findByVehiculoId(vehiculo_id).stream()
            .map(m -> {
                MantencionSerial s = new MantencionSerial(m);
                return s;
            })
            .toList();
    }
}