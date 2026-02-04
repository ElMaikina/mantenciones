package com.example.springboot.views;

import com.example.springboot.models.Mantencion;
import java.time.LocalDate;

/*
MantencionView:
	Clase intermedia para transformar el registro de una mantencion en
	una entidad visible para el usuario. Se crea a partir del modelo
	original y le quita ciertos campos para que el usuario no los vea.
*/

public class MantencionView {

    public long id;
    public LocalDate fecha;
    public int kilometros;
    public String ubicacion;
    public String detalle;

	public MantencionView() {
	}

	public MantencionView(Mantencion mantencion) {
		this.id = mantencion.getId();
		this.fecha = mantencion.getFecha();
		this.kilometros = mantencion.getKilometros();
		this.ubicacion = mantencion.getUbicacion();
		this.detalle = mantencion.getDetalle();		
	}

}