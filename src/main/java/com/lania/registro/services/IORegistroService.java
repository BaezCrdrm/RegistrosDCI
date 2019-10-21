package com.lania.registro.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lania.registro.errors.ApiSubError;
import com.lania.registro.errors.InvalidEntityException;
import com.lania.registro.models.IORegistro;
import com.lania.registro.models.Persona;
import com.lania.registro.repositories.IORegistroRepository;

@Service
public class IORegistroService {
	@Autowired
	private IORegistroRepository ioRegistroRepository;
	@Autowired
	private PersonaService personaService;
	
	
	public IORegistro crearRegistro(IORegistro registro) {
		List<ApiSubError> errores =  new ArrayList<ApiSubError>();
		boolean registroValido = this.validarRegistro(registro, errores);
		if(registroValido) {
			registro = ioRegistroRepository.saveAndFlush(registro);			
		}else {
			this.throwsInvalidEntityException("No es posible crear la entidad IORegistro", errores);
		}
		return registro;
	}
	
	public List<IORegistro> obtenerTodosLosRegistros(){
		return ioRegistroRepository.findAll();
	}
	
	public IORegistro obtenerRegistro(Long id) {
		Optional <IORegistro> optional = ioRegistroRepository.findById(id);
		if(!optional.isPresent()) {
			this.throwsEntityNotFoundException("No se encontró el registro con id = " + id);
		}
		return optional.get();
	}
	
	private boolean validarRegistro(IORegistro registro, List<ApiSubError> errores) {
		if(registro.isEntrada() || registro.isSalida()) {
			Persona persona = registro.getPersona();
			if(persona != null) {
				try {
					personaService.obtenerPersona(persona.getId());
				}catch(EntityNotFoundException e) {
					errores.add(new ApiSubError("IORegistro", "persona", registro, "No se encontró la persona"));
					return false;
				}			
			}else {
				errores.add(new ApiSubError("IORegistro", "persona", registro, "No se encontró la persona"));
				return false;
			}			
		}else {
			errores.add(new ApiSubError("IORegistro", "entrada/salida", registro, "No se encontró el tipo de registro"));
			return false;
		}	
		return true;
	}
	
	private void throwsInvalidEntityException (String mensage, List<ApiSubError> errores) {
		throw new InvalidEntityException(mensage, errores);
	}
	
	private void throwsEntityNotFoundException(String mensage) {
		throw new EntityNotFoundException(mensage);
	}
	
}
