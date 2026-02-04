package com.example.springboot.models;

import com.example.springboot.models.Vehiculo;
import jakarta.validation.constraints.*;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;

/*
Clase que sirve para modelar las mantenciones
de un vehiculo en particular.
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