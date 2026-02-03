package com.example.springboot.dto;

import com.example.springboot.models.Vehiculo;
import java.time.LocalDate;

/*
Clase que sirve para modelar los vehiculos
visibles para un usuario (DTO).
*/

public class VehiculoDTO {

    private long id;
	private String patente;
    private int kilometros;
	private String tipo;

	public VehiculoDTO() {
	}

	public VehiculoDTO(Vehiculo vehiculo) {
		this.id = vehiculo.getId();
		this.patente = vehiculo.getPatente();
		this.kilometros = vehiculo.getKilometros();
		this.tipo = vehiculo.getTipo();
	}

}