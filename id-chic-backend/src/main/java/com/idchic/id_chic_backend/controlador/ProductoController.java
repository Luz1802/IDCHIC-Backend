package com.idchic.id_chic_backend.controlador;

import com.idchic.id_chic_backend.modelo.Producto;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = {"http://127.0.0.1:5500", "http://127.0.0.1:3000"}) // Ajusta según tu Live Server
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

    // Endpoint para listar productos
    @GetMapping("/productos")
    public List<Producto> listarProductos() {
        return productos;
    }

    // Endpoint para agregar al carrito (simulado)
    @PostMapping("/carrito")
    public String agregarAlCarrito(@RequestBody Producto producto) {
        return "✅ " + producto.getNombre() + " agregado al carrito en el backend.";
    }
}
