package com.example.proyecto3ev_cliente.activities;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.proyecto3ev_cliente.API.Connector;
import com.example.proyecto3ev_cliente.R;
import com.example.proyecto3ev_cliente.activities.model.Carrito;
import com.example.proyecto3ev_cliente.activities.model.Contenido;
import com.example.proyecto3ev_cliente.base.BaseActivity;
import com.example.proyecto3ev_cliente.base.CallInterface;
import com.example.proyecto3ev_cliente.base.Parameters;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class ActivityDetailed extends BaseActivity implements CallInterface {

    private ImageView imagenPelicula;
    private TextView titulo;
    private TextView notaMedia;
    private TextView precio;
    private TextView genero;
    private TextView duracion;
    private TextView director;
    private TextView actores;
    private TextView fechaEstreno;
    private FloatingActionButton buttonCarrito;
    private FloatingActionButton buttonEliminarDelCarrito;
    private Button buttonVotar;
    private RatingBar ratingBar;
    private int valor=0;
    private Contenido contenido;
    private List<Contenido> contenidosCarritoCliente;
    private List<Contenido> contenidosAlquiladosCliente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vista_detallada);

        imagenPelicula = findViewById(R.id.imageViewPelicula);
        titulo = findViewById(R.id.textViewTituloDetailed);
        notaMedia = findViewById(R.id.textViewNotaMediaDetailed);
        precio = findViewById(R.id.textViewPrecioDetailed);
        genero = findViewById(R.id.textViewGeneroDetailed);
        duracion = findViewById(R.id.textViewDuracionDetailed);
        director = findViewById(R.id.textViewDirectorDetailed);
        actores = findViewById(R.id.textViewActoresDetailed);
        fechaEstreno = findViewById(R.id.textViewFechaDetailed);


        buttonVotar = findViewById(R.id.buttonValorar);
        ratingBar = findViewById(R.id.ratingBar);
        buttonCarrito = findViewById(R.id.buttonCarrito);
        buttonEliminarDelCarrito = findViewById(R.id.buttonQuitarDelCarrito);
        buttonEliminarDelCarrito.hide();

        ratingBar.setOnRatingBarChangeListener((ratingBar1, rating, fromUser) -> {
            valor = (int) rating;
        });
        buttonVotar.setOnClickListener(view -> {
            System.out.println(valor);
            executeCall(new CallInterface() {
                @Override
                public void doInBackground() {

                }

                @Override
                public void doInUI() {

                }
            });
        });
        buttonCarrito.setOnClickListener(view -> {
            showProgress();
            executeCall(this);
        });

        buttonEliminarDelCarrito.setOnClickListener(view -> {
            showProgress();
            executeCall(new CallInterface() {
                @Override
                public void doInBackground() {
                    Carrito carrito = Connector.getConector().get(Carrito.class,"/clientesCarrito/"+ Parameters.idClienteSesión);
                    Contenido cont = Connector.getConector().delete(Contenido.class,
                            "/contenidoEliminarCarrito/"+contenido.getIdContenido()+"/"+carrito.getIdCarrito());
                }

                @Override
                public void doInUI() {
                    hideProgress();
                    Toast.makeText(getApplicationContext(), "Contenido eliminado del carrito.", Toast.LENGTH_SHORT).show();
                    buttonCarrito.show();
                    buttonEliminarDelCarrito.hide();
                }
            });
        });

        // Carga del contenido pasado por extras
        Bundle extras = getIntent().getExtras();
        if (extras!=null){
            contenido = (Contenido) extras.getSerializable("contenido");
            titulo.setText(contenido.getTítulo());
            notaMedia.setText(contenido.getValoraciónMedia()+"");
            precio.setText(contenido.getPrecio()+"€");
            genero.setText(contenido.getGénero());
            duracion.setText(contenido.getDuración()+" min.");
            director.setText(contenido.getNombre_director());
            actores.setText(contenido.getActoresPrincipales());
            fechaEstreno.setText(contenido.getFechaEstreno());

            // Si el cliente ya tiene el contenido alquilado o en el carrito, no aparece el boton
            showProgress();
            executeCall(new CallInterface() {
                @Override
                public void doInBackground() {
                    Carrito carrito = Connector.getConector().get(Carrito.class,"/clientesCarrito/"+ Parameters.idClienteSesión);
                    contenidosCarritoCliente = Connector.getConector().getAsList(Contenido.class,"/contenidoCarrito/"+carrito.getIdCarrito());
                    contenidosAlquiladosCliente = Connector.getConector().getAsList(Contenido.class,"/contenidoCliente/"+Parameters.idClienteSesión);
                }

                @Override
                public void doInUI() {
                    hideProgress();
                    if (contenidosCarritoCliente.contains(contenido)){
                        buttonEliminarDelCarrito.show();
                        buttonCarrito.hide();
                    } else if (contenidosAlquiladosCliente.contains(contenido)){
                        buttonCarrito.hide();
                    }

                }
            });

        }



    }

    @Override
    public void doInBackground() {
        Carrito carrito = Connector.getConector().get(Carrito.class,"/clientesCarrito/"+ Parameters.idClienteSesión);
        Contenido cont = Connector.getConector().get(Contenido.class,
                "/contenidoAñadirCarrito/"+contenido.getIdContenido()+"/"+carrito.getIdCarrito());
    }

    @Override
    public void doInUI() {
        hideProgress();
        finish();
    }
}
