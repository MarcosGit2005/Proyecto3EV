package com.example.proyecto3ev_cliente.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyecto3ev_cliente.API.Connector;
import com.example.proyecto3ev_cliente.R;
import com.example.proyecto3ev_cliente.activities.model.Carrito;
import com.example.proyecto3ev_cliente.activities.model.Contenido;
import com.example.proyecto3ev_cliente.base.BaseActivity;
import com.example.proyecto3ev_cliente.base.CallInterface;
import com.example.proyecto3ev_cliente.base.Parameters;
import com.google.android.material.snackbar.Snackbar;

import java.lang.reflect.Parameter;
import java.util.List;

public class CarritoActivity extends BaseActivity implements CallInterface,View.OnClickListener {
    private RecyclerView recyclerView;
    private EditText sumaTotal;
    private Button alquilarboton;
    private List<Contenido> contenidos;
    private AdaptadorRecycleViewContenido adaptadorRecycleViewContenido;
    private Carrito carrito;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carrito);

        recyclerView = findViewById(R.id.recyclerViewCarrito);
        sumaTotal = findViewById(R.id.precioTotal);
        alquilarboton = findViewById(R.id.botonAlquilar);

        showProgress();
        executeCall(this);

        alquilarboton.setOnClickListener(view -> {
            if (!contenidos.isEmpty()){
                showProgress();

                    executeCall(new CallInterface() {
                        @Override
                        public void doInBackground() {
                            for (Contenido con:contenidos){
                                carrito = Connector.getConector().get(Carrito.class,"/clientesAlquilar/"+Parameters.idClienteSesión+"/"+con.getIdContenido());
                            }
                            contenidos = Connector.getConector().getAsList(Contenido.class,"/contenidoCarrito/"+carrito.getIdCarrito());
                        }

                        @Override
                        public void doInUI() {
                            hideProgress();
                            finish();
                        }
                    });


            }
        });

    }

    @Override
    public void doInBackground() {
        carrito = Connector.getConector().get(Carrito.class,"/clientesCarrito/"+ Parameters.idClienteSesión);
        contenidos = Connector.getConector().getAsList(Contenido.class,"/contenidoCarrito/"+carrito.getIdCarrito());
    }

    @Override
    public void doInUI() {
        hideProgress();

        sumaTotal.setText(carrito.getPrecioTotal()+"€");

        adaptadorRecycleViewContenido = new AdaptadorRecycleViewContenido(this, contenidos);

        adaptadorRecycleViewContenido.setOnClickListener(this);
        recyclerView.setAdapter(adaptadorRecycleViewContenido);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);
    }
    @Override
    public void onClick(View view) {
        Contenido contenido = contenidos.get(recyclerView.getChildAdapterPosition(view));
        Intent intent = new Intent(this, ActivityDetailed.class);
        intent.putExtra("contenido",contenido);
        intent.putExtra("con_boton",false);
        startActivity(intent);
    }
}
