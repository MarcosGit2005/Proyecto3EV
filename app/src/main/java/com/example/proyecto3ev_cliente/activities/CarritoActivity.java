package com.example.proyecto3ev_cliente.activities;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyecto3ev_cliente.R;
import com.google.android.material.snackbar.Snackbar;

public class CarritoActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private EditText sumaTotal;
    private Button alquilarboton;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_carrito);

        recyclerView = findViewById(R.id.recyclerViewCarrito);
        sumaTotal = findViewById(R.id.precioTotal);
        alquilarboton = findViewById(R.id.botonAlquilar);

    }
}
