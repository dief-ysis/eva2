package ventas.Producto.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp; // Para manejar automáticamente la fecha de actualización

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "productos") // Mapea esta clase a la tabla 'productos' en la base de datos
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Generación de ID automática, compatible con BIGSERIAL de PostgreSQL
    private Long id;

    @Column(name = "nombre", nullable = false) // Columna 'nombre', no puede ser nula
    private String nombre;

    @Column(name = "descripcion") // Columna 'descripcion', puede ser nula
    private String descripcion;

    @Column(name = "precio", nullable = false) // Columna 'precio', no puede ser nula
    private BigDecimal precio; // Usar BigDecimal para precisión en valores monetarios

    @Column(name = "stock", nullable = false) // Columna 'stock', no puede ser nula
    private Integer stock;

    @CreationTimestamp // Anotación de Hibernate para establecer la fecha de creación automáticamente
    @Column(name = "fecha_creacion", nullable = false, updatable = false) // Columna 'fecha_creacion', no puede ser nula y no se actualiza
    private LocalDateTime fechaCreacion;

    @UpdateTimestamp // Anotación de Hibernate para actualizar la fecha automáticamente en cada modificación
    @Column(name = "ultima_actualizacion", nullable = false) // Columna 'ultima_actualizacion', no puede ser nula
    private LocalDateTime ultimaActualizacion;
}