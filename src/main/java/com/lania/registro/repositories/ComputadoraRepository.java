package com.lania.registro.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lania.registro.models.Computadora;

@Repository
public interface ComputadoraRepository extends JpaRepository<Computadora, Long> {

}
