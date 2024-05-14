package com.example.proyecto3ev_cliente.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.proyecto3ev_cliente.R;


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
        setContentView(R.layout.activity_login);

        imagenLogo = findViewById(R.id.imageViewLogo);
        usuario = findViewById(R.id.editTextUsuario);
        password = findViewById(R.id.editTextContraseña);
        botonEntrar = findViewById(R.id.buttonEntrar);
        botonRecordarPass = findViewById(R.id.buttonRecordarPass);
        botonCrearCuenta = findViewById(R.id.buttonCrearCuenta);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.configuracion) {
            Intent intentPreferenciasActivity = new Intent(this, PreferenciasActivity.class);
            startActivity(intentPreferenciasActivity);
            return true;
        }

        if(item.getItemId()==R.id.exit){
            finish();
        }
        return super.onOptionsItemSelected(item);

    }
}