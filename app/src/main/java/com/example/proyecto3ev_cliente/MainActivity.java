package com.example.proyecto3ev_cliente;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private ImageView imagenLogo;
    private EditText usuario;
    private EditText password;
    private Button botonEntrar;
    private Button botonRecordarPass;
    private Button botonCrearCuenta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vista_detallada);

        imagenLogo = findViewById(R.id.imageViewLogo);
        usuario = findViewById(R.id.editTextUsuario);
        password = findViewById(R.id.editTextContraseña);
        botonEntrar = findViewById(R.id.buttonEntrar);
        botonRecordarPass = findViewById(R.id.buttonRecordarPass);
        botonCrearCuenta = findViewById(R.id.buttonCrearCuenta);


    }
}