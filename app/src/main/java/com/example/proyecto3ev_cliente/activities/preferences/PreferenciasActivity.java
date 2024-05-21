package com.example.proyecto3ev_cliente.activities.preferences;


import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.proyecto3ev_cliente.base.Parameters;

/**
 * Actividad para mostrar el menú de preferencias.
 */
public class PreferenciasActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportFragmentManager().beginTransaction()
                .replace(android.R.id.content, new PreferenciasFragment())
                .commit();
    }
    @Override
    public void finish() {
        Parameters.requestMapping = GestionPreferencias.getInstance().getRequestMapping(getApplicationContext());
        Parameters.ip_port = GestionPreferencias.getInstance().getRutaServer(getApplicationContext());
        Parameters.LANG = GestionPreferencias.getInstance().getLanguage(getApplicationContext());
        super.finish();
    }
}