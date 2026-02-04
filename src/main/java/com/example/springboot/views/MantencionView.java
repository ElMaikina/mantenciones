package com.example.springboot.views;

import com.example.springboot.models.Mantencion;
import java.time.LocalDate;

/*
Clase que sirve para modelar las mantenciones
visibles para un usuario.
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