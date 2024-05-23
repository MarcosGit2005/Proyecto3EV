package com.example.proyecto3ev_cliente.activities.actividades_detalladas;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
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
import java.util.stream.Collectors;

public class ActivityDetailedCapítuloSinBotones extends BaseActivity {

    private ImageView imagenPelicula;
    private TextView tituloCapítulo;
    private TextView nombreSerie;
    private TextView notaMedia;
    private TextView precio;
    private TextView genero;
    private TextView duracion;
    private TextView director;
    private TextView actores;
    private TextView fechaEstreno;
    private Button buttonVotar, buttonEliminarValoracion;
    private RatingBar ratingBar;
    private int valor=0;
    private Contenido contenidoBundleExtras;
    private Cliente cliente;
    private ClienteValoraContenido valoracion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vista_detallada_capitulo_sin_botones);

        imagenPelicula = findViewById(R.id.imageViewPelicula);
        nombreSerie = findViewById(R.id.textViewNombreSerieDetailed);
        tituloCapítulo = findViewById(R.id.textViewNombreCapitulo);
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

        ratingBar.setOnRatingBarChangeListener((ratingBar1, rating, fromUser) -> {
            valor = (int) rating;
        });
        buttonVotar.setOnClickListener(view -> {
            System.out.println(valor);
            showProgress();
            executeCall(new CallInterface() {
                @Override
                public void doInBackground() {
                    cliente = Connector.getConector().get(Cliente.class,"/clientesValorar/"+Parameters.idClienteSesión+"/"+ contenidoBundleExtras.getIdContenido()+"/"+valor);
                    int idCont = contenidoBundleExtras.getIdContenido();
                    contenidoBundleExtras = Connector.getConector().get(Contenido.class,"/contenido/"+idCont);
                }

                @Override
                public void doInUI() {
                    hideProgress();
                    Toast.makeText(getApplicationContext(), "Contendo valorado correctamente.", Toast.LENGTH_SHORT).show();
                    notaMedia.setText(contenidoBundleExtras.getValoraciónMedia()+"");
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
                                    Parameters.idClienteSesión+"/"+ contenidoBundleExtras.getIdContenido());
                    if (valoracion!=null){
                        cliente = Connector.getConector().delete(Cliente.class,"/clientesBorrarValoración/"+Parameters.idClienteSesión+"/"+ contenidoBundleExtras.getIdContenido());
                        int idCont = contenidoBundleExtras.getIdContenido();
                        contenidoBundleExtras = Connector.getConector().get(Contenido.class,"/contenido/"+idCont);
                    }
                }

                @Override
                public void doInUI() {
                    hideProgress();
                    if (valoracion!=null){
                        Toast.makeText(getApplicationContext(), "Valoración eliminada correctamente.", Toast.LENGTH_SHORT).show();
                        notaMedia.setText(contenidoBundleExtras.getValoraciónMedia()+"");
                    } else {
                        Toast.makeText(getApplicationContext(), "No tiene ninguna valoración.", Toast.LENGTH_SHORT).show();
                    }

                }
            });
        });

        // Carga del contenido pasado por extras
        Bundle extras = getIntent().getExtras();
        if (extras!=null){
            contenidoBundleExtras = (Contenido) extras.getSerializable("contenido");
            nombreSerie.setText(contenidoBundleExtras.getNombreSerie());
            tituloCapítulo.setText(contenidoBundleExtras.getNumeroTemporada()+"-"+ contenidoBundleExtras.getNumCapítulo() + ": "+ contenidoBundleExtras.getTítulo());
            notaMedia.setText(contenidoBundleExtras.getValoraciónMedia()+"");
            precio.setText(contenidoBundleExtras.getPrecio()+"€");
            genero.setText(contenidoBundleExtras.getGénero());
            duracion.setText(contenidoBundleExtras.getDuración()+" min.");
            director.setText(contenidoBundleExtras.getNombre_director());
            actores.setText(contenidoBundleExtras.getActoresPrincipales());
            fechaEstreno.setText(contenidoBundleExtras.getFechaEstreno());
            ImageDownloader.downloadImage(contenidoBundleExtras.getImagen(),imagenPelicula);
        }

    }
}
