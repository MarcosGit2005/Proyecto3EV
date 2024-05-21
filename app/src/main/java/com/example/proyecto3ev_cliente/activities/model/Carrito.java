package com.example.proyecto3ev_cliente.activities.model;

import java.io.Serializable;

/**
 * Clase para crear objetos Carrito
 */
public class Carrito implements Serializable {
    private int idCarrito;
    private String usuario;
    private double precioTotal;
    private String changedTs;

    public Carrito(int idCarrito, String usuario, double precioTotal, String changedTs) {
        this.idCarrito = idCarrito;
        this.usuario = usuario;
        this.precioTotal = precioTotal;
        this.changedTs = changedTs;
    }

    public int getIdCarrito() {
        return idCarrito;
    }

    public void setIdCarrito(int idCarrito) {
        this.idCarrito = idCarrito;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public double getPrecioTotal() {
        return precioTotal;
    }

    public void setPrecioTotal(double precioTotal) {
        this.precioTotal = precioTotal;
    }

    public String getChangedTs() {
        return changedTs;
    }

    public void setChangedTs(String changedTs) {
        this.changedTs = changedTs;
    }

    @Override
    public String toString() {
        return "Carrito{" +
                "idCarrito=" + idCarrito +
                ", usuario='" + usuario + '\'' +
                ", precioTotal=" + precioTotal +
                ", changedTs='" + changedTs + '\'' +
                '}';
    }
}
