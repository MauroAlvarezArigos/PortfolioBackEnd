package com.portfolio.alar.Controller;

import com.portfolio.alar.Dto.PersonaDTO;
import com.portfolio.alar.Entity.Persona;
import com.portfolio.alar.Security.Controller.Mensaje;
import com.portfolio.alar.Service.PersonaService;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/personas")
//@CrossOrigin(origins = "https://portfoliowebyoprogramo.web.app")
@CrossOrigin(origins = "http://localhost:4200")
public class PersonaController {
    @Autowired 
    PersonaService ps;
    
    
    @GetMapping("/lista")
    public ResponseEntity<List<Persona>> list() {
        List<Persona> list = ps.list();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<?> findExperienciaById(@PathVariable("id") int id) {
        if (!ps.existsById(id))
            return new ResponseEntity<>(new Mensaje("La persona que esta queriendo buscar no existe"), HttpStatus.NOT_FOUND);   
        return new ResponseEntity<>(ps.findById(id), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody PersonaDTO pDTO) {
        if (!ps.existsById(id))
            return new ResponseEntity<>(new Mensaje("La persona que esta queriendo modificar no existe"), HttpStatus.NOT_FOUND);   
        if (ps.existsByNombre(pDTO.getNombre()) && ps.findByNombre(pDTO.getNombre()).get().getId() != id)
            return new ResponseEntity<>(new Mensaje("La persona que intenta actualizar tiene un id distinto al de la almacenada en la base de datos."), HttpStatus.BAD_REQUEST);
        if (StringUtils.isBlank(pDTO.getNombre()))
            return new ResponseEntity<>(new Mensaje("El Nombre es Obligatorio"), HttpStatus.BAD_REQUEST);
        
        Persona p = ps.findById(id).get();
        p.setNombre(pDTO.getNombre());
        p.setDescripcion(pDTO.getDescripcion());
        p.setApellido(pDTO.getApellido());
        p.setImg(pDTO.getImg());

        ps.save(p);

        return new ResponseEntity<>(new Mensaje("Experiencia actualizada con exito"), HttpStatus.OK);
    }


/*
    NO SE CREAN NI SE DESTRUYEN PERSONAS POR LO QUE NO TIENE SENTIDO QUE ESTOS 
    METODOS ESTEN IMPLEMENTADOS PERO LO DEJO PARA UN FUTURO    

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody PersonaDTO pDTO) {
        if (StringUtils.isBlank(pDTO.getNombre())) {
            return new ResponseEntity<>(new Mensaje("El Nombre es Obligatorio"), HttpStatus.BAD_REQUEST);
        }

        if (ps.existsByNombre(pDTO.getNombre())) {
            return new ResponseEntity<>(new Mensaje("La persona que se quiere ingresar ya existe"),
                    HttpStatus.BAD_REQUEST);
        }

        Persona p = new Persona(pDTO.getNombre(), pDTO.getDescripcion(), pDTO.getApellido(), pDTO.getImg());
        ps.save(p);

        return new ResponseEntity<>(new Mensaje("Persona guardada con exito"), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id) {
        if (!ps.existsById(id))
            return new ResponseEntity<>(new Mensaje("La experiencia que esta queriendo eliminar no existe"), HttpStatus.NOT_FOUND);
        
        ps.delete(id);

        return new ResponseEntity<>(new Mensaje("La experiencia fue eliminada con exito"), HttpStatus.OK);
    }
*/
}
