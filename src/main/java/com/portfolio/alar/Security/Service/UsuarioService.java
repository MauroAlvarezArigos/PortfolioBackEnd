package com.portfolio.alar.Security.Service;

import com.portfolio.alar.Security.Entity.Usuario;
import com.portfolio.alar.Security.Repository.iUsuarioRepository;
import jakarta.transaction.Transactional;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class UsuarioService {
    @Autowired
    iUsuarioRepository iu;
    
    public Optional<Usuario> getByNombreUsuario(String nombre){
        return iu.findByNombreUsuario(nombre);
    }
    
    public boolean existsByNombreUsuario(String nombre){
        return iu.existsByNombreUsuario(nombre);
    }
    
    public boolean existsByEmail(String email){
        return iu.existsByEmail(email);
    }
    
    public void save(Usuario u){
        iu.save(u);
    }
           
}
