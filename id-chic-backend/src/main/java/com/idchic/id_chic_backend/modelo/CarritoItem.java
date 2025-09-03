package com.idchic.id_chic_backend.modelo;

public class CarritoItem {
    private Producto producto;
    private int cantidad;

    /**
     * Constructor vacío requerido para la serialización/deserialización.
     */
    public CarritoItem() {
    }

    public CarritoItem(Producto producto, int cantidad) {
        this.producto = producto;
        this.cantidad = cantidad;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}
