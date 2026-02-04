package com.example.springboot.models;

import jakarta.validation.constraints.*;
import jakarta.persistence.*;
import java.util.List;

/*
Clase que sirve para modelar a los usuarios de la aplicacion.
*/

@Entity
@Table(name="usuarios")
public class Usuario {

    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

	@Column(name = "nombre")
	@NotNull(message = "Debe registrarse con su nombre y apellido")
    private String nombre;

	@Column(name = "correo")
	@Email(message = "Debe ingresar un correo valido")
	@NotNull(message = "Debe registrarse con un correo")
    private String correo;

	@Column(name = "clave")
	@NotNull(message = "Debe registrarse una contrasena")
    private String clave;

    //@OneToMany(mappedBy = "vehiculo", cascade = CascadeType.ALL, orphanRemoval = true)
    //private List<Mantencion> mantenciones;

	public Usuario() {
	}

	public Usuario(Usuario usuario) {
		this.nombre = usuario.getNombre();
		this.correo = usuario.getCorreo();
		this.clave = usuario.getClave();
	}

	public long getId() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}

	public String getCorreo() {
		return correo;
	}

	public String getClave() {
		return clave;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

}