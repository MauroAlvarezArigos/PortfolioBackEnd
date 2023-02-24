package com.portfolio.alar.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.portfolio.alar.Entity.Skills;
import com.portfolio.alar.Repository.ISkillsRepository;

import jakarta.transaction.Transactional;


@Service
@Transactional
public class SkillsService {
    @Autowired
    ISkillsRepository isr;

    public List<Skills> list() {
        return isr.findAll();
    }

    public Optional<Skills> findById(int id){
        return isr.findById(id);
    }

    public Optional<Skills> findByNombre(String nombre){
        return isr.findByNombre(nombre);
    }

    public void save(Skills s){
        isr.save(s);
    }

    public void delete(int id){
        isr.deleteById(id);
    }

    public boolean existsById(int id){
        return isr.existsById(id);
    }

    public boolean existsByNombre(String nombre){
        return isr.existsByNombre(nombre);
    }
}
