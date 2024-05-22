package com.example.proyecto3ev_cliente.activities.model;


import java.io.Serializable;

public class Linea implements Serializable {
    private int idLinea;
    private int idFactura;
    private int idContenido;
    private String changedTs;

    public Linea(int idLinea, int idFactura, int idContenido, String changedTs) {
        this.idLinea = idLinea;
        this.idFactura = idFactura;
        this.idContenido = idContenido;
        this.changedTs = changedTs;
    }

    public int getIdLinea() {
        return idLinea;
    }

    public void setIdLinea(int idLinea) {
        this.idLinea = idLinea;
    }

    public int getIdFactura() {
        return idFactura;
    }

    public void setIdFactura(int idFactura) {
        this.idFactura = idFactura;
    }

    public int getIdContenido() {
        return idContenido;
    }

    public void setIdContenido(int idContenido) {
        this.idContenido = idContenido;
    }

    public String getChangedTs() {
        return changedTs;
    }

    public void setChangedTs(String changedTs) {
        this.changedTs = changedTs;
    }
}
