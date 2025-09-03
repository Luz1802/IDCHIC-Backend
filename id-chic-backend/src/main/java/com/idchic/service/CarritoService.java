package com.idchic.service;

import com.idchic.model.CarritoItem;
import com.idchic.model.Producto;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CarritoService {
    private final Map<Long, Producto> productos = new HashMap<>();
    private final Map<Long, CarritoItem> carrito = new HashMap<>();

    public CarritoService() {
        productos.put(1L, new Producto(1L, "Camisa", 20.0, 10));
        productos.put(2L, new Producto(2L, "Pantalón", 35.0, 5));
    }

    public List<Producto> listarProductos() {
        return new ArrayList<>(productos.values());
    }

    public List<CarritoItem> verCarrito() {
        return new ArrayList<>(carrito.values());
    }

    public boolean agregarProducto(Long id, int cantidad) {
        Producto producto = productos.get(id);
        if (producto == null || producto.getStock() < cantidad) {
            return false;
        }
        producto.setStock(producto.getStock() - cantidad);
        CarritoItem item = carrito.get(id);
        if (item == null) {
            carrito.put(id, new CarritoItem(producto, cantidad));
        } else {
            item.setCantidad(item.getCantidad() + cantidad);
        }
        return true;
    }

    public boolean eliminarProducto(Long id, int cantidad) {
        CarritoItem item = carrito.get(id);
        if (item == null || item.getCantidad() < cantidad) {
            return false;
        }
        item.setCantidad(item.getCantidad() - cantidad);
        Producto producto = productos.get(id);
        producto.setStock(producto.getStock() + cantidad);
        if (item.getCantidad() == 0) {
            carrito.remove(id);
        }
        return true;
    }

    public void vaciarCarrito() {
        for (CarritoItem item : carrito.values()) {
            Producto producto = productos.get(item.getProducto().getId());
            producto.setStock(producto.getStock() + item.getCantidad());
        }
        carrito.clear();
    }
}
