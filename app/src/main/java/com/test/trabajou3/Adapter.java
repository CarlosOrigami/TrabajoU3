package com.test.trabajou3;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ElementoViewHolder> {

    private final List<ElementoInformatico> elementos;

    public Adapter(List<ElementoInformatico> elementos) {
        this.elementos = elementos;
    }

    @NonNull
    @Override
    public ElementoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_elemento, parent, false);
        return new ElementoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ElementoViewHolder holder, int position) {
        ElementoInformatico elemento = elementos.get(position);
        holder.elementoFoto.setImageResource(elemento.getFoto());
        holder.elementoNombre.setText(elemento.getNombre());
        holder.elementoDescripcion.setText(elemento.getDescripcion());
    }

    @Override
    public int getItemCount() {
        return elementos.size();
    }

    static class ElementoViewHolder extends RecyclerView.ViewHolder {
        ImageView elementoFoto;
        TextView elementoNombre;
        TextView elementoDescripcion;

        public ElementoViewHolder(@NonNull View itemView) {
            super(itemView);
            elementoFoto = itemView.findViewById(R.id.itemImage);
            elementoNombre = itemView.findViewById(R.id.itemTitle);
            elementoDescripcion = itemView.findViewById(R.id.itemSubtitle);
        }
    }
}
