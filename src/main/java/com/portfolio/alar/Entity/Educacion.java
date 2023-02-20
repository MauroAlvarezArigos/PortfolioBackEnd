package com.portfolio.alar.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Educacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nombreE;
    private String descripcionE;
    
    
    public Educacion() {
    }

    public Educacion(String nombre, String descripcion) {
        this.nombreE = nombre;
        this.descripcionE = descripcion;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getNombre() {
        return nombreE;
    }
    public void setNombre(String nombre) {
        this.nombreE = nombre;
    }
    public String getDescripcion() {
        return descripcionE;
    }
    public void setDescripcion(String descripcion) {
        this.descripcionE = descripcion;
    }
}
