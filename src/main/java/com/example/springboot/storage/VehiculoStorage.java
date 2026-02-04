package com.example.springboot.storage;

import com.example.springboot.models.Vehiculo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehiculoStorage extends JpaRepository<Vehiculo, Long> {
}