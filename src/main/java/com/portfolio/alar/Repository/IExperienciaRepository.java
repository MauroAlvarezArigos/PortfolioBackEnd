package com.portfolio.alar.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.portfolio.alar.Entity.Experiencia;

@Repository
public interface IExperienciaRepository extends JpaRepository<Experiencia, Integer>{
    public Optional<Experiencia> findByNombreE(String nombre);
    public Boolean existsByNombreE(String nombre);    
}