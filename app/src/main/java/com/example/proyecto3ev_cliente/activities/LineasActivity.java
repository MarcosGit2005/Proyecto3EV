package com.example.proyecto3ev_cliente.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyecto3ev_cliente.API.Connector;
import com.example.proyecto3ev_cliente.R;
import com.example.proyecto3ev_cliente.activities.model.Contenido;
import com.example.proyecto3ev_cliente.activities.model.Factura;
import com.example.proyecto3ev_cliente.activities.model.Linea;
import com.example.proyecto3ev_cliente.base.BaseActivity;
import com.example.proyecto3ev_cliente.base.CallInterface;
import com.example.proyecto3ev_cliente.base.Parameters;

import java.util.List;

public class LineasActivity extends BaseActivity implements CallInterface {
    private RecyclerView recyclerViewLineas;
    private AdaptadorRecycleViewLinea adaptadorRecycleViewLinea;
    private List<Linea> lineas;
    private Factura factura;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lineas);

        recyclerViewLineas = findViewById(R.id.recyclerViewLineas);

        Bundle extras = getIntent().getExtras();
        if (extras!=null)
            factura = (Factura) extras.getSerializable("factura");

        showProgress();
        executeCall(this);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_facturas_carrito_alquiladas, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.exit) {
            finish();
        }
        return super.onOptionsItemSelected(item);

    }

    @Override
    public void doInBackground() {
        lineas = Connector.getConector().getAsList(Linea.class,"/clientesLineas/"+ factura.getIdFactura());
    }
    @Override
    public void doInUI() {
        hideProgress();

        adaptadorRecycleViewLinea = new AdaptadorRecycleViewLinea(this, lineas);

        recyclerViewLineas.setAdapter(adaptadorRecycleViewLinea);

        recyclerViewLineas.setLayoutManager(new LinearLayoutManager(this));

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerViewLineas.getContext(),DividerItemDecoration.VERTICAL);
        recyclerViewLineas.addItemDecoration(dividerItemDecoration);
    }


}
