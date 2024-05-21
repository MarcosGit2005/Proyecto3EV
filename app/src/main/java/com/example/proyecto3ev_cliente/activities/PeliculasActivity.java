package com.example.proyecto3ev_cliente.activities;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.SearchView;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyecto3ev_cliente.API.Connector;
import com.example.proyecto3ev_cliente.R;
import com.example.proyecto3ev_cliente.activities.model.Contenido;
import com.example.proyecto3ev_cliente.base.BaseActivity;
import com.example.proyecto3ev_cliente.base.CallInterface;

import java.util.List;

import okhttp3.Call;
import okhttp3.Connection;

public class PeliculasActivity extends BaseActivity implements CallInterface, View.OnClickListener{
    private SearchView busqueda;
    private RecyclerView recyclerView;
    private Button carrito;
    private Button peliculasAlquiladas;
    private List<Contenido> contenidos;
    private AdaptadorRecycleView adaptadorRecycleView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_busqueda);

        Bundle extras = getIntent().getExtras();

        busqueda=findViewById(R.id.searchViewBusqueda);
        recyclerView=findViewById(R.id.recyclerPeliculas);
        carrito=findViewById(R.id.carritoBoton);
        peliculasAlquiladas=findViewById(R.id.botonPeliculasAlquiladas);

        carrito.setOnClickListener(view ->{
            Intent intent = new Intent(this, CarritoActivity.class);
            startActivity(intent);
        });
        showProgress();
        executeCall(this);
    }

    @Override
    public void doInBackground() {
        contenidos = Connector.getConector().getAsList(Contenido.class,"/contenido/");
        System.out.println(contenidos);
    }

    @Override
    public void doInUI() {
        hideProgress();

        adaptadorRecycleView = new AdaptadorRecycleView(this, contenidos);

        adaptadorRecycleView.setOnClickListener(this);
        recyclerView.setAdapter(adaptadorRecycleView);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);

    }
    @Override
    public void onClick(View view) {
        Contenido contenido = contenidos.get(recyclerView.getChildAdapterPosition(view));
        Intent intent = new Intent(this, ActivityDetailed.class);
        intent.putExtra("contenido",contenido);
        startActivity(intent);
    }
}
