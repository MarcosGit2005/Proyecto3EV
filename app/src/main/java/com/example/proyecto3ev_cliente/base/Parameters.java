package com.example.proyecto3ev_cliente.base;

/**
 * Clase con los parametros para conectar con la API.
 */
public class Parameters {

    public static String API = "http://";
    public static String ip_port = "";
    public static String requestMapping = "";
    public static String URL = API + ip_port + requestMapping;
    public static String LANG = "";

    // Variable pública para acceder a los datos del cliente
    public static String idClienteSesión;

}
