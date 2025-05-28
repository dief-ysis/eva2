package ventas.inventario.model;

import jakarta.persistence.*;
@Entity
public class Producto {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    
    private String nombre;
    private Double precio;
    private Integer stock;

}
