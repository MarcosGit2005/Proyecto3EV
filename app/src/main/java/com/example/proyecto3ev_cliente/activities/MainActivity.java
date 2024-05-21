package com.example.proyecto3ev_cliente.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.proyecto3ev_cliente.API.Connector;
import com.example.proyecto3ev_cliente.R;
import com.example.proyecto3ev_cliente.activities.model.Cliente;
import com.example.proyecto3ev_cliente.activities.preferences.GestionPreferencias;
import com.example.proyecto3ev_cliente.activities.preferences.PreferenciasActivity;
import com.example.proyecto3ev_cliente.activities.preferences.ThemeSetup;
import com.example.proyecto3ev_cliente.base.BaseActivity;
import com.example.proyecto3ev_cliente.base.CallInterface;
import com.example.proyecto3ev_cliente.base.Parameters;

/**
 * Actividad principal en la que un cliente pone su usuario y contraseña para poder acceder al contenido de las demas actividades.
 */
public class MainActivity extends BaseActivity implements CallInterface {

    private ImageView imagenLogo;
    private EditText usuario;
    private EditText password;
    private Button botonEntrar;
    private Button botonRecordarPass;
    private Button botonCrearCuenta;
    private Cliente clienteSesion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ThemeSetup.applyPreferenceTheme(getApplicationContext());

        String ruta = GestionPreferencias.getInstance().getRutaServer(getApplicationContext());
        String requestMap = GestionPreferencias.getInstance().getRequestMapping(getApplicationContext());
        String lang = GestionPreferencias.getInstance().getLanguage(getApplicationContext());

        Parameters.ip_port = ruta;
        Parameters.requestMapping = requestMap;
        Parameters.LANG = lang;
        Parameters.URL = Parameters.API + Parameters.ip_port + Parameters.requestMapping;

        imagenLogo = findViewById(R.id.imageViewLogo);
        usuario = findViewById(R.id.editTextUsuario);
        password = findViewById(R.id.editTextContraseña);
        botonEntrar = findViewById(R.id.buttonEntrar);
        botonRecordarPass = findViewById(R.id.buttonRecordarPass);
        botonCrearCuenta = findViewById(R.id.buttonCrearCuenta);

        botonCrearCuenta.setOnClickListener(view -> {
            Intent intent = new Intent(this, CrearCuentaActivity.class);
            startActivity(intent);
        });
        botonRecordarPass.setOnClickListener(view ->{
            Intent intent = new Intent(this,ContrasenyaActivity.class);
            startActivity(intent);
        });

        botonEntrar.setOnClickListener(view -> {
            if (usuario.getText().toString().isEmpty() || password.getText().toString().isEmpty()){
                Toast.makeText(getApplicationContext(), "Escribe el usuario y la contraseña!", Toast.LENGTH_SHORT).show();
            } else {
                showProgress();
                executeCall(this);
            }
        });
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

    @Override
    public void doInBackground(){

        String user = usuario.getText().toString();
        String pass = password.getText().toString();

        clienteSesion = Connector.getConector().get(Cliente.class,
                "/clientesLogin/"+user+"/"+pass);

    }

    @Override
    public void doInUI() {
        hideProgress();
        if (clienteSesion==null){
            Toast.makeText(getApplicationContext(), "No se ha podido iniciar sesión.", Toast.LENGTH_SHORT).show();
        } else {
            Parameters.idClienteSesión = clienteSesion.getUsuario();
            Intent intent = new Intent(this, PeliculasActivity.class);
            intent.putExtra("clienteSesion",clienteSesion);
            startActivity(intent);
        }
    }
}