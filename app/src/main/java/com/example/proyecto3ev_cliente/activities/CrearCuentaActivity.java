package com.example.proyecto3ev_cliente.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;

import com.example.proyecto3ev_cliente.API.Connector;
import com.example.proyecto3ev_cliente.R;
import com.example.proyecto3ev_cliente.activities.model.Cliente;
import com.example.proyecto3ev_cliente.base.BaseActivity;
import com.example.proyecto3ev_cliente.base.CallInterface;

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
            String nombre = nombreEditText.getText().toString();
            String apellidos = apellidosEditText.getText().toString();
            String domicilio = domicilioEditText.getText().toString();
            String cp = codigoPostalEditText.getText().toString();
            String email = correoEditText.getText().toString();
            String fechaNac = dateEditText.getText().toString();
            String numTarjeta = numTarjetaEditText.getText().toString();
            String usuario = usuarioEditText.getText().toString();
            String password = passwordEditText.getText().toString();

            if (nombre.isEmpty() || apellidos.isEmpty() || domicilio.isEmpty() || cp.isEmpty() ||
                    email.isEmpty() || fechaNac.isEmpty() || numTarjeta.isEmpty() ||
                    usuario.isEmpty() || password.isEmpty()){
                Toast.makeText(getApplicationContext(), "Introduce todos los datos por favor.", Toast.LENGTH_SHORT).show();
            } else {

            }
            showProgress();
            executeCall(this);
        });

    }

    @Override
    public void doInBackground() {
        String nombre = nombreEditText.getText().toString();
        String apellidos = apellidosEditText.getText().toString();
        String domicilio = domicilioEditText.getText().toString();
        int cp = Integer.parseInt(codigoPostalEditText.getText().toString().equals("")?"0":codigoPostalEditText.getText().toString());
        String email = correoEditText.getText().toString();
        String fechaNac = dateEditText.getText().toString();
        String numTarjeta = numTarjetaEditText.getText().toString();
        String usuario = usuarioEditText.getText().toString();
        String password = passwordEditText.getText().toString();

        clienteCreado = Connector.getConector().post(Cliente.class,
                new Cliente(usuario,password,nombre,apellidos,domicilio,cp,email,
                        fechaNac,numTarjeta,"cliente",null),"/clientes/");
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