package com.portfolio.alar.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.portfolio.alar.Entity.Skills;

@Repository
public interface ISkillsRepository extends JpaRepository<Skills, Integer>{
    public Optional<Skills> findByNombre(String nombre);
    public boolean existsByNombre(String nombre);    
}
