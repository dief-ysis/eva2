package com.ejemplo.carrito.controller;

import com.ejemplo.carrito.model.Carrito;
import com.ejemplo.carrito.service.CarritoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/carrito")
public class CarritoController {

    private final CarritoService carritoService;

    public CarritoController(CarritoService carritoService) {
        this.carritoService = carritoService;
    }

    @GetMapping
    public List<Carrito> listar() {
        return carritoService.obtenerTodos();
    }

    @PostMapping
    public Carrito crear(@RequestBody Carrito carrito) {
        return carritoService.guardar(carrito);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        carritoService.eliminar(id);
    }
}
