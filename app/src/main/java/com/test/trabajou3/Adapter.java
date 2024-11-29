package com.test.trabajou3;

import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ElementoViewHolder> {

    // Lista de elementos informáticos que se va a mostrar
    private List<ElementoInformatico> elementos;

    // Constructor del adapter que recibe la lista de elementos
    public Adapter(List<ElementoInformatico> elementos) {
        this.elementos = elementos;
    }

    // Método llamado para inflar el layout de cada item del RecyclerView
    @NonNull
    @Override
    public ElementoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_elemento, parent, false);
        return new ElementoViewHolder(view);
    }

    // Método que vincula los datos de cada elemento a las vistas correspondientes
    @Override
    public void onBindViewHolder(@NonNull ElementoViewHolder holder, int position) {
        // Obtiene el elemento actual basado en la posición
        ElementoInformatico elemento = elementos.get(position);

        holder.elementoFoto.setImageResource(elemento.getFoto());
        holder.elementoNombre.setText(elemento.getNombre());
        holder.elementoDescripcion.setText(elemento.getDescripcion());
        holder.elementoId.setText("ID: " + elemento.getId());
        holder.elementoUnidadesTexto.setText("Unidades: ");
        holder.elementoUnidadesNumero.setText(String.valueOf(elemento.getUnidades()));

        // Cambiar el color del número de unidades basado en el valor
        if (elemento.getUnidades() <= 5) {
            holder.elementoUnidadesNumero.setTextColor(Color.RED);
            Log.d("Adapter", "El número de unidades es menor de 5, color rojo");
        } else {
            holder.elementoUnidadesNumero.setTextColor(Color.parseColor("#388E3C"));
            Log.d("Adapter", "El número de unidades es mayor de 5, color verde");
        }
    }

    // devuelve el tamaño de la lista de elementos
    @Override
    public int getItemCount() {
        // Log para verificar cuántos elementos hay en la lista
        Log.d("Adapter", "Número total de elementos en la lista: " + elementos.size());
        return elementos.size();
    }

    // actualizar la lista de elementos
    public void updateList(List<ElementoInformatico> nuevosElementos) {
        this.elementos = nuevosElementos; // Reemplaza la lista de elementos por la nueva
        notifyDataSetChanged();

        // Log para confirmar que la lista ha sido actualizada
        Log.d("Adapter", "Lista actualizada con " + nuevosElementos.size() + " elementos.");
    }

    // ViewHolder para representar y manejar las vistas de cada item en la lista
    static class ElementoViewHolder extends RecyclerView.ViewHolder {
        ImageView elementoFoto;
        TextView elementoNombre;
        TextView elementoDescripcion;
        TextView elementoId;
        TextView elementoUnidadesTexto;
        TextView elementoUnidadesNumero;

        // Constructor que asocia las vistas del item con las variables
        public ElementoViewHolder(@NonNull View itemView) {
            super(itemView);
            // Inicialización de las vistas
            elementoFoto = itemView.findViewById(R.id.imagen);
            elementoNombre = itemView.findViewById(R.id.titulo);
            elementoDescripcion = itemView.findViewById(R.id.subtitulo);
            elementoId = itemView.findViewById(R.id.idProducto);
            elementoUnidadesTexto = itemView.findViewById(R.id.unidadesTexto);
            elementoUnidadesNumero = itemView.findViewById(R.id.unidadesNumero);
        }
    }
}

