package com.example.crud.Presentador;

import android.content.Context;
import android.util.Log;

import com.example.crud.Interfaces.InterfaceUsuarios;
import com.example.crud.View.LoginActivity;
import com.example.crud.db.DbUsuarios;
import com.example.crud.entidades.Usuarios;
import com.example.crud.modelos.ModelUsuarios;

public class PresentUsuarios implements InterfaceUsuarios.Presenter {

    InterfaceUsuarios.View view;
    InterfaceUsuarios.Model model;
    Context context;

    public PresentUsuarios(InterfaceUsuarios.View view, Context context) {
        this.view = view;
        this.context = context;
        this.model = new ModelUsuarios(this, context);
    }

    @Override
    public boolean Iniciar(Usuarios usuarios) {
        return this.model.Login(usuarios);
    }

    @Override
    public long agregarUsuario(Usuarios usuarios) {
        return this.model.insertarU(usuarios);
    }
}
