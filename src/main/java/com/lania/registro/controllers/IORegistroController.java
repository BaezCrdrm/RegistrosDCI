package com.lania.registro.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lania.registro.models.IORegistro;
import com.lania.registro.services.IORegistroService;

@RestController
@RequestMapping(path = "/ioregistro")
public class IORegistroController {
	@Autowired
	private IORegistroService ioRegistroService;
	
	@GetMapping("/list")
	public List<IORegistro> listaDePersonas(){
		return ioRegistroService.obtenerTodosLosRegistros();
		
	}
	
	@GetMapping("/{id}")
	public IORegistro obtenerPersona(@PathVariable("id") Long id) {
		return ioRegistroService.obtenerRegistro(id);
	}
	
	@PostMapping("/create")
	public IORegistro crearPersona(@RequestBody IORegistro nuevoRegistro) {
		return ioRegistroService.crearRegistro(nuevoRegistro);
	}
}
