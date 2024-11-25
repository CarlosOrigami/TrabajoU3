package com.test.trabajou3;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private Adapter adapter;
    private List<ElementoInformatico> elementos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Configurar RecyclerView
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Obtener datos y configurar el adaptador
        elementos = obtenerListaDeElementos();
        adapter = new Adapter(elementos);
        recyclerView.setAdapter(adapter);
    }

    private List<ElementoInformatico> obtenerListaDeElementos() {
        List<ElementoInformatico> lista = new ArrayList<>();
        lista.add(new ElementoInformatico("Placa Base", "Soporte para todos los componentes", R.drawable.placabase));
        lista.add(new ElementoInformatico("RAM", "Memoria de acceso rápido", R.drawable.ram));
        lista.add(new ElementoInformatico("Procesador", "Cerebro del ordenador", R.drawable.procesador));
        lista.add(new ElementoInformatico("Gráfica", "Renderización de gráficos", R.drawable.grafica));
        return lista;
    }
}
