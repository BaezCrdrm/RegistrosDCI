package com.lania.registro.errors;

import javax.persistence.PersistenceException;

public class DuplicateEntityException extends PersistenceException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public DuplicateEntityException(){
		super();
	}
	
	public DuplicateEntityException(String message) {
		super(message);
	}

}
