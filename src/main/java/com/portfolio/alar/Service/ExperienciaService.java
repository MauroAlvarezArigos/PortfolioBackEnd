package com.portfolio.alar.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.portfolio.alar.Entity.Experiencia;
import com.portfolio.alar.Repository.IExperienciaRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ExperienciaService {
    @Autowired
    IExperienciaRepository ier;

    public List<Experiencia> list(){
        return ier.findAll();
    }

    public Optional<Experiencia> findById(int id){
        return ier.findById(id);
    }

    public Optional<Experiencia> findByNombreE(String nombre){
        return ier.findByNombreE(nombre);
    }

    public void save(Experiencia xp){
        ier.save(xp);
    }

    public void delete(int id){
        ier.deleteById(id);
    }

    public boolean existsById(int id){
        return ier.existsById(id);
    }

    public boolean existsByNombreE(String nombre){
        return ier.existsByNombreE(nombre);
    }
}
