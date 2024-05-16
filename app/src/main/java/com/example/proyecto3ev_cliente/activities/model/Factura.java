package com.example.proyecto3ev_cliente.activities.model;

import java.sql.Timestamp;

public class Factura {
    private int idFactura;
    private String usuario;
    private String fecha;
    private double importeBase;
    private double importeIVA;
    private double totalFactura;
    private Timestamp changedTs;
}
