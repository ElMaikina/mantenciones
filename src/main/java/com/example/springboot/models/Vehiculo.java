package com.example.springboot.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name="vehiculos")
public class Vehiculo {

    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

	@Column(name="patente")
    private String patente;

	@Column(name="unidad")
    private String unidad;

	@Column(name="kilometraje")
    private int kilometraje;

	public Vehiculo() {
	}

	public Vehiculo(Vehiculo vehiculo) {
		this.patente = vehiculo.getPatente();
		this.unidad = vehiculo.getUnidad();
		this.kilometraje = vehiculo.getKilometraje();
	}

	public long getId() {
		return id;
	}
	public String getPatente() {
		return patente;
	}
	public String getUnidad() {
		return unidad;
	}
	public int getKilometraje() {
		return kilometraje;
	}

	public void setId(Long id) {
		this.id = id;
	}
	public void setType(String patente) {
		this.patente = patente;
	}
	public void setUnidad(String unidad) {
		this.unidad = unidad;
	}
	public void setKilometraje(Integer kilometraje) {
		this.kilometraje = kilometraje;
	}

}

