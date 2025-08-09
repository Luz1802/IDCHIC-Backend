package com.idchic.id_chic_backend.modelo;

public class Producto {
    private String codigo;
    private String nombre;
    private double precio;
    private int stock;
    private String imagen; // Campo para imagen

    // Constructor
    public Producto(String codigo, String nombre, double precio, int stock, String imagen) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
        this.imagen = imagen;
    }

    // Getters
    public String getCodigo() { return codigo; }
    public String getNombre() { return nombre; }
    public double getPrecio() { return precio; }
    public int getStock() { return stock; }
    public String getImagen() { return imagen; }

    // Setters
    public void setStock(int stock) { this.stock = stock; }
}
