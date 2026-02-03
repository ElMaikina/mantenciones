package com.example.springboot.repository;

import com.example.springboot.model.Mantencion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MantencionRepository extends JpaRepository<Mantencion, Long> {
}
