package com.example.crud.entidades;

import android.content.Context;
import android.content.SharedPreferences;

public class SharePreference {
     Context context;
     SharedPreferences.Editor editor;
     SharedPreferences sp;

    public SharePreference(Context context) {
        this.context = context;
        sp = context.getSharedPreferences("bd_shared", Context.MODE_PRIVATE);
        editor = sp.edit();
    }

    public String getSharedPreferences() {
        return sp.getString("correo", "Usuario no registrado");
    }

    public void setSharedPreferences(String correo) {
        editor.putString("correo", correo);
        editor.apply();
    }

    public void guardarSesion(Boolean checked) {
        editor.putBoolean("llave", checked);
        editor.apply();
    }

    public boolean revisarSesion() {
        return this.sp.getBoolean("llave", false);
    }

}
