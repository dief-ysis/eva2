package ventas.inventario.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ventas.inventario.model.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
}
