package com.example.springboot.model;

import com.example.springboot.model.Vehiculo;
import jakarta.validation.constraints.*;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;

/*
Clase que sirve para modelar las mantenciones
de un vehiculo en particular.
*/

@Entity
@Table(name="Mantenciones")
public class Mantencion {

    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

	@Column(name = "fecha")
	@NotNull(message = "La fecha es obligatoria")
    private LocalDate fecha;

	@Column(name = "kilometros")
	@NotNull(message = "Los Kilometros de la mantencion son obligatorios")
    private int kilometros;

    @ManyToOne
    @JoinColumn(name = "vehiculo_id")
    private Vehiculo vehiculo;

	public Mantencion() {
	}

	public Mantencion(Mantencion mantencion) {
		this.fecha = mantencion.getFecha();
		this.kilometros = mantencion.getKilometros();
		this.vehiculo = mantencion.getVehiculo();
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

}