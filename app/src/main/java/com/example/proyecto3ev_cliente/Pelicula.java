package com.example.proyecto3ev_cliente;

import android.widget.ImageView;

public class Pelicula {
    private ImageView imageView;
    private String titulo;
    private double precio;

    public Pelicula(String titulo,double precio){
        this.titulo=titulo;
        this.precio=precio;
    }
    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }
}
