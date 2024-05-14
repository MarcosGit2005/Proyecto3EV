package com.example.proyecto3ev_cliente.activities;


import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.proyecto3ev_cliente.PreferenciasFragment;

public class PreferenciasActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportFragmentManager().beginTransaction()
                .replace(android.R.id.content, new PreferenciasFragment())
                .commit();
    }
}