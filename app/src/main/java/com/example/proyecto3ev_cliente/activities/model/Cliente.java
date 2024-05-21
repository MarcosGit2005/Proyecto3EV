package com.example.proyecto3ev_cliente.activities.model;

import java.io.Serializable;
import java.util.Objects;

/**
 * Clase para crear objetos Cliente.
 */
public class Cliente implements Serializable {
    private String usuario;
    private String contraseña;
    private String nombre;
    private String apellidos;
    private String domicilio;
    private String email;
    private String fechaNacimiento;
    private String numTarjeta;
    private String tipoUsuario;
    private String changedTs;
    private int cp;

    public Cliente(String usuario, String contraseña, String nombre, String apellidos,
                   String domicilio, int cp, String email, String fechaNacimiento,
                   String numTarjeta, String tipoUsuario, String changedTs) {
        this.usuario = usuario;
        this.contraseña = contraseña;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.domicilio = domicilio;
        this.cp = cp;
        this.email = email;
        this.fechaNacimiento = fechaNacimiento;
        this.numTarjeta = numTarjeta;
        this.tipoUsuario = tipoUsuario;
        this.changedTs = changedTs;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public int getCP() {
        return cp;
    }

    public void setCP(int cp) {
        this.cp = cp;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getNumTarjeta() {
        return numTarjeta;
    }

    public void setNumTarjeta(String numTarjeta) {
        this.numTarjeta = numTarjeta;
    }

    public String getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public String getChangedTs() {
        return changedTs;
    }

    public void setChangedTs(String changedTs) {
        this.changedTs = changedTs;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cliente)) return false;
        Cliente cliente = (Cliente) o;
        return Objects.equals(getUsuario(), cliente.getUsuario()) && Objects.equals(getNombre(), cliente.getNombre()) && Objects.equals(getApellidos(), cliente.getApellidos());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUsuario(), getNombre(), getApellidos());
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "usuario='" + usuario + '\'' +
                ", contraseña='" + contraseña + '\'' +
                ", nombre='" + nombre + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", domicilio='" + domicilio + '\'' +
                ", cp=" + cp +
                ", email='" + email + '\'' +
                ", fechaNacimiento='" + fechaNacimiento + '\'' +
                ", numTarjeta='" + numTarjeta + '\'' +
                ", tipoUsuario='" + tipoUsuario + '\'' +
                ", changedTs='" + changedTs + '\'' +
                '}';
    }
}
