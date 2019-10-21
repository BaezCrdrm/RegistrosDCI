package com.lania.registro.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Persona {
	@Id
	@GeneratedValue
	private Long id;
	private String nombre;
	private String institucionDeOrigen;
	private String facebook;
	private String telefono;
	private String whatsapp;
	private String gradoAcademico;
	private String status;
	private String interes;
	@Column(unique=true)
	private String correo;
	@ManyToOne
	private Equipo equipo;
    @OneToOne
    private Computadora computadora;
    @Lob
    @Column(columnDefinition="BLOB")
    @JsonIgnore
    private byte[] qr;
    @JsonIgnore
    @Column(unique=true)
    private String hash;
    @JsonIgnore
    @Column(unique=true)
    private String uuid;
    
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getInstitucionDeOrigen() {
		return institucionDeOrigen;
	}
	
	public void setInstitucionDeOrigen(String institucionDeOrigen) {
		this.institucionDeOrigen = institucionDeOrigen;
	}
	
	public String getFacebook() {
		return facebook;
	}
	
	public void setFacebook(String facebook) {
		this.facebook = facebook;
	}
	
	public String getCorreo() {
		return correo;
	}
	
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	
	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getWhatsapp() {
		return whatsapp;
	}

	public void setWhatsapp(String whatsapp) {
		this.whatsapp = whatsapp;
	}

	public String getGradoAcademico() {
		return gradoAcademico;
	}

	public void setGradoAcademico(String gradoAcademico) {
		this.gradoAcademico = gradoAcademico;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getInteres() {
		return interes;
	}

	public void setInteres(String interes) {
		this.interes = interes;
	}

	public Equipo getEquipo() {
		return equipo;
	}
	
	public void setEquipo(Equipo equipo) {
		this.equipo = equipo;
	}
	
	public Computadora getComputadora() {
		return computadora;
	}
	
	public void setComputadora(Computadora computadora) {
		this.computadora = computadora;
	}

	public String getHash() {
		return hash;
	}

	public void setHash(String hash) {
		this.hash = hash;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public byte[] getQr() {
		return qr;
	}

	public void setQr(byte[] qr) {
		this.qr = qr;
	}
    
    
}
