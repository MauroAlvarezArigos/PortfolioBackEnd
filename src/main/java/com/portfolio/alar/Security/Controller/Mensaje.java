package com.portfolio.alar.Security.Controller;

public class Mensaje {
    private String mensaje;

    public Mensaje(){
    }
    public Mensaje(String m){
        this.mensaje = m;
    }
    public String getMensaje(){
        return this.mensaje;
    }
    public void setMensaje(String m){
        this.mensaje = m;
    }

}
