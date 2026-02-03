package com.example.springboot.repositories;

import com.example.springboot.models.Mantencion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MantencionRepository extends JpaRepository<Mantencion, Long> {
    List<Mantencion> findByVehiculoId(Long vehiculoId);
}