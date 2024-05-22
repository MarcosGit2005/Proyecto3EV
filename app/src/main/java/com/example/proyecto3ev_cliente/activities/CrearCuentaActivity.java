package com.example.proyecto3ev_cliente.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;

import com.example.proyecto3ev_cliente.API.Connector;
import com.example.proyecto3ev_cliente.R;
import com.example.proyecto3ev_cliente.activities.model.Cliente;
import com.example.proyecto3ev_cliente.base.BaseActivity;
import com.example.proyecto3ev_cliente.base.CallInterface;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Actividad para crear una cuenta nueva de cliente con un formulario para añadirlo a la base de datos.
 */
public class CrearCuentaActivity extends BaseActivity implements CallInterface {
    private EditText nombreEditText;
    private EditText apellidosEditText;
    private EditText domicilioEditText;
    private EditText codigoPostalEditText;
    private EditText correoEditText;
    private EditText dateEditText;
    private EditText numTarjetaEditText;
    private EditText usuarioEditText;
    private EditText passwordEditText;
    private Button botonCrear;
    private Cliente clienteCreado;


    private String nombre;
    private String apellidos;
    private String domicilio;
    private int cp;
    private String email;
    private String fechaNac;
    private String numTarjeta;
    private String usuario;
    private String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_cuenta);

        nombreEditText = findViewById(R.id.nombre);
        apellidosEditText = findViewById(R.id.apellidos);
        domicilioEditText = findViewById(R.id.domicilio);
        codigoPostalEditText = findViewById(R.id.codigoPostal) ;
        correoEditText = findViewById(R.id.correo);
        dateEditText = findViewById(R.id.date);
        numTarjetaEditText = findViewById(R.id.numTarjeta);
        usuarioEditText = findViewById(R.id.nomUsuario);
        passwordEditText = findViewById(R.id.password);

        botonCrear = findViewById(R.id.botonCrear);
        botonCrear.setOnClickListener(view -> {

            nombre = nombreEditText.getText().toString();
            apellidos = apellidosEditText.getText().toString();
            domicilio = domicilioEditText.getText().toString();
            cp = Integer.parseInt(codigoPostalEditText.getText().toString().equals("")?"0":codigoPostalEditText.getText().toString());
            email = correoEditText.getText().toString();
            fechaNac = dateEditText.getText().toString();
            numTarjeta = numTarjetaEditText.getText().toString();
            usuario = usuarioEditText.getText().toString();
            password = passwordEditText.getText().toString();

            // Controla que la fecha se pase con el formato correcto
            boolean validDate = isValidDate(fechaNac);

            if (nombre.isEmpty() || apellidos.isEmpty() || domicilio.isEmpty() ||
                    email.isEmpty() || fechaNac.isEmpty() || numTarjeta.isEmpty() ||
                    usuario.isEmpty() || password.isEmpty()){
                Toast.makeText(getApplicationContext(), "Introduce todos los datos por favor.", Toast.LENGTH_SHORT).show();
            } else if (!validDate){
                Toast.makeText(getApplicationContext(), "Introduce una fecha válida por favor.", Toast.LENGTH_SHORT).show();
            } else {
                showProgress();
                executeCall(this);
            }

        });

    }
    private static boolean isValidDate(String date) {
        // Verificar el formato básico usando una expresión regular
        String regex = "^(\\d{4})-(\\d{2})-(\\d{2})$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(date);

        if (!matcher.matches()) {
            return false;
        }

        // Verificar si la fecha es válida usando SimpleDateFormat
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sdf.setLenient(false);

        try {
            sdf.parse(date);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

    @Override
    public void doInBackground() {
        clienteCreado = Connector.getConector().post(Cliente.class,
                new Cliente(usuario,password,nombre,apellidos,domicilio,cp,email,
                        fechaNac,numTarjeta,"cliente",null),"/clientes/");
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_facturas_carrito_alquiladas, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.exit) {
            finish();
        }
        return super.onOptionsItemSelected(item);

    }

    @Override
    public void doInUI() {
        hideProgress();
        if (clienteCreado==null){
            Toast.makeText(getApplicationContext(), "No se ha podido crear el cliente.", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getApplicationContext(), "Cuenta creada, ya puedes iniciar sesión.", Toast.LENGTH_SHORT).show();
        }
    }
}