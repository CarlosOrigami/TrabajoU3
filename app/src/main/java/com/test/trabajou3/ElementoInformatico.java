package com.test.trabajou3;

public class ElementoInformatico {
    private String nombre;
    private String descripcion;
    private int foto;

    public ElementoInformatico(String nombre, String descripcion, int foto) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.foto = foto;
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
                "nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", foto=" + foto +
                '}';
    }
}
