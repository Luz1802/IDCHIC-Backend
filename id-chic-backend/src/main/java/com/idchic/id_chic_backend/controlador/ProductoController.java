package com.idchic.id_chic_backend.controlador;

import com.idchic.id_chic_backend.modelo.Producto;
import com.idchic.id_chic_backend.modelo.CarritoItem;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
// Permitir peticiones desde los puertos usados por el frontend
@CrossOrigin(origins = {
        "http://localhost:5500",
        "http://localhost:3000",
        "http://127.0.0.1:5500",
        "http://127.0.0.1:3000"
})
public class ProductoController {

    private List<Producto> productos = new ArrayList<>(List.of(
            // ------------------- PORTA CARNETS -------------------
            new Producto("P001", "Porta Carnet Enfermera", 20000, 10, "./images/Porta Carnets/PC1.jpg"),
            new Producto("P002", "Porta Carnet Odontología", 20000, 12, "./images/Porta Carnets/PC2.jpg"),
            new Producto("P003", "Porta Carnet Mariposas", 20000, 8, "./images/Porta Carnets/PC3.jpg"),
            new Producto("P004", "Porta Carnet Van Gogh", 20000, 14, "./images/Porta Carnets/PC4.jpg"),
            new Producto("P005", "Porta Carnet Brillos", 20000, 9, "./images/Porta Carnets/PC5.jpg"),
            new Producto("P006", "Porta Carnet Rick & Morty", 20000, 7, "./images/Porta Carnets/PC6.jpg"),
            new Producto("P007", "Porta Carnet Marvel", 20000, 11, "./images/Porta Carnets/PC7.jpg"),
            new Producto("P008", "Porta Carnet Monsters Inc", 20000, 10, "./images/Porta Carnets/PC8.jpg"),
            new Producto("P009", "Porta Carnet Nightmare", 20000, 13, "./images/Porta Carnets/PC9.jpg"),
            new Producto("P010", "Porta Carnet Minnie Mouse", 20000, 6, "./images/Porta Carnets/PC10.jpg"),

            // ------------------- CINTAS (LANYARDS) -------------------
            new Producto("L001", "Lanyard Área de Salud", 10000, 15, "./images/Cintas/1.jpg"),
            new Producto("L002", "Lanyard Huellitas", 10000, 20, "./images/Cintas/2.jpg"),
            new Producto("L003", "Lanyard Perritos", 10000, 18, "./images/Cintas/3.jpg"),
            new Producto("L004", "Lanyard Flores", 10000, 16, "./images/Cintas/4.jpg"),
            new Producto("L005", "Lanyard Enfermería", 10000, 22, "./images/Cintas/5.jpg"),
            new Producto("L006", "Lanyard Stitch", 10000, 14, "./images/Cintas/6.jpg"),
            new Producto("L007", "Lanyard Capibara", 10000, 19, "./images/Cintas/7.jpg"),
            new Producto("L008", "Lanyard Dinosaurios", 10000, 10, "./images/Cintas/8.jpg"),
            new Producto("L009", "Lanyard Disney", 10000, 17, "./images/Cintas/9.jpg"),
            new Producto("L010", "Lanyard Harry Potter", 10000, 12, "./images/Cintas/10.jpg"),

            // ------------------- PINES -------------------
            new Producto("PI001", "Pin Cámaras", 5000, 25, "./images/Pines/1.jpg"),
            new Producto("PI002", "Pin Odontología", 5000, 22, "./images/Pines/2.jpg"),
            new Producto("PI003", "Pin Perritos", 5000, 18, "./images/Pines/3.jpg"),
            new Producto("PI004", "Pin Estetoscopio", 5000, 20, "./images/Pines/4.jpg"),
            new Producto("PI005", "Pin LGBT+", 5000, 16, "./images/Pines/5.jpg"),
            new Producto("PI006", "Pin El Principito", 5000, 14, "./images/Pines/6.jpg"),
            new Producto("PI007", "Pin Harry Potter", 5000, 19, "./images/Pines/7.jpg"),
            new Producto("PI008", "Pin Cars", 5000, 15, "./images/Pines/8.jpg"),
            new Producto("PI009", "Pin Stitch", 5000, 21, "./images/Pines/9.jpg"),
            new Producto("PI010", "Pin Marvel", 5000, 12, "./images/Pines/10.jpg")
    ));

    private List<CarritoItem> carrito = new ArrayList<>();

    // Endpoint para listar productos
    @GetMapping("/productos")
    public List<Producto> listarProductos() {
        return productos;
    }

    // Endpoint para obtener el carrito
    @GetMapping("/carrito")
    public List<CarritoItem> obtenerCarrito() {
        return carrito;
    }

    // Endpoint para agregar productos al carrito
    @PostMapping("/carrito/{codigo}")
    public ResponseEntity<?> agregarAlCarrito(@PathVariable String codigo, @RequestParam(defaultValue = "1") int cantidad) {
        Producto producto = buscarProducto(codigo);
        if (producto == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Producto no encontrado");
        }
        if (producto.getStock() < cantidad) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Stock insuficiente");
        }
        producto.setStock(producto.getStock() - cantidad);
        CarritoItem item = buscarItemEnCarrito(codigo);
        if (item != null) {
            item.setCantidad(item.getCantidad() + cantidad);
        } else {
            item = new CarritoItem(producto, cantidad);
            carrito.add(item);
        }
        return ResponseEntity.ok(item);
    }

    // Endpoint para eliminar productos del carrito
    @DeleteMapping("/carrito/{codigo}")
    public ResponseEntity<?> eliminarDelCarrito(@PathVariable String codigo, @RequestParam(defaultValue = "1") int cantidad) {
        CarritoItem item = buscarItemEnCarrito(codigo);
        if (item == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Producto no está en el carrito");
        }
        Producto producto = item.getProducto();
        if (cantidad >= item.getCantidad()) {
            producto.setStock(producto.getStock() + item.getCantidad());
            carrito.remove(item);
        } else {
            item.setCantidad(item.getCantidad() - cantidad);
            producto.setStock(producto.getStock() + cantidad);
        }
        return ResponseEntity.ok(item);
    }

    // Endpoint para vaciar completamente el carrito
    @DeleteMapping("/carrito")
    public ResponseEntity<Void> vaciarCarrito() {
        carrito.forEach(i -> {
            Producto producto = i.getProducto();
            producto.setStock(producto.getStock() + i.getCantidad());
        });
        carrito.clear();
        return ResponseEntity.ok().build();
    }

    // Busca un producto por código
    private Producto buscarProducto(String codigo) {
        return productos.stream()
                .filter(p -> p.getCodigo().equals(codigo))
                .findFirst()
                .orElse(null);
    }

    // Busca un ítem del carrito por código de producto
    private CarritoItem buscarItemEnCarrito(String codigo) {
        return carrito.stream()
                .filter(i -> i.getProducto().getCodigo().equals(codigo))
                .findFirst()
                .orElse(null);
    }
}
