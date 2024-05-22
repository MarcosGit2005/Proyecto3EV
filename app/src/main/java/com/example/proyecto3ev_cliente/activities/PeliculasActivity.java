package com.example.proyecto3ev_cliente.activities;

import android.content.Intent;
import android.os.Bundle;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyecto3ev_cliente.API.Connector;
import com.example.proyecto3ev_cliente.R;
import com.example.proyecto3ev_cliente.activities.model.Contenido;
import com.example.proyecto3ev_cliente.base.BaseActivity;
import com.example.proyecto3ev_cliente.base.CallInterface;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PeliculasActivity extends BaseActivity implements CallInterface, View.OnClickListener, SearchView.OnQueryTextListener{
    private SearchView busqueda;
    private RecyclerView recyclerView;

    private List<Contenido> contenidosOriginal;
    private List<Contenido> contenidosBúsqueda;
    private AdaptadorRecycleViewContenido adaptadorRecycleViewContenido;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_busqueda);

        Bundle extras = getIntent().getExtras();

        busqueda=findViewById(R.id.searchViewBusqueda);
        recyclerView=findViewById(R.id.recyclerPeliculas);

        busqueda.setOnQueryTextListener(this);

        showProgress();
        executeCall(this);
    }

    @Override
    public void doInBackground() {
        contenidosOriginal = Connector.getConector().getAsList(Contenido.class,"/contenido/");
        contenidosBúsqueda = new ArrayList<>(contenidosOriginal);
    }

    @Override
    public void doInUI() {
        hideProgress();

        adaptadorRecycleViewContenido = new AdaptadorRecycleViewContenido(this, contenidosBúsqueda);

        adaptadorRecycleViewContenido.setOnClickListener(this);
        recyclerView.setAdapter(adaptadorRecycleViewContenido);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_actividad_peliculas, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.carrito) {
            Intent intent = new Intent(this, CarritoActivity.class);
            startActivity(intent);
        } else if(item.getItemId()==R.id.peliculasAlquiladas){
            Intent intent = new Intent(this, AlquiladasActivity.class);
            startActivity(intent);
        } else if (item.getItemId()==R.id.facturas){
            Intent intent = new Intent(this, FacturasActivity.class);
            startActivity(intent);
        } else if(item.getItemId()==R.id.exit) {
            finish();
        }
        return super.onOptionsItemSelected(item);

    }
    @Override
    public void onClick(View view) {
        Contenido contenido = contenidosBúsqueda.get(recyclerView.getChildAdapterPosition(view));
        Intent intent = new Intent(this, ActivityDetailed.class);
        intent.putExtra("contenido",contenido);
        startActivity(intent);
    }
    public void filtrado(String txtBuscar){
        int length = txtBuscar.length();
        if (length==0){
            contenidosBúsqueda.clear();
            contenidosBúsqueda.addAll(contenidosOriginal);
        } else {
            List<Contenido> list = contenidosOriginal.stream()
                    .filter( cont -> cont.getTítulo().toLowerCase().contains(txtBuscar.toLowerCase()))
                    .collect(Collectors.toList());
            contenidosBúsqueda.clear();
            contenidosBúsqueda.addAll(list);
        }
        updateAdapter();
    }
    public void updateAdapter(){
        adaptadorRecycleViewContenido = new AdaptadorRecycleViewContenido(this, contenidosBúsqueda);

        adaptadorRecycleViewContenido.setOnClickListener(this);
        recyclerView.setAdapter(adaptadorRecycleViewContenido);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);
    }
    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        filtrado(newText);
        return false;
    }
}
