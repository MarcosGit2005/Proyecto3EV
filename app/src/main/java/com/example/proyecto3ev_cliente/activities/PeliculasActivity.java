package com.example.proyecto3ev_cliente.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SearchView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyecto3ev_cliente.R;

public class PeliculasActivity extends AppCompatActivity {
    private SearchView busqueda;
    private RecyclerView peliculas;
    private Button carrito;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_busqueda);

        busqueda=findViewById(R.id.searchViewBusqueda);
        peliculas=findViewById(R.id.recyclerPeliculas);
        carrito=findViewById(R.id.carritoBoton);

        carrito.setOnClickListener(view ->{
            Intent intent = new Intent(this, CarritoActivity.class);
            startActivity(intent);
        });
    }
}
