package com.idchic.controller;

import com.idchic.model.CarritoItem;
import com.idchic.model.Producto;
import com.idchic.service.CarritoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class ProductoController {

    private final CarritoService carritoService;

    public ProductoController(CarritoService carritoService) {
        this.carritoService = carritoService;
    }

    @GetMapping("/productos")
    public List<Producto> listarProductos() {
        return carritoService.listarProductos();
    }

    @GetMapping("/carrito")
    public List<CarritoItem> verCarrito() {
        return carritoService.verCarrito();
    }

    @PostMapping("/carrito/{id}")
    public ResponseEntity<?> agregarProducto(@PathVariable Long id, @RequestBody Map<String, Integer> body) {
        int cantidad = body.getOrDefault("cantidad", 0);
        boolean agregado = carritoService.agregarProducto(id, cantidad);
        if (!agregado) {
            return ResponseEntity.badRequest().body("Stock insuficiente");
        }
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/carrito/{id}")
    public ResponseEntity<?> eliminarProducto(@PathVariable Long id, @RequestParam(defaultValue = "1") int cantidad) {
        boolean eliminado = carritoService.eliminarProducto(id, cantidad);
        if (!eliminado) {
            return ResponseEntity.badRequest().body("No existe en el carrito");
        }
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/carrito")
    public void vaciarCarrito() {
        carritoService.vaciarCarrito();
    }
}
