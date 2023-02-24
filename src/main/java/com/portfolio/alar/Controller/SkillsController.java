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

import com.portfolio.alar.Dto.SkillsDTO;
import com.portfolio.alar.Entity.Skills;
import com.portfolio.alar.Security.Controller.Mensaje;
import com.portfolio.alar.Service.SkillsService;

@RestController
@RequestMapping("skills")
@CrossOrigin(origins = "https://portfoliowebyoprogramo.web.app")
public class SkillsController {
    @Autowired
    SkillsService ss;

    @GetMapping("/lista")
    public ResponseEntity<List<Skills>> list() {
        List<Skills> list = ss.list();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<?> findSkillById(@PathVariable("id") int id) {
        if (!ss.existsById(id))
            return new ResponseEntity<>(new Mensaje("La skill que esta queriendo buscar no existe"), HttpStatus.NOT_FOUND);   
        return new ResponseEntity<>(ss.findById(id), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody SkillsDTO skillsDTO) {
        if (StringUtils.isBlank(skillsDTO.getNombre())) {
            return new ResponseEntity<>(new Mensaje("El Nombre es Obligatorio"), HttpStatus.BAD_REQUEST);
        }

        if (ss.existsByNombre(skillsDTO.getNombre())) {
            return new ResponseEntity<>(new Mensaje("La skill que se quiere ingresar ya existe"),
                    HttpStatus.BAD_REQUEST);
        }

        Skills skill = new Skills(skillsDTO.getNombre(), skillsDTO.getPorcentaje());
        ss.save(skill);

        return new ResponseEntity<>(new Mensaje("Skill guardada con exito"), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody SkillsDTO skillsDTO) {
        if (!ss.existsById(id))
            return new ResponseEntity<>(new Mensaje("La skill que esta queriendo modificar no existe"), HttpStatus.NOT_FOUND);   
        if (ss.existsByNombre(skillsDTO.getNombre()) && ss.findByNombre(skillsDTO.getNombre()).get().getId() != id)
            return new ResponseEntity<>(new Mensaje("La skill que intenta actualizar tiene un id distinto al de la almacenada en la base de datos."), HttpStatus.BAD_REQUEST);
        if (StringUtils.isBlank(skillsDTO.getNombre()))
            return new ResponseEntity<>(new Mensaje("El Nombre es Obligatorio"), HttpStatus.BAD_REQUEST);
        
        Skills skill = ss.findById(id).get();
        skill.setNombre(skillsDTO.getNombre());
        skill.setPorcentaje(skillsDTO.getPorcentaje());

        ss.save(skill);

        return new ResponseEntity<>(new Mensaje("Skill actualizada con exito"), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id) {
        if (!ss.existsById(id))
            return new ResponseEntity<>(new Mensaje("La Skill que esta queriendo eliminar no existe"), HttpStatus.NOT_FOUND);
        
        ss.delete(id);

        return new ResponseEntity<>(new Mensaje("La Skill fue eliminada con exito"), HttpStatus.OK);
    }
}
