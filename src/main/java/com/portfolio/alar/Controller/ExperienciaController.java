package com.portfolio.alar.Controller;

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

import com.portfolio.alar.Dto.ExperienciaDTO;
import com.portfolio.alar.Entity.Experiencia;
import com.portfolio.alar.Security.Controller.Mensaje;
import com.portfolio.alar.Service.ExperienciaService;

@RestController
@RequestMapping("experiencia")
@CrossOrigin(origins = "https://portfoliowebyoprogramo.web.app")
public class ExperienciaController {
    @Autowired
    ExperienciaService expS;

    @GetMapping("/lista")
    public ResponseEntity<List<Experiencia>> list() {
        List<Experiencia> list = expS.list();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<?> findExperienciaById(@PathVariable("id") int id) {
        if (!expS.existsById(id))
            return new ResponseEntity<>(new Mensaje("La experiencia que esta queriendo buscar no existe"), HttpStatus.NOT_FOUND);   
        return new ResponseEntity<>(expS.findById(id), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody ExperienciaDTO xpDTO) {
        if (StringUtils.isBlank(xpDTO.getNombreE())) {
            return new ResponseEntity<>(new Mensaje("El Nombre es Obligatorio"), HttpStatus.BAD_REQUEST);
        }

        if (expS.existsByNombreE(xpDTO.getNombreE())) {
            return new ResponseEntity<>(new Mensaje("La experiencia que se quiere ingresar ya existe"),
                    HttpStatus.BAD_REQUEST);
        }

        Experiencia xp = new Experiencia(xpDTO.getNombreE(), xpDTO.getDescripcionE());
        expS.save(xp);

        return new ResponseEntity<>(new Mensaje("Experiencia guardada con exito"), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody ExperienciaDTO xpDTO) {
        if (!expS.existsById(id))
            return new ResponseEntity<>(new Mensaje("La experiencia que esta queriendo modificar no existe"), HttpStatus.NOT_FOUND);   
        if (expS.existsByNombreE(xpDTO.getNombreE()) && expS.findByNombreE(xpDTO.getNombreE()).get().getId() != id)
            return new ResponseEntity<>(new Mensaje("La experiencia que intenta actualizar tiene un id distinto al de la almacenada en la base de datos."), HttpStatus.BAD_REQUEST);
        if (StringUtils.isBlank(xpDTO.getNombreE()))
            return new ResponseEntity<>(new Mensaje("El Nombre es Obligatorio"), HttpStatus.BAD_REQUEST);
        
        Experiencia xp = expS.findById(id).get();
        xp.setNombreE(xpDTO.getNombreE());
        xp.setDescripcionE(xpDTO.getDescripcionE());

        expS.save(xp);

        return new ResponseEntity<>(new Mensaje("Experiencia actualizada con exito"), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id) {
        if (!expS.existsById(id))
            return new ResponseEntity<>(new Mensaje("La experiencia que esta queriendo eliminar no existe"), HttpStatus.NOT_FOUND);
        
        expS.delete(id);

        return new ResponseEntity<>(new Mensaje("La experiencia fue eliminada con exito"), HttpStatus.OK);
    }

}
