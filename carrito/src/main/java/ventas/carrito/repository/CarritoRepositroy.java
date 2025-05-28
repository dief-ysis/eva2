package com.ejemplo.carrito.repository;

import com.ejemplo.carrito.model.Carrito;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarritoRepository extends JpaRepository<Carrito, Long> {
}

