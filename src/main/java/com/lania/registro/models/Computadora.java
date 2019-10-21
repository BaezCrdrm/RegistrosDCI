package com.lania.registro.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Computadora {
	@Id
	@GeneratedValue
	private Long id;
	private String color;
	private String modelo;
    @OneToOne
    private Persona propietario;
    
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getColor() {
		return color;
	}
	
	public void setColor(String color) {
		this.color = color;
	}
	
	public String getModelo() {
		return modelo;
	}
	
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	
	@JsonIgnore
	public Persona getPropietario() {
		return propietario;
	}
	
	public void setPropietario(Persona propietario) {
		this.propietario = propietario;
	}
    
    
}
