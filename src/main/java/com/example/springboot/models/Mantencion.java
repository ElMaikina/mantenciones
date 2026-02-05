package com.example.springboot.models;

import jakarta.validation.constraints.*;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;

/*
Mantencion:
	Clase que representa las mantenciones hechas en un mismo vehiculo.
	Cada vehiculo puede tener multiples mantenciones, sin embargo, cada
	mantencion corresponde a un unico vehiculo.
	Las mantenciones guardan informacion adicional para analisis posterior
	si el usuario lo desea.

	Campos:
	* id: Numero entero que representa un valor unico entre cada mantencion.
	* fecha: Fecha en la cual se hizo la mantencion (formato: YYYY-mm-DD).
	* kilometros: Kilometraje reportado al momento de la mantencion.
	* ubicacion: Lugar en donde se hizo la mantencion.
	* detalle: Detalles u observaciones relevantes.
	* vehiculo: Vehiculo al cual corresponde.
*/

@Entity
@Table(name="mantenciones")
public class Mantencion {

    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

	@Column(name = "fecha")
	@NotNull(message = "Debe indicar la fecha al momento de la mantencion")
    private LocalDate fecha;

	@Column(name = "kilometros")
	@PositiveOrZero(message = "El kilometraje al momento de la mantencion no puede ser negativo")
	@NotNull(message = "Debe indicar el kilometraje al momento de la mantencion")
    private int kilometros;

	@Column(name = "ubicacion")
    private String ubicacion;

	@Column(name = "detalle")
    private String detalle;

    @ManyToOne
	@JoinColumn(name = "vehiculo_id")
	@NotNull(message = "Debe indicar a que vehiculo se le hizo la mantencion")
    private Vehiculo vehiculo;

	public Mantencion() {
	}

	public Mantencion(Mantencion mantencion) {
		this.fecha = mantencion.getFecha();
		this.kilometros = mantencion.getKilometros();
		this.ubicacion = mantencion.getUbicacion();
		this.vehiculo = mantencion.getVehiculo();
		this.detalle = mantencion.getDetalle();
	}

	public long getId() {
		return id;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public int getKilometros() {
		return kilometros;
	}

	public String getUbicacion() {
		return ubicacion;
	}

	public String getDetalle() {
		return detalle;
	}

	public Vehiculo getVehiculo() {
		return vehiculo;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public void setKilometros(int kilometros) {
		this.kilometros = kilometros;
	}

	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}

	public void setDetalle(String detalle) {
		this.detalle = detalle;
	}

	public void setVehiculo(Vehiculo vehiculo) {
		this.vehiculo = vehiculo;
	}

}