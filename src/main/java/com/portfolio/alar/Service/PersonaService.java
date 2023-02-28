package com.portfolio.alar.Service;

import com.portfolio.alar.Entity.Persona;
import com.portfolio.alar.Repository.IPersonaRepository;

import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class PersonaService {
    @Autowired  
    IPersonaRepository ipr;
    
    public List<Persona> list(){
        return ipr.findAll();
    }

    public Optional<Persona> findById(int id){
        return ipr.findById(id);
    }

    public Optional<Persona> findByNombre(String nombre){
        return ipr.findByNombre(nombre);
    }

    public void save(Persona p){
        ipr.save(p);
    }

    public void delete(int id){
        ipr.deleteById(id);
    }

    public boolean existsById(int id){
        return ipr.existsById(id);
    }

    public boolean existsByNombre(String nombre){
        return ipr.existsByNombre(nombre);
    }
}
