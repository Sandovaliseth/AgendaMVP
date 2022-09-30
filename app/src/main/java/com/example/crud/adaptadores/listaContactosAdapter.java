package com.example.crud.adaptadores;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.crud.R;
import com.example.crud.View.VerActivity;
import com.example.crud.entidades.Contactos;

import java.util.ArrayList;

public class listaContactosAdapter extends RecyclerView.Adapter<listaContactosAdapter.contactoViewHolder> {

    ArrayList<Contactos> listaContactos;

    public listaContactosAdapter (ArrayList<Contactos> listaContactos){
        this.listaContactos= listaContactos;
    }

    @NonNull
    @Override
    public contactoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_item_contacto, null,    false);
        return new contactoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull contactoViewHolder holder, int position) {
        holder.viewNombre.setText(listaContactos.get(position).getNombre());
        holder.viewTelefono.setText(listaContactos.get(position).getTelefono());
        holder.viewCorreo.setText(listaContactos.get(position).getCorreo_electronico());
    }

    @Override
    public int getItemCount() {
        return listaContactos.size();
    }

    public class contactoViewHolder extends RecyclerView.ViewHolder {
        TextView viewNombre, viewTelefono, viewCorreo;
        public contactoViewHolder(@NonNull View itemView) {
            super(itemView);
            viewNombre = itemView.findViewById(R.id.viewNombre);
            viewTelefono = itemView.findViewById(R.id.viewTelefono);
            viewCorreo = itemView.findViewById(R.id.viewCorreo);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Context context = view.getContext();
                    Intent intent = new Intent(context, VerActivity.class);
                    intent.putExtra("ID", listaContactos.get(getAdapterPosition()).getId());
                    context.startActivity(intent);
                }
            });
        }
    }
}
