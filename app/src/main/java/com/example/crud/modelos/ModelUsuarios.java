package com.example.crud.modelos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.crud.Interfaces.InterfaceUsuarios;
import com.example.crud.db.DbUsuarios;
import com.example.crud.entidades.Usuarios;

public class ModelUsuarios extends DbUsuarios implements InterfaceUsuarios.Model {

    InterfaceUsuarios.Presenter presenter;
    Context context;
    public static final String NOMBRE = "nombre";
    public static final String APELLIDO = "apellido";
    public static final String EMAIL = "correo";
    public static final String CONTRASENA = "contrasena";
    SQLiteDatabase db;

    public ModelUsuarios(InterfaceUsuarios.Presenter presenter, Context context) {
        super(context);
        this.context = context;
        this.presenter = presenter;
    }

    public SQLiteDatabase getOpenConexionDb (Context context) {
        DbUsuarios dbUsuarios = new DbUsuarios(context);
        SQLiteDatabase db = dbUsuarios.getWritableDatabase();
        this.context= context;
        return db;
    }

    @Override
    public boolean Login(Usuarios usuarios) {
        db= this.getOpenConexionDb(context);
        Cursor cursorU = db.rawQuery("SELECT * FROM " + TABLE_USUARIOS + " WHERE correo= '" + usuarios.getCorreo() + "' AND contrasena= '" + usuarios.getContrasena()+"'", null);
        if (cursorU.getCount()>0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public long insertarU(Usuarios usuarios) {
        long id = 0;
        try {
            db= this.getOpenConexionDb(context);
            ContentValues values = new ContentValues();
            values.put(NOMBRE, usuarios.getNombre());
            values.put(APELLIDO, usuarios.getApellido());
            values.put(EMAIL, usuarios.getCorreo());
            values.put(CONTRASENA, usuarios.getContrasena());
            id = db.insert(TABLE_USUARIOS, null, values);
        } catch (Exception ex) {
            ex.toString();
        }
        return id;
    }
}
