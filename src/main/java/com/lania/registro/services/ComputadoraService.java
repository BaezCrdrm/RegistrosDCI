package com.lania.registro.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lania.registro.errors.ApiSubError;
import com.lania.registro.errors.InvalidEntityException;
import com.lania.registro.models.Computadora;
import com.lania.registro.repositories.ComputadoraRepository;

@Service
public class ComputadoraService {
	@Autowired
	private ComputadoraRepository computadoraRepository;
	
	public Computadora crearComputadora(Computadora computadora) {
		List<ApiSubError> errores =  new ArrayList<ApiSubError>();
		if(this.laComputadoraEsValida(computadora, errores)) {
			computadora = computadoraRepository.saveAndFlush(computadora);
		}else {
			this.throwsInvalidEntityException ("No es posible crear la entidad Computadora", errores);
		}
		return computadora;
	}
	
	public Computadora actualizarComputadora(Computadora computadoraActualizada) {
		List<ApiSubError> errores =  new ArrayList<ApiSubError>();
		boolean computadoraValida = this.laComputadoraEsValida(computadoraActualizada, errores);
		if(computadoraValida) {
			Computadora computadoraEncontrada = this.obtenerComputadora(computadoraActualizada.getId());
			computadoraActualizada.setId(computadoraEncontrada.getId());
			computadoraActualizada = computadoraRepository.saveAndFlush(computadoraActualizada);
		}else {
			this.throwsInvalidEntityException ("No es posible actualizar la entidad Computadora", errores);
		}
		return computadoraActualizada;
		
	}
	
	public List<Computadora> obtenerTodasLasComputadoras(){
		return computadoraRepository.findAll();
	}
	
	public Computadora obtenerComputadora(long id) {
		Optional <Computadora> optional = computadoraRepository.findById(id);
		if(!optional.isPresent()) {
			this.throwsEntityNotFoundException("No se encontró la computadora con id = " + id);
		}
		return optional.get();
	}
	
	public boolean laComputadoraEsValida(Computadora computadora, List<ApiSubError> errores) {
		if(computadora != null) {
			boolean success = true;
			String color = computadora.getColor();
			if(color == null || color.isEmpty()) {
				errores.add(new ApiSubError("Computadora", "color", color , "Color inválido"));
				success = false;
			}	
			String modelo = computadora.getModelo();
			if(modelo == null || modelo.isEmpty()) {
				errores.add(new ApiSubError("Computadora", "modelo", modelo , "Modelo inválido"));
				success = false;
			}
			if(success) {
				return true;
			}
		}
		return false;
	}
	
	private void throwsEntityNotFoundException(String mensage) {
		throw new EntityNotFoundException(mensage);
	}
	
	private void throwsInvalidEntityException (String mensage, List<ApiSubError> errores) {
		throw new InvalidEntityException(mensage, errores);
	}
}
