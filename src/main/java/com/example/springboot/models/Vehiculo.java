package com.example.springboot.model;

import jakarta.validation.constraints.*;
import jakarta.persistence.*;
import java.util.List;

/*
Clase que sirve para modelar los distintos tipos de vehiculo
a los cuales se les hace la mantencion.
*/

@Entity
@Table(name="Vehiculos")
public class Vehiculo {

    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

	@Column(name = "patente")
	@NotNull(message = "La patente unica es obligatoria")
	@Pattern(regexp = "[A-Za-z]{4}[-]?[0-9]{2}", message = "La patente ingresada es invalida")
    private String patente;

	@NotNull(message = "Los kilometros entre mantenciones son obligatorios")
	@Column(name = "kilometros")
    private int kilometros;

	@NotNull(message = "El tipo de vehiculo es obligatorio")
	@Column(name = "tipo")
    private String tipo;

	public Vehiculo() {
	}

	public Vehiculo(Vehiculo vehiculo) {
		this.patente = vehiculo.getPatente().toUpperCase().replaceAll("-", "");
		this.kilometros = vehiculo.getKilometros();
		this.tipo = vehiculo.getTipo();
	}

	public long getId() {
		return id;
	}

	public String getPatente() {
		return patente;
	}

	public int getKilometros() {
		return kilometros;
	}

	public String getTipo() {
		return tipo;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setPatente(String patente) {
		this.patente = patente;
	}

	public void setKilometros(int kilometros) {
		this.kilometros = kilometros;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

}