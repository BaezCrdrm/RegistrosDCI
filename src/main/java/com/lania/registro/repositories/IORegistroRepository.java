package com.lania.registro.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lania.registro.models.IORegistro;

@Repository
public interface IORegistroRepository extends JpaRepository<IORegistro, Long>{

}
