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

public class ActivityDetailedCapítulo extends BaseActivity implements CallInterface {

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
    private FloatingActionButton buttonCarrito;
    private FloatingActionButton buttonEliminarDelCarrito;
    private Button buttonVotar, buttonEliminarValoracion;
    private RatingBar ratingBar;
    private int valor=0;
    private Contenido contenidoBundleExtras;
    private List<Contenido> contenidosCarritoCliente;
    private List<Contenido> contenidosAlquiladosCliente;
    private Cliente cliente;
    private ClienteValoraContenido valoracion;

    private List<Contenido> listaContenidos;
    private Contenido contenidoSelected;
    private List<Contenido> contenidosEstaSerie;
    private List<String> temporadasSerie;
    private List<String> capítulosSerie;
    private Spinner spinnerTemp, spinnerCap;
    private String temporadaSelected,capituloSelected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vista_detallada_capitulo);

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
        buttonCarrito = findViewById(R.id.buttonCarrito);

        buttonEliminarDelCarrito = findViewById(R.id.buttonQuitarDelCarrito);

        spinnerTemp = findViewById(R.id.spinnerTemporadas);
        spinnerTemp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                temporadaSelected = (String)parent.getItemAtPosition(position);

                contenidosEstaSerie = listaContenidos.stream()
                        .filter(c -> c.getTipoContenido().equals("capítulo"))
                        .filter(c -> c.getNombreSerie().equals(contenidoBundleExtras.getNombreSerie()))
                        .filter(c -> c.getNumeroTemporada() == Integer.parseInt(temporadaSelected))
                        .collect(Collectors.toList());

                capítulosSerie = contenidosEstaSerie.stream().map(Contenido::getNumCapítulo)
                        .map((capNum -> String.valueOf(capNum)))
                        .distinct().collect(Collectors.toList());

                ArrayAdapter<String> adapterCaps = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item, capítulosSerie);

                spinnerCap.setAdapter(adapterCaps);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinnerCap = findViewById(R.id.spinnerCapitulos);
        spinnerCap.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                capituloSelected = (String)parent.getItemAtPosition(position);

                contenidoSelected = contenidosEstaSerie.stream()
                                .filter(c -> String.valueOf(c.getNumCapítulo()).equals(capituloSelected))
                                        .findFirst().orElse(null);
                tituloCapítulo.setText(contenidoSelected.getNumeroTemporada()+"-"+contenidoSelected.getNumCapítulo() + ": "+contenidoSelected.getTítulo());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

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

        buttonCarrito.setOnClickListener(view -> {

            if (capituloSelected.isEmpty() || temporadaSelected.isEmpty()){
                Toast.makeText(getApplicationContext(), "Selecciona un capítulo y una temporada.", Toast.LENGTH_SHORT).show();
            } else {
                showProgress();
                executeCall(new CallInterface() {
                    @Override
                    public void doInBackground() {
                        Carrito carrito = Connector.getConector().get(Carrito.class,"/clientesCarrito/"+ Parameters.idClienteSesión);
                        contenidosCarritoCliente = Connector.getConector().getAsList(Contenido.class,"/contenidoCarrito/"+carrito.getIdCarrito());
                        contenidosAlquiladosCliente = Connector.getConector().getAsList(Contenido.class,"/contenidoCliente/"+Parameters.idClienteSesión);

                        if (contenidosCarritoCliente.stream()
                                .filter(con -> con.getTipoContenido().equals("capítulo"))
                                .noneMatch(con -> con.getIdContenido()==contenidoSelected.getIdContenido())
                                && contenidosAlquiladosCliente.stream()
                                .filter(con -> con.getTipoContenido().equals("capítulo"))
                                .noneMatch(con -> con.getIdContenido()==contenidoSelected.getIdContenido()))
                            Connector.getConector().get(Contenido.class,
                                    "/contenidoAñadirCarrito/"+ contenidoSelected.getIdContenido()+"/"+carrito.getIdCarrito());
                    }

                    @Override
                    public void doInUI() {
                        hideProgress();
                        if (contenidosAlquiladosCliente.stream()
                                .filter(con -> con.getTipoContenido().equals("capítulo"))
                                .anyMatch(con -> con.getIdContenido()==contenidoSelected.getIdContenido()) )
                            Toast.makeText(getApplicationContext(), "Ya tiene alquilado el contenido.", Toast.LENGTH_SHORT).show();
                        else if (contenidosCarritoCliente.stream()
                                .filter(con -> con.getTipoContenido().equals("capítulo"))
                                .anyMatch(con -> con.getIdContenido()==contenidoSelected.getIdContenido()))
                            Toast.makeText(getApplicationContext(), "Ya tiene el contenido en el carrito.", Toast.LENGTH_SHORT).show();
                        else
                            finish();
                    }
                });
            }
        });

        buttonEliminarDelCarrito.setOnClickListener(view -> {
            showProgress();
            executeCall(new CallInterface() {
                @Override
                public void doInBackground() {
                    Carrito carrito = Connector.getConector().get(Carrito.class,"/clientesCarrito/"+ Parameters.idClienteSesión);
                    contenidosCarritoCliente = Connector.getConector().getAsList(Contenido.class,"/contenidoCarrito/"+carrito.getIdCarrito());
                    contenidosAlquiladosCliente = Connector.getConector().getAsList(Contenido.class,"/contenidoCliente/"+Parameters.idClienteSesión);

                    if (contenidosCarritoCliente.contains(contenidoBundleExtras))
                        Connector.getConector().delete(Contenido.class,
                                "/contenidoEliminarCarrito/"+ contenidoBundleExtras.getIdContenido()+"/"+carrito.getIdCarrito());
                }

                @Override
                public void doInUI() {
                    hideProgress();
                    if (contenidosCarritoCliente.contains(contenidoBundleExtras)){
                        Toast.makeText(getApplicationContext(), "Contenido eliminado del carrito.", Toast.LENGTH_SHORT).show();
                    } else if (contenidosAlquiladosCliente.contains(contenidoBundleExtras)){
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

        showProgress();
        executeCall(this);

    }
    @Override
    public void doInBackground() {
        listaContenidos = Connector.getConector().getAsList(Contenido.class,"/contenido/");
    }

    @Override
    public void doInUI() {
        hideProgress();
        contenidosEstaSerie = listaContenidos.stream()
                .filter(c -> c.getTipoContenido().equals("capítulo"))
                .filter(c -> c.getNombreSerie().equals(contenidoBundleExtras.getNombreSerie()))
                .collect(Collectors.toList());
        temporadasSerie = contenidosEstaSerie.stream().map(Contenido::getNumeroTemporada)
                .map((tempNum -> String.valueOf(tempNum)))
                .distinct().collect(Collectors.toList());

        ArrayAdapter<String> adapterTemp = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, temporadasSerie);

        spinnerTemp.setAdapter(adapterTemp);
    }

}
