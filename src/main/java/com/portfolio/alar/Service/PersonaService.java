package com.portfolio.alar.Service;

import com.portfolio.alar.Entity.Persona;
import com.portfolio.alar.Interface.IPersonaService;
import com.portfolio.alar.Repository.IPersonaRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonaService implements IPersonaService{
    @Autowired IPersonaRepository ipersonaRepository;
    
    @Override
    public List<Persona> getPersona() {
        List<Persona> personas = ipersonaRepository.findAll();
        return personas;
    }

    @Override
    public void savePersona(Persona p) {
        ipersonaRepository.save(p);
    }

    @Override
    public void deletePersona(Long id) {
        ipersonaRepository.deleteById(id);
    }

    @Override
    public Persona findPersona(Long id) {
        return ipersonaRepository.findById(id).orElse(null);
    }
}
