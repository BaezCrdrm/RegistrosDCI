package com.lania.registro.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lania.registro.models.Equipo;

@Repository
public interface EquipoRepository extends JpaRepository<Equipo, Long>{
	Optional <Equipo> findByNombre(String nombre);
}
