package ventas.Producto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ventas.Producto.model.Producto;
import ventas.Producto.service.ProductoService;

import java.util.List;

@RestController // Marca esta clase como un controlador REST
@RequestMapping("/api/v1/inventario") // Define la ruta base para todos los endpoints de este controlador
public class ProductoController {

    private final ProductoService productoService;

    @Autowired // Inyecta una instancia de ProductoService
    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    // Endpoint para crear un nuevo producto (POST /api/v1/inventario)
    @PostMapping
    public ResponseEntity<Producto> crearProducto(@RequestBody Producto producto) {
        Producto nuevoProducto = productoService.crearProducto(producto);
        return new ResponseEntity<>(nuevoProducto, HttpStatus.CREATED); // Devuelve 201 Created
    }

    // Endpoint para obtener un producto por ID (GET /api/v1/inventario/{id})
    @GetMapping("/{id}")
    public ResponseEntity<Producto> obtenerProductoPorId(@PathVariable Long id) {
        return productoService.obtenerProductoPorId(id)
              .map(producto -> new ResponseEntity<>(producto, HttpStatus.OK)) // Devuelve 200 OK
              .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND)); // Devuelve 404 Not Found
    }

    // Endpoint para obtener todos los productos (GET /api/v1/inventario)
    @GetMapping
    public ResponseEntity<List<Producto>> obtenerTodosLosProductos() {
        List<Producto> productos = productoService.obtenerTodosLosProductos();
        return new ResponseEntity<>(productos, HttpStatus.OK); // Devuelve 200 OK
    }

    // Endpoint para actualizar un producto existente (PUT /api/v1/inventario/{id})
    @PutMapping("/{id}")
    public ResponseEntity<Producto> actualizarProducto(@PathVariable Long id, @RequestBody Producto productoDetalles) {
        try {
            Producto productoActualizado = productoService.actualizarProducto(id, productoDetalles);
            return new ResponseEntity<>(productoActualizado, HttpStatus.OK); // Devuelve 200 OK
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Devuelve 404 Not Found si el producto no existe
        }
    }

    // Endpoint para eliminar un producto (DELETE /api/v1/inventario/{id})
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarProducto(@PathVariable Long id) {
        try {
            productoService.eliminarProducto(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT); // Devuelve 204 No Content
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Devuelve 404 Not Found si el producto no existe
        }
    }

    // Endpoint para actualizar el stock de un producto (PATCH /api/v1/inventario/{id}/stock)
    // Este es un ejemplo de un endpoint más específico para una operación de negocio
    @PatchMapping("/{id}/stock")
    public ResponseEntity<Producto> actualizarStockProducto(@PathVariable Long id, @RequestParam Integer cantidad) {
        try {
            Producto productoActualizado = productoService.actualizarStock(id, cantidad);
            return new ResponseEntity<>(productoActualizado, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST); // Devuelve 400 Bad Request si el stock es insuficiente
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}