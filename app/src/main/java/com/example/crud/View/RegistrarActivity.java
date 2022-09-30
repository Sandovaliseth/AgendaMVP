package com.example.crud.View;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.crud.Interfaces.InterfaceUsuarios;
import com.example.crud.Presentador.PresentUsuarios;
import com.example.crud.R;
import com.example.crud.db.DbUsuarios;
import com.example.crud.entidades.Usuarios;

public class RegistrarActivity extends AppCompatActivity implements View.OnClickListener, InterfaceUsuarios.View {

    EditText nombre, apellido, correo, contrasena;
    Button registrar;
    InterfaceUsuarios.Presenter presenter;
    Usuarios usuarios;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar);

        usuarios = new Usuarios();
        presenter = new PresentUsuarios(this, RegistrarActivity.this);
        this.variables();
    }

    private void limpiar(){
        nombre.setText("");
        apellido.setText("");
        correo.setText("");
        contrasena.setText("");
    }

    @Override
    public void variables() {
        nombre = findViewById(R.id.txtNombreU);
        apellido = findViewById(R.id.txtApellidoU);
        correo = findViewById(R.id.txtCorreo);
        contrasena = findViewById(R.id.txtContrasena);
        registrar = findViewById(R.id.btnRegistrar);
        registrar.setOnClickListener(this);
    }

    private long procesoExitoso(long dato) {
        if(dato>0){
            Toast.makeText(RegistrarActivity.this, "Registro guardado", Toast.LENGTH_LONG).show();
            limpiar();
        } else{
            Toast.makeText(RegistrarActivity.this, "No se pudo registrar el usuario", Toast.LENGTH_LONG).show();
        }
        return dato;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnRegistrar:
                DbUsuarios dbUsuarios = new DbUsuarios(RegistrarActivity.this);
                String nombreU = nombre.getText().toString();
                usuarios.setNombre(nombreU);
                String apellidoU = apellido.getText().toString();
                usuarios.setApellido(apellidoU);
                String correoU = correo.getText().toString();
                usuarios.setCorreo(correoU);
                String contrasenaU = contrasena.getText().toString();
                usuarios.setContrasena(contrasenaU);
                long id = presenter.agregarUsuario(usuarios);
                procesoExitoso(id);
                break;
        }
    }

}