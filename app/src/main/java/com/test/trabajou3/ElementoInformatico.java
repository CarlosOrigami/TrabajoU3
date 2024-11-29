package com.test.trabajou3;

public class ElementoInformatico {
    private String categoria;
    private String nombre;
    private String descripcion;
    private int foto;
    private int id;  // ID único del producto
    private int unidades;  // Número de unidades disponibles

    // Constructor modificado
    public ElementoInformatico(int id, String categoria, String nombre, String descripcion, int foto, int unidades) {
        this.id = id;
        this.categoria = categoria;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.foto = foto;
        this.unidades = unidades;
    }

    // Getter y Setter para el id
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    // Getter y Setter para las unidades
    public int getUnidades() {
        return unidades;
    }

    public void setUnidades(int unidades) {
        this.unidades = unidades;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getFoto() {
        return foto;
    }

    public void setFoto(int foto) {
        this.foto = foto;
    }

    @Override
    public String toString() {
        return "ElementoInformatico{" +
                "id=" + id +
                ", categoria='" + categoria + '\'' +
                ", nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", foto=" + foto +
                ", unidades=" + unidades +
                '}';
    }
}
