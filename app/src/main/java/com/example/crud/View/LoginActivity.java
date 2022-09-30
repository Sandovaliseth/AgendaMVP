package com.example.crud.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.crud.Interfaces.InterfaceUsuarios;
import com.example.crud.Presentador.PresentUsuarios;
import com.example.crud.R;
import com.example.crud.db.DbUsuarios;
import com.example.crud.entidades.SharePreference;
import com.example.crud.entidades.Usuarios;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener, InterfaceUsuarios.View {

    EditText correo, contrasena;
    Button iniciarSesion;
    TextView irRegistrar;
    Usuarios usuarios;
    CheckBox GuardarCredenciales;
    SharePreference sharePreference;
    InterfaceUsuarios.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        sharePreference= new SharePreference(LoginActivity.this);
        usuarios = new Usuarios();
        presenter = new PresentUsuarios(this, getApplicationContext());
        this.variables();

        if(sharePreference.revisarSesion()) {
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }

    @Override
    public void variables() {
        correo = findViewById(R.id.txtCorreoLogin);
        contrasena = findViewById(R.id.txtContrasenaLogin);
        iniciarSesion = findViewById(R.id.btnIniciar);
        iniciarSesion.setOnClickListener(this);
        irRegistrar= findViewById(R.id.irRegistrar);
        irRegistrar.setOnClickListener(this);
        GuardarCredenciales = findViewById(R.id.GuardarCredenciales);
    }

    private boolean vacioValidar(String dato) {
        return !dato.equals("");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnIniciar:
                String email = correo.getText().toString(), contrasenaU= contrasena.getText().toString();
                if(vacioValidar(email) & vacioValidar(contrasenaU)) {
                    usuarios.setCorreo(email);
                    usuarios.setContrasena(contrasenaU);
                    Boolean validarCC = presenter.Iniciar(usuarios);
                    if(validarCC==true){
                        sharePreference.guardarSesion(GuardarCredenciales.isChecked());
                        Toast.makeText(LoginActivity.this, "Bienvenido", Toast.LENGTH_LONG
                        ).show();
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(LoginActivity.this, "Error de credenciales", Toast.LENGTH_LONG
                        ).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Ingrese el datos vacío", Toast.LENGTH_LONG
                    ).show();
                }
                break;
            case R.id.irRegistrar:
                Intent intent = new Intent(LoginActivity.this, RegistrarActivity.class);
                startActivity(intent);
                finish();
                break;
        }
    }
}