package com.example.proyecto3ev_cliente.activities;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.proyecto3ev_cliente.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ActivityDetailed extends AppCompatActivity {

    private ImageView imagenPelicula;
    private TextView titulo;
    private TextView nota;
    private TextView notaMedia;
    private TextView precio;
    private TextView genero;
    private TextView duracion;
    private TextView director;
    private TextView actores;
    private TextView fechaEstreno;
    private EditText voto;
    private FloatingActionButton buttonCarrito;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vista_detallada);

        imagenPelicula = findViewById(R.id.imageViewPelicula);
        titulo = findViewById(R.id.textViewTituloDetailed);
        nota = findViewById(R.id.textViewNotaDetailed);
        notaMedia = findViewById(R.id.textViewNotaMediaDetailed);
        precio = findViewById(R.id.textViewPrecioDetailed);
        genero = findViewById(R.id.textViewGeneroDetailed);
        duracion = findViewById(R.id.textViewDuracionDetailed);
        director = findViewById(R.id.textViewDirectorDetailed);
        actores = findViewById(R.id.textViewActoresDetailed);
        fechaEstreno = findViewById(R.id.textViewFechaDetailed);
        voto = findViewById(R.id.editTextNota);
        buttonCarrito = findViewById(R.id.buttonCarrito);

    }
}
