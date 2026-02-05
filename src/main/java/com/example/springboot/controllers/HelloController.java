package com.example.springboot.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/*
HelloController:
	Clase que sirve netamente para saber si el servidor esta
	funcionando correctamente. No tiene parametros ni metodos
	adicionales.
*/

@RestController
public class HelloController {

	@GetMapping("/")
	public String index() {
		return "Hola mundo!";
	}
}
