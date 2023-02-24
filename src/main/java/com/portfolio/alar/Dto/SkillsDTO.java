package com.portfolio.alar.Dto;

import jakarta.validation.constraints.NotBlank;

public class SkillsDTO {
    @NotBlank
    private String nombre;
    @NotBlank
    private int porcentaje;

    public SkillsDTO() {
    }

    public SkillsDTO(@NotBlank String nombre, @NotBlank int porcentaje) {
        this.nombre = nombre;
        this.porcentaje = porcentaje;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(int porcentaje) {
        this.porcentaje = porcentaje;
    }

}
