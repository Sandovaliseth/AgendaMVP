package com.example.crud.modelos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.crud.Interfaces.InterfaceContactos;
import com.example.crud.db.DbHelper;
import com.example.crud.entidades.Contactos;

import java.util.ArrayList;

public class ModelContactos extends DbHelper implements InterfaceContactos.Model{

    Context context;
    InterfaceContactos.Presenter presenter;
    SQLiteDatabase db;
    public static final String NOMBRE = "nombre";
    public static final String TELEFONO = "telefono";
    public static final String EMAIL = "correo_electronico";

    public ModelContactos(InterfaceContactos.Presenter presenter, Context context) {
        super(context);
        this.context = context;
        this.presenter = presenter;
    }

    public SQLiteDatabase getOpenConexionDb (Context context) {
        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        this.context= context;
        return db;
    }

    @Override
    public long insertarContacto(Contactos contactos) {
        long id=0;
        try{
            db= this.getOpenConexionDb(context);
            ContentValues values = new ContentValues();
            values.put(NOMBRE, contactos.getNombre());
            values.put(TELEFONO, contactos.getTelefono());
            values.put(EMAIL, contactos.getCorreo_electronico());
            id = db.insert(TABLE_CONTACTOS, null, values);
        } catch (Exception ex){
            ex.toString();
        }
        return id;
    }

    @Override
    public ArrayList<Contactos> mostrarContactos(){
        db= this.getOpenConexionDb(context);
        ArrayList<Contactos> listaContactos = new ArrayList<>();
        Contactos contacto = null;
        Cursor cursorContactos = null;
        cursorContactos = db.rawQuery("SELECT * FROM " + TABLE_CONTACTOS, null);
        if(cursorContactos.moveToFirst()){
            do{
                contacto = new Contactos();
                contacto.setId(cursorContactos.getInt(0));
                contacto.setNombre(cursorContactos.getString(1));
                contacto.setTelefono(cursorContactos.getString(2));
                contacto.setCorreo_electronico(cursorContactos.getString(3));
                listaContactos.add(contacto);
            } while (cursorContactos.moveToNext());
        }
        cursorContactos.close();
        return listaContactos;
    }

    @Override
    public Contactos verContacto(Contactos contactos) {
        db= this.getOpenConexionDb(context);
        Contactos contacto = null;
        Cursor cursorContactos;
        cursorContactos = db.rawQuery("SELECT * FROM " + TABLE_CONTACTOS + " WHERE id= " + contactos.getId() + " LIMIT 1", null);
        if(cursorContactos.moveToFirst()){
            contacto = new Contactos();
            contacto.setId(cursorContactos.getInt(0));
            contacto.setNombre(cursorContactos.getString(1));
            contacto.setTelefono(cursorContactos.getString(2));
            contacto.setCorreo_electronico(cursorContactos.getString(3));
        }
        cursorContactos.close();
        return contacto;
    }

    @Override
    public boolean editarContacto(Contactos contactos) {
        boolean correcto = false;
        db= this.getOpenConexionDb(context);
        try{
            db.execSQL("UPDATE " + TABLE_CONTACTOS + " SET nombre= '" +contactos.getNombre()+"', telefono= '" +contactos.getTelefono()+"', correo_electronico= '" + contactos.getCorreo_electronico() +"' WHERE id= '" +contactos.getId()+"'");
            correcto = true;
        } catch (Exception ex){
            ex.toString();
            correcto = false;
        } finally {
            db.close();
        }
        return correcto;
    }

    @Override
    public boolean eliminarContacto(Contactos contactos) {
        boolean correcto = false;
        db= this.getOpenConexionDb(context);
        try{
            db.execSQL("DELETE FROM " + TABLE_CONTACTOS + " WHERE id= '" +contactos.getId()+"'");
            correcto = true;
        } catch (Exception ex){
            ex.toString();
            correcto = false;
        } finally {
            db.close();
        }
        return correcto;
    }
}
