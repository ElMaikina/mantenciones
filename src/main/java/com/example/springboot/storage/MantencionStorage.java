package com.example.springboot.storage;

import com.example.springboot.models.Mantencion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/*
MantencionStorage:
    Clase que sirve para definir los metodos para la extraccion, carga
    y transformacion de los registros de las mantenciones.
*/

public interface MantencionStorage extends JpaRepository<Mantencion, Long> {
    List<Mantencion> findByVehiculoId(Long vehiculoId);
}