package com.lania.registro.errors;

import java.util.List;

import javax.persistence.PersistenceException;

public class InvalidEntityException extends PersistenceException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<ApiSubError> errores;

	public InvalidEntityException() {
		
	}
	
	public InvalidEntityException(String message) {
		super(message);
	}
	
	public InvalidEntityException(String message, List<ApiSubError> errores) {
		super(message);
		this.errores = errores;
	}

	public List<ApiSubError> getErrores() {
		return errores;
	}

	public void setErrores(List<ApiSubError> errores) {
		this.errores = errores;
	}
	
	
}
