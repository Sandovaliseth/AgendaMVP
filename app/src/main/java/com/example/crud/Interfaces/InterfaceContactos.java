package com.example.crud.Interfaces;

import com.example.crud.entidades.Contactos;

import java.util.ArrayList;

public interface InterfaceContactos {

    interface View {
        void variables();
    }

    interface Presenter {
        long insertar(Contactos contactos);
        ArrayList<Contactos> mostrar();
        Contactos ver(Contactos contactos);
        boolean editar(Contactos contactos);
        boolean eliminar(Contactos contactos);
    }

    interface Model {
        long insertarContacto(Contactos contactos);
        ArrayList<Contactos> mostrarContactos();
        Contactos verContacto (Contactos contactos);
        boolean editarContacto(Contactos contactos);
        boolean eliminarContacto(Contactos contactos);
    }
}
