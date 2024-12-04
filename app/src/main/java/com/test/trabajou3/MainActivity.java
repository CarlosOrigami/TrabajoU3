package com.test.trabajou3;

import android.annotation.SuppressLint;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.Switch;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

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
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_FULL_SENSOR);
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

        TabLayout tabLayout = findViewById(R.id.tabLayout);

        // Agregar las pestañas
        tabLayout.addTab(tabLayout.newTab().setText("Principal"));
        tabLayout.addTab(tabLayout.newTab().setText("Próximamente"));

        // Configurar el listener para las pestañas
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getPosition() == 1) {
                    Snackbar.make(findViewById(android.R.id.content), "Próximamente", Snackbar.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });


        // Listener para filtrar elementos según los switches
        View.OnClickListener switchListener = view -> {
            List<ElementoInformatico> elementosFiltrados = new ArrayList<>();
            if (switchPB.isChecked()) {
                elementosFiltrados.addAll(filtrarPorCategoria("PB"));
            }
            if (switchRAM.isChecked()) {
                elementosFiltrados.addAll(filtrarPorCategoria("RAM"));
            }
            if (switchGPU.isChecked()) {
                elementosFiltrados.addAll(filtrarPorCategoria("GPU"));
            }
            if (switchCPU.isChecked()) {
                elementosFiltrados.addAll(filtrarPorCategoria("CPU"));
            }

            if (elementosFiltrados.isEmpty()) {
                elementosFiltrados = elementos;
            }

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

            if (query.isEmpty()) {
                Snackbar.make(view, "Por favor, ingrese una marca", Snackbar.LENGTH_SHORT).show();
                mostrarPopup(view, "Por favor, ingrese una marca");
            } else {
                List<ElementoInformatico> elementosFiltrados = filtrarPorMarca(query);
                if (elementosFiltrados.isEmpty()) {
                    Snackbar.make(view, "Marca no válida", Snackbar.LENGTH_SHORT).show();
                    mostrarPopup(view, "Marca no válida");
                } else {
                    adapter.updateList(elementosFiltrados);
                    mostrarPopup(view, "Elementos encontrados: " + elementosFiltrados.size());
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
        });
    }
    private void mostrarPopup(View view, String mensaje) {
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.popup_layout, null);

        PopupWindow popupWindow = new PopupWindow(popupView,
                RecyclerView.LayoutParams.WRAP_CONTENT,
                RecyclerView.LayoutParams.WRAP_CONTENT, true);

        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);

        Button closePopup = popupView.findViewById(R.id.buttonClosePopup);
        closePopup.setOnClickListener(v -> popupWindow.dismiss());

        EditText textViewPopup = popupView.findViewById(R.id.textViewPopup);
        textViewPopup.setText(mensaje);
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
        return elementos.stream()
                .filter(elemento -> elemento.getCategoria().equalsIgnoreCase(categoria))
                .collect(Collectors.toList());
    }

    private List<ElementoInformatico> filtrarPorMarca(String marca) {
        return elementos.stream()
                .filter(elemento -> elemento.getNombre().toLowerCase().contains(marca.toLowerCase()) ||
                        elemento.getDescripcion().toLowerCase().contains(marca.toLowerCase()))
                .collect(Collectors.toList());
    }
}


