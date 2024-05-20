package com.example.proyecto3ev_cliente.activities.model;

import java.io.Serializable;

public class ClienteValoraContenido implements Serializable {
    private String usuario;
    private int idContenido;
    private int valoracion;

    public ClienteValoraContenido(String usuario, int idContenido, int valoracion) {
        this.usuario = usuario;
        this.idContenido = idContenido;
        this.valoracion = valoracion;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public int getIdContenido() {
        return idContenido;
    }

    public void setIdContenido(int idContenido) {
        this.idContenido = idContenido;
    }

    public int getValoracion() {
        return valoracion;
    }

    public void setValoracion(int valoracion) {
        this.valoracion = valoracion;
    }

    @Override
    public String toString() {
        return "ClienteValoraContenido{" +
                "usuario='" + usuario + '\'' +
                ", idContenido=" + idContenido +
                ", valoracion=" + valoracion +
                '}';
    }
}
