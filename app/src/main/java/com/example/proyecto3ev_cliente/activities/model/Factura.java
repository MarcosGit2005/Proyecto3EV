package com.example.proyecto3ev_cliente.activities.model;




public class Factura {
    private int idFactura;
    private String usuario;
    private String fecha;
    private double importeBase;
    private double importeIVA;
    private double totalFactura;
    private String changedTs;

    public Factura(int idFactura, String usuario, String fecha, double importeBase, double importeIVA, double totalFactura, String changedTs) {
        this.idFactura = idFactura;
        this.usuario = usuario;
        this.fecha = fecha;
        this.importeBase = importeBase;
        this.importeIVA = importeIVA;
        this.totalFactura = totalFactura;
        this.changedTs = changedTs;
    }

    public int getIdFactura() {
        return idFactura;
    }

    public void setIdFactura(int idFactura) {
        this.idFactura = idFactura;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public double getImporteBase() {
        return importeBase;
    }

    public void setImporteBase(double importeBase) {
        this.importeBase = importeBase;
    }

    public double getImporteIVA() {
        return importeIVA;
    }

    public void setImporteIVA(double importeIVA) {
        this.importeIVA = importeIVA;
    }

    public double getTotalFactura() {
        return totalFactura;
    }

    public void setTotalFactura(double totalFactura) {
        this.totalFactura = totalFactura;
    }

    public String getChangedTs() {
        return changedTs;
    }

    public void setChangedTs(String changedTs) {
        this.changedTs = changedTs;
    }
}
