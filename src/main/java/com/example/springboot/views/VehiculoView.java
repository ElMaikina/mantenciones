package com.example.springboot.views;

import com.example.springboot.models.Vehiculo;
import java.time.LocalDate;

/*
VehiculoView:
	Clase intermedia para transformar el registro de un vehiculo en
	una entidad visible para el usuario. Se crea a partir del modelo
	original y le quita ciertos campos para que el usuario no los vea.
*/

public class VehiculoView {

    public long id;
	public String patente;
    public int kilometros_actuales;
    public int kilometros_entre;
	public String observacion;
	public String tipo;

	public VehiculoView() {
	}

	public VehiculoView(Vehiculo vehiculo) {
		this.id = vehiculo.getId();
		this.patente = vehiculo.getPatente();
		this.kilometros_actuales = vehiculo.getKilometrosActuales();
		this.kilometros_entre = vehiculo.getKilometrosEntre();
		this.observacion = vehiculo.getObservacion();
		this.tipo = vehiculo.getTipo();
	}

}