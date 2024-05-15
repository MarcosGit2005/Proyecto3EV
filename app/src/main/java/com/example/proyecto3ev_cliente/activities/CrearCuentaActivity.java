package com.example.proyecto3ev_cliente.activities;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;


import androidx.appcompat.app.AppCompatActivity;

import com.example.proyecto3ev_cliente.R;
import com.example.proyecto3ev_cliente.base.BaseActivity;
import com.example.proyecto3ev_cliente.base.CallInterface;

public class CrearCuentaActivity extends AppCompatActivity implements CallInterface {
    private EditText nombreApellidos;
    private EditText domicilio;
    private EditText codigoPostal;
    private EditText correo;
    private EditText date;
    private EditText numTarjeta;
    private EditText nomUsuario;
    private EditText password;
    private Button botonCrear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_cuenta);

        nombreApellidos = findViewById(R.id.nombreApellidos);
        domicilio = findViewById(R.id.domicilio);
        codigoPostal = findViewById(R.id.domicilio) ;
        correo = findViewById(R.id.correo);
        date = findViewById(R.id.date);
        numTarjeta = findViewById(R.id.numTarjeta);
        nomUsuario = findViewById(R.id.nomUsuario);
        password = findViewById(R.id.password);
        botonCrear = findViewById(R.id.botonCrear);


    }

    @Override
    public void doInBackground() {

    }

    @Override
    public void doInUI() {

    }
}