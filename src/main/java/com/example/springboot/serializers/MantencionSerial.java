package com.example.springboot.serializers;

import com.example.springboot.models.Mantencion;
import java.time.LocalDate;

/*
Clase que sirve para modelar las mantenciones
visibles para un usuario.
*/

public class MantencionSerial {

    public long id;
    public LocalDate fecha;
    public int kilometros;
    public String ubicacion;
    public String detalle;

	public MantencionSerial() {
	}

	public MantencionSerial(Mantencion mantencion) {
		this.id = mantencion.getId();
		this.fecha = mantencion.getFecha();
		this.kilometros = mantencion.getKilometros();
		this.ubicacion = mantencion.getUbicacion();
		this.detalle = mantencion.getDetalle();		
	}

}