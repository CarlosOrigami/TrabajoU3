package com.test.trabajou3;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MainActivity extends AppCompatActivity {

    private Adapter adapter;
    private List<ElementoInformatico> elementos;
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inicializar vistas
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        editText = findViewById(R.id.editTextText2);
        Button botonEnviar = findViewById(R.id.enviar);
        Button botonBorrar = findViewById(R.id.borrar);

        // Inicializar switches
        @SuppressLint("UseSwitchCompatOrMaterialCode") Switch switchPB = findViewById(R.id.switch_pb);
        @SuppressLint("UseSwitchCompatOrMaterialCode") Switch switchRAM = findViewById(R.id.switch_ram);
        @SuppressLint("UseSwitchCompatOrMaterialCode") Switch switchGPU = findViewById(R.id.switch_gpu);
        @SuppressLint("UseSwitchCompatOrMaterialCode") Switch switchCPU = findViewById(R.id.switch_cpu);

        // Configurar RecyclerView
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Obtener datos y configurar el adaptador
        elementos = obtenerListaDeElementos();
        adapter = new Adapter(elementos);
        recyclerView.setAdapter(adapter);


        // Listener para filtrar elementos según los switches
        View.OnClickListener switchListener = view -> {
            List<ElementoInformatico> elementosFiltrados = new ArrayList<>();
            if (switchPB.isChecked()) {
                Log.d("MainActivity", "Filtrando por categoría: PB");
                elementosFiltrados.addAll(filtrarPorCategoria("PB"));
            }
            if (switchRAM.isChecked()) {
                Log.d("MainActivity", "Filtrando por categoría: RAM");
                elementosFiltrados.addAll(filtrarPorCategoria("RAM"));
            }
            if (switchGPU.isChecked()) {
                Log.d("MainActivity", "Filtrando por categoría: GPU");
                elementosFiltrados.addAll(filtrarPorCategoria("GPU"));
            }
            if (switchCPU.isChecked()) {
                Log.d("MainActivity", "Filtrando por categoría: CPU");
                elementosFiltrados.addAll(filtrarPorCategoria("CPU"));
            }

            // Mostrar todos si no hay ningún switch seleccionado
            if (elementosFiltrados.isEmpty()) {
                Log.d("MainActivity", "No se ha seleccionado ninguna categoría.");
                elementosFiltrados = elementos;
            }

            // Actualizar la lista en el adapter
            adapter.updateList(elementosFiltrados);
        };

        // Asignar el listener a cada switch
        switchPB.setOnClickListener(switchListener);
        switchRAM.setOnClickListener(switchListener);
        switchGPU.setOnClickListener(switchListener);
        switchCPU.setOnClickListener(switchListener);

        // Acción al presionar el botón de Enviar
        botonEnviar.setOnClickListener(view -> {
            String query = editText.getText().toString().trim();
            Log.d("MainActivity", "Consulta de marca: " + query);

            if (query.isEmpty()) {
                Toast.makeText(MainActivity.this, "Por favor, ingrese una marca", Toast.LENGTH_SHORT).show();
            } else {
                List<ElementoInformatico> elementosFiltrados = filtrarPorMarca(query);
                if (elementosFiltrados.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Marca no válida", Toast.LENGTH_SHORT).show();
                } else {
                    adapter.updateList(elementosFiltrados);

                }
            }
        });

        // Acción al presionar el botón de Borrar
        botonBorrar.setOnClickListener(view -> {
            editText.setText(""); // Limpiar el campo de texto
            switchPB.setChecked(false);
            switchRAM.setChecked(false);
            switchGPU.setChecked(false);
            switchCPU.setChecked(false);
            adapter.updateList(elementos);
            Log.d("MainActivity", "Campos borrados");
        });
    }

    private List<ElementoInformatico> obtenerListaDeElementos() {
        List<ElementoInformatico> lista = new ArrayList<>();
        lista.add(new ElementoInformatico(1, "CPU", "Procesador AMD Ryzen", "Bajo rendimiento para PC", R.drawable.cpu_amd, 10));
        lista.add(new ElementoInformatico(2, "CPU", "Procesador Intel Core", "Alto rendimiento para PC", R.drawable.cpu_intel, 3));
        lista.add(new ElementoInformatico(3, "GPU", "Tarjeta gráfica Gigabyte", "Para juegos y diseño gráfico", R.drawable.gpu_gigabyte, 8));
        lista.add(new ElementoInformatico(4, "GPU", "Tarjeta gráfica MSI", "Para juegos y edición de video", R.drawable.gpu_msi, 12));
        lista.add(new ElementoInformatico(5, "GPU", "Tarjeta gráfica Zotac", "Para juegos y diseño gráfico", R.drawable.gpu_zotac, 20));
        lista.add(new ElementoInformatico(6, "PB", "Placa base ASUS", "Compatible con procesadores Intel", R.drawable.pb_asus, 4));
        lista.add(new ElementoInformatico(7, "PB", "Placa base Gigabyte", "Compatible con procesadores AMD", R.drawable.pb_gigabyte, 2));
        lista.add(new ElementoInformatico(8, "PB", "Placa base MSI", "Compatible con procesadores Intel", R.drawable.pb_msi, 5));
        lista.add(new ElementoInformatico(9, "RAM", "Memoria Corsair", "DDR4 de alta velocidad", R.drawable.ram_corsair, 6));
        lista.add(new ElementoInformatico(10, "RAM", "Memoria HyperX", "DDR4 de alta velocidad", R.drawable.ram_hyperx, 2));
        lista.add(new ElementoInformatico(11, "RAM", "Memoria Team Group", "DDR4 de alta velocidad", R.drawable.ram_teamgroup, 0));
        return lista;
    }

    private List<ElementoInformatico> filtrarPorCategoria(String categoria) {
        Log.d("MainActivity", "Filtrando por categoría: " + categoria);
        return elementos.stream()
                .filter(elemento -> elemento.getCategoria().equalsIgnoreCase(categoria))
                .collect(Collectors.toList());
    }

    private List<ElementoInformatico> filtrarPorMarca(String marca) {
        Log.d("MainActivity", "Filtrando  por marca: " + marca);
        return elementos.stream()
                .filter(elemento -> elemento.getNombre().toLowerCase().contains(marca.toLowerCase()) ||
                        elemento.getDescripcion().toLowerCase().contains(marca.toLowerCase()))
                .collect(Collectors.toList());
    }
}
