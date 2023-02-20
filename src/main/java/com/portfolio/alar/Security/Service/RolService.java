package com.portfolio.alar.Security.Service;

import com.portfolio.alar.Security.Entity.Rol;
import com.portfolio.alar.Security.Enums.RolNombre;
import com.portfolio.alar.Security.Repository.iRolRepository;
import jakarta.transaction.Transactional;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class RolService {
    @Autowired
    iRolRepository irol;
    
    public Optional<Rol> getByRolNombre(RolNombre rolNombre){
        return irol.findByRolNombre(rolNombre);
    }
    
    public void save(Rol rol){
        irol.save(rol);
    }
}
