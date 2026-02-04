package com.example.springboot.models;

import jakarta.validation.constraints.*;
import jakarta.persistence.*;
import java.util.List;

/*
Vehiculo:
	Clase que representa los vehiculos registrados por el usuario.
	Cada vehiculo puede tener multiples mantenciones, sin embargo, cada
	mantencion corresponde a un unico vehiculo.
	Cada vehiculo se caracteriza por tener una patente unica en el
	marco legal chileno.

	Campos:
	* id: Numero entero que representa un valor unico entre cada vehiculo.
	* kilometros_actuales: Ultimo kilometraje reportado por el usuario.
	* kilometros_entre: Kilometraje ideal entre cada mantencion.
	* tipo: Tipo de vehiculo (Bus, Minibus, Camion, Auto, etc.).
	* observacion: Detalles especificos del vehiculo.
	* mantenciones: Lista de mantenciones hechas.
*/

@Entity
@Table(name="vehiculos")
public class Vehiculo {

    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

	@Column(name = "patente")
	@NotNull(message = "Debe indicar la patente unica del vehiculo")
	@Pattern(regexp = "[A-Za-z]{4}[-]?[0-9]{2}", message = "La patente ingresada es invalida")
    private String patente;

	@Column(name = "kilometros_actuales")
	@PositiveOrZero(message = "El kilometraje actual no puede ser negativo")
	@NotNull(message = "Debe indicar el kilometraje actual del vehiculo")
    private int kilometros_actuales;

	@Column(name = "kilometros_entre")
	@Positive(message = "El kilometraje entre mantenciones debe ser mayor a cero")
	@NotNull(message = "Debe indicar el kilometraje entre mantenciones")
    private int kilometros_entre;

	@Column(name = "tipo")
	@NotNull(message = "Debe indicar el tipo de vehiculo")
    private String tipo;

	@Column(name = "observacion")
    private String observacion;

    @OneToMany(mappedBy = "vehiculo", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Mantencion> mantenciones;

	public Vehiculo() {
	}

	public Vehiculo(Vehiculo vehiculo) {
		this.patente = vehiculo.getPatente().toUpperCase().replaceAll("-", "");
		this.kilometros_actuales = vehiculo.getKilometrosActuales();
		this.kilometros_entre = vehiculo.getKilometrosEntre();
		this.observacion = vehiculo.getObservacion();
		this.tipo = vehiculo.getTipo();
	}

	public long getId() {
		return id;
	}

	public String getPatente() {
		return patente;
	}

	public int getKilometrosActuales() {
		return kilometros_actuales;
	}

	public int getKilometrosEntre() {
		return kilometros_entre;
	}

	public String getTipo() {
		return tipo;
	}

	public String getObservacion() {
		return observacion;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setPatente(String patente) {
		this.patente = patente;
	}

	public void setKilometrosActuales(int kilometros) {
		this.kilometros_actuales = kilometros;
	}

	public void setKilometrosEntre(int kilometros) {
		this.kilometros_entre = kilometros;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public void Observacion(String observacion) {
		this.observacion = observacion;
	}

}