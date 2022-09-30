package com.example.crud.Presentador;

import android.content.Context;

import com.example.crud.Interfaces.InterfaceContactos;
import com.example.crud.Interfaces.InterfaceUsuarios;
import com.example.crud.entidades.Contactos;
import com.example.crud.modelos.ModelContactos;
import com.example.crud.modelos.ModelUsuarios;

import java.util.ArrayList;

public class PresentContactos implements InterfaceContactos.Presenter {

    InterfaceContactos.View view;
    InterfaceContactos.Model model;
    Context context;

    public PresentContactos(InterfaceContactos.View view, Context context) {
        this.view = view;
        this.context = context;
        this.model = new ModelContactos(this, context);
    }

    @Override
    public long insertar(Contactos contactos) {
        return this.model.insertarContacto(contactos);
    }

    @Override
    public ArrayList<Contactos> mostrar() {
        return this.model.mostrarContactos();
    }

    @Override
    public Contactos ver(Contactos contactos) {
        return this.model.verContacto(contactos);
    }

    @Override
    public boolean editar(Contactos contactos) {
        return this.model.editarContacto(contactos);
    }

    @Override
    public boolean eliminar(Contactos contactos) {
        return this.model.eliminarContacto(contactos);
    }
}
