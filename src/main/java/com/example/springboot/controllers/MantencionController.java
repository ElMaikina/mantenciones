package com.example.springboot.controller;

import com.example.springboot.models.Vehiculo;
import com.example.springboot.models.Mantencion;
import com.example.springboot.storage.VehiculoStorage;
import com.example.springboot.storage.MantencionStorage;
import com.example.springboot.views.MantencionView;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.*;
import java.util.List;

@RestController
@RequestMapping("/vehiculos/{vehiculo_id}/mantenciones")
public class MantencionController {

    private final VehiculoStorage vehiculos;
    private final MantencionStorage mantenciones;

    public MantencionController(VehiculoStorage vehiculos, MantencionStorage mantenciones) {
        this.vehiculos = vehiculos;
        this.mantenciones = mantenciones;
    }

    @PostMapping
    public MantencionView create(@PathVariable Long vehiculo_id, @Valid @RequestBody Mantencion body) {
        Vehiculo vehiculo = vehiculos.findById(vehiculo_id).orElseThrow();
        body.setVehiculo(vehiculo);
        Mantencion m = mantenciones.save(body);
        MantencionView s = new MantencionView(m);
        return s;
    }

    @GetMapping
    public List<MantencionView> list(@PathVariable Long vehiculo_id) {
        return mantenciones.findByVehiculoId(vehiculo_id).stream()
            .map(m -> {
                MantencionView s = new MantencionView(m);
                return s;
            })
            .toList();
    }
}