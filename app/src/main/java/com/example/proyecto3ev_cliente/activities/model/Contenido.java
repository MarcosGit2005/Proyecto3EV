package com.example.proyecto3ev_cliente.activities.model;

import java.io.Serializable;

/**
 * Clase para crear objetos de contenido.
 */
public class Contenido implements Serializable {
    private int idContenido;
    private String título;
    private String género;
    private String descripción;
    private String imagen;
    private String idioma;
    private double precio;
    private double valoraciónMedia;
    private String nombre_director;
    private int duración;
    private String actoresPrincipales;
    private String fechaEstreno;
    private String tipoContenido;
    private String changedTs;
    // 4 variables de serie
    private String nombreSerie;
    private int numeroTemporada;
    private int numCapítulo;
    private boolean disponibilidad;
    // variable de película
    private String disponibleHasta;

    public Contenido(int idContenido, String título, String género, String descripción, String imagen,
                     String idioma, double precio, double valoraciónMedia, String nombre_director,
                     int duración, String actoresPrincipales, String fechaEstreno, String tipoContenido,
                     String changedTs, String nombreSerie, int numeroTemporada, int numCapítulo,
                     boolean disponibilidad, String disponibleHasta) {
        this.idContenido = idContenido;
        this.título = título;
        this.género = género;
        this.descripción = descripción;
        this.imagen = imagen;
        this.idioma = idioma;
        this.precio = precio;
        this.valoraciónMedia = valoraciónMedia;
        this.nombre_director = nombre_director;
        this.duración = duración;
        this.actoresPrincipales = actoresPrincipales;
        this.fechaEstreno = fechaEstreno;
        this.tipoContenido = tipoContenido;
        this.changedTs = changedTs;
        this.nombreSerie = nombreSerie;
        this.numeroTemporada = numeroTemporada;
        this.numCapítulo = numCapítulo;
        this.disponibilidad = disponibilidad;
        this.disponibleHasta = disponibleHasta;
    }

    public int getIdContenido() {
        return idContenido;
    }

    public void setIdContenido(int idContenido) {
        this.idContenido = idContenido;
    }

    public String getTítulo() {
        return título;
    }

    public void setTítulo(String título) {
        this.título = título;
    }

    public String getGénero() {
        return género;
    }

    public void setGénero(String género) {
        this.género = género;
    }

    public String getDescripción() {
        return descripción;
    }

    public void setDescripción(String descripción) {
        this.descripción = descripción;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public double getValoraciónMedia() {
        return valoraciónMedia;
    }

    public void setValoraciónMedia(double valoraciónMedia) {
        this.valoraciónMedia = valoraciónMedia;
    }

    public String getNombre_director() {
        return nombre_director;
    }

    public void setNombre_director(String nombre_director) {
        this.nombre_director = nombre_director;
    }

    public int getDuración() {
        return duración;
    }

    public void setDuración(int duración) {
        this.duración = duración;
    }

    public String getActoresPrincipales() {
        return actoresPrincipales;
    }

    public void setActoresPrincipales(String actoresPrincipales) {
        this.actoresPrincipales = actoresPrincipales;
    }

    public String getFechaEstreno() {
        return fechaEstreno;
    }

    public void setFechaEstreno(String fechaEstreno) {
        this.fechaEstreno = fechaEstreno;
    }

    public String getTipoContenido() {
        return tipoContenido;
    }

    public void setTipoContenido(String tipoContenido) {
        this.tipoContenido = tipoContenido;
    }

    public String getChangedTs() {
        return changedTs;
    }

    public void setChangedTs(String changedTs) {
        this.changedTs = changedTs;
    }

    public String getNombreSerie() {
        return nombreSerie;
    }

    public void setNombreSerie(String nombreSerie) {
        this.nombreSerie = nombreSerie;
    }

    public int getNumeroTemporada() {
        return numeroTemporada;
    }

    public void setNumeroTemporada(int numeroTemporada) {
        this.numeroTemporada = numeroTemporada;
    }

    public int getNumCapítulo() {
        return numCapítulo;
    }

    public void setNumCapítulo(int numCapítulo) {
        this.numCapítulo = numCapítulo;
    }

    public boolean isDisponibilidad() {
        return disponibilidad;
    }

    public void setDisponibilidad(boolean disponibilidad) {
        this.disponibilidad = disponibilidad;
    }

    public String getDisponibleHasta() {
        return disponibleHasta;
    }

    public void setDisponibleHasta(String disponibleHasta) {
        this.disponibleHasta = disponibleHasta;
    }

    @Override
    public String toString() {
        return "Contenido{" +
                "idContenido=" + idContenido +
                ", título='" + título + '\'' +
                ", género='" + género + '\'' +
                ", descripción='" + descripción + '\'' +
                ", imagen='" + imagen + '\'' +
                ", idioma='" + idioma + '\'' +
                ", precio=" + precio +
                ", valoraciónMedia=" + valoraciónMedia +
                ", nombre_director='" + nombre_director + '\'' +
                ", duración=" + duración +
                ", actoresPrincipales='" + actoresPrincipales + '\'' +
                ", fechaEstreno='" + fechaEstreno + '\'' +
                ", tipoContenido='" + tipoContenido + '\'' +
                ", changedTs='" + changedTs + '\'' +
                ", nombreSerie='" + nombreSerie + '\'' +
                ", numeroTemporada=" + numeroTemporada +
                ", numCapítulo=" + numCapítulo +
                ", disponibilidad=" + disponibilidad +
                ", disponibleHasta='" + disponibleHasta + '\'' +
                '}';
    }
}
