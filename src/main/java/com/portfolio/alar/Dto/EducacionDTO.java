package com.portfolio.alar.Dto;

import jakarta.validation.constraints.NotBlank;

public class EducacionDTO {
    @NotBlank
    private String nombreE;
    @NotBlank
    private String descripcionE;
    
    public EducacionDTO() {
    }

    public EducacionDTO(@NotBlank String nombre, @NotBlank String descripcion) {
        this.nombreE = nombre;
        this.descripcionE = descripcion;
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
