package com.example.crud.Interfaces;

import android.content.Context;

import com.example.crud.db.DbUsuarios;
import com.example.crud.entidades.Usuarios;

public interface InterfaceUsuarios {

    interface View {
        void variables();
    }

    interface Presenter {
        boolean Iniciar(Usuarios usuarios);
        long agregarUsuario(Usuarios usuarios);
    }

    interface Model {
        boolean Login(Usuarios usuarios);
        long insertarU(Usuarios usuarios);
    }
}
