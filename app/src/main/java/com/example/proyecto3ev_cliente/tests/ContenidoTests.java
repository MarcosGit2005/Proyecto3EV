package com.example.proyecto3ev_cliente.tests;

import static junit.framework.TestCase.assertNotNull;

import com.example.proyecto3ev_cliente.API.Connector;
import com.example.proyecto3ev_cliente.activities.model.Contenido;
import com.example.proyecto3ev_cliente.base.Parameters;

import org.junit.Test;

import java.util.List;

public class ContenidoTests {
    @Test
    public void getAllContenidoTest(){
        Parameters.URL = "http://localhost:8080/api";
        List<Contenido> contenidos = Connector.getConector().getAsList(Contenido.class,
                "/contenido/");
        assertNotNull(contenidos);
    }
    @Test
    public void getContenidoEnCarritoTest(){
        Parameters.URL = "http://localhost:8080/api";
        List<Contenido> contenidos = Connector.getConector().getAsList(Contenido.class,
                "/contenidoCarrito/16");
    }
    @Test
    public void getContenidoAlquilado(){
        Parameters.URL = "http://localhost:8080/api";
        List<Contenido> contenidos = Connector.getConector().getAsList(Contenido.class,
                "/contenidoCliente/root");
    }
}
