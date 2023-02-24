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

import com.portfolio.alar.Dto.EducacionDTO;
import com.portfolio.alar.Entity.Educacion;
import com.portfolio.alar.Security.Controller.Mensaje;
import com.portfolio.alar.Service.EducacionService;

@RestController
@RequestMapping("educacion")
@CrossOrigin(origins = "https://portfoliowebyoprogramo.web.app")
public class EducacionController {
    @Autowired
    EducacionService eds;

    @GetMapping("/lista")
    public ResponseEntity<List<Educacion>> list() {
        List<Educacion> list = eds.list();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<?> findEducacionById(@PathVariable("id") int id) {
        if (!eds.existsById(id))
            return new ResponseEntity<>(new Mensaje("La educacion que esta queriendo buscar no existe"),
                    HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(eds.findById(id), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody EducacionDTO eduDTO) {
        if (StringUtils.isBlank(eduDTO.getNombre())) {
            return new ResponseEntity<>(new Mensaje("El Nombre es Obligatorio"), HttpStatus.BAD_REQUEST);
        }

        if (eds.existsByNombreE(eduDTO.getNombre())) {
            return new ResponseEntity<>(new Mensaje("La educacion que se quiere ingresar ya existe"),
                    HttpStatus.BAD_REQUEST);
        }

        Educacion edu = new Educacion(eduDTO.getNombre(), eduDTO.getDescripcion());
        eds.save(edu);
        return new ResponseEntity<>(new Mensaje("Educacion guardada con exito"), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody EducacionDTO eduDTO) {
        if (!eds.existsById(id))
            return new ResponseEntity<>(new Mensaje("La educacion que esta queriendo modificar no existe"),
                    HttpStatus.NOT_FOUND);
        if (eds.existsByNombreE(eduDTO.getNombre()) && eds.findByNombre(eduDTO.getNombre()).get().getId() != id)
            return new ResponseEntity<>(new Mensaje(
                    "La educacion que intenta actualizar tiene un id distinto al de la almacenada en la base de datos."),
                    HttpStatus.BAD_REQUEST);
        if (StringUtils.isBlank(eduDTO.getNombre()))
            return new ResponseEntity<>(new Mensaje("El Nombre es Obligatorio"), HttpStatus.BAD_REQUEST);

        Educacion edu = eds.findById(id).get();
        edu.setNombre(eduDTO.getNombre());
        edu.setDescripcion(eduDTO.getDescripcion());

        eds.save(edu);

        return new ResponseEntity<>(new Mensaje("Educacion actualizada con exito"), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id) {
        if (!eds.existsById(id))
            return new ResponseEntity<>(new Mensaje("La educacion que esta queriendo eliminar no existe"),
                    HttpStatus.NOT_FOUND);

        eds.delete(id);

        return new ResponseEntity<>(new Mensaje("La educacion fue eliminada con exito"), HttpStatus.OK);
    }

}
