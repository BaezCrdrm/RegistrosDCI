package com.lania.registro.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lania.registro.errors.ApiSubError;
import com.lania.registro.errors.DuplicateEntityException;
import com.lania.registro.errors.InvalidEntityException;
import com.lania.registro.models.Equipo;
import com.lania.registro.repositories.EquipoRepository;

@Service
public class EquipoService {
	@Autowired
	private EquipoRepository equipoRepository;
	
	
	public Equipo crearEquipo(Equipo equipo) {
		List<ApiSubError> errores =  new ArrayList<ApiSubError>();
		boolean equipoValido = this.elEquipoEsValido(equipo, errores);
		if(equipoValido) {
			String nombreEquipo = equipo.getNombre();
			try {
				this.obtenerEquipoPorNombre(nombreEquipo);
				this.throwsDuplicateEntityException("Ya existe un equipo con el nombre = " + nombreEquipo);
			}catch(EntityNotFoundException ex) {
				equipo = equipoRepository.saveAndFlush(equipo);
			}
		}else {
			this.throwsInvalidEntityException("No es posible crear la entidad Equipo", errores);
		}
		return equipo;
	}
	
	public Equipo actualizarEquipo(Equipo equipoActualizado) {
		List<ApiSubError> errores =  new ArrayList<ApiSubError>();
		boolean equipoValido = this.elEquipoEsValido(equipoActualizado, errores);
		if(equipoValido) {
			Equipo equipoEncontrado = this.obtenerEquipo(equipoActualizado.getId());
			equipoActualizado.setId(equipoEncontrado.getId());
			equipoActualizado = equipoRepository.saveAndFlush(equipoActualizado);
		}else {
			this.throwsInvalidEntityException("No es posible actualizar la entidad Equipo", errores);
		}
		return equipoActualizado;
	}
	
	public List<Equipo> obtenerTodosLosEquipos(){
		return equipoRepository.findAll();
	}
	
	public Equipo obtenerEquipo(Long id) {
		Optional <Equipo> optional = equipoRepository.findById(id);
		if(!optional.isPresent()) {
			this.throwsEntityNotFoundException("No se encontró equipo con id = " + id);
		}
		return optional.get();
	}
	
	public Equipo obtenerEquipoPorNombre(String nombre) {
		Optional <Equipo> optional = equipoRepository.findByNombre(nombre);
		if(!optional.isPresent()) {
			this.throwsEntityNotFoundException("No se encontró equipo con nombre = " + nombre);
		}
		return optional.get();
	}
	
	public boolean elEquipoEsValido(Equipo equipo, List<ApiSubError> errores) {
		boolean result = true;
		if(equipo != null) {
			String nombre = equipo.getNombre();
			if(nombre == null || nombre.isEmpty()) {
				errores.add(new ApiSubError("Equipo", "nombre", nombre , "Nombre inválido"));
				result = false;
			}
			boolean nombreDisponible = this.comprobarDisponibilidadDeNombre(equipo);
			if(!nombreDisponible) {
				errores.add(new ApiSubError("Equipo", "nombre", nombre , "Nombre ya ocupado por otro equipo"));
				result = false;
			}
		}
		return result;
	}
	
	private boolean comprobarDisponibilidadDeNombre(Equipo equipo) {
		try {
			Equipo eq = this.obtenerEquipoPorNombre(equipo.getNombre());
			Long id = equipo.getId();
			if(id != null && !id.equals(eq.getId())) {
				return false;
			}else {
				return true;
			}
		}catch(EntityNotFoundException e) {
			return true;
		}
	}
	
	private void throwsInvalidEntityException (String mensage, List<ApiSubError> errores) {
		throw new InvalidEntityException(mensage, errores);
	}
	
	private void throwsEntityNotFoundException(String mensage) {
		throw new EntityNotFoundException(mensage);
	}
	
	private void throwsDuplicateEntityException(String mensage) {
		throw new DuplicateEntityException(mensage);
	}
}
