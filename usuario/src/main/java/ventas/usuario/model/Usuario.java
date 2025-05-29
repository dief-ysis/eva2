package ventas.usuario.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "usuarios")
@Data
@AllArgsConstructor
@NoArgsConstructor 
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Generación de ID automática, compatible con BIGSERIAL de PostgreSQL
    private Long id;

    @Column(name = "nombre", nullable = false) // Columna 'nombre', no puede ser nula
    private String nombre;

    @Column(name = "email", nullable = false, unique = true) // Columna 'email', no puede ser nula y debe ser única
    private String email;

    @Column(name = "contrasena", nullable = false) // Columna 'contrasena', no puede ser nula
    private String contrasena;

    @Column(name = "rol", length = 50, columnDefinition = "VARCHAR(50) DEFAULT 'CLIENTE'") // Columna 'rol' con valor por defecto
    private String rol;

    @CreationTimestamp // Anotación de Hibernate para establecer la fecha de creación automáticamente
    @Column(name = "fecha_registro", nullable = false, updatable = false) // Columna 'fecha_registro', no puede ser nula y no se actualiza
    private LocalDateTime fechaRegistro;
}