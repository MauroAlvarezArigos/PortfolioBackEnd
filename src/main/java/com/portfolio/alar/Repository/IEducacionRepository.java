package com.portfolio.alar.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.portfolio.alar.Entity.Educacion;

@Repository
public interface IEducacionRepository extends JpaRepository<Educacion, Integer>{
    public Optional<Educacion> findByNombreE(String nombre);
    public boolean existsByNombreE(String nombre);   
}
