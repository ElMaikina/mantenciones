package com.example.springboot.storage;

import com.example.springboot.models.Vehiculo;
import org.springframework.data.jpa.repository.JpaRepository;

/*
VehiculoStorage:
    Clase que sirve para definir los metodos para la extraccion, carga
    y transformacion de los registros de los vehiculos.
*/

public interface VehiculoStorage extends JpaRepository<Vehiculo, Long> {
}