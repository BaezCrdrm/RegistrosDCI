package com.lania.registro.models;

import java.time.ZonedDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;

@Entity
public class IORegistro {
	@Id
	@GeneratedValue
	private Long id;
	@ManyToOne
	private Persona persona;
	private boolean entrada;
	private boolean salida;
	private ZonedDateTime createdAt;
	
	
	@PrePersist
	void addTimestamp() {
	  createdAt = ZonedDateTime.now();
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public Persona getPersona() {
		return persona;
	}


	public void setPersona(Persona persona) {
		this.persona = persona;
	}


	public boolean isEntrada() {
		return entrada;
	}


	public void setEntrada(boolean entrada) {
		this.entrada = entrada;
	}


	public boolean isSalida() {
		return salida;
	}


	public void setSalida(boolean salida) {
		this.salida = salida;
	}


	public ZonedDateTime getCreatedAt() {
		return createdAt;
	}


	public void setCreatedAt(ZonedDateTime createdAt) {
		this.createdAt = createdAt;
	}
	
	
	
}
