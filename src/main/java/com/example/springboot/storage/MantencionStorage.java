package com.example.springboot.storage;

import com.example.springboot.models.Mantencion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MantencionStorage extends JpaRepository<Mantencion, Long> {
    List<Mantencion> findByVehiculoId(Long vehiculoId);
}