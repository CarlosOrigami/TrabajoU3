package com.test.trabajou3;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private Adapter adapter;
    private List<ElementoInformatico> elementos;
    private EditText editText;
    private Button botonEnviar, botonBorrar;
    private RadioGroup radioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inicializar vistas
        recyclerView = findViewById(R.id.recyclerView);
        editText = findViewById(R.id.editTextText2);
        botonEnviar = findViewById(R.id.enviar);
        botonBorrar = findViewById(R.id.borrar);
        radioGroup = findViewById(R.id.radioGroup); // Inicializar el RadioGroup

        // Configurar RecyclerView
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Obtener datos y configurar el adaptador
        elementos = obtenerListaDeElementos();
        adapter = new Adapter(elementos);
        recyclerView.setAdapter(adapter);

        // Configurar RadioGroup para filtrar elementos por categoría
        radioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            List<ElementoInformatico> elementosFiltrados;
            if (checkedId == R.id.radio_cpu) {
                elementosFiltrados = filtrarPorCategoria("CPU");
            } else if (checkedId == R.id.radio_gpu) {
                elementosFiltrados = filtrarPorCategoria("GPU");
            } else if (checkedId == R.id.radio_pb) {
                elementosFiltrados = filtrarPorCategoria("PB");
            } else if (checkedId == R.id.radio_ram) {
                elementosFiltrados = filtrarPorCategoria("RAM");
            } else {
                elementosFiltrados = elementos; // Mostrar todos si no hay selección válida
            }
            adapter.updateList(elementosFiltrados); // Actualizar el adaptador dinámicamente
        });

        // Configurar Switch para el modo oscuro
        Switch darkModeSwitch = findViewById(R.id.switch1);
        darkModeSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                // Activar el modo oscuro
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            } else {
                // Activar el modo claro
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            }
        });

        // Acción al presionar el botón de Enviar
        botonEnviar.setOnClickListener(view -> {
            String query = editText.getText().toString().trim();
            if (query.isEmpty()) {
                // Si el campo está vacío, mostrar un Toast
                Toast.makeText(MainActivity.this, "Por favor, ingrese una marca", Toast.LENGTH_SHORT).show();
            } else {
                List<ElementoInformatico> elementosFiltrados = filtrarPorMarca(query);
                if (elementosFiltrados.isEmpty()) {
                    // Si no hay elementos que coincidan, mostrar un Toast
                    Toast.makeText(MainActivity.this, "Marca no válida", Toast.LENGTH_SHORT).show();
                } else {
                    // Si hay elementos que coinciden, actualizar el adaptador
                    adapter.updateList(elementosFiltrados);
                }
            }
        });

        // Acción al presionar el botón de Borrar
        botonBorrar.setOnClickListener(view -> {
            editText.setText(""); // Limpiar el campo de texto
            radioGroup.clearCheck(); // Deseleccionar todos los RadioButtons
            adapter.updateList(elementos); // Restaurar la lista completa
        });
    }

    private List<ElementoInformatico> obtenerListaDeElementos() {
        List<ElementoInformatico> lista = new ArrayList<>();
        lista.add(new ElementoInformatico(1, "CPU", "Procesador AMD Ryzen", "Procesador de alto rendimiento para PC", R.drawable.cpu_amd, 10));
        lista.add(new ElementoInformatico(2, "CPU", "Procesador Intel Core", "Procesador de alto rendimiento de Intel", R.drawable.cpu_intel, 15));
        lista.add(new ElementoInformatico(3, "GPU", "Tarjeta gráfica Gigabyte", "Tarjeta gráfica para juegos y diseño gráfico", R.drawable.gpu_gigabyte, 8));
        lista.add(new ElementoInformatico(4, "GPU", "Tarjeta gráfica MSI", "Tarjeta gráfica para juegos y edición de video", R.drawable.gpu_msi, 12));
        lista.add(new ElementoInformatico(5, "PB", "Placa base ASUS", "Placa base compatible con procesadores Intel", R.drawable.pb_asus, 5));
        lista.add(new ElementoInformatico(6, "RAM", "Memoria Corsair", "Memoria RAM DDR4 de alta velocidad", R.drawable.ram_corsair, 20));
        return lista;
    }


    private List<ElementoInformatico> filtrarPorCategoria(String categoria) {
        return elementos.stream()
                .filter(elemento -> elemento.getCategoria().equalsIgnoreCase(categoria))
                .collect(Collectors.toList());
    }

    // Filtrar la lista de elementos por marca (basado en el texto del EditText)
    private List<ElementoInformatico> filtrarPorMarca(String marca) {
        return elementos.stream()
                .filter(elemento -> elemento.getNombre().toLowerCase().contains(marca.toLowerCase()) ||
                        elemento.getDescripcion().toLowerCase().contains(marca.toLowerCase()))
                .collect(Collectors.toList());
    }
}
