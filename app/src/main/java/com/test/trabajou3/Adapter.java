package com.test.trabajou3;

import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ElementoViewHolder> {

    private List<ElementoInformatico> elementos;

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

        // Asignar los datos a las vistas
        holder.elementoFoto.setImageResource(elemento.getFoto());
        holder.elementoNombre.setText(elemento.getNombre());
        holder.elementoDescripcion.setText(elemento.getDescripcion());
        holder.elementoId.setText("ID: " + elemento.getId());  // ID del producto
        holder.elementoUnidades.setText("Unidades: " + elemento.getUnidades());  // Unidades

        // Crear un borde redondeado para cada elemento
        GradientDrawable drawable = new GradientDrawable();
        drawable.setShape(GradientDrawable.RECTANGLE);
        drawable.setCornerRadius(12f); // Establecer radio de esquinas redondeadas
        drawable.setStroke(2, 0xFFE0E0E0); // Borde de 2px de color gris claro
        drawable.setColor(0xFFFFFFFF); // Fondo blanco
        holder.itemView.setBackground(drawable);  // Aplicar el fondo redondeado al LinearLayout
    }

    @Override
    public int getItemCount() {
        return elementos.size();
    }

    public void updateList(List<ElementoInformatico> nuevosElementos) {
        this.elementos = nuevosElementos;
        notifyDataSetChanged(); // Notificar al adaptador que los datos han cambiado
    }

    static class ElementoViewHolder extends RecyclerView.ViewHolder {
        ImageView elementoFoto;
        TextView elementoNombre;
        TextView elementoDescripcion;
        TextView elementoId;
        TextView elementoUnidades;

        public ElementoViewHolder(@NonNull View itemView) {
            super(itemView);
            elementoFoto = itemView.findViewById(R.id.imagen);
            elementoNombre = itemView.findViewById(R.id.titulo);
            elementoDescripcion = itemView.findViewById(R.id.subtitulo);
            elementoId = itemView.findViewById(R.id.idProducto);
            elementoUnidades = itemView.findViewById(R.id.unidades);
        }
    }
}
