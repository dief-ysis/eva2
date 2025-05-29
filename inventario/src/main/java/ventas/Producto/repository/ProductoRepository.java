package ventas.Producto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ventas.Producto.model.Producto;

@Repository // Marca esta interfaz como un componente de repositorio de Spring
public interface ProductoRepository extends JpaRepository<Producto, Long> {
    // JpaRepository ya proporciona métodos CRUD básicos (save, findById, findAll, delete, etc.)
    // Puedes añadir métodos personalizados aquí si necesitas consultas específicas, por ejemplo:
    // Optional<Producto> findByNombre(String nombre);
}
