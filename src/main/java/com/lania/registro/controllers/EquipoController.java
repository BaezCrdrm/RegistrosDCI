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

import com.lania.registro.models.Equipo;
import com.lania.registro.services.EquipoService;

@RestController
@RequestMapping(path = "/equipo")
public class EquipoController {
	@Autowired
	private EquipoService equipoService;
	
	
	@GetMapping("/list")
	public List<Equipo> listaDeEquipos(){
		return equipoService.obtenerTodosLosEquipos();
	}
	
	@GetMapping("/{id}")
	public Equipo obtenerEquipo(@PathVariable("id") Long equipoId) {
		return equipoService.obtenerEquipo(equipoId);
	}
	
	@PostMapping("/create")
	public Equipo crearEquipo(@RequestBody Equipo equipoNuevo) {
		return equipoService.crearEquipo(equipoNuevo);
	}
	
	@PutMapping("/update")
	public Equipo actualizarEquipo(@RequestBody Equipo equipoActualizado) {
		return equipoService.actualizarEquipo(equipoActualizado);
	}
}
