package com.ejemplo.carrito.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Carrito {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long idUsuario;

    private Double total;

    // Asumimos por simplicidad que guardaremos los nombres de los productos
    @ElementCollection
    private List<String> productos;

    // Getters y Setters
}
