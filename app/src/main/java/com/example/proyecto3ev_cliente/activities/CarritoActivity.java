package com.example.proyecto3ev_cliente.activities;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyecto3ev_cliente.R;
import com.example.proyecto3ev_cliente.base.BaseActivity;
import com.example.proyecto3ev_cliente.base.CallInterface;
import com.google.android.material.snackbar.Snackbar;

public class CarritoActivity extends BaseActivity implements CallInterface {
    private RecyclerView recyclerView;
    private EditText sumaTotal;
    private Button alquilarboton;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carrito);

        recyclerView = findViewById(R.id.recyclerViewCarrito);
        sumaTotal = findViewById(R.id.precioTotal);
        alquilarboton = findViewById(R.id.botonAlquilar);

    }

    @Override
    public void doInBackground() {

    }

    @Override
    public void doInUI() {

    }
}
