package com.example.springboot.dto;

import com.example.springboot.models.Mantencion;
import java.time.LocalDate;

/*
Clase que sirve para modelar las mantenciones
visibles para un usuario (DTO).
*/

public class MantencionDTO {

    private long id;
    private LocalDate fecha;
    private int kilometros;


	public MantencionDTO() {
	}

	public MantencionDTO(Mantencion mantencion) {
        this.id = mantencion.getId();
		this.fecha = mantencion.getFecha();
		this.kilometros = mantencion.getKilometros();
	}

}