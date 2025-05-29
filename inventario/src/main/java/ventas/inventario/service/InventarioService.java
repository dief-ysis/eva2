package ventas.inventario.service;

import ventas.inventario.model.Producto;
import ventas.inventario.repository.ProductoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoService {

    private final ProductoRepository repo;

    public ProductoService(ProductoRepository repo) {
        this.repo = repo;
    }

    public List<Producto> findAll() {
        return repo.findAll();
    }

    public Optional<Producto> findById(Long id) {
        return repo.findById(id);
    }

    public Producto save(Producto p) {
        return repo.save(p);
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }

    public List<Producto> findByCategoria(String cat) {
        return repo.findByCategoria(cat);
    }
}
