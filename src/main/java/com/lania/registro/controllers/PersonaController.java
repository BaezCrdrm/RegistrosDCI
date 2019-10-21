package com.lania.registro.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.lania.registro.models.Persona;
import com.lania.registro.services.PersonaService;

@RestController
@RequestMapping(path = "/persona")
public class PersonaController {
	@Autowired
	private PersonaService personaService;

	@GetMapping("/list")
	public List<Persona> listaDePersonas(){
		return personaService.obtenerTodasLasPersonas();
		
	}
	
	@GetMapping("/{id}")
	public Persona obtenerPersona(@PathVariable("id") Long id) {
		return personaService.obtenerPersona(id);
	}
	
	@GetMapping("/by-hash")
	public Persona obtenerPersonaPorHash(@RequestParam("hash") String hash) {
		return personaService.obtenerPersonaPorHash(hash);
	}
	
	@PostMapping("/create")
	public Persona crearPersona(@RequestBody Persona nuevaPersona) {
		return personaService.crearPersona(nuevaPersona);
	}
	
	@GetMapping(
			path = "qr/{id}",
			 produces = MediaType.IMAGE_JPEG_VALUE)
	public @ResponseBody byte[] getImageWithMediaType(@PathVariable("id") Long id){
		Persona p = personaService.obtenerPersona(id);
		return p.getQr();
	}
	
	@PutMapping("/update")
	public Persona actualizarPersona(@RequestBody Persona personaActualizada) {
		return personaService.actualizarPersona(personaActualizada);
	}

}
