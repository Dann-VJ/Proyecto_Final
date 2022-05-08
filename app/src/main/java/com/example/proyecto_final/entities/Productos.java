package com.example.proyecto_final.entities;

/*
Representamos la tabla de mi bd
 */
public class Productos {
    private int idProducto;
    private String nomproducto;
    private TipoProducto idtp;
    private double precio;
    private int existencia;
    private String descripcion;

    public int getIdProducto() {
        return idProducto;
    }

    public String getNomProducto() {
        return nomproducto;
    }

    public TipoProducto getIdtp() {
        return idtp;
    }

    public double getPrecio() {
        return precio;
    }

    public int getExistencia() {
        return existencia;
    }


}
