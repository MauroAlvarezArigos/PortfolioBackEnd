package com.portfolio.alar.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.portfolio.alar.Entity.Proyectos;
import com.portfolio.alar.Repository.IProyectosRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ProyectosService {
    @Autowired
    IProyectosRepository ipr;

    public List<Proyectos> list(){
        return ipr.findAll();
    }

    public Optional<Proyectos> findById(int id){
        return ipr.findById(id);
    }

    public Optional<Proyectos> findByNombre(String nombre){
        return ipr.findByNombre(nombre);
    }

    public void save(Proyectos p){
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
