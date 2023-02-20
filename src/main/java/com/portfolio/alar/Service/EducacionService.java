package com.portfolio.alar.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.portfolio.alar.Entity.Educacion;
import com.portfolio.alar.Repository.IEducacionRepository;

import jakarta.transaction.Transactional;


@Service
@Transactional
public class EducacionService {
    @Autowired
    IEducacionRepository ier;

    public List<Educacion> list() {
        return ier.findAll();
    }

    public Optional<Educacion> findById(int id) {
        return ier.findById(id);
    }

    public void save(Educacion e) {
        ier.save(e);
    }

    public void delete(int id) {
        ier.deleteById(id);
    }

    public boolean existsById(int id) {
        return ier.existsById(id);
    }

    public boolean existsByNombreE(String nombre) {
        return ier.existsByNombreE(nombre);
    }

    public Optional<Educacion> findByNombre(String nombre){
        return ier.findByNombreE(nombre);
    }
}
