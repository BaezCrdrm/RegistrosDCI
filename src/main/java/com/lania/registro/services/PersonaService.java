package com.lania.registro.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.zxing.WriterException;
import com.lania.registro.errors.ApiSubError;
import com.lania.registro.errors.InvalidEntityException;
import com.lania.registro.models.Computadora;
import com.lania.registro.models.Equipo;
import com.lania.registro.models.Persona;
import com.lania.registro.repositories.PersonaRepository;
import com.lania.registro.security.BcryptGenerator;
import com.lania.registro.utils.QRCodeGenerator;

@Service
public class PersonaService {
	
	@Autowired
	private PersonaRepository personaRepository;
	@Autowired
	private EquipoService equipoService;
	@Autowired
	private ComputadoraService computadoraService;
	
	
	public Persona crearPersona(Persona nuevaPersona) {
		List<ApiSubError> errores =  new ArrayList<ApiSubError>();
		boolean personaValida = this.laPersonaEsValida(nuevaPersona, errores);
		if(personaValida) {
			Equipo equipo = nuevaPersona.getEquipo();
			equipo = equipoService.obtenerEquipo(equipo.getId());
			nuevaPersona.setEquipo(equipo);
			Computadora computadora = nuevaPersona.getComputadora();
			if(computadora != null) {
				computadora = computadoraService.obtenerComputadora(computadora.getId());
				computadora.setPropietario(nuevaPersona);
				nuevaPersona.setComputadora(computadora);				
			}
			String hash = BcryptGenerator.generate(nuevaPersona.getCorreo() + " - " + nuevaPersona.getNombre());
			String uuid = String.valueOf(UUID.randomUUID());
			byte[] qr = null;
			try {
				qr = QRCodeGenerator.getQRCodeImage("{\"hash\":\"" + hash + "\",\"uuid\":\"" + uuid + "\"}", 300, 300);
			} catch (WriterException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			nuevaPersona.setHash(hash);
			nuevaPersona.setUuid(uuid);
			nuevaPersona.setQr(qr);
			nuevaPersona = personaRepository.saveAndFlush(nuevaPersona);
		}else {
			this.throwsInvalidEntityException("No es posible crear la persona", errores);
		}
		return nuevaPersona;
	}
	
	public Persona actualizarPersona(Persona personaActualizada) {
		List<ApiSubError> errores =  new ArrayList<ApiSubError>();
		boolean personaValida = this.laPersonaEsValida(personaActualizada, errores);
		if(personaValida) {
			Persona personaEncontrada = this.obtenerPersona(personaActualizada.getId());
			clonarPropiedadesActualizables(personaActualizada, personaEncontrada);
			personaActualizada = personaRepository.saveAndFlush(personaEncontrada);
		}else {
			this.throwsInvalidEntityException("No es posible actualizar la entidad Persona", errores);
		}
		return personaActualizada;
	}
	
	public List<Persona> obtenerTodasLasPersonas(){
		return personaRepository.findAll();
	}
	
	public Persona obtenerPersona(Long id) {
		Optional <Persona> optional = personaRepository.findById(id);
		if(!optional.isPresent()) {
			this.throwsEntityNotFoundException("No se encontró la persona con id = " + id);
		}
		return optional.get();
	}
	
	public Persona obtenerPersonaPorHash(String hash) {
		Optional <Persona> optional = personaRepository.findByHash(hash);
		if(!optional.isPresent()) {
			this.throwsEntityNotFoundException("No se encontró la persona con hash = " + hash);
		}
		return optional.get();
	}
	
	public boolean laPersonaEsValida(Persona persona, List<ApiSubError> errores) {
		if(persona != null) {
			boolean success = true;
			String nombre = persona.getNombre();
			if(nombre == null || nombre.isEmpty()) {
				success = false;
			}else {
				errores.add(new ApiSubError("Persona", "nombre", "null", "El campo nombre no puede ser vacio"));
			}
			String institucion = persona.getInstitucionDeOrigen();
			if(institucion == null || institucion.isEmpty()) {
				success = false;
			}else {
				errores.add(new ApiSubError("Persona", "institucion", "null", "El campo institucion no puede ser vacio"));
			}
			String correo = persona.getCorreo();
			if(correo == null || correo.isEmpty()) {
				success = false;
			}else {
				errores.add(new ApiSubError("Persona", "correo", "null", "El campo correo no puede ser vacio"));
			}
			Equipo equipo = persona.getEquipo();
			if(equipo != null) {
				try {
					equipo = equipoService.obtenerEquipo(equipo.getId());
				}catch(Exception e) {
					String msg = "No se encontró un equipo con id = " + equipo.getId();
					errores.add(new ApiSubError("Persona", "equipo", "null", msg));
				}
			}else {
				errores.add(new ApiSubError("Persona", "equipo", "null", "El campo equipo de persona no puede ser null"));
			}			
			if(success) {
				return true;
			}
		}
		return false;
	}
	
	public static void clonarPropiedadesActualizables(Persona origen, Persona destino) {
		destino.setComputadora(origen.getComputadora());
		destino.setCorreo(origen.getCorreo());
		destino.setEquipo(origen.getEquipo());
		destino.setFacebook(origen.getFacebook());
		destino.setGradoAcademico(origen.getGradoAcademico());
		destino.setInstitucionDeOrigen(origen.getInstitucionDeOrigen());
		destino.setInteres(origen.getInteres());
		destino.setNombre(origen.getNombre());
		destino.setStatus(origen.getStatus());
		destino.setTelefono(origen.getTelefono());
		destino.setWhatsapp(origen.getWhatsapp());
	}
	
	private void throwsEntityNotFoundException(String mensage) {
		throw new EntityNotFoundException(mensage);
	}
	
	private void throwsInvalidEntityException (String mensage, List<ApiSubError> errores) {
		throw new InvalidEntityException(mensage, errores);
	}
}
