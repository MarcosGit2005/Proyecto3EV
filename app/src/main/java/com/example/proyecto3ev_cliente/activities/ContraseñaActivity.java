package com.example.proyecto3ev_cliente.activities;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.proyecto3ev_cliente.API.Connector;
import com.example.proyecto3ev_cliente.R;
import com.example.proyecto3ev_cliente.activities.model.Cliente;
import com.example.proyecto3ev_cliente.base.BaseActivity;
import com.example.proyecto3ev_cliente.base.CallInterface;

public class ContraseñaActivity extends BaseActivity implements CallInterface {

    private EditText nombreUsuario,newPassword,repNewPassword;
    private Button validar;
    private String usuario, pass, repPass;
    private Cliente oldCliente;
    private Cliente newCliente;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recordar_contrasenya);

        nombreUsuario = findViewById(R.id.usuario);
        newPassword = findViewById(R.id.newPassword);
        repNewPassword = findViewById(R.id.RepNewPassword);

        validar = findViewById(R.id.buttonValidar);
        validar.setOnClickListener(view -> {
            usuario = nombreUsuario.getText().toString();
            pass = newPassword.getText().toString();
            repPass = repNewPassword.getText().toString();

            if (!usuario.isEmpty() && !pass.isEmpty() && !repPass.isEmpty()){
                showProgress();
                executeCall(this);
            }
        });

    }
    @Override
    public void doInBackground() {
        oldCliente = Connector.getConector().get(Cliente.class,"/clientes/"+usuario);
        if (oldCliente!=null){
            if (pass.equals(repPass)){
                oldCliente.setContraseña(pass);
                newCliente = Connector.getConector().put(Cliente.class,oldCliente,"/clientes/");
            }
        }
    }

    @Override
    public void doInUI() {
        hideProgress();
        if (oldCliente==null){
            if (!pass.equals(repPass))
                Toast.makeText(getApplicationContext(), "El cliente no existe y las contraseñas no coinciden.", Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(getApplicationContext(), "El cliente no existe.", Toast.LENGTH_SHORT).show();
        } else if(!pass.equals(repPass)){
            Toast.makeText(getApplicationContext(), "Las contraseñas no coiciden.", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getApplicationContext(), "Has cambiado la contraseña.", Toast.LENGTH_SHORT).show();
        }
    }
}
