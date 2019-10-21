package com.lania.registro.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lania.registro.models.Computadora;
import com.lania.registro.services.ComputadoraService;

@RestController
@RequestMapping(path = "/computadora")
public class ComputadoraController {
	@Autowired
	ComputadoraService computadoraService;
	
	@GetMapping("/list")
	public List<Computadora> listaDeComputadoras(){
		return computadoraService.obtenerTodasLasComputadoras();
		
	}
	
	@GetMapping("/{id}")
	public Computadora obtenerComputadora(@PathVariable("id") Long computadoraId) {
		return computadoraService.obtenerComputadora(computadoraId);
	}
	
	@PostMapping("/create")
	public Computadora crearComputadora(@RequestBody Computadora computadoraNueva) {
		return computadoraService.crearComputadora(computadoraNueva);
	}
	
	@PutMapping("/update")
	public Computadora actualizarComputadora(@RequestBody Computadora computadoraActualizada) {
		return computadoraService.actualizarComputadora(computadoraActualizada);
	}
}
