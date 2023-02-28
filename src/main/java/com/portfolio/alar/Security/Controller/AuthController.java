package com.portfolio.alar.Security.Controller;

import com.portfolio.alar.Security.Dto.JwtDto;
import com.portfolio.alar.Security.Dto.LoginUsuario;
import com.portfolio.alar.Security.Dto.NuevoUsuario;
import com.portfolio.alar.Security.Entity.Rol;
import com.portfolio.alar.Security.Entity.Usuario;
import com.portfolio.alar.Security.Enums.RolNombre;
import com.portfolio.alar.Security.Service.RolService;
import com.portfolio.alar.Security.Service.UsuarioService;
import com.portfolio.alar.Security.jwt.JwtProvider;

import jakarta.validation.Valid;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
//@CrossOrigin(origins = "http://localhost:4200")
@CrossOrigin(origins = "https://portfoliowebyoprogramo.web.app")
public class AuthController {
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    UsuarioService usuarioService;
    @Autowired
    RolService rolService;
    @Autowired
    JwtProvider jwtProvider;

    @PostMapping("/nuevo")
    public ResponseEntity<?> nuevo(@Valid @RequestBody NuevoUsuario nuevoUsuario, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(new Mensaje("Los datos ingresados son incorrectos"), HttpStatus.BAD_REQUEST);
        }

        if (usuarioService.existsByNombreUsuario(nuevoUsuario.getNombreUsuario())) {
            return new ResponseEntity<>(new Mensaje("El nombre de usuario ya existe"), HttpStatus.BAD_REQUEST);
        }

        if (usuarioService.existsByEmail(nuevoUsuario.getEmail())) {
            return new ResponseEntity<>(new Mensaje("El email de usuario ya existe"), HttpStatus.BAD_REQUEST);
        }

        Usuario u = new Usuario(nuevoUsuario.getNombre(), nuevoUsuario.getNombreUsuario(), nuevoUsuario.getEmail(),
                passwordEncoder.encode(nuevoUsuario.getPassword()));
        Set<Rol> roles = new HashSet<>();
        roles.add(rolService.getByRolNombre(RolNombre.ROLE_USER).get());

        if (nuevoUsuario.getRoles().contains("admin"))
            roles.add(rolService.getByRolNombre(RolNombre.ROLE_ADMIN).get());
        u.setRoles(roles);
        usuarioService.save(u);
        return new ResponseEntity<>(new Mensaje("Usuario Guardado"), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginUsuario loginUsuario, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(new Mensaje("Los Campos ingresados estan mal puestos"), HttpStatus.BAD_REQUEST);
        }

        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginUsuario.getNombreUsuario(), loginUsuario.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(auth);

        String jwt = jwtProvider.generateToken(auth);
        UserDetails userDetails = (UserDetails) auth.getPrincipal();
        JwtDto jwtDTO = new JwtDto(jwt, userDetails.getUsername(), userDetails.getAuthorities());

        return new ResponseEntity<>(jwtDTO, HttpStatus.OK);
    }

}
