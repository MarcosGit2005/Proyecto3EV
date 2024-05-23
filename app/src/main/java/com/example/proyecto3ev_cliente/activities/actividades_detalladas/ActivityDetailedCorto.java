package com.example.proyecto3ev_cliente.activities.actividades_detalladas;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.proyecto3ev_cliente.API.Connector;
import com.example.proyecto3ev_cliente.R;
import com.example.proyecto3ev_cliente.activities.model.Carrito;
import com.example.proyecto3ev_cliente.activities.model.Cliente;
import com.example.proyecto3ev_cliente.activities.model.ClienteValoraContenido;
import com.example.proyecto3ev_cliente.activities.model.Contenido;
import com.example.proyecto3ev_cliente.base.BaseActivity;
import com.example.proyecto3ev_cliente.base.CallInterface;
import com.example.proyecto3ev_cliente.base.ImageDownloader;
import com.example.proyecto3ev_cliente.base.Parameters;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class ActivityDetailedCorto extends BaseActivity implements CallInterface {

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
    private Button buttonVotar, buttonEliminarValoracion;
    private RatingBar ratingBar;
    private int valor=0;
    private Contenido contenido;
    private List<Contenido> contenidosCarritoCliente;
    private List<Contenido> contenidosAlquiladosCliente;
    private Cliente cliente;
    private ClienteValoraContenido valoracion;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vista_detallada_corto);

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
        buttonEliminarValoracion = findViewById(R.id.buttonEliminarValoracion);
        ratingBar = findViewById(R.id.ratingBar);
        buttonCarrito = findViewById(R.id.buttonCarrito);

        buttonEliminarDelCarrito = findViewById(R.id.buttonQuitarDelCarrito);



        ratingBar.setOnRatingBarChangeListener((ratingBar1, rating, fromUser) -> {
            valor = (int) rating;
        });
        buttonVotar.setOnClickListener(view -> {
            System.out.println(valor);
            showProgress();
            executeCall(new CallInterface() {
                @Override
                public void doInBackground() {
                    cliente = Connector.getConector().get(Cliente.class,"/clientesValorar/"+Parameters.idClienteSesión+"/"+contenido.getIdContenido()+"/"+valor);
                    int idCont = contenido.getIdContenido();
                    contenido = Connector.getConector().get(Contenido.class,"/contenido/"+idCont);
                }

                @Override
                public void doInUI() {
                    hideProgress();
                    Toast.makeText(getApplicationContext(), "Contendo valorado correctamente.", Toast.LENGTH_SHORT).show();
                    notaMedia.setText(contenido.getValoraciónMedia()+"");
                }
            });
        });

        buttonEliminarValoracion.setOnClickListener(view -> {
            showProgress();
            executeCall(new CallInterface() {
                @Override
                public void doInBackground() {
                    valoracion = Connector.getConector()
                            .get(ClienteValoraContenido.class,"/contenidoValoración/"+
                                    Parameters.idClienteSesión+"/"+contenido.getIdContenido());
                    if (valoracion!=null){
                        cliente = Connector.getConector().delete(Cliente.class,"/clientesBorrarValoración/"+Parameters.idClienteSesión+"/"+contenido.getIdContenido());
                        int idCont = contenido.getIdContenido();
                        contenido = Connector.getConector().get(Contenido.class,"/contenido/"+idCont);
                    }
                }

                @Override
                public void doInUI() {
                    hideProgress();
                    if (valoracion!=null){
                        Toast.makeText(getApplicationContext(), "Valoración eliminada correctamente.", Toast.LENGTH_SHORT).show();
                        notaMedia.setText(contenido.getValoraciónMedia()+"");
                    } else {
                        Toast.makeText(getApplicationContext(), "No tiene ninguna valoración.", Toast.LENGTH_SHORT).show();
                    }

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
                    contenidosCarritoCliente = Connector.getConector().getAsList(Contenido.class,"/contenidoCarrito/"+carrito.getIdCarrito());
                    contenidosAlquiladosCliente = Connector.getConector().getAsList(Contenido.class,"/contenidoCliente/"+Parameters.idClienteSesión);

                    if (contenidosCarritoCliente.contains(contenido))
                        Connector.getConector().delete(Contenido.class,
                            "/contenidoEliminarCarrito/"+contenido.getIdContenido()+"/"+carrito.getIdCarrito());
                }

                @Override
                public void doInUI() {
                    hideProgress();
                    if (contenidosCarritoCliente.contains(contenido)){
                        Toast.makeText(getApplicationContext(), "Contenido eliminado del carrito.", Toast.LENGTH_SHORT).show();
                    } else if (contenidosAlquiladosCliente.contains(contenido)){
                        Toast.makeText(getApplicationContext(), "Ya tiene alquilado el contenido.", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "No tiene el contenido en el carrito.", Toast.LENGTH_SHORT).show();
                    }
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
            ImageDownloader.downloadImage(contenido.getImagen(),imagenPelicula);

        }

    }

    @Override
    public void doInBackground() {
        Carrito carrito = Connector.getConector().get(Carrito.class,"/clientesCarrito/"+ Parameters.idClienteSesión);
        contenidosCarritoCliente = Connector.getConector().getAsList(Contenido.class,"/contenidoCarrito/"+carrito.getIdCarrito());
        contenidosAlquiladosCliente = Connector.getConector().getAsList(Contenido.class,"/contenidoCliente/"+Parameters.idClienteSesión);
        if (!contenidosAlquiladosCliente.contains(contenido) && !contenidosCarritoCliente.contains(contenido))
            Connector.getConector().get(Contenido.class,
                "/contenidoAñadirCarrito/"+contenido.getIdContenido()+"/"+carrito.getIdCarrito());
    }

    @Override
    public void doInUI() {
        hideProgress();
        if (contenidosAlquiladosCliente.contains(contenido))
            Toast.makeText(getApplicationContext(), "Ya tiene alquilado el contenido.", Toast.LENGTH_SHORT).show();
        else if (contenidosCarritoCliente.contains(contenido))
            Toast.makeText(getApplicationContext(), "Ya tiene el contenido en el carrito.", Toast.LENGTH_SHORT).show();
        else
            finish();
    }
}
