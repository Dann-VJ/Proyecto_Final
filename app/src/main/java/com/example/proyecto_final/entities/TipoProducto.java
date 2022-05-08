package com.example.proyecto_final.entities;

public class TipoProducto {
    private int idtp;
    private String descripcion;

    /*
    Generamos los Getters y setters
    de los atributos
     */

    public int getIdtp() {
        return idtp;
    }

    public void setIdtp(int idtp) {
        this.idtp = idtp;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
