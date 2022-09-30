package com.example.crud.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.crud.Interfaces.InterfaceContactos;
import com.example.crud.Presentador.PresentContactos;
import com.example.crud.R;
import com.example.crud.entidades.Contactos;

public class NuevoActivity extends AppCompatActivity implements View.OnClickListener, InterfaceContactos.View {

    EditText txtNombre, txtTelefono, txtCorreoElectronico;
    Button btn_guardar;
    PresentContactos presenter;
    Contactos contactos = new Contactos();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo);

        this.variables();
        presenter = new PresentContactos(this, getApplicationContext());
    }

    private void limpiar(){
        txtNombre.setText("");
        txtTelefono.setText("");
        txtCorreoElectronico.setText("");
    }

    @Override
    public void variables() {
        txtNombre = findViewById(R.id.txtNombre);
        txtTelefono = findViewById(R.id.txtTelefono);
        txtCorreoElectronico = findViewById(R.id.txtCorreoElectronico);
        btn_guardar = findViewById(R.id.btn_guardar);
        btn_guardar.setOnClickListener(this);
    }

    private long procesoExitoso(long dato) {
        if(dato>0){
            Toast.makeText(NuevoActivity.this, "Registro guardado", Toast.LENGTH_LONG).show();
            limpiar();
            Intent intent = new Intent(NuevoActivity.this, MainActivity.class);
            startActivity(intent);
        } else{
            Toast.makeText(NuevoActivity.this, "Error al guardar registro", Toast.LENGTH_LONG
            ).show();
        }
        return dato;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_guardar:
                String nombreC = txtNombre.getText().toString();
                contactos.setNombre(nombreC);
                String telefonoC = txtTelefono.getText().toString();
                contactos.setTelefono(telefonoC);
                String correoC = txtCorreoElectronico.getText().toString();
                contactos.setCorreo_electronico(correoC);
                long id= presenter.insertar(contactos);
                procesoExitoso(id);
                break;
        }
    }
}