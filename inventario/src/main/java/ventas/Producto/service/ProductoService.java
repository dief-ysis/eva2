package ventas.Producto.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ventas.Producto.model.Producto;
import ventas.Producto.repository.ProductoRepository;

import java.util.List;
import java.util.Optional;

@Service // Marca esta clase como un componente de servicio de Spring
public class ProductoService {

    private final ProductoRepository productoRepository;

    @Autowired // Inyecta una instancia de ProductoRepository
    public ProductoService(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    // Método para crear un nuevo producto
    public Producto crearProducto(Producto producto) {
        // Aquí podrías añadir lógica de negocio adicional, como:
        // - Validar que el stock inicial no sea negativo
        // - Validar que el precio no sea negativo
        return productoRepository.save(producto);
    }

    // Método para obtener un producto por su ID
    public Optional<Producto> obtenerProductoPorId(Long id) {
        return productoRepository.findById(id);
    }

    // Método para obtener todos los productos
    public List<Producto> obtenerTodosLosProductos() {
        return productoRepository.findAll();
    }

    // Método para actualizar un producto existente
    public Producto actualizarProducto(Long id, Producto productoDetalles) {
        return productoRepository.findById(id)
              .map(productoExistente -> {
                    productoExistente.setNombre(productoDetalles.getNombre());
                    productoExistente.setDescripcion(productoDetalles.getDescripcion());
                    productoExistente.setPrecio(productoDetalles.getPrecio());
                    productoExistente.setStock(productoDetalles.getStock());
                    // La 'ultimaActualizacion' se maneja automáticamente por @UpdateTimestamp
                    return productoRepository.save(productoExistente);
                })
              .orElseThrow(() -> new RuntimeException("Producto no encontrado con ID: " + id));
    }

    // Método para eliminar un producto por su ID
    public void eliminarProducto(Long id) {
        if (productoRepository.existsById(id)) {
            productoRepository.deleteById(id);
        } else {
            throw new RuntimeException("Producto no encontrado con ID: " + id);
        }
    }

    // Método para actualizar el stock de un producto (ejemplo de lógica de negocio específica)
    public Producto actualizarStock(Long id, Integer cantidad) {
        return productoRepository.findById(id)
               .map(productoExistente -> {
                    if (productoExistente.getStock() + cantidad < 0) {
                        throw new IllegalArgumentException("Stock insuficiente para la operación.");
                    }
                    productoExistente.setStock(productoExistente.getStock() + cantidad);
                    return productoRepository.save(productoExistente);
                })
               .orElseThrow(() -> new RuntimeException("Producto no encontrado con ID: " + id));
    }
}