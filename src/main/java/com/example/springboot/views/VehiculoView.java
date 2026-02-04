package com.example.springboot.views;

import com.example.springboot.models.Vehiculo;
import java.time.LocalDate;

/*
Clase que sirve para modelar los vehiculos
visibles para un usuario.
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