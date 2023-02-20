package com.portfolio.alar.Security.Service;

import com.portfolio.alar.Security.Entity.Usuario;
import com.portfolio.alar.Security.Entity.UsuarioPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsImpl implements UserDetailsService{
    @Autowired
    UsuarioService u;
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario user = u.getByNombreUsuario(username).get();
        return UsuarioPrincipal.build(user);
    }
    
}
