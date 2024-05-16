package com.example.proyecto3ev_cliente.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.proyecto3ev_cliente.R;

public class ContrasenyaActivity extends AppCompatActivity {

    private EditText nombreUsuario;
    private EditText nuevaContrasenya;
    private EditText repetirContrasenya;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recordar_contrasenya);

        nombreUsuario = findViewById(R.id.usuario);
        nuevaContrasenya = findViewById(R.id.newPassword);
        repetirContrasenya = findViewById(R.id.RepNewPassword);


    }
}
