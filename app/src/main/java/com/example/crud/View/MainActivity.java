package com.example.crud.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.crud.Interfaces.InterfaceContactos;
import com.example.crud.Presentador.PresentContactos;
import com.example.crud.R;
import com.example.crud.adaptadores.listaContactosAdapter;
import com.example.crud.entidades.Contactos;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, InterfaceContactos.View{

    SharedPreferences sharedPreference;
    RecyclerView listaContactos;
    ArrayList<Contactos> listaArrayContactos;
    PresentContactos presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.variables();
    }

    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_principal, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.menuNuevo:
                nuevoRegistro();
                return true;
            case R.id.cerrarSesion:
                CerrarSesion();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void nuevoRegistro(){
        Intent intent = new Intent(this, NuevoActivity.class);
        startActivity(intent);
    }

    private void CerrarSesion(){
        sharedPreference.edit().clear().apply();
        Toast.makeText(MainActivity.this, "Sesion finalizada", Toast.LENGTH_LONG
        ).show();
        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }

    @Override
    public void variables() {
        sharedPreference = getSharedPreferences("bd_shared", Context.MODE_PRIVATE);
        listaContactos= findViewById(R.id.listaContactos);
        listaContactos.setLayoutManager(new LinearLayoutManager(this));
        presenter = new PresentContactos(this, getApplicationContext());
        listaArrayContactos = new ArrayList<>();
        listaContactosAdapter adapter = new listaContactosAdapter(presenter.mostrar());
        listaContactos.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
        }
    }
}