package com.example.proyecto3ev_cliente.tests;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;

import com.example.proyecto3ev_cliente.API.Connector;
import com.example.proyecto3ev_cliente.activities.model.Carrito;
import com.example.proyecto3ev_cliente.activities.model.Cliente;
import com.example.proyecto3ev_cliente.base.Parameters;

import org.junit.Test;

import java.util.List;

public class ClienteTests {
    @Test
    public void testLogin() {
        Parameters.URL = "http://localhost:8080/api";
        Cliente clienteSesion = Connector.getConector().get(Cliente.class,
                "/clientesLogin/root/1234");
        assertNotNull(clienteSesion);
    }
    @Test
    public void getAllTest(){
        Parameters.URL = "http://localhost:8080/api";
        List<Cliente> clienteSesion = Connector.getConector().getAsList(Cliente.class,
                "/clientes/");
        assertNotNull(clienteSesion);
    }
    @Test
    public void cambiarContraseñaTest() {
        Parameters.URL = "http://localhost:8080/api";
        Cliente clieteOld = new Cliente("root","1234",null,null,
                null,0,null,null,null,null,
                null);
        Cliente clienteSesion = Connector.getConector().put(Cliente.class,clieteOld,
                "/clientes/");
        assertNotNull(clienteSesion);
    }
    @Test
    public void getCarritoTest(){
        Parameters.URL = "http://localhost:8080/api";
        Carrito carritoRoot = Connector.getConector().get(Carrito.class,
                "/clientesCarrito/root");
        assertNotNull(carritoRoot);
    }
}
