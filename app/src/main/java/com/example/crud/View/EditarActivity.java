package com.example.crud.View;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.crud.Interfaces.InterfaceContactos;
import com.example.crud.Presentador.PresentContactos;
import com.example.crud.R;
import com.example.crud.entidades.Contactos;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class EditarActivity extends AppCompatActivity implements View.OnClickListener, InterfaceContactos.View {

    EditText txtNombre, txtTelefono, txtCorreo;
    Button btnGuarda;
    boolean correcto = false;
    Contactos contacto = new Contactos();
    int id=0;
    FloatingActionButton fabEditar, fabEliminar;
    PresentContactos presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver);

        this.variables();
        presenter = new PresentContactos(this, getApplicationContext());

        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras == null) {
                id = Integer.parseInt(null);
            } else {
                id = extras.getInt("ID");
            }
        } else {
            id = (int) savedInstanceState.getSerializable("ID");
        }

        contacto.setId(id);
        contacto = presenter.ver(contacto);
        if (contacto != null) {
            txtNombre.setText(contacto.getNombre());
            txtTelefono.setText(contacto.getTelefono());
            txtCorreo.setText(contacto.getCorreo_electronico());
        }
    }

    private void verRegistro() {
        Intent intent = new Intent(this, VerActivity.class);
        intent.putExtra("ID", contacto.getId());
        startActivity(intent);
    }

    private boolean vacioValidar(String dato) {
        return !dato.equals("");
    }

    private boolean procesoExitoso(boolean dato) {
        if(dato){
            Toast.makeText(EditarActivity.this, "Registro modificado", Toast.LENGTH_LONG).show();
            verRegistro();
        } else{
            Toast.makeText(EditarActivity.this, "Error al modificar registro", Toast.LENGTH_LONG).show();
        }
        return dato;
    }


    @Override
    public void variables() {
        txtNombre = findViewById(R.id.txtNombre);
        txtTelefono = findViewById(R.id.txtTelefono);
        txtCorreo = findViewById(R.id.txtCorreoElectronico);
        btnGuarda = findViewById(R.id.btn_guardar);
        btnGuarda.setOnClickListener(this);
        fabEditar = findViewById(R.id.fabEditar);
        fabEditar.setVisibility(View.INVISIBLE);
        fabEliminar = findViewById(R.id.fabEliminar);
        fabEliminar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_guardar:
                String nombre= txtNombre.getText().toString(), telefono = txtTelefono.getText().toString();
                if(vacioValidar(nombre) & vacioValidar(telefono)){
                    String nombreC = txtNombre.getText().toString();
                    contacto.setNombre(nombreC);
                    String telefonoC = txtTelefono.getText().toString();
                    contacto.setTelefono(telefonoC);
                    String correoC = txtCorreo.getText().toString();
                    contacto.setCorreo_electronico(correoC);
                    correcto= presenter.editar(contacto);
                    procesoExitoso(correcto);
                } else{
                    Toast.makeText(EditarActivity.this, "Debe llenar los cambios obligatorios", Toast.LENGTH_LONG).show();
                }
                break;
        }
    }
}
